package ie.iadt.scalka.diary.list_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;


import ie.iadt.scalka.diary.R;
import ie.iadt.scalka.diary.entry_fragment.DiaryActivity;
import ie.iadt.scalka.diary.entry_fragment.DiaryFragment;
import ie.iadt.scalka.diary.model.DiaryEntry;
import ie.iadt.scalka.diary.model.DiaryModel;


public class DiaryListFragment extends Fragment {
    private RecyclerView mDiaryRecyclerView;
    private static final String TAG = "DiaryListFragment";
    private ArrayList<DiaryEntry> mDiaryEntries;
    private DiaryEntryAdapter adapter;
    private DiaryAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //returns hosting activity - DiaryActivity
        getActivity().setTitle(R.string.app_name);
        //returns list of entries
        DiaryModel dm = DiaryModel.get(getActivity());
        dm.getmDiaryEntry();
        mDiaryEntries = DiaryModel.get(getActivity()).getmDiaryEntry();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_diary_list, container, false);
        mDiaryRecyclerView = (RecyclerView)view.findViewById(R.id.diary_recycler_view);
        //recycler view requires a layout manager
        mDiaryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private class DiaryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitleTextView;
        public TextView mDateTextView;
        private DiaryEntry mDiaryEntry;

        public DiaryHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView)itemView.findViewById(R.id.diary_list_item_titleTextView);
            mDateTextView = (TextView)itemView.findViewById(R.id.diary_list_item_date);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, mDiaryEntry.getId() + " was clicked");
            Intent intent = new Intent(getActivity(), DiaryActivity.class);
            intent.putExtra(DiaryFragment.EXTRA_DIARY_ID, mDiaryEntry.getId());
            startActivity(intent);
        }

        public void bindDiaryEntry(DiaryEntry de){
            mDiaryEntry = de;
            mTitleTextView.setText(de.getTitle());
            mDateTextView.setText(de.getDate());
        }
    }

    private class DiaryAdapter extends RecyclerView.Adapter<DiaryHolder>{
        private ArrayList<DiaryEntry> mDiaryEntries;
        public DiaryAdapter(ArrayList<DiaryEntry> diaryEntries){
            mDiaryEntries = diaryEntries;
        }
        @Override
        public DiaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_diary, parent, false);
            return new DiaryHolder(view);
        }

        @Override
        public void onBindViewHolder(DiaryHolder holder, int position) {
            DiaryEntry de = mDiaryEntries.get(position);
            holder.bindDiaryEntry(de);


        }

        @Override
        public int getItemCount() {
            return mDiaryEntries.size();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        DiaryModel crimeModel = DiaryModel.get(getActivity());
        ArrayList<DiaryEntry> mDiaryEntries = crimeModel.getmDiaryEntry();

        mAdapter = new DiaryAdapter(mDiaryEntries);
        mDiaryRecyclerView.setAdapter(mAdapter);
    }
}
