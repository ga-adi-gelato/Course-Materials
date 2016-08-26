package generalassemb.ly.solution_code;

import android.app.job.JobParameters;
import android.app.job.JobService;

import java.util.Random;

/**
 * Created by alanjcaceres on 8/22/16.
 */
public class SecondJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Random random = new Random();
        DataSingleton.getInstance().updateSecondString(String.valueOf(random.nextInt(100)));
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
