package drewmahrt.generalassemb.ly.databasethreading.classesyoudontneedtomodify;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by alanjcaceres on 8/2/16.
 */
public final class MyViewHolder extends RecyclerView.ViewHolder {


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


    private TextView mName;

    public MyViewHolder(View itemView) {
        super(itemView);

        mName = (TextView) itemView.findViewById(android.R.id.text1);
    }

    public void setName(String firstName, String lastName){
        String fullName = String.format("%s %s", firstName, lastName);
        mName.setText(fullName);
    }
}
