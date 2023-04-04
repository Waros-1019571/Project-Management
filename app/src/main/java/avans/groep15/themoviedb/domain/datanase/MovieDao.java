package avans.groep15.themoviedb.domain.datanase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import avans.groep15.themoviedb.domain.Movie;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie")
    List<Movie> getAllMovies();

    @Insert
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);
}
