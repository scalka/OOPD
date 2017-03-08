package com.example.aw.permissions;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aw.permissions.model.Flower;
import com.example.aw.permissions.parsers.FlowerXMLParser;

public class MainActivity extends ListActivity {

    TextView output;
    ProgressBar pb;
    List<MyTask> tasks;

    // We will now retrieve a list of Flowers rather than raw XML
    List<Flower> flowerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
//        setSupportActionBar(toolbar);



        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        // This array list of tasks allows for control over parallel tasks
        // you know when there are tasks still left to do
        // the application will add and remove AsyncTasks from this list
        taskrequestData("http://10.0.2.2/scalka/movies.xml");s = new ArrayList<>();

        requestData("http://10.0.2.2/hanselandpetal/flowers.xml");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void getData(View v){
        requestData("http://10.0.2.2/hanselandpetal/flowers.xml");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_get_data) {
       //     if (isOnline()) {
             //
                //http://www.feedforall.com/sample.xml

                requestData("http://10.0.2.2/hanselandpetal/flowers.xml");
              //  requestData("http://services.hanselandpetal.com/feeds/flowers.xml");
         //   } else {
            //    Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        //    }
        }
        return false;
    }



    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    // udpateDisplay now does not accept a String parameter
    // it has access to the flowerList that should be populated with the Flowers from the XML parser
    protected void updateDisplay() {

        // Loop through the flowerList displaying the name of each flower.
//        if (flowerList != null){
//            for (Flower flower : flowerList
//                 ) {
//                output.append(flower.getName() +"\n");
//            }
//        }

        FlowerAdapter adapter = new FlowerAdapter(this, R.layout.item_flower, flowerList);
        setListAdapter(adapter);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    // inner class that is instantiated and executed for background tasks
    // can only be executed once, therefore need to create a new object for
    // each background task. See RequestData() above that instantiates this class.
    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
        //    updateDisplay("Starting task");

            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... params) {

            // ask the HttpManager to go to this uri and get the data
            // getData should return a string of XML (the URI above request xml)
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            // pass result that was received from doInBackground() into the Parser
            flowerList = FlowerXMLParser.parseFeed(result);

            // Update Display has now been changed to reveive no parameters, it has access to the flowerList so will display this.
            updateDisplay();



            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        protected void onProgressUpdate(String... values) {
            //updateDisplay(values[0]);
        }

    }


}
