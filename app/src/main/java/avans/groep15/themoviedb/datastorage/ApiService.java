package avans.groep15.themoviedb.datastorage;

import avans.groep15.themoviedb.domain.LoginData;
import avans.groep15.themoviedb.domain.responses.LoginResult;
import avans.groep15.themoviedb.domain.responses.MovieResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<MovieResult> getMovies(@Query("api_key")String apiKey);

    @GET("authentication/token/new")
    Call<LoginResult> getToken(@Query("api_key")String apiKey);

    @POST("authentication/token/validate_with_login")
    Call<LoginResult> login(@Query("api_key")String apiKey, @Body() LoginData loginData);



}
