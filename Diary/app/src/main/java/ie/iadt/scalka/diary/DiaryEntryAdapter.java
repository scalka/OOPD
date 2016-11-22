package ie.iadt.scalka.diary;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DiaryEntryAdapter extends ArrayAdapter<DiaryEntry>{
    //constructor
    public DiaryEntryAdapter(ArrayList<DiaryEntry> diaryEntries) {
        super(getActivity(), 0, diaryEntries);
    }
    //ovverride the getView method
    //we do notexplicitly call this method, it is called when a ListView needs a view object to display
    //it has a conversation with its adapated asking it to get the view
/*    @Override
    public View getView(){
        //if we werent given a view, inflate one
        if (null == convertView){
            convertView = getActivity().getLayoutInflater()
                    .inflate(R.layout.list_item_crime, null);
        }
        DiaryEntry de = getItem(position);

    }*/
}
