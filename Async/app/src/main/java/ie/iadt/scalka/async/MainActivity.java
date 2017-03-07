package ie.iadt.scalka.async;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ie.iadt.scalka.async.model.Movie;
import ie.iadt.scalka.async.parsers.MovieXMLParser;

public class MainActivity extends ListActivity {

    private TextView output;
    private ProgressBar mProgress;
    private static final String PHOTOS_BASE_URL = "http://10.0.2.2/scalka/photos/";

    List<MyTask> tasks;
    HttpManager httpManager;
    List<Movie> movieList;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mProgress.setVisibility(View.INVISIBLE);

        btn = (Button)findViewById(R.id.button);


        // This array list of tasks allows for control over parallel tasks
        // you know when there are tasks still left to do
        // the application will add and remove AsyncTasks from this list
        tasks = new ArrayList<>();
        // C:\xampp\htdocs\scalka
        requestData("http://10.0.2.2/scalka/movies.xml");

        httpManager = new HttpManager();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {

        }
        return false;
    }

    protected void updateDisplay() {
        //output.append(msg + "\n");
        MovieAdapter adapter = new MovieAdapter(this, R.layout.item_movie, movieList);
        // Bind to our new adapter.
        setListAdapter(adapter);
    }

    private void requestData(String uri){
       // updateDisplay("request data");
        MyTask task = new MyTask();
        task.execute(uri);

    }

    // <params, progress, result>
    private class MyTask extends AsyncTask<String, String, List<Movie>> {

        @Override
        protected void onPreExecute() {
           // updateDisplay("Starting task");
            if (tasks.size() == 0) {
                mProgress.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected List<Movie> doInBackground(String... params) {
            //ask Http manager to get the data(xml) from this uri
            //getData returns a string of XML
            String content = HttpManager.getData(params[0]);
            //pass received content from manager to parser
            movieList = MovieXMLParser.parseFeed(content);
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

        }
    }
}