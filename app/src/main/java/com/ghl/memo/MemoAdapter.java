package com.ghl.memo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ghl.memo.room.Memo;

import java.util.ArrayList;
import java.util.List;

//<VH>泛型
public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<Memo> memoData;

    public MemoAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setMemoData(List<Memo> memoData){
        this.memoData = memoData;
    }

    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_memo, parent, false);
        return new MemoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder holder, int position) {
        Memo memo = memoData.get(position);
        holder.title.setText(memo.getTitle());
        holder.content.setText(memo.getContent());
        holder.date.setText(memo.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("memo",memo);
                intent.putExtra("memo",bundle);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(v.getContext())
                        .setTitle("确认删除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataManager.getInstance().deleteMemo(memo);
                                ((MainActivity) context).updateUI();
//                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                               dialog.dismiss();
                            }
                        })
                        .create();
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return memoData == null ? 0 : memoData.size();
    }

    class MemoViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView content;
        public TextView date;

        public MemoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
            date = itemView.findViewById(R.id.tv_date);
        }
    }
}
