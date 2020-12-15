package com.ghl.memo.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Memo.class}, version = 1)
public abstract class MemoDatabase extends RoomDatabase {
    public abstract MemoDao memoDao();
}
