package bgroup.configuration;

import org.springframework.http.HttpMethod;

/**
 * Created by VSB on 12.12.2017.
 * cleverClients
 */

public class EnvVariable {
    private static String apiUrl;
    private static String apiAuth;
    private static String apiSurname;
    private static String apiUserName;
    private static String apiPassword;
    private static String xmlApiKey;
    private static String xmlApiUrl;
    private static HttpMethod xmlApiMethod;

    public static HttpMethod getXmlApiMethod() {
        return xmlApiMethod;
    }

    public static void setXmlApiMethod(String method) {
        if (method.equals("GET"))
            EnvVariable.xmlApiMethod = HttpMethod.GET;
        else EnvVariable.xmlApiMethod = HttpMethod.POST;
    }

    public static String getXmlApiUrl() {
        return xmlApiUrl;
    }

    public static void setXmlApiUrl(String xmlApiUrl) {
        EnvVariable.xmlApiUrl = xmlApiUrl;
    }

    public static String getXmlApiKey() {
        return xmlApiKey;
    }

    public static void setXmlApiKey(String xmlApiKey) {
        EnvVariable.xmlApiKey = xmlApiKey;
    }

    public static String getApiUrl() {
        return apiUrl;
    }

    public static void setApiUrl(String apiUrl) {
        EnvVariable.apiUrl = apiUrl;
    }

    public static String getApiAuth() {
        return apiAuth;
    }

    public static void setApiAuth(String apiAuth) {
        EnvVariable.apiAuth = apiAuth;
    }

    public static String getApiSurname() {
        return apiSurname;
    }

    public static void setApiSurname(String apiSurname) {
        EnvVariable.apiSurname = apiSurname;
    }

    public static String getApiUserName() {
        return apiUserName;
    }

    public static void setApiUserName(String apiUserName) {
        EnvVariable.apiUserName = apiUserName;
    }

    public static String getApiPassword() {
        return apiPassword;
    }

    public static void setApiPassword(String apiPassword) {
        EnvVariable.apiPassword = apiPassword;
    }
}
