package com.example.aw.permissions;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.aw.permissions.model.Flower;

    public class FlowerAdapter extends ArrayAdapter<Flower> {

        private Context context;

        private List<Flower> flowerList;

        public FlowerAdapter(Context context, int resource, List<Flower> objects) {
            super(context, resource, objects);
            this.context = context;
            this.flowerList = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_flower, parent, false);

            //Display flower name in the TextView widget,
            // position is the position in the FlowerList that we want to display
            Flower flower = flowerList.get(position);

            // R.id.textView1 is the textView in item_Flower.xml - its a placeholder for the Flower name
            TextView tv = (TextView) view.findViewById(R.id.textView1);

            tv.setText(flower.getName());

            return view;
        }

    }

