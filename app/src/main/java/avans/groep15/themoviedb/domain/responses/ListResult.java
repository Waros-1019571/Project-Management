package avans.groep15.themoviedb.domain.responses;

public class ListResult {
    private final int list_id;

    public ListResult(int list_id) {
        this.list_id = list_id;
    }

    public int getList_id() {
        return list_id;
    }
}
