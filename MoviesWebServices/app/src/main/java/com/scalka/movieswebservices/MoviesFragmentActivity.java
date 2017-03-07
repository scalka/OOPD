package com.scalka.movieswebservices;

import android.support.v4.app.Fragment;

public class MoviesFragmentActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MoviesFragment();
    }
}
