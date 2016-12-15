package ie.iadt.scalka.diary;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
//class helper to implement the swipe and drag functionality
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback{
    private final ItemTouchHelperAdapter mAdapter;

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        /*ItemTouchHelper easily determine the direction of an event
        * getMovementFlags() specify which directions of drags and swipes are supported*/
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }
    /** Interface to listen for a move or dismissal event*/
    public interface ItemTouchHelperAdapter {
        /* Called when an item has been dragged far enough to trigger a move*/
        boolean onItemMove(int fromPosition, int toPosition);
        /*Called when an item has been dismissed by a swipe*/
        void onItemDismiss(int position);
    }
    /*onMove() and onSwiped() are needed to notify anything in charge of updating the underlying data*/
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(),
                target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
    @Override
    public boolean isItemViewSwipeEnabled() {
        /*To enable swiping from touch events that start anywhere within the view*/
        return true;
    }
}
