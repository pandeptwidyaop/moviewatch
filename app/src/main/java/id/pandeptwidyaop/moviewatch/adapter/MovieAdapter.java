package id.pandeptwidyaop.moviewatch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.pandeptwidyaop.moviewatch.R;
import java.util.List;

import id.pandeptwidyaop.moviewatch.listerner.MovieClickListener;
import id.pandeptwidyaop.moviewatch.model.ResultsItem;

/**
 * Created by John Doe on 12/6/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final String TAG = "Adapter";
    private Context context;
    private List<ResultsItem> movieList;
    private MovieClickListener movieClickListener;

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_movie) ImageView iv_movie;

        public MovieViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public MovieAdapter(Context context, List<ResultsItem> list){
        this.context = context;
        this.movieList = list;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.movie_item,parent,false
                );
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        ResultsItem movieResult = movieList.get(position);

        //holder.tvUserName.setText(user.getName().getFullName());

        Glide.with(context)
                .load(movieResult.getPosterPath())
                .into(holder.iv_movie);
        

        holder.iv_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: WORK");
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public List<ResultsItem> getMovieList(){
        return this.movieList;
    }

    public void setMovieList(List<ResultsItem> movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public void setMovieClickListener(MovieClickListener movieClickListener){
        this.movieClickListener = movieClickListener;
    }

}
