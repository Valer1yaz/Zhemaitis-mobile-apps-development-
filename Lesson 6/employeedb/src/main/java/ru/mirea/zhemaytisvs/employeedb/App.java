package ru.mirea.zhemaytisvs.employeedb;

import android.app.Application;
import androidx.room.Room;

public class App extends Application {
    private static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room
                .databaseBuilder(this, AppDatabase.class, "superhero_db")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}