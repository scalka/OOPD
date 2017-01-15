package com.example.aw.ci2layoutsandwidgets;

/**
 * Created by user10 on 07/11/2016.
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

// The default implementation of ListFragment inflates a layout that defines a Listview
// We are using this at the moment. But you can override ListFragment.onCreateView() to add more advanced features
public class CrimeListFragment extends ListFragment {

    private static final String TAG = "CrimeListFragment";

    private ArrayList<Crime> mCrimes;
    private CrimeAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getActivity()is a fragment convience method that returns the hosting Activity...CrimeActivity
        getActivity().setTitle(R.string.crimes_title);


        // Returns the list of Crimes from the Model and places it in mCrimes
        CrimeModel crimeModel = CrimeModel.get(getActivity());

        mCrimes = crimeModel.getCrimes();
        // Instead of ArrayAdapter use the CrimeAdapter (Defined below)
        // CrimeAdapter knows how to get content from Crime objects and place it in a list item.
        mAdapter = new CrimeAdapter(mCrimes);
        setListAdapter(mAdapter);
    }



    // CrimeAdapter class is only needed in this class therefore its perfectly acceptable to define
    // this inner class as a private member of the outer class.
    private class CrimeAdapter extends ArrayAdapter<Crime> {

        // Constructor
        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);

        }

        // here we override the Adapter getView() method.
        // Remember, we do not explicitly call this method
            // The ListView has a conversation with the adapter asking to getView()

            //   @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);
            }

            // configure the view for this Crime
            Crime c = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate());
            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked((c.isSolved() != 0));

            return convertView;
        }
    }// End of the inner class

    // THis method will eventually display the complete Crime that was chosen in the list
// at the moment just log that a ListItem was clicked.
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime c = (Crime)(getListAdapter()).getItem(position);

        // Send the Crime from CrimeListFragement to CrimeActivity to CrimeFragment for Display
        Intent intent = new Intent(getActivity(), CrimeActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(intent);
    }


    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeModel crimeModel = CrimeModel.get(getActivity());
        ArrayList<Crime> mCrimes = crimeModel.getCrimes();

        mAdapter = new CrimeAdapter(mCrimes);
        setListAdapter(mAdapter);
    }



}

