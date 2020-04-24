package xyz.nokt.btf.playingwithmaps;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class MapsJob extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
