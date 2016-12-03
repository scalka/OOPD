package ie.iadt.scalka.diary.list_fragment;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ListView;
import android.view.View;
import java.util.ArrayList;

import ie.iadt.scalka.diary.R;
import ie.iadt.scalka.diary.model.DiaryEntry;
import ie.iadt.scalka.diary.model.DiaryModel;


public class DiaryListFragment extends ListFragment {

    private static final String TAG = "DiaryListFragment";
    private ArrayList<DiaryEntry> mDiaryEntries;

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

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        DiaryEntry de = (DiaryEntry)(getListAdapter()).getItem(position);
        Log.d(TAG, de.getTitle() + " was clicked");
    }
}
