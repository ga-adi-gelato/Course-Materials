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
 * Created by alanjcaceres on 7/19/16.
 */

public class AListFragment extends Fragment {

    public static final String LIST_NUMBER = "list_number";

    ListView mListView;

    int listNumber = 0;

    public interface OnListItemClickListener {
        void onListItemClicked(int tabPosition, int listItemPosition);
    }

    private OnListItemClickListener mListItemClickListener;

    public static AListFragment newInstance(int sectionNumber,
                                            OnListItemClickListener listener)
    {
        AListFragment fragment = new AListFragment();
        Bundle args = new Bundle();
        args.putInt(LIST_NUMBER, sectionNumber);
        fragment.setArguments(args);
        fragment.mListItemClickListener = listener;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listview_fragment);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<String> adapter;

        listNumber = getArguments().getInt(LIST_NUMBER, 0); //Getting the argument

        //Setting the adapter based on the ViewPager position we provided in the argument
        switch (listNumber){
            default:
            case 0:
                adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.planets));
                break;
            case 1:

                adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.grocery_list));
                break;
            case 2:
                adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.to_do_list));
                break;
        }

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mListItemClickListener.onListItemClicked(listNumber, position);
            }
        });
    }
}
