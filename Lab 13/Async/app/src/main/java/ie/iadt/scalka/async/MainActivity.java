package ie.iadt.scalka.async;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView output;
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    HttpManager httpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        output = (TextView) findViewById(R.id.output);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mProgress.setVisibility(View.INVISIBLE);

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
            requestData("http://services.hanselandpetal.com/feeds/flowers.xml");
        }
        return false;
    }

    protected void updateDisplay(String msg) {
        output.append(msg + "\n");
    }

    private void requestData(String uri){
        //updateDisplay("request data");
        MyTask task = new MyTask();
        task.execute(uri);
    }

    // <params, progress, result>
    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            updateDisplay("Starting task");
            mProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String data = httpManager.getData(strings[0]);
            updateDisplay("data: " + data);
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            updateDisplay("result: " + result);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                output.append(value + "\n");
            }
            try {
                Thread.sleep(1250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mProgress.setVisibility(View.INVISIBLE);

        }


    }


}