package drewmahrt.generalassemb.ly.instagramexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginActivity extends AppCompatActivity {
    WebView mWebView;

    private final String YOUR_AUTHORIZATION_URL = ""; //ENTER YOUR AUTHORIZATION URL HERE
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mWebView = (WebView) findViewById(R.id.webview);

        mWebView.setWebViewClient(new WebViewClient() {

            /*
                YOU CAN READ THE DOCUMENTATION ON THIS METHOD TO FIND OUT WHAT IT DOES

                IN SHORT, IT WAITS FOR A URL REQUEST TO HAPPEN AND THEN TRIES TO INTERCEPT IT TO DO
                SOMETHING ELSE OTHER THAN LOADING A WEB PAGE.

                WE NEED THIS SO WE CAN HANDLE THE REDIRECT URL WHEN IT COMES THROUGH.
            */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.contains("code=")){ //CHECKING TO SEE IF THE URL WE HAVE IS THE ONE WE WANT
                    Log.i(TAG, "shouldOverrideUrlLoading: " + url);

                    int index = url.indexOf("=");
                    Log.i(TAG, url.substring(index+1));

                    //STRIPPING AWAY THE URL AND ONLY KEEPING THE CODE
                    String code = url.substring(index+1);
                    getAccessToken(code);
                    return true;
                }
                else {
                    return false;
                }
            }

        });
        mWebView.loadUrl(YOUR_AUTHORIZATION_URL); //WHAT DO YOU THINK THE URL SHOULD BE?

    }

    /**
     * WE WILL WORK ON THIS TOGETHER
     * @param code THE CODE WE RETRIEVED FROM THE REDIRECT URL
     */
    private void getAccessToken(String code){
        //WE'LL WORK ON THIS TOGETHER
    }


}
