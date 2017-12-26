package bgroup.jsonModel;

/**
 * Created by VSB on 06.12.2017.
 * cleverClients
 */
public class SurnameApi {
    private String surname;
    private String status;
    private String error_message;

    public SurnameApi() {
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public String toString() {
        return "SurnameApi{" +
                "surname='" + surname + '\'' +
                ", status='" + status + '\'' +
                ", error_message='" + error_message + '\'' +
                '}';
    }
}
