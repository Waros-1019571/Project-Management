package avans.groep15.themoviedb.domain;

public class Account {
    private final int id;
    private final String username;
    private final boolean include_adult;

    public Account(int id, String username, boolean include_adult) {
        this.id = id;
        this.username = username;
        this.include_adult = include_adult;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isInclude_adult() {
        return include_adult;
    }
}
