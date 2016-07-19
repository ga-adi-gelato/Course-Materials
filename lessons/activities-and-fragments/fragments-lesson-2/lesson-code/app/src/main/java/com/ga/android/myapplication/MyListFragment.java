package com.ga.android.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by alanjcaceres on 7/18/16.
 */

public class MyListFragment extends Fragment {

    private ListView mListView;
    private OnPlanetSelectedListener mListener;

    public interface OnPlanetSelectedListener{
        void onPlanetSelected(String selectedPlanet);
    }

    public static Fragment newInstance(Bundle bundle, OnPlanetSelectedListener listener){
        MyListFragment fragment = new MyListFragment();
        fragment.setArguments(bundle);
        fragment.mListener = listener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        mListView = (ListView) v.findViewById(R.id.listview_fragment);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Planets));
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mListener.onPlanetSelected(
                        adapterView.getAdapter().getItem(position).toString());
            }
        });
    }
}
