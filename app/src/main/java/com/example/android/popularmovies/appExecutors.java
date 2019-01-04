package com.example.android.popularmovies;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class appExecutors {

    private static final Object LOCK = new Object();
    private static appExecutors sInstance;
    private final Executor diskIO;

    private appExecutors(Executor diskIO) {
        this.diskIO = diskIO;
    }

    public static appExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new appExecutors(Executors.newSingleThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor diskIO() {
        return diskIO;
    }
}
