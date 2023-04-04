package avans.groep15.themoviedb.domain.datanase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import avans.groep15.themoviedb.domain.Movie;

@Database(entities = {Movie.class}, version = 2)

public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    private static MovieDatabase INSTANCE;

    public static MovieDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, "movie-database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}

