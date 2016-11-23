package ie.iadt.scalka.diary;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
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
/*       ArrayAdapter<DiaryEntry>adapter =
                new ArrayAdapter<DiaryEntry>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        mDiaryEntries);
        setListAdapter(adapter);*/
       DiaryEntryAdapter adapter = new DiaryEntryAdapter(getActivity(), mDiaryEntries);
        setListAdapter(adapter);

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        DiaryEntry de = (DiaryEntry)(getListAdapter()).getItem(position);
        Log.d(TAG, de.getTitle() + " was clicked");
    }
}
