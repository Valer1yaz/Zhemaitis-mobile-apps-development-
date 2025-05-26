package ru.mirea.zhemaytisvs.employeedb;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewHeroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewHeroes = findViewById(R.id.listViewHeroes);

        AppDatabase db = App.getInstance().getDatabase();

        // Добавление записей
        if (db.superheroDao().getAll().isEmpty()) {
            db.superheroDao().insert(new Superhero("Spider-Punk Hobby Brown", "Punk Music & Spider Senses", "New York Earth-138B"));
            db.superheroDao().insert(new Superhero("Spider-Man Miguel O'Hara", "Genius & Spider Senses", "New York Earth-2099"));
            db.superheroDao().insert(new Superhero("Spider-Man Pavitr Prabhakar", "YoYo & Spider Senses", "Mumbai Earth-50101"));
        }

        List<Superhero> heroes = db.superheroDao().getAll();
        List<String> heroDescriptions = new ArrayList<>();

        for (Superhero h : heroes) {
            heroDescriptions.add(h.name + " — " + h.superpower + " (" + h.city + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                heroDescriptions
        );
        listViewHeroes.setAdapter(adapter);
    }
}