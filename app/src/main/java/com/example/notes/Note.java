package com.example.notes;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    private String title;
    private String description;
    private int dayofweek;
    private int priority;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Note(int id, String title, String description, int dayofweek, int priority) {
        this.title = title;
        this.description = description;
        this.dayofweek = dayofweek;
        this.priority = priority;
        this.id = id;
    }

    @Ignore
    public Note(String title, String description, int dayofweek, int priority) {
        this.title = title;
        this.description = description;
        this.dayofweek = dayofweek;
        this.priority = priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDayofweek(int dayofweek) {
        this.dayofweek = dayofweek;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDayofweek() {
        return dayofweek;
    }

    public int getPriority() {
        return priority;
    }

    public String getStringDayOfWeek(int position) {
        switch (position) {
            case 0:
                return "Понедельник";
            case 1:
                return "Вторник";
            case 2:
                return "Среда";
            case 3:
                return "Четверг";
            case 4:
                return "Пятница";
            case 5:
                return "Суббота";
            default:
                return "Воскресенье";
        }
    }
}
