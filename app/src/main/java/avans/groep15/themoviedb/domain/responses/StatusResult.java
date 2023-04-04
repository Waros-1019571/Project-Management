package avans.groep15.themoviedb.domain.responses;

public class StatusResult {
    private final boolean success;

    public StatusResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
