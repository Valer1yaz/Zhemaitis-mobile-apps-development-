package ru.mirea.zhemaytisvs.securesharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences secureSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView poetName = findViewById(R.id.tvPoetName);
        Button saveButton = findViewById(R.id.saveButton);

        try {
            String mainKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    mainKeyAlias,
                    getApplicationContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            String savedPoet = secureSharedPreferences.getString("favorite_poet", "Бродский Иосиф Александрович");
            poetName.setText(savedPoet);

        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }

        saveButton.setOnClickListener(v -> secureSharedPreferences
                .edit()
                .putString("favorite_poet", poetName.getText().toString())
                .apply());
    }
}