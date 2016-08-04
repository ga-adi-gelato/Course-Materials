package drewmahrt.generalassemb.ly.databasethreading.classesyoudontneedtomodify;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alanjcaceres on 8/2/16.
 */
public class RecyclerViewCursorAdapter extends RecyclerView.Adapter<MyViewHolder> {


    /**
     * DO NOT MODIFY THIS CLASS
     *
     *
     *
     *
     *
     * DO NOT MODIFY THIS CLASS
     *
     *
     *
     *
     *
     * DO NOT MODIFY THIS CLASS
     *
     *
     *
     *
     *
     * DO NOT MODIFY THIS CLASS
     *
     *
     *
     *
     *
     * DO NOT MODIFY THIS CLASS
     */


    Cursor mCursor;

    public RecyclerViewCursorAdapter(Cursor cursor){
        mCursor = cursor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(mCursor.moveToFirst()){
            mCursor.moveToPosition(position);
            String firstName = mCursor.getString(
                    mCursor.getColumnIndex(ExampleDBHelper.COL_FIRST_NAME));

            String lastName = mCursor.getString(
                    mCursor.getColumnIndex(ExampleDBHelper.COL_LAST_NAME));

            holder.setName(firstName, lastName);
        }
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    public void swapCursor(Cursor cursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = cursor;
        notifyDataSetChanged();
    }
}
