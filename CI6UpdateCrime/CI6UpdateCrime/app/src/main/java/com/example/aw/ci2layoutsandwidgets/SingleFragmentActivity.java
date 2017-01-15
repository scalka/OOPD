package com.example.aw.ci2layoutsandwidgets;

/**
 * Created by user10 on 03/11/2016.
 */
// This is an abstract class, its code is nearly the same as the previous Activity code we use to create an Fragmen
    // As we always write the same code to create the Fragment we are putting it here in an abstract class
    // and then every activity that needs it can extend from this abstract class

    import android.os.Bundle;

    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentActivity;
    import android.support.v4.app.FragmentManager;

    public abstract class SingleFragmentActivity extends FragmentActivity {
        // createFragment() will be implmented in the CrimeActvity code to create the appropriate Fragment
        protected abstract Fragment createFragment();

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fragment);
            FragmentManager manager = getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

            if (fragment == null) {
                fragment = createFragment();
                manager.beginTransaction()
                        .add(R.id.fragmentContainer, fragment)
                        .commit();
            }
        }
    }

