package com.example.notes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase database;
    private static  final String DB_NAME = "notes2.db";

    public static NotesDatabase getInstance(Context context)
    {
        synchronized (NotesDatabase.class)
        {
            if (database==null)
            {
                database= Room.databaseBuilder(context,NotesDatabase.class,DB_NAME)

                        .build();
            }

        }
        return database;
    }
    public abstract NotesDao notesDao();
}
