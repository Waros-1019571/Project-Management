package avans.groep15.themoviedb.domain;


import java.util.List;

public class PersonalList {
    private String title;
    private String description;
    private List<String> movies;

    public PersonalList(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getMovies() {
        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    public int getItems(){
        return movies.size();
    }
}
