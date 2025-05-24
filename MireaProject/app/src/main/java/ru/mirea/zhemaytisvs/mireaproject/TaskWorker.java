package ru.mirea.zhemaytisvs.mireaproject;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class TaskWorker extends Worker {
    private static final String TAG = "TaskWorker";

    public TaskWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: Starting background task");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "doWork: Task failed", e);
            return Result.failure();
        }
        Log.d(TAG, "doWork: Task completed successfully");
        return Result.success();
    }
}
