package ly.generalassemb.drewmahrt.searchviewdemo;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SearchResultActivity extends AppCompatActivity {
    private CursorAdapter mCursorAdapter;
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        mListView = (ListView)findViewById(R.id.numbers_list_view);
        mListView.setEmptyView(findViewById(R.id.no_results_text));

        Intent intent = getIntent();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor = NumbersSQLiteHelper.getInstance(this).searchNumbers(query);

            mCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{NumbersSQLiteHelper.COL_NUMBER_VALUE}, new int[]{android.R.id.text1}, 0);
            mListView.setAdapter(mCursorAdapter);
        }
    }
}
