package com.example.android.popularmovies.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class appExecutors {

    private static final Object LOCK = new Object();
    private static appExecutors sInstance;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    private appExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {

        this.diskIO = diskIO;
        this.mainThread = mainThread;
        this.networkIO = networkIO;
    }

    public static appExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new appExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor mainThread() {
        return mainThread;
    }

    public Executor networkIO() {
        return networkIO;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
