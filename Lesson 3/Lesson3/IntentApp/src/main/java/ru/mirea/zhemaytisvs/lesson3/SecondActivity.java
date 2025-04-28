package ru.mirea.zhemaytisvs.lesson3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView = findViewById(R.id.textView);

        String time = getIntent().getStringExtra("time");

        int number = 9;
        int square = number * number;

        String result = "Квадрат номера по списку группы составляет: " +
                square + ", текущее время: " + time;
        textView.setText(result);
    }
}