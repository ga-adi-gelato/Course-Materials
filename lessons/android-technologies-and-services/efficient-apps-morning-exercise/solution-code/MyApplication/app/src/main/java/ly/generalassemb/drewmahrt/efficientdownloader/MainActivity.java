package ly.generalassemb.drewmahrt.efficientdownloader;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private int mCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);
        mCount = 0;

        final Button button = (Button)findViewById(R.id.counter_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount++;
                button.setText("Count: "+mCount);
            }
        });

        DownloadTask task = new DownloadTask();
        task.execute("http://www.nasa.gov/sites/default/files/thumbnails/image/hs-2015-02-a-hires_jpg.jpg");
    }

    private class DownloadTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {
            downloadImage(urls[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mTextView.setTextColor(Color.GREEN);
            mTextView.setText("All done!");
        }
    }

    //Don't modify this method
    private void downloadImage (String url){
        URL currentUrl = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedInputStream in = null;
        try {
            currentUrl = new URL(url);

            in = new BufferedInputStream(currentUrl.openStream());
            byte[] byteChunk = new byte[4096];

            int n;

            while ( (n = in.read(byteChunk)) > 0 ) {
                baos.write(byteChunk, 0, n);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
