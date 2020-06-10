package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String posterPath;
    String title;
    String overview;

    String backdropPath;



    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath =jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

//    Populates the movies onto a list (moviejsonArray)
    public static List<Movie> fromJsonArray(JSONArray moviejsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < moviejsonArray.length(); i++){
            movies.add(new Movie(moviejsonArray.getJSONObject(i)));
        }
        return movies;

    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

//  Poster Path
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath); // Hard coding size of 342
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
