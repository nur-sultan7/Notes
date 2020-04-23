package com.example.notes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerDaysOfWeek;
    private RadioGroup radioGroupPriority;
    private NotesDatabase database;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        spinnerDaysOfWeek = findViewById(R.id.spinnerDayOfWeek);
        radioGroupPriority = findViewById(R.id.radipGroupPriority);
        database = NotesDatabase.getInstance(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null)
        {
            actionBar.hide();
        }
    }

    public void onSaveNote(View view) {
        String tittle = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int day_of_week = spinnerDaysOfWeek.getSelectedItemPosition();
        int rb_id = radioGroupPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(rb_id);
        int priority = Integer.parseInt(radioButton.getText().toString());


      //  NotesDBHelper dbHelper = new NotesDBHelper(this);
       // SQLiteDatabase database = dbHelper.getWritableDatabase();
        if (isFill(tittle, description)) {
          /*  ContentValues contentValues = new ContentValues();
            contentValues.put(NotesContract.NotesEEntry.COLUMN_TITLE, tittle);
            contentValues.put(NotesContract.NotesEEntry.COLUMN_DESCRIPTION, description);
            contentValues.put(NotesContract.NotesEEntry.COLUMN_DAY_OF_WEEK, day_of_week);
            contentValues.put(NotesContract.NotesEEntry.COLUMN_PRIORITY, priority);
            database.insert(NotesContract.NotesEEntry.TABLE_NAME, null, contentValues);
            */
          Note note = new Note(tittle,description,day_of_week,priority);
         // database.notesDao().insertNote(note);
            viewModel.insertNote(note);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.warning_fill_strings, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isFill(String title, String description) {
        return !title.isEmpty() && !description.isEmpty();
    }
}
