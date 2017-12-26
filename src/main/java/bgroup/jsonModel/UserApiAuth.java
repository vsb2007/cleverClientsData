package bgroup.jsonModel;

public class UserApiAuth {

    private String username;
    private String password;


    public UserApiAuth(String user, String password) {
        this.username = user;
        this.password = password;

    }

    public UserApiAuth() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserApiAuth{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String toJsonString() {
        return "{ \"username\": \"" + username + "\",  \"password\": \"" + password + "\"}";
    }
}
