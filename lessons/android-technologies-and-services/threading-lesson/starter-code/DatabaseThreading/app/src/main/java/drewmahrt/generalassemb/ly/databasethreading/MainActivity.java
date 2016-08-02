package drewmahrt.generalassemb.ly.databasethreading;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import drewmahrt.generalassemb.ly.databasethreading.classesyoudontneedtomodify.ExampleDBHelper;
import drewmahrt.generalassemb.ly.databasethreading.classesyoudontneedtomodify.RecyclerViewCursorAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ProgressBar mProgressBar;
    TextView mTextView;

    RecyclerView mRecyclerView;
    RecyclerViewCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mTextView = (TextView) findViewById(R.id.text);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Cursor firstHundred = ExampleDBHelper.getInstance(getApplicationContext()).getFirstHundred();

        mAdapter = new RecyclerViewCursorAdapter(firstHundred);
        mRecyclerView.setAdapter(mAdapter);

        Button addButton = (Button) findViewById(R.id.button_add);
        Button removeButton = (Button) findViewById(R.id.button_remove);

        addButton.setOnClickListener(this);
        removeButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_add:

                addDatabaseItems();

                break;

            case R.id.button_remove:

                removeDatabaseItems();

                break;
        }
    }


    private void addDatabaseItems(){
        mTextView.setText("Adding items to database...");
        mProgressBar.setVisibility(View.VISIBLE);

        for (int i = 0; i < 3000; i++) {
            ExampleDBHelper.getInstance(getApplicationContext()).addName("John","Doe");
        }

        int count = ExampleDBHelper.getInstance(getApplicationContext()).getItemCount();
        mTextView.setText("All items added to database! Current item count: " + count);
        mProgressBar.setVisibility(View.INVISIBLE);
        mAdapter.swapCursor(ExampleDBHelper.getInstance(getApplicationContext()).getFirstHundred());
    }


    private void removeDatabaseItems() {
        ExampleDBHelper.getInstance(getApplicationContext()).removeAll();
        mAdapter.swapCursor(null);
    }

}
