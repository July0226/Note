package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Dao.NoteDao;
import com.example.myapplication.Entity.EntityNote;
import com.example.myapplication.Util.UtilMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddOrEditNoteActivity extends AppCompatActivity {
    Button closeButton,saveButton;
    EditText noteTitle,noteContent;
    InitDataBase dataBase;
    NoteDao noteDao;
    TextView createTimeText,wordCount;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String createTime;
    EntityNote note;
    boolean isAdd = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_note);
        closeButton = findViewById(R.id.close_button);
        saveButton = findViewById(R.id.save_button);
        noteTitle = findViewById(R.id.note_title);
        createTimeText = findViewById(R.id.create_time);
        wordCount = findViewById(R.id.words_count);
        noteContent = findViewById(R.id.note_content);
        initMethod();
        Intent intent = getIntent();
        if (!(intent != null && intent.getBooleanExtra("isAdd",true))){
            isAdd = false;
            long noteId = intent.getLongExtra("noteCardId", -1);
            note = noteDao.getNoteById(noteId);
            noteTitle.setText(note.getNoteTitle().length() > 0 ? note.getNoteTitle() : "");
            noteContent.setText(note.getNoteContent());
        }

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
        noteContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                wordCount.setText(getString(R.string.note_length,s.toString().trim().length()));
            }
        });
        wordCount.setText(getString(R.string.note_length,noteContent.getText().toString().length()));
    }

    private void initMethod() {
        createTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        createTimeText.setText(createTime);
        dataBase = UtilMethod.getInstance(this);
        noteDao = dataBase.getNoteDao();
        wordCount.setText(getString(R.string.note_length,0));
    }

    private void saveNote() {
        if (!isAdd) {
            if (noteContent.getText().toString().trim().isEmpty()) {
                UtilMethod.showToast(getApplicationContext(), "Content is empty~");
            } else {
                String content = noteContent.getText().toString().trim();
                note.setNoteContent(content);
                note.setNoteTitle(noteTitle.getText().toString().trim().length() > 0 ? noteTitle.getText().toString().trim() : "");
                noteDao.updateNote(note);
                UtilMethod.showToast(getApplicationContext(), "Save note success!");
                finish();
            }
        } else {
            if (noteContent.getText().toString().trim().isEmpty() || noteTitle.getText().toString().isEmpty()) {
                UtilMethod.showToast(this, "标题或内容不能为空");
            } else {
                noteDao.insertNote(new EntityNote(
                        noteTitle.getText().toString(),
                        noteContent.getText().toString(),
                        null,
                        createTime
                ));
                UtilMethod.showToast(this, "保存成功");
                finish();
            }
        }

    }
}