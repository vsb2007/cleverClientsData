package bgroup.jsonService;

import bgroup.configuration.EnvVariable;
import bgroup.jsonModel.CardNumberRequest;
import bgroup.jsonModel.SurnameApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * Created by VSB on 06.12.2017.
 * cleverClients
 */

public class CcardApi {
    static final Logger logger = LoggerFactory.getLogger(CcardApi.class);

    public boolean isSurnameByCardNumberIsEmpty(String cardNumber) {
        //ApiFunc apiFunc = new ApiFunc();
        String requestString = new CardNumberRequest("card_number", cardNumber).toJsonString();
        String url = EnvVariable.getApiSurname();
        logger.info("Url:{}", url);
        logger.info("request string:" + requestString);
        ResponseEntity<String> response = ApiFunc.getResponse(url, requestString);
        ObjectMapper mapper = new ObjectMapper();
        SurnameApi surnameApi = null;
        try {
            surnameApi = mapper.readValue(response.getBody(), SurnameApi.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (surnameApi==null || surnameApi.equals("")) return true;
        else return false;
    }
}
