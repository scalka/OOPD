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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scalka.movieswebservices.model.Movie;
import com.scalka.movieswebservices.parsers.MovieXMLParser;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment{

    private RecyclerView mMovieRecyclerView;
    private MovieAdapter mAdapter;
    List<MyTask> tasks;

    private static final String PHOTOS_BASE_URL = "http://10.0.2.2/scalka/photos/";
    HttpManager httpManager;
    List<Movie> movieList;

    public MoviesFragment() {
    }

    public static MoviesFragment newInstance(){
        return new MoviesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        tasks = new ArrayList<>();
        // C:\xampp\htdocs\scalka
        //requestData("http://10.0.2.2/scalka/movies.xml");
        requestData("http://10.0.2.2/scalka/movies.xml");
        httpManager = new HttpManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_movies, container, false);

        mAdapter = new MovieAdapter(movieList);

        mMovieRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_movies_recycler_view);
        mMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMovieRecyclerView.setAdapter(mAdapter);

        return v;
    }

    private void updateDisplay() {
        Log.d("moviesfragment", String.valueOf(movieList));
        mAdapter = new MovieAdapter(movieList);
        mMovieRecyclerView.setAdapter(mAdapter);


    }

    private void requestData(String uri){

        MyTask task = new MyTask();
        task.execute(uri);

    }

    // <params, progress, result>
    private class MyTask extends AsyncTask<String, String, List<Movie>> {

        @Override
        protected void onPreExecute() {
            tasks.add(this);
        }

        @Override
        protected List<Movie> doInBackground(String... params) {
            //ask Http manager to get the data(xml) from this uri
            //getData returns a string of XML
            String content = HttpManager.getData(params[0]);
            //pass received content from manager to parser
            movieList = MovieXMLParser.parseFeed(content);
            Log.d("movie", String.valueOf(movieList));
            //loop through movie list and make a network request for image
            //store the image in Bitmap var in each movie object
            for (Movie movie : movieList){
                try {
                    String imageUrl = PHOTOS_BASE_URL + movie.getPhoto();
                    Log.d("MainLog", imageUrl);
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

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);

            updateDisplay();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }



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
            mSecondText.setText("heeelllo");
        }
    }

    private class MovieAdapter extends RecyclerView.Adapter<MoviesHolder>{
        private List<Movie> movieList;

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
           // return movieList.size();
            return 0;
        }
    }

}
