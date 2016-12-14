package ie.iadt.scalka.diary.list_fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import android.content.Intent;
import android.widget.TextView;


import ie.iadt.scalka.diary.R;
import ie.iadt.scalka.diary.SimpleItemTouchHelperCallback;
import ie.iadt.scalka.diary.entry_fragment.DiaryActivity;
import ie.iadt.scalka.diary.entry_fragment.DiaryFragment;
import ie.iadt.scalka.diary.model.DiaryEntry;
import ie.iadt.scalka.diary.model.DiaryModel;
import ie.iadt.scalka.diary.pictures.PictureUtils;


public class DiaryListFragment extends Fragment {
    private RecyclerView mDiaryRecyclerView;
    private static final String TAG = "DiaryListFragment";
    private ArrayList<DiaryEntry> mDiaryEntries;
    private DiaryAdapter mAdapter;
    private File mPhotoFile;
    public ImageView mImageView;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // telling that the function onCreteOptionsMenu should be called
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
        mAdapter = new DiaryAdapter(mDiaryEntries);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mDiaryRecyclerView);

        updateUI();
        return view;
    }

    public class DiaryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitleTextView;
        public TextView mDateTextView;

        private DiaryEntry mDiaryEntry;

        public DiaryHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView)itemView.findViewById(R.id.diary_list_item_titleTextView);
            mDateTextView = (TextView)itemView.findViewById(R.id.diary_list_item_date);
            mImageView = (ImageView)itemView.findViewById(R.id.list_item_imageView);

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
            mPhotoFile = DiaryModel.get(getActivity()).getPhotoFile(mDiaryEntry); //grabbing photo file location
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mImageView.setImageBitmap(bitmap);
        }
    }

    private class DiaryAdapter extends RecyclerView.Adapter<DiaryHolder> implements SimpleItemTouchHelperCallback.ItemTouchHelperAdapter{
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
        /*ItemTouch methods
        * call notifyItemRemoved() and notifyItemMoved() so the Adapter is aware of the changes
        * It’s also important to note that we’re changing the position of the item every time the view is shifted to a new index*/
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mDiaryEntries, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mDiaryEntries, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(int position) {
            mDiaryEntries.remove(position);
         //   DiaryModel.get(getActivity()).deleteEntry(mDiaryEntries.get(position).getId());
            Log.d("listfr", "to be deleted " + mDiaryEntries.get(position).getId());
            notifyItemRemoved(position);
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
    //inflating the menu resource
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_diary_entry_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_new_entry:
                DiaryEntry de = new DiaryEntry();
                DiaryModel.get(getActivity()).addEntry(de);
                Intent intent = new Intent(getActivity(), DiaryActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_delete:
               // DiaryModel.deleteEntry(1);
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void updateUI() {
        DiaryModel crimeModel = DiaryModel.get(getActivity());
        ArrayList<DiaryEntry> mDiaryEntries = crimeModel.getmDiaryEntry();

        mAdapter = new DiaryAdapter(mDiaryEntries);
        mDiaryRecyclerView.setAdapter(mAdapter);
    }


}
