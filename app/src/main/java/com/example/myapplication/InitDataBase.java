package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.Dao.NoteDao;
import com.example.myapplication.Entity.EntityNote;

@Database(entities = {EntityNote.class}, version = 1,exportSchema = false)
public abstract class InitDataBase extends RoomDatabase {
    public abstract NoteDao getNoteDao();
}
