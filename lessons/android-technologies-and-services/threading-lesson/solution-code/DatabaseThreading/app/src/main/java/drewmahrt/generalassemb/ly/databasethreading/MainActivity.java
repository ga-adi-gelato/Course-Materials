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
import android.widget.Toast;

import drewmahrt.generalassemb.ly.databasethreading.classesyoudontneedtomodify.ExampleDBHelper;
import drewmahrt.generalassemb.ly.databasethreading.classesyoudontneedtomodify.RecyclerViewCursorAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ProgressBar mProgressBar;
    TextView mTextView;

    RecyclerView mRecyclerView;
    RecyclerViewCursorAdapter mAdapter;
    AsyncTask<Integer, Integer, Cursor> task;

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

    private void removeDatabaseItems() {
        if(task != null && task.getStatus() == AsyncTask.Status.RUNNING){
            Toast.makeText(this, "Still adding data to the database. Please wait",
                    Toast.LENGTH_LONG).show();
        }
        else {
            ExampleDBHelper.getInstance(getApplicationContext()).removeAll();
            mAdapter.swapCursor(ExampleDBHelper.getInstance(getApplicationContext()).getFirstHundred());
        }
    }

    public void addDatabaseItems(){

        if(task != null && task.getStatus() == AsyncTask.Status.RUNNING){
            Toast.makeText(this, "Still adding data to the database. Please wait",
                    Toast.LENGTH_LONG).show();
        }
        else{
            task = new AsyncTask<Integer, Integer, Cursor>() {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    mTextView.setText("Adding items to database...");
                    mProgressBar.setVisibility(View.VISIBLE);
                }

                @Override
                protected Cursor doInBackground(Integer... params) {
                    for (int i = 0; i < params[0]; i++) {
                        ExampleDBHelper.getInstance(getApplicationContext()).addName("John","Doe");
                        publishProgress(new Integer[]{i+1});
                    }
                    return ExampleDBHelper.getInstance(getApplicationContext()).getFirstHundred();
                }

                @Override
                protected void onProgressUpdate(Integer... values) {
                    super.onProgressUpdate(values);
                    mTextView.setText("Adding items to database... " + values[0]);
                }

                @Override
                protected void onPostExecute(Cursor cursor) {
                    super.onPostExecute(cursor);
                    int count = ExampleDBHelper.getInstance(getApplicationContext()).getItemCount();
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mTextView.setText("All items added to database! Current item count: "+count);
                    mAdapter.swapCursor(cursor);
                }

            };

            task.execute(1000);
        }



    }
}
