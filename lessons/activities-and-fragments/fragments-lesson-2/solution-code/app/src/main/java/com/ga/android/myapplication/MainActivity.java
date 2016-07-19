package com.ga.android.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AListFragment.OnListItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content_container,
                        MainFragment.newInstance(this))
                .commit();

    }

    @Override
    public void onListItemClicked(int tabPosition, int listItemPosition) {

        Bundle aBundleOfJoy = new Bundle();
        aBundleOfJoy.putInt("tab_position", tabPosition);
        aBundleOfJoy.putInt("list_item_position", listItemPosition);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("MainActivity")
                .replace(R.id.main_content_container, DetailFragment.newInstance(aBundleOfJoy))
                .commit();
    }
}
