package generalassemb.ly.solution_code;

import android.app.job.JobParameters;
import android.app.job.JobService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by alanjcaceres on 8/22/16.
 */
public class FirstJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        DataSingleton.getInstance().updateFirstString(timeFormat.format(cal.getTime()));
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
