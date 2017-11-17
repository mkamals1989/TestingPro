package com.example.jeesan_2.testingpro.Room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by KAMAL on 11/15/2017.
 */

@Dao
public interface RepoDao {

    @Query("SELECT * FROM repo")
    LiveData<List<Repo>> getAllRepos();

    @Query("SELECT * FROM repo WHERE id=:id")
    LiveData<Repo> getRepo(int id);

    @Query("SELECT * FROM repo WHERE id=:id")
    Repo getSingleRepo(int id);

    @Query("SELECT * FROM repo")
    Cursor getRepoCursor();

    @Query("SELECT * FROM repo WHERE name=:name")
    List<Repo> getReposByName(String name);

    @Query("SELECT * FROM repo WHERE name=:name LIMIT :max")
    List<Repo> getReposByName(int max, String... name);

    @Insert
    void insert(Repo... repos);

    @Insert(onConflict = REPLACE)
    void insert(Repo repo);

    @Insert
    void insert(List<Repo> repoList);

    @Update
    void update(Repo... repos);

    @Delete
    void delete(Repo... repos);


}
