package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AddOrEditNoteActivity;
import com.example.myapplication.Dao.NoteDao;
import com.example.myapplication.Entity.EntityNoteCard;
import com.example.myapplication.InitDataBase;
import com.example.myapplication.R;
import com.example.myapplication.Util.UtilMethod;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    ArrayList<EntityNoteCard> list;
    Context context;
    InitDataBase initDataBase;
    NoteDao noteDao;
    int deletePosition;
    CountListen countListen;
    public NoteAdapter(ArrayList<EntityNoteCard> list, Context context, CountListen countListen) {
        this.list = list;
        this.context = context;
        this.countListen = countListen;
        initDataBase = UtilMethod.getInstance(context);
        noteDao = initDataBase.getNoteDao();
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        if (position == holder.getLayoutPosition()){
            holder.createTime.setText(list.get(position).getCreateTime());
            holder.title.setText(list.get(position).getTitle());
            holder.content.setText(list.get(position).getContent());
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MaterialAlertDialogBuilder(context)
                            .setTitle("删除")
                            .setMessage("确定删除吗？")
                            .setPositiveButton("确定", (dialog, which) -> {
                                deletePosition = holder.getLayoutPosition();
                                deleteNote();
                            }).setNegativeButton("取消", null).show();
                }
            });
        }
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddOrEditNoteActivity.class);
                intent.putExtra("isAdd",false);
                intent.putExtra("noteCardId",list.get(position).getNoteCardId());
                context.startActivity(intent);
            }
        });
    }

    private void deleteNote() {
        noteDao.deleteNoteById(list.get(deletePosition).getNoteCardId());
        Log.d("delete", "deleteNote: "+list.get(deletePosition));
        notifyItemRemoved(deletePosition);
        list.remove(deletePosition);
        countListen.countListen(list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        MaterialCardView itemCard;

        TextView title;
        TextView content;
        TextView createTime;
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            createTime = itemView.findViewById(R.id.create_time);
            delete = itemView.findViewById(R.id.delete);
            itemCard = itemView.findViewById(R.id.item_card);
        }
    }
    public interface CountListen{
        void countListen(int count);
    }
}
