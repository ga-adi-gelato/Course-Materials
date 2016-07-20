package com.ga.android.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by alanjcaceres on 7/19/16.
 */

public class DetailFragment extends Fragment {

    private TextView textView;

    private int mTabPosition, mListItemPosition;

    public static Fragment newInstance(Bundle args){
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pikaView = inflater.inflate(android.R.layout.simple_list_item_1, container, false);

        textView = (TextView) pikaView.findViewById(android.R.id.text1);

        return pikaView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTabPosition = getArguments().getInt("tab_position", 0);
        mListItemPosition = getArguments().getInt("list_item_position", 0);

        String[] strings = null;
        switch(mTabPosition){
            default:
            case 0:
                strings = getResources().getStringArray(R.array.planets);
                break;
            case 1:
                strings = getResources().getStringArray(R.array.grocery_list);
                break;
            case 2:
                strings = getResources().getStringArray(R.array.to_do_list);
                break;
        }

        textView.setText(strings[mListItemPosition]);

    }
}
