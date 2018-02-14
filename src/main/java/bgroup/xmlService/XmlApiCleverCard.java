package bgroup.xmlService;

import bgroup.configuration.EnvVariable;
import bgroup.jsonModel.SurnameApi;
import bgroup.model.CleverCard;
import bgroup.xmlModel.XmlFindRequest;
import bgroup.xmlModel.XmlSetInfoRequest;
import bgroup.xmlModel.XmlTransactionsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

/**
 * Created by VSB on 06.12.2017.
 * cleverClients
 */

public class XmlApiCleverCard {
    static final Logger logger = LoggerFactory.getLogger(XmlApiCleverCard.class);

    public boolean setCardProperties(CleverCard cleverCard) {
        String cardNumberString = ((Integer) cleverCard.getCardNumber()).toString();
        //String requestString = new XmlTransactionsRequest(cardNumberString, "select", "2017-11-01", "2017-12-01", EnvVariable.getXmlApiKey()).toRequestString();
        //String requestString = new XmlFindRequest(cardNumberString, "info", EnvVariable.getXmlApiKey()).toRequestString();
        //String cardNumber, String fName, String mName, String lName, String bDate, String phone, String type, String key
        String requestString = new XmlSetInfoRequest(
                cardNumberString
                , cleverCard.getI()
                , cleverCard.getO()
                , cleverCard.getF()
                , cleverCard.getDateBirth().toString()
                , cleverCard.getPhoneNumber()
                , "setinfo", EnvVariable.getXmlApiKey()
        ).toRequestString();
        //String url = EnvVariable.getXmlApiUrl();
        //logger.info("Url:{}", url);
        logger.info("request string:" + requestString);
        ResponseEntity<String> response = FuncXmlApiCleverCard.getResponse(null, requestString);

        ObjectMapper mapper = new ObjectMapper();
        SurnameApi surnameApi = null;
        /*
        try {
            surnameApi = mapper.readValue(response.getBody(), SurnameApi.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (surnameApi == null || surnameApi.equals("")) return true;

        else return false;
        */
        String xmlString = null;
        if (response != null) {
            //logger.info(response.getBody());
            xmlString = response.getBody();
        } else logger.info("ничего не ответили");


        if (xmlString != null) {
        }
        logger.info(xmlString);
        return true;
    }
}
