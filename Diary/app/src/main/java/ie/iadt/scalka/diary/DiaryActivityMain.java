package ie.iadt.scalka.diary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
//
public class DiaryActivityMain extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DiaryFragment();
    }
}
