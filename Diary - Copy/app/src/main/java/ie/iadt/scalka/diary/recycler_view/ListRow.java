package ie.iadt.scalka.diary.recycler_view;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import ie.iadt.scalka.diary.R;

public class ListRow extends RecyclerView.ViewHolder {
    public ImageView mThumbnail;

    public ListRow(View itemView) {
        super(itemView);

       // mThumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
    }
}
