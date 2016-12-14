package ie.iadt.scalka.diary.entry_fragment;

import android.support.v4.app.Fragment;
import ie.iadt.scalka.diary.SingleFragmentActivity;

/*DiaryActivity class which is creating the DiaryFragment*/
public class DiaryActivity extends SingleFragmentActivity {
    //createFragment() create the appropriate Fragment
    @Override
    protected Fragment createFragment() {
        return new DiaryFragment();
    }
}
