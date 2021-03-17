package com.example.flixster;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;
import okhttp3.Headers;

// Import for API Key

public class DetailActivity extends AppCompatActivity {
    /* We make a request ot the new endpoint, and parse each object, and parse for the key. We only initialize youtube until after we get the key from the Movie DB*/
    private static final String YOUTUBE_API_KEY = BuildConfig.CONSUMER_KEY;

//  Movie DB API Commented out, in file.
    final private static String VIDEOS_URL = BuildConfig.CONSUMER_SECRET;
    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;
//   YouTubePlayerView youTubePlayerView; We do not use the class of YoutubePlayerView, we use the fragment
//   Using fragment instead of extending class


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        ratingBar = findViewById(R.id.ratingBar);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        ratingBar.setRating((float) movie.getRating());
        Log.d("DetailActivity", "ratingBar: " + ratingBar);


        // Network Requests are Asynchronous -> Response Handler
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEOS_URL, movie.getMovieID()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if (results.length() == 0){
                        return;
                    }
                    String YoutubeKey = results.getJSONObject(0).getString("key");
                    Log.d("DetailActivity", YoutubeKey);
                    initializeYoutube(YoutubeKey);
                } catch (JSONException e) {

                    Log.e("DetailActivity", "Failed to parse JSON", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });
    }
/* Create the error handling for when the platform that is hosting the video is not Youtube, we catch it before parsing for the Key. If no results, we need a place holder image; begin with a toast.*/
    private void initializeYoutube(final String youtubeKey) {

        YouTubePlayerFragment youtubeFragment = (YouTubePlayerFragment)
                getFragmentManager().findFragmentById(R.id.youtubeFragment);
        youtubeFragment.initialize(YOUTUBE_API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo(youtubeKey);
                        Log.d("DetailActivity", "onInitializationSuccess");
                        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
                        if(movie.getRating() >= 7.0){
                            youTubePlayer.loadVideo(youtubeKey);
                        } else {
                            youTubePlayer.cueVideo(youtubeKey);
                        }
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                        Log.d("DetailActivity", "onInitializationFailure");
                    }
                });
    }
}