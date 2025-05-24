package ru.mirea.zhemaytisvs.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.zhemaytisvs.musicplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        binding.seekBar.setMax(mediaPlayer.getDuration());

        // Обновление SeekBar
        handler.postDelayed(updateSeekBar, 500);

        // Кнопка Play/Pause
        binding.btnPlayPause.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                binding.btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
                isPlaying = false;
            } else {
                mediaPlayer.start();
                binding.btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                isPlaying = true;
            }
        });

        binding.btnPrev.setOnClickListener(v -> {
            int pos = mediaPlayer.getCurrentPosition();
            mediaPlayer.seekTo(Math.max(pos - 5000, 0));
        });

        binding.btnNext.setOnClickListener(v -> {
            int pos = mediaPlayer.getCurrentPosition();
            mediaPlayer.seekTo(Math.min(pos + 5000, mediaPlayer.getDuration()));
        });

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            boolean userTouch = false;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                userTouch = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                userTouch = false;
            }
        });
    }

    private final Runnable updateSeekBar = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && isPlaying) {
                binding.seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
            handler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateSeekBar);
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}