package com.example.myapplication.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Entity.EntityNote;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM t_note")
    List<EntityNote> getAllNote();

    @Query("SELECT * FROM t_note where note_id=:noteId")
    EntityNote getNoteById(long noteId);

    @Insert
    long insertNote(EntityNote note);

    @Delete
    void deleteNote(EntityNote note);

    @Query("DELETE FROM t_note where note_id=:noteId")
    void deleteNoteById(long noteId);

    @Update
    void updateNote(EntityNote note);

}
