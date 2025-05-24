package ru.mirea.zhemaytisvs.mireaproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class MicrophoneFragment extends Fragment {
    private MediaRecorder recorder;
    private boolean isRecording = false;
    private Button btnRecord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_microphone, container, false);
        btnRecord = view.findViewById(R.id.btnRecord);

        btnRecord.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 101);
            } else {
                toggleRecording();
            }
        });
        return view;
    }

    private void toggleRecording() {
        if (isRecording) {
            recorder.stop();
            recorder.release();
            recorder = null;
            btnRecord.setText("Начать запись");
        } else {
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setOutputFile(getActivity().getExternalCacheDir().getAbsolutePath() + "/record.3gp");
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                recorder.prepare();
                recorder.start();
                btnRecord.setText("Остановить запись");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        isRecording = !isRecording;
    }
}
