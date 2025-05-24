package ru.mirea.zhemaytisvs.workmanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

public class MainActivity extends AppCompatActivity {

    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        statusTextView = findViewById(R.id.textView);

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(true)
                .build();

        WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class)
                .setConstraints(constraints)
                .build();


        WorkManager workManager = WorkManager.getInstance(this);
        workManager.enqueue(uploadWorkRequest);


        workManager.getWorkInfoByIdLiveData(uploadWorkRequest.getId()).observe(this, workInfo -> {
            if (workInfo != null) {
                WorkInfo.State state = workInfo.getState();
                switch (state) {
                    case ENQUEUED:
                        statusTextView.setText("Task Enqueued: Waiting for conditions...");
                        break;
                    case RUNNING:
                        statusTextView.setText("Task Running: Work in progress...");
                        break;
                    case SUCCEEDED:
                        statusTextView.setText("Task Succeeded: Work completed!");
                        break;
                    case FAILED:
                        statusTextView.setText("Task Failed: Something went wrong.");
                        break;
                    case BLOCKED:
                        statusTextView.setText("Task Blocked: Waiting for dependencies...");
                        break;
                    case CANCELLED:
                        statusTextView.setText("Task Cancelled.");
                        break;
                }
            }
        });
    }
}