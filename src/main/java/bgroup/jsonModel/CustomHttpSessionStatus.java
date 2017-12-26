package bgroup.jsonModel;

/**
 * Created by VSB on 23.09.2017.
 * ccardApi
 */
public class CustomHttpSessionStatus {
    private Boolean auth;
    private String username;
    private String message;
    private MyCsrfToken token;


    public CustomHttpSessionStatus(Boolean auth, String username, MyCsrfToken token, String message) {
        this.auth = auth;
        this.username = username;
        this.message = message;
        this.token = token;
    }

    public CustomHttpSessionStatus() {
    }

    public MyCsrfToken getToken() {
        return token;
    }

    public void setToken(MyCsrfToken token) {
        this.token = token;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CustomHttpSessionStatus{" +
                "auth=" + auth +
                ", username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", token=" + token +
                '}';
    }
}
