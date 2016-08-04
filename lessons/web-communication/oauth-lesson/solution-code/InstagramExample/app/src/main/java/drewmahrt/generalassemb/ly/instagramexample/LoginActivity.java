package drewmahrt.generalassemb.ly.instagramexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    WebView mWebView;

    private final String YOUR_AUTHORIZATION_URL =
            new StringBuilder("https://api.instagram.com/oauth/authorize/?")
            .append("client_id=").append(InstagramAppData.CLIENT_ID)
            .append("&redirect_uri=" ).append(InstagramAppData.CALLBACK_URL).append("&response_type=code")
            .toString(); //ENTER YOUR AUTHORIZATION URL HERE

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

                    //STRIPPING AWAY THE URL AND ONLY KEEPING THE "CODE"
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

    /** Pseudocode
     *
     * Now we have the auth code
     * Make a request to the server that includes the code, client id, client secret, redirect uri
     * We get an access token as a JSON Object
     * Parse the JSON Object to get the access token
     * Make our api calls
     *
     *
     */


    /**
     * WE WILL WORK ON THIS TOGETHER
     * @param code THE CODE WE RETRIEVED FROM THE REDIRECT URL
     */
    private void getAccessToken(String code){
        //WE'LL WORK ON THIS TOGETHER
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("client_id", InstagramAppData.CLIENT_ID)
                .add("client_secret", InstagramAppData.CLIENT_SECRET)
                .add("redirect_uri", InstagramAppData.CALLBACK_URL)
                .add("code", code)
                .add("grant_type", "authorization_code")
                .build();

        Request request = new Request.Builder()
                .url("https://api.instagram.com/oauth/access_token")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: request failed", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()){
                    throw new IOException("Unexpected code " + response);
                }

                String responseString = response.body().string();
                Log.i(TAG, "onResponse: " + responseString);

                try {
                    JSONObject result = new JSONObject(responseString);
                    String accessToken = result.getString("access_token");

                    Log.i(TAG, "onResponse: access token - " + accessToken);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("access_token", accessToken);
                    startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }


}
