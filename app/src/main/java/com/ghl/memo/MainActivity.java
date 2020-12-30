package com.ghl.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;

import com.ghl.memo.room.Memo;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView ivAdd;
    private RecyclerView recyclerView;
    private MemoAdapter memoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivAdd = findViewById(R.id.iv_add);
        recyclerView = findViewById(R.id.rv_memo);
        memoAdapter = new MemoAdapter(this);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(memoAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI(){
        List<Memo> memoList = DataManager.getInstance().selectMemo();
        memoAdapter.setMemoData(memoList);
        memoAdapter.notifyDataSetChanged();
    }
}