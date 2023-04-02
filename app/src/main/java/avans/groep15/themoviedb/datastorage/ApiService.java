package avans.groep15.themoviedb.datastorage;

import avans.groep15.themoviedb.domain.responses.MovieResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<MovieResult> getMovies(@Query("api_key")String apiKey);

    //Based on the code you provided, the MovieResult object returned by the API call may not include the genre information.
    //You may need to modify your ApiService interface to include additional parameters in the API call that will return genre
    //information for each movie. For example, you could modify the getMovies method to include the append_to_response parameter
    //with a value of "genres" like this:
    //
    // ~~~ ~~~
    //@GET("movie/popular")
    //Call<MovieResult> getMovies(@Query("api_key")String apiKey, @Query("append_to_response")String appendToResponse);
    // ~~~ ~~~
    //
    //Then, when you make the API call, you would pass in "genres" as the appendToResponse parameter value:
    //
    // ~~~ ~~~
    //Call<MovieResult> call = apiService.getMovies(apiKey, "genres");
    // ~~~ ~~~
    //
    //This should return genre information for each movie in the MovieResult object.
}
