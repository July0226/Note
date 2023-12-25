package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Adapter.NoteAdapter;
import com.example.myapplication.Dao.NoteDao;
import com.example.myapplication.Entity.EntityNote;
import com.example.myapplication.Entity.EntityNoteCard;
import com.example.myapplication.Util.UtilMethod;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    InitDataBase initDataBase;
    NoteDao noteDao;
    TextView noteAlert;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.note_list);
        button = findViewById(R.id.floating_action_button);
        noteAlert = findViewById(R.id.note_alert);
        initDataBase = UtilMethod.getInstance(this);
        noteDao = initDataBase.getNoteDao();
        initList();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddOrEditNoteActivity.class));
            }
        });
    }

    private ArrayList<EntityNoteCard> noteToCard(List<EntityNote> localNote) {
        ArrayList<EntityNoteCard> list = new ArrayList<>();
        for (EntityNote note : localNote){
            EntityNoteCard entityNoteCard = new EntityNoteCard();
            entityNoteCard.setNoteCardId(note.getNoteId());
            entityNoteCard.setCreateTime(note.getCreatedTime());
            entityNoteCard.setTitle(note.getNoteTitle());
            entityNoteCard.setContent(note.getNoteContent());
            list.add(entityNoteCard);
        }
        return list;
    }

    private List<EntityNote> getNoteList(){
        List<EntityNote> allNote = noteDao.getAllNote();
        if (allNote.size() == 0){
            return null;
        } else {
            return allNote;
        }
    }

    private void initList() {
        List<EntityNote> localNote = getNoteList();
        if (localNote != null && localNote.size() > 0){
            ArrayList<EntityNoteCard> list =  noteToCard(localNote);
            NoteAdapter adapter = new NoteAdapter(list, this , new NoteAdapter.CountListen() {
                @Override
                public void countListen(int count) {
                    noteAlert.setText(getString(R.string.note_alter, count + ""));
                }
            });
            recyclerView.setAdapter(adapter);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(manager);
            noteAlert.setText(getString(R.string.note_alter, list.size() + ""));
        } else {
            noteAlert.setText(getString(R.string.note_alter, 0 + ""));
            System.out.println("localNote is null");
        }
    }

    @Override
    public void onResume() {
        initList();
        super.onResume();
    }


}