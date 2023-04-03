package avans.groep15.themoviedb.domain;

public class LoginData {
    private final String username;
    private final String password;
    private final String request_token;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRequest_token() {
        return request_token;
    }

    public LoginData(String username, String password, String request_token) {
        this.username = username;
        this.password = password;
        this.request_token = request_token;
    }
}
