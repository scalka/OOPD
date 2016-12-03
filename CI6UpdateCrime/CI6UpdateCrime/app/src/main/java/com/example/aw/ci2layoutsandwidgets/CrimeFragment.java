package com.example.aw.ci2layoutsandwidgets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "com.example.aw.ci2layoutsandwidgets.crime_id";
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    // onCreate is public as its called from an activity, you may see onCreate() in activity as protected.
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get the Crime ID that was sent as an Extra
        String crimeId = getActivity().getIntent().getStringExtra(EXTRA_CRIME_ID);
        // use this ID to get the Crime object from the Model (which will it from Database)
        mCrime = CrimeModel.get(getActivity()).getCrime(crimeId);

    }

    @Override
    // YOu create and configure the fragment’s view in this method, not in onCreate() above (activities use onCreate())
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);

        // set the Title
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());

        // Set the date
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        // set the Solve Checkbox
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(true);

        // Set up listeners for fields that are editable - in this case Title and Solved
        // Listener to see if Title text field changed
        mTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mCrime.setTitle(c.toString());
                CrimeModel crimeModel = CrimeModel.get(getActivity());
                crimeModel.updateCrime(mCrime);
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank you need to implement the method of the TextWatcher() interface
                // however you do not want this method for this example
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });



        // Listener to see if solved checkbox changed.
         mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set the crime solved to checked/unchecked depending on the checkbox
                // update the database..etc.

            }
        });

        return v;
    }
}
