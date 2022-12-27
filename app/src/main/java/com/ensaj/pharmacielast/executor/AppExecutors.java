package com.ensaj.pharmacielast.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppExecutors {
    private static AppExecutors instance;

    public static AppExecutors getInstance() {
        if (instance == null) {
            instance = new AppExecutors();
        }

        return instance;

    }
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool (  3);

    public ScheduledExecutorService networkI0(){
        return mNetworkIO;
    }



}
