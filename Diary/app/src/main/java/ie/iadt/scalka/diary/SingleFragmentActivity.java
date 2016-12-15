package ie.iadt.scalka.diary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * This class is an abstract it should never be instantiated
 * The code can be inherited in a subclass.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity{
    //createFragment() will be implemented to CrimeActivity code to create the appropriate Fragment
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        // fragment manager handles the list of fragments for the activity and adding their views to the list of views for the activity
        // getSupportFragmentManager() as Support Library is used
        FragmentManager manager = getSupportFragmentManager();
        //this container view ID tells the manager where in the activity's view the fragment should appear
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null){
            fragment = createFragment();
            manager.beginTransaction()
                    //add() creates and commits a fragment transaction
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
