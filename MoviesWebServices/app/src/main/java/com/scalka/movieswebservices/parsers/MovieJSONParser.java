package com.scalka.movieswebservices.parsers;

import android.util.Log;

import com.scalka.movieswebservices.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieJSONParser {

    static ArrayList<Movie> movieList = new ArrayList<>();

    public static ArrayList<Movie> parseFeed(String content){

        JSONArray array = null;

        try {
            array = new JSONArray(content);

            for (int i=0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                Movie movie = new Movie();

                movie.setTitle(object.getString("Title"));
                movie.setDirectors(object.getString("Directors"));
                movie.setRating(object.getDouble("IMDb"));
                movie.setYear(object.getInt("Year"));
                movie.setGenres(object.getString("Genres"));
                movie.setPhoto(object.getString("Photo"));

                movieList.add(movie);
            }
            Log.d("json", String.valueOf(movieList));
            return movieList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
