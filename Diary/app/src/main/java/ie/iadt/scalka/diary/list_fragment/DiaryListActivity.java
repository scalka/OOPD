package ie.iadt.scalka.diary.list_fragment;

import android.support.v4.app.Fragment;
import ie.iadt.scalka.diary.SingleFragmentActivity;

/*DiaryListActivity class which is creating the DiaryListFragment*/
public class DiaryListActivity extends SingleFragmentActivity {
    //createFragment() create the appropriate Fragment
    @Override
    protected Fragment createFragment(){
        return new DiaryListFragment();
    }
}
