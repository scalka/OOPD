package ie.iadt.scalka.diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DiaryEntryAdapter extends ArrayAdapter<DiaryEntry>{
    Context context;
    //constructor
    public DiaryEntryAdapter(Context context, ArrayList<DiaryEntry> diaryEntries) {
        super(context, 0, diaryEntries);
        this.context = context;
    }
    //ovverride the getView method
    //we do notexplicitly call this method, it is called when a ListView needs a view object to display
    //it has a conversation with its adapated asking it to get the view
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //if we werent given a view, inflate one
        if (null == convertView){

            convertView = ((DiaryListActivity) context).getLayoutInflater()
                    .inflate(android.R.layout.simple_list_item_1, null);
        }
        DiaryEntry de = getItem(position);
// TODO LAYOUT FOR ITEM AND CONTINUES WITH LAB 10
        TextView titleTextView = (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
        titleTextView.setText(de.getTitle());


        return convertView;
    }
}
