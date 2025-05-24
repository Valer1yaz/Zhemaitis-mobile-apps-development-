package ru.mirea.zhemaytisvs.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.zhemaytisvs.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyLooper myLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String res = msg.getData().getString("result");
                Log.d("MainActivity", res);
            }
        };

        myLooper = new MyLooper(mainHandler);
        myLooper.start();

        binding.button.setOnClickListener(v -> {
            if (myLooper.mHandler == null) {
                Log.d("MainActivity", "Looper ещё не готов, подожди пару мс");
                return;
            }

            String age = binding.editTextAge.getText().toString();
            String prof = binding.editTextProfession.getText().toString();

            Message msg = Message.obtain();
            Bundle b = new Bundle();
            b.putString("AGE", age);
            b.putString("PROF", prof);
            msg.setData(b);

            myLooper.mHandler.sendMessage(msg);
        });
    }
}