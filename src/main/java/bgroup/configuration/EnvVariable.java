package bgroup.configuration;

import org.springframework.stereotype.Service;

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
