package bgroup.jsonModel;

/**
 * Created by VSB on 06.12.2017.
 * cleverClients
 */
public class MyCsrfToken {
    private String headerName;
    private String token;
    private String parameterName;

    public MyCsrfToken() {
    }

    public MyCsrfToken(String headerName, String token, String parameterName) {
        this.headerName = headerName;
        this.token = token;
        this.parameterName = parameterName;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String toString() {
        return "MyCsrfToken{" +
                "headerName='" + headerName + '\'' +
                ", token='" + token + '\'' +
                ", parameterName='" + parameterName + '\'' +
                '}';
    }
}
