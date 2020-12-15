package com.ghl.memo;

import androidx.room.Room;

import com.ghl.memo.room.MemoDatabase;

public class DataManager {

    private static DataManager instance;

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public MemoDatabase getDb() {
        return Room.databaseBuilder(MemoApplication.getApplication(),
                MemoDatabase.class, "database-memo").build();
    }

}
