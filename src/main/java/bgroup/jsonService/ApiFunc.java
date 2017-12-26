package bgroup.jsonService;

import bgroup.configuration.EnvVariable;
import bgroup.jsonModel.CustomHttpSessionStatus;
import bgroup.jsonModel.UserApiAuth;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * Created by VSB on 06.12.2017.
 * cleverClients
 */

public class ApiFunc {

    static final Logger logger = LoggerFactory.getLogger(ApiFunc.class);

    private static final MediaType JSON = MediaType.APPLICATION_JSON;
    private static CustomHttpSessionStatus customHttpSessionStatus;
    private static String sessionId;
    private static final String apiUrl = EnvVariable.getApiUrl();
    private static final String apiAuth = EnvVariable.getApiAuth();
    private static HttpComponentsClientHttpRequestFactory requestFactory;

    public static ResponseEntity<String> getResponse(String urlRequest, String requestString) {
        if (!isAuth()) {
            customHttpSessionStatus = getCustomHttpSessionStatus(apiUrl + apiAuth);
        }
        if (!isAuth()) return null;
        String url = apiUrl + urlRequest;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(JSON);
        headers.add(customHttpSessionStatus.getToken().getHeaderName(), customHttpSessionStatus.getToken().getToken());
        headers.set("Cookie", sessionId);
        logger.info(customHttpSessionStatus.toString());
        logger.info(headers.toString());

        HttpEntity<String> entity = new HttpEntity<String>(requestString, headers);

        if (requestFactory == null) {
            requestFactory = getRequestFactory();
        }
        ResponseEntity<String> response = null;
        try {
            response = new RestTemplate(requestFactory).exchange(url, HttpMethod.POST, entity, String.class);
        } catch (Exception e) {

        }

        /**
         * Если код возврата не 2хх то пробуем обновить авторизацию и повторить запрос, иначе null
         */
        if (response == null || !response.getStatusCode().is2xxSuccessful()) {
            customHttpSessionStatus = null;
            customHttpSessionStatus = getCustomHttpSessionStatus(apiUrl + apiAuth);
            if (customHttpSessionStatus == null) return null;

            headers = new HttpHeaders();
            headers.setContentType(JSON);
            headers.add(customHttpSessionStatus.getToken().getHeaderName(), customHttpSessionStatus.getToken().getToken());
            headers.set("Cookie", sessionId);

            response = new RestTemplate(requestFactory).exchange(url, HttpMethod.POST, entity, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) return null;
        }
        /*
        System.out.println(response.getStatusCode());
        System.out.println(response.toString());
        System.out.println(response.getBody());
        */
        return response;
    }

    private static CustomHttpSessionStatus getCustomHttpSessionStatus(String url) {
        String userName = EnvVariable.getApiUserName();
        String password = EnvVariable.getApiPassword();

        //if (customHttpSessionStatus == null) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(JSON);
        UserApiAuth userApiAuth = new UserApiAuth(userName, password);
        logger.info(userApiAuth.toString());
        HttpEntity<String> entity = new HttpEntity<String>(userApiAuth.toJsonString(), headers);
        SSLContext sslContext = null;
        sslContext = getSslContext(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        ResponseEntity<String> response = null;
        try {
            response = new RestTemplate(requestFactory).exchange(url, HttpMethod.POST, entity, String.class);
        } catch (Exception e) {
            return null;
        }

        /**
         * get session id
         */
        List<String> cookies = response.getHeaders().get("Cookie");
        if (cookies == null) {
            cookies = response.getHeaders().get("Set-Cookie");
        }
        String cookie = cookies.get(cookies.size() - 1);
        int start = cookie.indexOf('=');
        int end = cookie.indexOf(';');
        sessionId = cookie.substring(start + 1, end);

        sessionId = response.getHeaders().get("Set-Cookie").get(0);
        //response.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
        logger.info("headers");
        List<String> cooks = response.getHeaders().get("Set-Cookie");
        for (String str : cooks) {
            logger.info(str);
        }
        /*
        System.out.println(response.getStatusCode());
        System.out.println(response.toString());
        System.out.println(response.getBody());
        */
        if (!response.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();

        try {
            customHttpSessionStatus = mapper.readValue(response.getBody(), CustomHttpSessionStatus.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // }
        return customHttpSessionStatus;
    }

    private static HttpComponentsClientHttpRequestFactory getRequestFactory() {
        SSLContext sslContext = null;
        sslContext = getSslContext(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        return requestFactory;
    }

    private static SSLContext getSslContext(SSLContext sslContext) {
        try {
            sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, (certificate, authType) -> true).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return sslContext;
    }

    public static boolean isAuth() {
        if (customHttpSessionStatus == null || customHttpSessionStatus.getToken() == null
                || customHttpSessionStatus.getToken().getHeaderName() == null
                || customHttpSessionStatus.getToken().getToken() == null)
            return false;
        return true;
    }
}
