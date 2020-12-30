package com.ghl.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ghl.memo.room.Memo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {
    private EditText etTitle;
    private EditText etContent;
    private ImageView ivSave;
    private Toolbar toolbar;
    private Memo memo;
    private boolean isUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getBundleExtra("memo");
        if (bundle != null){
            memo = (Memo) bundle.getSerializable("memo");
        }

        setContentView(R.layout.activity_edit);
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        ivSave = findViewById(R.id.iv_save);
        toolbar = findViewById(R.id.toolbar);

        if (memo != null){
            isUpdate = true;
            etTitle.setText(memo.getTitle());
            etContent.setText(memo.getContent());
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = getDate();
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                if (isUpdate){
                    memo.setTitle(title);
                    memo.setContent(content);
                    memo.setDate(date);
                    DataManager.getInstance().updateMemo(memo);
                }else {
                    Memo memo = new Memo(title,content,date);
                    DataManager.getInstance().insertMemo(memo);
                }
                finish();
            }
        });

    }

    public String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }

}