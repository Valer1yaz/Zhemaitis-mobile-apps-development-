package ru.mirea.zhemaytisvs.audiorecord;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

import ru.mirea.zhemaytisvs.audiorecord.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION = 200;
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private boolean isWork;
    private String fileName = null;
    private Button recordButton = null;
    private Button playButton = null;
    private MediaRecorder recorder = null;
    private MediaPlayer player = null;
    boolean isStartRecording = true;
    boolean isStartPlaying = true;
    private String recordFilePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int audioRecordPermissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int storagePermissionStatus = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (audioRecordPermissionStatus == PackageManager.PERMISSION_GRANTED && storagePermissionStatus == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
        }

        recordButton = binding.recordButton;
        playButton = binding.playButton;
        recordFilePath = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC),
                "audiorecordtest.3gp"
        ).getAbsolutePath();

        recordButton.setOnClickListener(v -> {
            if (isStartRecording) {
                recordButton.setText("Stop recording");
                playButton.setEnabled(false);
                startRecording();
            } else {
                recordButton.setText("Start recording");
                stopRecording();
                playButton.setEnabled(true);
            }
            isStartRecording = !isStartRecording;
        });

        playButton.setOnClickListener(v -> {
            if (isStartPlaying) {
                playButton.setText("Stop playing");
                recordButton.setEnabled(false);
                startPlaying();
            } else {
                playButton.setText("Start playing");
                stopPlaying();
                recordButton.setEnabled(true);
            }
            isStartPlaying = !isStartPlaying;
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                isWork = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!isWork) finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
        if (player != null) {
            player.release();
            player = null;
        }
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(recordFilePath);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            Log.e(TAG, "startRecording failed", e);
        }
    }

    private void stopRecording() {
        try {
            recorder.stop();
        } catch (RuntimeException e) {
            Log.e(TAG, "stopRecording failed", e);
        }
        recorder.release();
        recorder = null;
    }

    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(recordFilePath);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.e(TAG, "startPlaying failed", e);
        }
    }

    private void stopPlaying() {
        player.stop();
        player.release();
        player = null;
    }
}