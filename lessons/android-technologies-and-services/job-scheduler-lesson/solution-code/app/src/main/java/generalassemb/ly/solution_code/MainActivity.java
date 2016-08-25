package generalassemb.ly.solution_code;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DataSingleton.DataSingletonListener {

    TextView oldDataText1, newDataText1, oldDataText2, newDataText2, oldDataText3, newDataText3;

    DataSingleton dataSingleton;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextViews();

        dataSingleton = DataSingleton.getInstance();
        dataSingleton.addDataChangeListener(this);

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        JobInfo firstJob = new JobInfo.Builder(1, new ComponentName(getPackageName(),
                FirstJobService.class.getName()))
                .setPeriodic(10000)
                .build();

        JobInfo secondJob = new JobInfo.Builder(2, new ComponentName(getPackageName(),
                SecondJobService.class.getName()))
                .setPeriodic(10000)
                .build();

        JobInfo thirdJob = new JobInfo.Builder(3, new ComponentName(getPackageName(),
                ThirdJobService.class.getName()))
                .setPeriodic(5000)
                .build();

        jobScheduler.schedule(firstJob);
        jobScheduler.schedule(secondJob);
        jobScheduler.schedule(thirdJob);


    }

    private void initTextViews() {
        oldDataText1 = (TextView) findViewById(R.id.textview_old_task1);
        oldDataText1.setText(DataSingleton.getInstance().getFirstDataString());
        oldDataText2 = (TextView) findViewById(R.id.textview_old_task2);
        oldDataText2.setText(DataSingleton.getInstance().getSecondDataString());
        oldDataText3 = (TextView) findViewById(R.id.textview_old_task3);
        oldDataText3.setText(DataSingleton.getInstance().getThirdDataString() + "");

        newDataText1 = (TextView) findViewById(R.id.textview_new_task1);
        newDataText2 = (TextView) findViewById(R.id.textview_new_task2);
        newDataText3 = (TextView) findViewById(R.id.textview_new_task3);
    }

    @Override
    public void onFirstStringChanged(String oldString) {
        if(newDataText1.getText().length() > 0){
            oldDataText1.setText(oldString);
        }
        newDataText1.setText(dataSingleton.getFirstDataString());
    }

    @Override
    public void onSecondStringChanged(String oldString) {
        if(newDataText2.getText().length() > 0){
            oldDataText2.setText(oldString);
        }
        newDataText2.setText(dataSingleton.getSecondDataString());
    }

    @Override
    public void onThirdStringChanged(String oldValue) {
        if(newDataText3.getText().length() > 0){
            oldDataText3.setText(oldValue);
            try {
                oldDataText3.setBackgroundColor(Color.parseColor(oldValue));
            }catch (IllegalArgumentException e) {
                oldDataText3.setBackgroundColor(Color.RED);
                Log.e(TAG, "onThirdStringChanged: ", e);
            }
        }
        newDataText3.setText(dataSingleton.getThirdDataString());
        try {
            newDataText3.setBackgroundColor(Color.parseColor(dataSingleton.getThirdDataString()));
        }catch (IllegalArgumentException e){
            newDataText3.setBackgroundColor(Color.RED);
            Log.e(TAG, "onThirdStringChanged: ", e);
        }
    }
}
