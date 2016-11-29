package ie.iadt.scalka.diary.list_fragment;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import ie.iadt.scalka.diary.R;
import ie.iadt.scalka.diary.list_fragment.DiaryListActivity;
import ie.iadt.scalka.diary.model.DiaryEntry;

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
                    .inflate(R.layout.list_item_diary, null);
        }
        DiaryEntry de = getItem(position);

        TextView titleTextView = (TextView)convertView.findViewById(R.id.diary_list_item_titleTextView);
        titleTextView.setText(de.getTitle());

        TextView dateTextView = (TextView)convertView.findViewById(R.id.diary_list_item_date);
        dateTextView.setText(de.getDate().toString());

        return convertView;
    }
}
