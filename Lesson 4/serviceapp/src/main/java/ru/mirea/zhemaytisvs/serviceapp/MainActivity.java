package ru.mirea.zhemaytisvs.serviceapp;

import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import ru.mirea.zhemaytisvs.serviceapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int PermissionCode = 200;
    public String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{POST_NOTIFICATIONS, FOREGROUND_SERVICE},
                    PermissionCode
            );
        }

        binding.buttonPlay.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(this, PlayerService.class);
            ContextCompat.startForegroundService(this, serviceIntent);
            Log.d(TAG, "Sent startForegroundService");
        });

        binding.buttonStop.setOnClickListener(v -> {
            stopService(new Intent(this, PlayerService.class));
            Log.d(TAG, "Service stopped");
        });
    }
}