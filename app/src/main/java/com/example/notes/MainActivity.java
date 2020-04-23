package com.example.notes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewNotes;
    public final ArrayList<Note> arrayListNotes = new ArrayList<>();
    private NotesAdapter adapter;
   //private NotesDBHelper dbHelper;
   // private SQLiteDatabase database;
    //private  NotesDatabase database;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
     //   dbHelper = new NotesDBHelper(this);
      //  database = dbHelper.getWritableDatabase();
        //database = NotesDatabase.getInstance(this);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        adapter = new NotesAdapter(arrayListNotes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(adapter);

        getData();


        adapter.setSetOnNoteClickListener(new NotesAdapter.setOnNoteClickListener() {
            @Override
            public void onClick(int position) {
            }

            @Override
            public void onLongClick(int position) {
                remove(position);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);

    }

    private void remove(int position) {
        //int id = arrayListNotes.get(position).getId();
        //String where = NotesContract.NotesEEntry._ID + " =?";
        //String[] whereArgs = new String[]{Integer.toString(id)};
        //database.delete(NotesContract.NotesEEntry.TABLE_NAME, where, whereArgs);
        Note note = arrayListNotes.get(position);
      //  database.notesDao().deleteNote(note);
       // getData();
        viewModel.deleteNote(note);

    }

    public void onClickNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    private void getData() {
        LiveData<List<Note>> noteList = viewModel.getNotes();
        //database.notesDao().getAllNotes();

       noteList.observe(this, new Observer<List<Note>>() {
           @Override
           public void onChanged(List<Note> notes) {
               arrayListNotes.clear();
               arrayListNotes.addAll(notes);
               adapter.notifyDataSetChanged();

           }
       });

    }
}
