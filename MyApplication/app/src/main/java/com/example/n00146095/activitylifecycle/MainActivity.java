/*
* start - onCreate, onStart, onResume
* stop and restart -
* press back button - onPause, onStop, onDestroy
* press home btn - onStop, onPause, onResume
* */

package com.example.n00146095.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LifeCycleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(TAG, "onStart");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "onPause");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG, "onStop");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

}
