package ie.iadt.scalka.diary.entry_fragment;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.ArrayList;

import ie.iadt.scalka.diary.R;
import ie.iadt.scalka.diary.model.DiaryEntry;
import ie.iadt.scalka.diary.model.DiaryModel;

public class DiaryFragment extends android.support.v4.app.Fragment {
    public static final String EXTRA_DIARY_ID = "ie.iadt.scalka.diary.list_fragment.diary_id";
    private DiaryEntry mDiaryEntry;
    private EditText mTitleField;
    private Button mDateButton;
    private EditText mEntryField;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        String entryId = getActivity().getIntent().getStringExtra(EXTRA_DIARY_ID);
        Log.d("diaryfragment", "error: " + entryId);
        mDiaryEntry = DiaryModel.get(getActivity()).getDiaryEntry(entryId);

        //mDiaryEntry = new DiaryEntry();
    }
    @Override
    //create and configure fragment's view, not in onCreat() (activities use onCreate())
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_diary_entry, parent, false);
        mTitleField = (EditText)v.findViewById(R.id.entry_title);
        mTitleField.setText(mDiaryEntry.getTitle());

        mPhotoButton = (ImageButton)v.findViewById(R.id.entry_camera);
        mPhotoView = (ImageView)v.findViewById(R.id.entry_photo);

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
        mEntryField.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mDiaryEntry.setEntry(charSequence.toString());
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

}
