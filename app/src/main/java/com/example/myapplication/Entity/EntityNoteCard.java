package com.example.myapplication.Entity;

public class EntityNoteCard {
    long noteCardId;

    String title;
    String content;
    String createTime;

    public EntityNoteCard() {
    }

    public EntityNoteCard(long noteCardId, String cover, String title, String content, String createTime) {
        this.noteCardId = noteCardId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getNoteCardId() {
        return noteCardId;
    }

    public void setNoteCardId(long noteCardId) {
        this.noteCardId = noteCardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EntityNoteCard{" +
                "noteCardId=" + noteCardId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}