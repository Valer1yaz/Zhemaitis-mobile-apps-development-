package ru.mirea.zhemaytisvs.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView devBookView = findViewById(R.id.textViewFavBook);
            TextView devQuoteView = findViewById(R.id.textViewFavQuote);

            String book_name = extras.getString(MainActivity.BOOK_NAME_KEY);
            String quotes_name = extras.getString(MainActivity.QUOTES_KEY);

            devBookView.setText(String.format("Любимая книга разработчика: %s", book_name));
            devQuoteView.setText(String.format("Цитата: %s", quotes_name));
        }
    }

    public void sendUserData(View view) {
        EditText userBookEditText = findViewById(R.id.editTextFavBook);
        EditText userQuoteEditText = findViewById(R.id.editTextFavQuote);

        String userBook = userBookEditText.getText().toString();
        String userQuote = userQuoteEditText.getText().toString();

        String resultText = String.format("Название Вашей любимой книги: %s. Цитата: %s",
                userBook, userQuote);

        Intent data = new Intent();
        data.putExtra(MainActivity.USER_MESSAGE, resultText);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}