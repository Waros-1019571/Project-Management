package avans.groep15.themoviedb.datastorage.database;

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


    @Query("DELETE FROM movie")
    void deleteMovies();

    @Insert
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);
}
