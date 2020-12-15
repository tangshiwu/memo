package com.ghl.memo.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemoDao {
    @Query("SELECT * FROM memo")
    List<Memo> getAll();

    @Insert
    void insert(Memo memo);

    @Delete
    void delete(Memo memo);

    @Update
    void update(Memo memo);
}
