package ie.iadt.scalka.diary.entry_fragment;

import android.graphics.Bitmap;
import android.media.Image;
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

public class DiaryFragment extends android.support.v4.app.Fragment {
    public static final String EXTRA_DIARY_ID = "ie.iadt.scalka.diary.list_fragment.diary_id";
    private DiaryEntry mDiaryEntry;
    private EditText mTitleField;
    private Button mDateButton;
    private EditText mEntryField;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private File mPhotoFile;
    private CheckBox mSwitch;
    private Button mSaveButton;
    public static final int REQUEST_PHOTO = 2;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // telling that the function onCreteOptionsMenu should be called
        String entryId = getActivity().getIntent().getStringExtra(EXTRA_DIARY_ID);
        mDiaryEntry = DiaryModel.get(getActivity()).getDiaryEntry(entryId);
        mPhotoFile = DiaryModel.get(getActivity()).getPhotoFile(mDiaryEntry); //grabbing photo file location
    }
    @Override
    //create and configure fragment's view, not in onCreate() (activities use onCreate())
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_diary_entry, parent, false);
        mTitleField = (EditText)v.findViewById(R.id.entry_title);
        mTitleField.setText(mDiaryEntry.getTitle());
        mEntryField = (EditText)v.findViewById(R.id.entry_entry);
        mEntryField.setText(mDiaryEntry.getEntry());

    // taking pictures
        mPhotoView = (ImageView)v.findViewById(R.id.entry_photo);
        mPhotoButton = (ImageButton)v.findViewById(R.id.entry_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null;
        mPhotoButton.setEnabled(canTakePhoto);
        if (canTakePhoto){
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        mPhotoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });
        updatePhotoView(mPhotoFile);
    // end of taking pictures
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
        mSwitch = (CheckBox) v.findViewById(R.id.moodSwitch);
        mSwitch.setChecked(mDiaryEntry.getGoodday() == 1);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if ( checked == true ){
                    mDiaryEntry.setGoodday(1);
                    Log.d("diary", "true");
                } else {
                    mDiaryEntry.setGoodday(0);
                    Log.d("diary", "else");
                }
                DiaryModel diaryModel = DiaryModel.get(getActivity());
                diaryModel.updateDiaryEntry(mDiaryEntry);

            }
        });
        mDateButton = (Button)v.findViewById(R.id.dataPickerButton);
        mDateButton.setText(mDiaryEntry.getDate().toString());
        mDateButton.setEnabled(false);

        mSaveButton = (Button)v.findViewById(R.id.save_btn);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DiaryListActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
    public void updatePhotoView(File mPhotoFile){
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.entry_diary_menu, menu);
    }
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
