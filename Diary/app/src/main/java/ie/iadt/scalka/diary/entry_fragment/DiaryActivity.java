package ie.iadt.scalka.diary.entry_fragment;

import android.support.v4.app.Fragment;

import ie.iadt.scalka.diary.SingleFragmentActivity;
import ie.iadt.scalka.diary.entry_fragment.DiaryFragment;

//
public class DiaryActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DiaryFragment();
    }
}
