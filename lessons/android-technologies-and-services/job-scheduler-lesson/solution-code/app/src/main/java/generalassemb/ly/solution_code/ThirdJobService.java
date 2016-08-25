package generalassemb.ly.solution_code;

import android.app.job.JobParameters;
import android.app.job.JobService;

import java.util.Random;

/**
 * Created by alanjcaceres on 8/22/16.
 */
public class ThirdJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Random random = new Random();
        String red = Integer.toHexString(random.nextInt(256));
        String blue = Integer.toHexString(random.nextInt(256));
        String green = Integer.toHexString(random.nextInt(256));
        String color = String.format("#%s%s%s", red,blue,green);
        DataSingleton.getInstance().updateThirdValue(color);
        jobFinished(jobParameters, false);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
