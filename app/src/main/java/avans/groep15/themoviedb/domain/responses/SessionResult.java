package avans.groep15.themoviedb.domain.responses;

public class SessionResult {
    private final String session_id;

    public SessionResult(String session_id) {
        this.session_id = session_id;
    }

    public String getSession_id() {
        return session_id;
    }
}
