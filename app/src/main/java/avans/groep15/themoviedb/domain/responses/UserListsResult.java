package avans.groep15.themoviedb.domain.responses;

import java.util.List;

import avans.groep15.themoviedb.domain.WatchList;

public class UserListsResult {
    private List<WatchList> results;

    public UserListsResult(List<WatchList> results) {
        this.results = results;
    }

    public List<WatchList> getResults() {
        return this.results;
    }
}
