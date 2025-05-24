package ru.mirea.zhemaytisvs.data_thread;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.TimeUnit;

import ru.mirea.zhemaytisvs.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.textViewInfo.setText("runn1");
                Log.d("DataThread", "runn1 executed");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.textViewInfo.setText("runn2");
                Log.d("DataThread", "runn2 executed");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.textViewInfo.setText("runn3");
                Log.d("DataThread", "runn3 executed");
            }
        };

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    Log.d("DataThread", "runOnUiThread(runn1) called");
                    TimeUnit.SECONDS.sleep(1);
                    binding.textViewInfo.postDelayed(runn3, 2000);
                    Log.d("DataThread", "binding.textViewInfo.postDelayed(runn3, 2000) called");
                    binding.textViewInfo.post(runn2);
                    Log.d("DataThread", "binding.textViewInfo.post(runn2) called");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        binding.textViewInfo.append(
                "Различие методов:\n" +
                        "- post - добавляет задачу в очередь сообщений текущего View, выполняется в UI-потоке.\n" +
                        "- runOnUiThread - выполняет код в главном потоке (UI), полезно из других потоков.\n" +
                        "- postDelayed - то же, что и post, но с временной задержкой.\n" +
                        "\n" +
                        "Порядок выполнения зависит от времени попадания в очередь.\n"
        );
    }
}