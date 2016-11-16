package ie.iadt.scalka.diary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * This class is abstract – you never want to instantiate it.
 * You can only inherit the code in a subclass.
 */
public abstract class SingleFragmentActivity extends FragmentActivity{
    //createFragment() will be implemented to CrimeActivity code to create the appropriate Fragment
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        // fragment menager handles the list of fragments for the activity and adding therir views to the list of views for the actvity
        // getSupportFragmentManagaer() as Support Library is used
        FragmentManager manager = getSupportFragmentManager();
        //this container view ID tells the manager where in the activity's view the fragment should appear
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        if (fragment == null){
            fragment = new DiaryFragment();
            manager.beginTransaction()
                    //add() creates and commits a fragment transaction
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

}
