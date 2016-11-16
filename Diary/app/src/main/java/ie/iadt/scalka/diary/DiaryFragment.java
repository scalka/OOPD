package ie.iadt.scalka.diary;

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
import android.widget.Switch;

public class DiaryFragment extends android.support.v4.app.Fragment {
    DiaryEntry mDiaryEntry;
    EditText mTitleField;
    Button mDateButton;
    Switch mMoodSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mDiaryEntry = new DiaryEntry();
    }
    @Override
    //create and configure fragment's view, not in onCreat() (activities use onCreate())
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_diary_entry, parent, false);

        mTitleField = (EditText)v.findViewById(R.id.entry_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mDiaryEntry.setTitle(charSequence.toString());
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

        mMoodSwitch = (Switch)v.findViewById(R.id.moodSwitch);
        mMoodSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //set the good mood
                mDiaryEntry.setGoodDay(isChecked);
                Log.i("fragm", "checked");
            }
        });



        return v;
    }

}
