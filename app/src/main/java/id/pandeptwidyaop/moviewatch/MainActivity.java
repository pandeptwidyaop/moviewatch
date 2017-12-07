package id.pandeptwidyaop.moviewatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.pandeptwidyaop.moviewatch.adapter.MovieAdapter;
import id.pandeptwidyaop.moviewatch.model.MovieResult;
import id.pandeptwidyaop.moviewatch.model.ResultsItem;
import id.pandeptwidyaop.moviewatch.network.ApiClient;
import id.pandeptwidyaop.moviewatch.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String API_KEY  = BuildConfig.API_KEY;
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<ResultsItem> movieList;
    private MovieAdapter movieAdapter;
    @BindView(R.id.rv_movie) RecyclerView rv_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(this,movieList);
        loadMovies();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv_movie.setLayoutManager(layoutManager);
        rv_movie.setAdapter(movieAdapter);

    }

    private void loadMovies(){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<MovieResult> movieResultCall = apiService.movieList(API_KEY);

        movieResultCall.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                List<ResultsItem> resultsItemList = response.body().getResults();
                Log.d(TAG, "onResponse: API HAS BEEN CALLED");
                if (resultsItemList != null){
                    movieList = resultsItemList;
                    movieAdapter.setMovieList(movieList);
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Call failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ON FAILURE", "api call", t);
            }
        });
    }
}
