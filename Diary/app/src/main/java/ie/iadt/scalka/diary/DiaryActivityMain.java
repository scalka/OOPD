package ie.iadt.scalka.diary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
// TODO finished on slide 3 Lab8 - xml for fragment
public class DiaryActivityMain extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_main);
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
