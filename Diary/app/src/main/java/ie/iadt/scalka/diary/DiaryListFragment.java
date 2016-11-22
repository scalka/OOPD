package ie.iadt.scalka.diary;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DiaryListFragment extends ListFragment {

    private static final String TAG = "DiaryListFragment";
    private ArrayList<DiaryEntry> mDiaryEntries;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //returns hosting activity - DiaryActivity
        getActivity().setTitle(R.string.app_name);
        //returns list of entries
        mDiaryEntries = DiaryModel.get(getActivity()).getmDiaryEntry();
        //adapter is responsible for creating the view object, populating it with data from the model and returning the view object to listview
        //simple_list_item is predefined layout from android, has a textview as its root elements
        /*ArrayAdapter<DiaryEntry>adapter =
                new ArrayAdapter<DiaryEntry>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        mDiaryEntries);
        setListAdapter(adapter);*/
        // instead of arrayadapter i use diaryentryadapter
        //it knows ho to get content from diaryentry objects and place it in a list item
        DiaryEntryAdapter adapter = new DiaryEntryAdapter(mDiaryEntries);
        setListAdapter(adapter);
    }


}
