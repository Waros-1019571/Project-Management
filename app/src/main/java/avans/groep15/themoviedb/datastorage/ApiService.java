package avans.groep15.themoviedb.datastorage;

import avans.groep15.themoviedb.domain.responses.MovieResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<MovieResult> getMovies(@Query("api_key")String apiKey);
}
