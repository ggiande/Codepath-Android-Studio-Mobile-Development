package com.example.flixster.adapters;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.Target;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;
import org.parceler.Parcels;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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
        Log.d("MovieAdapter", "onBindViewHolder" + position);
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
        RelativeLayout container;
    // Define member variables for each view in the view holder
        TextView tvTitle;
        TextView tvOverview;
        TextView tvTitlePopular;
        TextView tvOverviewPopular;
        ImageView ivPoster;
        ImageView playButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            tvTitlePopular = itemView.findViewById(R.id.tvTitlePopular);
            tvOverviewPopular = itemView.findViewById(R.id.tvOverviewPopular);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
            playButton = itemView.findViewById(R.id.playButton);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            tvTitlePopular.setText(movie.getTitle());
            tvOverviewPopular.setText(movie.getOverview());

            // if phone is in landscape mode
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                if(movie.getRating() >= 7.0){
                    Glide.with(context).load(movie.getBackdropPath()).placeholder(R.drawable.ic_red_error).transform(new RoundedCorners(15)).override(575).into(ivPoster);
                // SET VISIBILITY OF POPULAR TVs AND
                    tvTitle.setVisibility(View.GONE);
                    tvOverview.setVisibility(View.GONE);

                    tvTitlePopular.setVisibility(View.VISIBLE);
                    tvOverviewPopular.setVisibility(View.VISIBLE);
                } else {
                    Glide.with(context).load(movie.getPosterPath()).placeholder(R.drawable.ic_red_error).transform( new RoundedCornersTransformation(15, 0)).override(250, 400).into(ivPoster);
                    tvTitle.setVisibility(View.VISIBLE);
                    tvOverview.setVisibility(View.VISIBLE);

                    tvTitlePopular.setVisibility(View.GONE);
                    tvOverviewPopular.setVisibility(View.GONE);
                }
            } else {
                //else image Url  = backdrop image
                Glide.with(context).load(movie.getBackdropPath()).placeholder(R.drawable.ic_red_error).transform(new FitCenter(), new RoundedCorners(15)).into(ivPoster);

                tvTitlePopular.setVisibility(View.GONE);
                tvOverviewPopular.setVisibility(View.GONE);
            }
//          1. Register OnClickListener on the whole row (Container)

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//            2. Navigate to the new activity on tap, being by creating a new intent
                    Intent i = new Intent(context, DetailActivity.class);
//                    Intent below to add the pass the object movie fails because movie is custom, so we us4e the parceler library to wrap the object
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });

            if (movie.getRating() >= 7.0){
                playButton.setVisibility(View.VISIBLE);
            } else {
                playButton.setVisibility(View.INVISIBLE);
            }

            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "POPCORN", Toast.LENGTH_SHORT).show();
                    //2. Navigate to a new activity on tap
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
        }
    }
}
