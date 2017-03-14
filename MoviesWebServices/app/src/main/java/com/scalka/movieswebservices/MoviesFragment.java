package com.scalka.movieswebservices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scalka.movieswebservices.model.Movie;
import com.scalka.movieswebservices.parsers.MovieJSONParser;
import com.scalka.movieswebservices.parsers.MovieXMLParser;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment{

    private RecyclerView mMovieRecyclerView;
    private MovieAdapter mAdapter;
    List<MyTask> tasks;

    // C:\xampp\htdocs\scalka
    //requestData("http://10.0.2.2/scalka/movies.xml");
    private static final String PHOTOS_BASE_URL = "http://10.0.2.2/scalka/photos/";
    public static final String XML_URL = "http://10.0.2.2/scalka/movies.xml";
    public static final String JSON_URL = "http://10.0.2.2/scalka/movies.json";
    HttpManager httpManager;
    List<Movie> movieList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        setHasOptionsMenu(true); // telling that the function onCreteOptionsMenu should be called
        List<Movie> movieList = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_movies, container, false);
        mAdapter = new MovieAdapter(movieList);
        mMovieRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_movies_recycler_view);
        mMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMovieRecyclerView.setAdapter(mAdapter);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //create menu on top with buttons
        inflater.inflate(R.menu.fragment_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //starting a task when click on button
        switch (item.getItemId()){
            //pull xml file
            case R.id.xml_pull:
                tasks = new ArrayList<>();
                requestData(XML_URL);
                httpManager = new HttpManager();
                return true;
            //pull json file
            case R.id.json_pull:
                tasks = new ArrayList<>();
                requestData(JSON_URL);
                httpManager = new HttpManager();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //updating a display in a list
    private void updateDisplay(List<Movie> movieList) {
        mAdapter = new MovieAdapter(movieList);
        mMovieRecyclerView.setAdapter(mAdapter);
    }
    //starting a new task - requesting the data from web service
    private void requestData(String uri){
        MyTask task = new MyTask();
        task.execute(uri);
    }

    //view for a single item in a list
    private class MoviesHolder extends RecyclerView.ViewHolder{

        public TextView mTitileTextView;
        public ImageView mImageView;
        public TextView mSecondText;
        private Movie mMovie;

        public MoviesHolder(View itemView) {
            super(itemView);
            mTitileTextView = (TextView)itemView.findViewById(R.id.movie_title);
            mImageView = (ImageView)itemView.findViewById(R.id.movie_image);
            mSecondText = (TextView)itemView.findViewById(R.id.mSecondText);
        }

        public void bindMovie(Movie movie){
            mMovie = movie;
            mTitileTextView.setText(mMovie.getTitle());
            mImageView.setImageBitmap(mMovie.getBitmap());
        }
    }
    //adapter
    private class MovieAdapter extends RecyclerView.Adapter<MoviesHolder>{
        public MovieAdapter(List<Movie> movies){
            movieList = movies;
        }
        @Override
        public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_movie, parent, false);
            return new MoviesHolder(view);
        }
        @Override
        public void onBindViewHolder(MoviesHolder holder, int position) {
            Movie movie = movieList.get(position);
            holder.bindMovie(movie);
        }
        @Override
        public int getItemCount() {
            return movieList.size();
        }
    }

    // extends AsyncTask <params, progress, result>
    private class MyTask extends AsyncTask<String, String, List<Movie>> {
        //before background task
        @Override
        protected void onPreExecute() {
            tasks.add(this);
        }
        //background task
        @Override
        protected List<Movie> doInBackground(String... params) {
            Log.d("sting", String.valueOf(params[0]));
            //ask Http manager to get the data(xml) from this uri
            //getData returns a string of XML
            String content = HttpManager.getData(params[0]);
            //pass received content from manager to parser
            if (params[0] == XML_URL){
                movieList = MovieXMLParser.parseFeed(content);
            }
            if (params[0] == JSON_URL){
                movieList = MovieJSONParser.parseFeed(content);
            }
            //loop through movie list and make a network request for image
            //store the image in Bitmap var in each movie object
            for (Movie movie : movieList){
                try {
                    String imageUrl = PHOTOS_BASE_URL + movie.getPhoto();
                    InputStream in = (InputStream) new URL(imageUrl).getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    movie.setBitmap(bitmap);
                    in.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return movieList;
        }
        //after background task
        @Override
        protected void onPostExecute(List<Movie> movieList) {
            super.onPostExecute(movieList);
            //updating display
            updateDisplay(movieList);
        }
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}
