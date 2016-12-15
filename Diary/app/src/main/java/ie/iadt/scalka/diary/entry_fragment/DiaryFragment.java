package ie.iadt.scalka.diary.entry_fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;
import java.io.File;
import ie.iadt.scalka.diary.R;
import ie.iadt.scalka.diary.list_fragment.DiaryListActivity;
import ie.iadt.scalka.diary.model.DiaryEntry;
import ie.iadt.scalka.diary.model.DiaryModel;
import ie.iadt.scalka.diary.pictures.PictureUtils;

/*DiaryFragment holds the views to see details about particular diary entry, it has a menu delete button which deletes the entry*/

public class DiaryFragment extends android.support.v4.app.Fragment {
    public static final String EXTRA_DIARY_ID = "ie.iadt.scalka.diary.list_fragment.diary_id";
    private DiaryEntry mDiaryEntry;
    private ImageView mPhotoView;
    private File mPhotoFile;
    private static final int REQUEST_PHOTO = 2;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // telling that the function onCreteOptionsMenu should be called to create menu
        String entryId = getActivity().getIntent().getStringExtra(EXTRA_DIARY_ID); // getting extra from the intent
        mDiaryEntry = DiaryModel.get(getActivity()).getDiaryEntry(entryId); // getting diary entry
        mPhotoFile = DiaryModel.get(getActivity()).getPhotoFile(mDiaryEntry); //grabbing photo file location
        //hide keyboard from the screen
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    @Override
    //create and configure fragment's view, not in onCreate() (activities use onCreate())
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_diary_entry, parent, false);
        //setting content from database
        EditText mTitleField = (EditText) v.findViewById(R.id.entry_title);
        mTitleField.setText(mDiaryEntry.getTitle());
        EditText mEntryField = (EditText) v.findViewById(R.id.entry_entry);
        mEntryField.setText(mDiaryEntry.getEntry());

        //taking pictures code
        mPhotoView = (ImageView)v.findViewById(R.id.entry_photo);
        ImageButton mPhotoButton = (ImageButton) v.findViewById(R.id.entry_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // open camera intent
        boolean canTakePhoto = mPhotoFile != null;
        mPhotoButton.setEnabled(canTakePhoto);
        //save photo to File
        if (canTakePhoto){
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        //opening camera
        mPhotoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });
        updatePhotoView(mPhotoFile);
        //end of taking pictures code
        //listeners for content change
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mDiaryEntry.setTitle(charSequence.toString());
                DiaryModel diaryModel = DiaryModel.get(getActivity());
                diaryModel.updateDiaryEntry(mDiaryEntry);
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // not used method but has to be here
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // not used method but has to be here
            }
        });
        mEntryField = (EditText)v.findViewById(R.id.entry_entry);
        mEntryField.setText(mDiaryEntry.getEntry());
        mEntryField.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mDiaryEntry.setEntry(charSequence.toString());
                DiaryModel diaryModel = DiaryModel.get(getActivity());
                diaryModel.updateDiaryEntry(mDiaryEntry);
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // not used method but has to be here
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // not used method but has to be here
            }
        });
        // if goodday = 1 checkbox is checked
        CheckBox mSwitch = (CheckBox) v.findViewById(R.id.moodSwitch);
        mSwitch.setChecked(mDiaryEntry.getGoodday() == 1);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if ( checked ){
                    mDiaryEntry.setGoodday(1);
                } else {
                    mDiaryEntry.setGoodday(0);
                }
                DiaryModel diaryModel = DiaryModel.get(getActivity());
                diaryModel.updateDiaryEntry(mDiaryEntry);
            }
        });
        Button mDateButton = (Button) v.findViewById(R.id.dataPickerButton);
        mDateButton.setText(mDiaryEntry.getDate().toString());
        mDateButton.setEnabled(false);
        //save button, saves and goes back to ListFragment
        Button mSaveButton = (Button) v.findViewById(R.id.save_btn);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DiaryListActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
    //update photo in the imageview
    private void updatePhotoView(File mPhotoFile){
        //if photo was not taken set it to image from drawable folder
        if (mPhotoFile == null || !mPhotoFile.exists()){
            mPhotoView.setImageDrawable(getResources().getDrawable(R.drawable.picture));
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        updatePhotoView(mPhotoFile);
    }
    //creating menu on the top
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.entry_diary_menu, menu);
    }
    //calling delete method
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_delete:
                DiaryModel.get(getActivity()).deleteEntry(mDiaryEntry.getId());
                Log.d("delete", "deleting");
                Intent intent = new Intent(getActivity(), DiaryListActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
