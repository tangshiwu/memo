package com.ghl.memo;

import androidx.room.Room;

import com.ghl.memo.room.Memo;
import com.ghl.memo.room.MemoDao;
import com.ghl.memo.room.MemoDatabase;

import java.util.List;

public class DataManager {
    private static MemoDatabase memoDatabase;

    private static DataManager instance;

    private DataManager() {
    }

    //单例模式
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
            memoDatabase = Room.databaseBuilder(MemoApplication.getApplication(),
                    MemoDatabase.class, "database-memo").allowMainThreadQueries().build();
        }
        return instance;
    }

    public MemoDao getMemoDao(){
        return memoDatabase.memoDao();
    }

    public void insertMemo(Memo memo){
        getMemoDao().insert(memo);
    }

    public List<Memo> selectMemo(){
        return getMemoDao().getAll();
    }

    public void updateMemo(Memo memo){
        getMemoDao().update(memo);
    }

    public void deleteMemo(Memo memo){
        getMemoDao().delete(memo);
    }
}
