package ie.iadt.scalka.diary.list_fragment;

import android.support.v4.app.Fragment;

import ie.iadt.scalka.diary.SingleFragmentActivity;

public class DiaryListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new DiaryListFragment();
    }
}
