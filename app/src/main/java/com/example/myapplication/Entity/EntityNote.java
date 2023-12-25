package com.example.myapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_note")
public class EntityNote {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    public long noteId;
    @ColumnInfo(name = "note_title")
    String noteTitle;
    @ColumnInfo(name = "note_content")
    String noteContent;
    @ColumnInfo(name = "note_image_uri")
    String noteImageUri;
    @ColumnInfo(name = "created_time")
    String createdTime;

    public EntityNote(String noteTitle, String noteContent, String noteImageUri, String createdTime) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.noteImageUri = noteImageUri;
        this.createdTime = createdTime;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public void setNoteImageUri(String noteImageUri) {
        this.noteImageUri = noteImageUri;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public long getNoteId() {
        return noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public String getNoteImageUri() {
        return noteImageUri;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    @Override
    public String toString() {
        return "EntityNote{" +
                "noteId=" + noteId +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", noteImageUri='" + noteImageUri + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}
