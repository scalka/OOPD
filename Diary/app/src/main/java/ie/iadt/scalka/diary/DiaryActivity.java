package ie.iadt.scalka.diary;

import android.support.v4.app.Fragment;

//
public class DiaryActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DiaryFragment();
    }
}
