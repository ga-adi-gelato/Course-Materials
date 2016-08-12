package ly.generalassemb.jobschedulerlesson;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DataSingleton.DataChangeListener{

    TextView oldText, newText;
    private JobScheduler scheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataSingleton.getInstance().setListener(this);

        oldText = (TextView) findViewById(R.id.textview_1);
        newText = (TextView) findViewById(R.id.textview_2);

        //This is where we will instantiate our JobScheduler and JobInfo objects


    }

    @Override
    public void onDataChanged(String oldTextString) {
        if(newText.getText().length() != 0){
            oldText.setText(oldTextString);
        }
        newText.setText(DataSingleton.getInstance().getMyText());
    }
}
