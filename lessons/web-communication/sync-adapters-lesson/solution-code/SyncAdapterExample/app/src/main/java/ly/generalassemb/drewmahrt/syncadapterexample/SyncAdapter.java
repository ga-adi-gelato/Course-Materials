package ly.generalassemb.drewmahrt.syncadapterexample;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import ly.generalassemb.drewmahrt.syncadapterexample.models.SearchResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by drewmahrt on 3/2/16.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private static String TAG = SyncAdapter.class.getCanonicalName();

    // Global variables
    // Define a variable to contain a content resolver instance
    ContentResolver mContentResolver;

    /**
     * Set up the sync adapter
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }


    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public SyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();

    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        mContentResolver.delete(NewsContentProvider.CONTENT_URI,null,null);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.nytimes.com/svc/news/v3/content/all/all/all.json?limit=20&api-key=d1934738c85789ae6e8dac61ddca1abc%3A12%3A74602111")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);

            Gson gson = new Gson();
            SearchResult result = gson.fromJson(response.body().string(),SearchResult.class);

            for (int i = 0; i < result.getResults().size(); i++) {
                ContentValues values = new ContentValues();
                values.put(NewsDBHelper.COLUMN_TITLE,result.getResults().get(i).getTitle());
                mContentResolver.insert(NewsContentProvider.CONTENT_URI,values);
                Log.d(TAG,"Latest story: "+result.getResults().get(i).getTitle());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
