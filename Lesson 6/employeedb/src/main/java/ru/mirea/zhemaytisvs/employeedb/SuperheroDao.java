package ru.mirea.zhemaytisvs.employeedb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SuperheroDao {
    @Insert
    void insert(Superhero superhero);

    @Update
    void update(Superhero hero);

    @Delete
    void delete(Superhero hero);
    @Query("SELECT * FROM Superhero")
    List<Superhero> getAll();

    @Query("SELECT * FROM Superhero WHERE id = :id")
    Superhero getById(long id);
}
