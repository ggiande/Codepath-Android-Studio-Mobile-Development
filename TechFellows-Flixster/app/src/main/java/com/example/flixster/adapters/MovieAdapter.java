package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.w3c.dom.Text;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;
// Constructor for context
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

//    Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);

    }

//    Involves populating data into the item through view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MoveieAdapter", "onBindViewHolder" + position);
//      Get the movie at the passed in position
        Movie movie = movies.get(position);

//      Bind the total count of items in the list
        holder.bind(movie);

    }
//    Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }



    // Define Inner View Holder Class
    public class ViewHolder extends RecyclerView.ViewHolder {

    // Define member variables for each view in the view holder
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());



            String imageUrl;
            // if phone is in landscape mode
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            // set imageUrl = backdrop image
                imageUrl = movie.getBackdropPath();
            } else {
            //else image Url  = poster image
                imageUrl = movie.getPosterPath();
            }

//            Glide
            Glide.with(context).load(imageUrl).into(ivPoster);
        }
    }
}
