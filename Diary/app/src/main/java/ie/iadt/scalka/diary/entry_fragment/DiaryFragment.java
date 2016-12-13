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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.content.Intent;

import java.io.File;
import java.util.ArrayList;

import ie.iadt.scalka.diary.R;
import ie.iadt.scalka.diary.model.DiaryEntry;
import ie.iadt.scalka.diary.model.DiaryModel;
import ie.iadt.scalka.diary.pictures.PictureUtils;

import static android.app.Activity.RESULT_OK;

public class DiaryFragment extends android.support.v4.app.Fragment {
    public static final String EXTRA_DIARY_ID = "ie.iadt.scalka.diary.list_fragment.diary_id";
    public static final String EXTRA_NEW_ENTRY = "ie.iadt.scalka.diary.list_fragment.diary";
    private DiaryEntry mDiaryEntry;
    private EditText mTitleField;
    private Button mDateButton;
    private EditText mEntryField;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private File mPhotoFile;
    private CheckBox mSwitch;
    public static final int REQUEST_PHOTO = 2;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        String entryId = getActivity().getIntent().getStringExtra(EXTRA_DIARY_ID);
        //Log.d("diaryfragment", entryId);
        mDiaryEntry = DiaryModel.get(getActivity()).getDiaryEntry(entryId);
        mPhotoFile = DiaryModel.get(getActivity()).getPhotoFile(mDiaryEntry); //grabbing photo file location
        //mDiaryEntry = new DiaryEntry();
    }
    @Override
    //create and configure fragment's view, not in onCreat() (activities use onCreate())
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_diary_entry, parent, false);
        mTitleField = (EditText)v.findViewById(R.id.entry_title);
        mTitleField.setText(mDiaryEntry.getTitle());
        mEntryField = (EditText)v.findViewById(R.id.entry_entry);
        mEntryField.setText(mDiaryEntry.getEntry());
        mSwitch = (CheckBox) v.findViewById(R.id.moodSwitch);
        if (mDiaryEntry.getGoodday() == 1){
            mSwitch.setChecked(true);
        }
    // taking pictures
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
                /**not working
                 *
                 *
                 * ***/
                onActivityResult(REQUEST_PHOTO, RESULT_OK, captureImage, (ImageView)view.findViewById(R.id.entry_photo));
            }
        });
        mPhotoView = (ImageView)v.findViewById(R.id.entry_photo);
        updatePhotoView();

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


        mDateButton = (Button)v.findViewById(R.id.dataPickerButton);
        mDateButton.setText(mDiaryEntry.getDate().toString());
        mDateButton.setEnabled(false);

        return v;
    }
    public void updatePhotoView(){
        if (mPhotoFile == null || !mPhotoFile.exists()){
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data, ImageView view) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            view.setImageBitmap(imageBitmap);
        }
    }
}
