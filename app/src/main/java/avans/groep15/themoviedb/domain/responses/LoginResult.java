package avans.groep15.themoviedb.domain.responses;

public class LoginResult {
    private final String request_token;

    public LoginResult(String request_token) {
        this.request_token = request_token;
    }

    public String getRequest_token() {
        return request_token;
    }
}
