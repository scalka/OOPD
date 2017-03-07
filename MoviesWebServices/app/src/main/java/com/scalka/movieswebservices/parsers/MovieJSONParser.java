package com.scalka.movieswebservices.parsers;

import com.scalka.movieswebservices.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieJSONParser {

    public static ArrayList<Movie> parseFeed(String content){

        JSONArray array = null;
        try {
            array = new JSONArray(content);
            List<Movie> movieList = new ArrayList<>();

            for (int i=0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                Movie movie = new Movie();

                movie.setTitle(object.getString("Titile"));
                movie.setDirectors(object.getString("Directors"));
                movie.setRating(object.getDouble("Rating"));
                movie.setYear(object.getInt("Year"));
                movie.setGenres(object.getString("Genres"));
                movie.setPhoto(object.getString("Photo"));

                movieList.add(movie);
            }

            return (ArrayList<Movie>) movieList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
