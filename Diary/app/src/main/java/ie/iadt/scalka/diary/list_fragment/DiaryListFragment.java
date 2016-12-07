package ie.iadt.scalka.diary.list_fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ListView;
import android.view.View;
import java.util.ArrayList;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;


import ie.iadt.scalka.diary.R;
import ie.iadt.scalka.diary.entry_fragment.DiaryActivity;
import ie.iadt.scalka.diary.entry_fragment.DiaryFragment;
import ie.iadt.scalka.diary.model.DiaryEntry;
import ie.iadt.scalka.diary.model.DiaryModel;


public class DiaryListFragment extends ListFragment {

    private static final String TAG = "DiaryListFragment";
    private ArrayList<DiaryEntry> mDiaryEntries;
    private DiaryEntryAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //returns hosting activity - DiaryActivity
        getActivity().setTitle(R.string.app_name);
        //returns list of entries
        DiaryModel dm = DiaryModel.get(getActivity());
        dm.getmDiaryEntry();
        mDiaryEntries = DiaryModel.get(getActivity()).getmDiaryEntry();
        //adapter is responsible for creating the view object, populating it with data from the model and returning the view object to listview
        //simple_list_item is predefined layout from android, has a textview as its root elements
       DiaryEntryAdapter adapter = new DiaryEntryAdapter(getActivity(), mDiaryEntries);
       setListAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)(getActivity()).findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        DiaryEntry de = (DiaryEntry)(getListAdapter()).getItem(position);
        Log.d(TAG, de.getTitle() + " was clicked");

        Intent intent = new Intent(getActivity(), DiaryActivity.class);
        intent.putExtra(DiaryFragment.EXTRA_DIARY_ID, de.getId());
        startActivity(intent);
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        DiaryModel crimeModel = DiaryModel.get(getActivity());
        ArrayList<DiaryEntry> mDiaryEntries = crimeModel.getmDiaryEntry();

        adapter = new DiaryEntryAdapter(getActivity(), mDiaryEntries);
        setListAdapter(adapter);
    }
}
