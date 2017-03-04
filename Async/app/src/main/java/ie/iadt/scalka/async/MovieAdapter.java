package ie.iadt.scalka.async;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ie.iadt.scalka.async.model.Movie;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private Context context;

    private List<Movie> movieList;

    public MovieAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_movie, parent, false);

        //Display flower name in the TextView widget,
        // position is the position in the FlowerList that we want to display
        Movie movie = movieList.get(position);

        // R.id.textView1 is the textView in item_Flower.xml - its a placeholder for the Flower name
        TextView tv = (TextView) view.findViewById(R.id.movie_title);
        tv.setText(movie.getTitle());

        ImageView image = (ImageView)view.findViewById(R.id.movie_image);
        image.setImageBitmap(movie.getBitmap());

        return view;
    }

}
