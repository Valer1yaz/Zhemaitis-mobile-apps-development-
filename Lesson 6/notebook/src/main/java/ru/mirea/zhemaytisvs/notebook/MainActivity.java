package ru.mirea.zhemaytisvs.notebook;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFileName, editTextQuote;
    private final String LOG_TAG = "Notebook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFileName = findViewById(R.id.editTextFileName);
        editTextQuote = findViewById(R.id.editTextQuote);
        Button saveButton = findViewById(R.id.buttonSave);
        Button loadButton = findViewById(R.id.buttonLoad);

        saveButton.setOnClickListener(v -> writeFileToExternalStorage());
        loadButton.setOnClickListener(v -> readFileFromExternalStorage());
    }

    private void writeFileToExternalStorage() {
        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "Внешнее хранилище недоступно", Toast.LENGTH_SHORT).show();
            return;
        }
        String fileName = editTextFileName.getText().toString();
        String text = editTextQuote.getText().toString();

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName);

        try {
            path.mkdirs();
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(text.getBytes(StandardCharsets.UTF_8));
            stream.close();
            Toast.makeText(this, "Файл сохранён", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.w(LOG_TAG, "Ошибка записи файла: " + e.getMessage());
            Toast.makeText(this, "Ошибка сохранения", Toast.LENGTH_SHORT).show();
        }
    }

    private void readFileFromExternalStorage() {
        if (!isExternalStorageReadable()) {
            Toast.makeText(this, "Внешнее хранилище недоступно", Toast.LENGTH_SHORT).show();
            return;
        }
        String fileName = editTextFileName.getText().toString();

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName);

        try {
            FileInputStream stream = new FileInputStream(file.getAbsoluteFile());
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            stream.close();
            editTextQuote.setText(stringBuilder.toString().trim());
            Toast.makeText(this, "Файл загружен", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.w(LOG_TAG, "Ошибка чтения файла: " + e.getMessage());
            Toast.makeText(this, "Ошибка загрузки", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }
}