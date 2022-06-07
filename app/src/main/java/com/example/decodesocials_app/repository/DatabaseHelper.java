package com.example.decodesocials_app.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.decodesocials_app.model.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String NOTE_TABLE = "NOTE_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NOTE_TITLE = "NOTE_TITLE";
    public static final String COLUMN_NOTE_NOTE = "NOTE_NOTE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "note.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabase = "CREATE TABLE " + NOTE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOTE_TITLE + " TEXT, " + COLUMN_NOTE_NOTE + " TEXT)";
        db.execSQL(createDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNewNote(NoteModel noteModel) {
        SQLiteDatabase db = DatabaseHelper.this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NOTE_TITLE, noteModel.getTitle());
        contentValues.put(COLUMN_NOTE_NOTE, noteModel.getNote());

        db.insert(NOTE_TABLE, null, contentValues);

    }

    public void updateNote(NoteModel noteModel, int id){
        SQLiteDatabase db = DatabaseHelper.this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NOTE_TITLE, noteModel.getTitle());
        contentValues.put(COLUMN_NOTE_NOTE, noteModel.getNote());

        db.update(NOTE_TABLE,contentValues,COLUMN_ID + " = " + id, null);

        db.close();
    }


    public List<NoteModel> getAllNotes() {
        List<NoteModel> noteModelList = new ArrayList<>();

        String query = "SELECT * FROM " + NOTE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String note = cursor.getString(2);

                NoteModel noteModel = new NoteModel(id, title, note);
                noteModelList.add(noteModel);

            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return noteModelList;
    }


    public void deleteNote(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(NOTE_TABLE, COLUMN_ID + " = " + id, null);

        db.close();
    }



}
