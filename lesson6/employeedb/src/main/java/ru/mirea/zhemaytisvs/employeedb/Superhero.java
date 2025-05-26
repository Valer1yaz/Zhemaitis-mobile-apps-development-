package ru.mirea.zhemaytisvs.employeedb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Superhero {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String superpower;
    public String city;

    public Superhero(String name, String superpower, String city) {
        this.name = name;
        this.superpower = superpower;
        this.city = city;
    }
}
