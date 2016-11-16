package ie.iadt.scalka.diary;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;



public class DiaryListFragment extends ListFragment {

    private static final String TAG = "CrimesListFragment";
    private ArrayList<DiaryEntry> mDiaryEntries;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //returns hosting activity
        getActivity().setTitle(R.string.app_name);
        //returns list of entries
        mDiaryEntries = DiaryModel.get(getActivity()).getmDiaryEntry();
        //adapter is responsible for creating the view object, populating it with data from the model and returning the view object to listview
        //simple_list_item is predefined layout from android, has a textview as its root elements
        ArrayAdapter<DiaryEntry>adapter =
                new ArrayAdapter<DiaryEntry>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        mDiaryEntries);
        setListAdapter(adapter);
    }

    //this method will display complete entries that was chosen in the list
/*
    @Override
    public void onListItemClick(ListView list, View v, int position, long id){
        DiaryEntry de = (DiaryEntry)(getListAdapter()).getItem(position);
        Log.d(TAG, de.getTitle() + " was clicked");
    }*/
}
