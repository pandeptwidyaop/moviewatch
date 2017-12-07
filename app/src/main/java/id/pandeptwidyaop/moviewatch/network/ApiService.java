package id.pandeptwidyaop.moviewatch.network;

import id.pandeptwidyaop.moviewatch.model.MovieResult;
import id.pandeptwidyaop.moviewatch.model.ResultsItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by John Doe on 12/7/2017.
 */

public interface ApiService {
    @GET("movie/upcoming")
    Call<MovieResult> movieList (
        @Query("api_key") String api_key
    );
}
