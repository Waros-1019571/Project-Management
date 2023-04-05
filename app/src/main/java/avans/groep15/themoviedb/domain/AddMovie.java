package avans.groep15.themoviedb.domain;

public class AddMovie {
    private final int media_id;
    private final int list_id;

    public AddMovie(int media_id, int list_id) {
        this.media_id = media_id;
        this.list_id = list_id;
    }

    public int getMedia_id() {
        return media_id;
    }

    public int getList_id() {
        return list_id;
    }
}
