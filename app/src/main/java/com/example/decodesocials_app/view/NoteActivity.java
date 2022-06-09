package com.example.decodesocials_app.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.databinding.ActivityNoteBinding;
import com.example.decodesocials_app.model.NoteModel;
import com.example.decodesocials_app.repository.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;

public class NoteActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    public int setId;
    private int updateId;

    ActivityNoteBinding binding;

    public NoteActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("noteToUpdate") != null) {
                String noteToUpdate = bundle.getString("noteToUpdate".toString());
                String titleToUpdate = bundle.getString("titleToUpdate".toString());
                binding.editTextNote.setText(noteToUpdate);
                binding.textViewAddTitle.setText(titleToUpdate);
            }
        }
        Bundle bundleDecoded = getIntent().getExtras();
        if (bundleDecoded != null) {
            if (bundleDecoded.getString("decodeSocial") != null) {
                String decodedSituation = bundleDecoded.getString("decodeSocial");
                binding.editTextNote.setText(decodedSituation);
            }
        }

        databaseHelper = new DatabaseHelper(NoteActivity.this);

        List<NoteModel> showAllNotes = databaseHelper.getAllNotes();


        databaseHelper = new DatabaseHelper(NoteActivity.this);


        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NoteModel notemodel;
                try {
                    notemodel = new NoteModel(setId, Objects.requireNonNull(binding.textViewAddTitle.getText()).toString(), binding.editTextNote.getText().toString());

                    if (notemodel.getNote().isEmpty()) {
                        Toast.makeText(NoteActivity.this, "Tom anteckning, ej sparat", Toast.LENGTH_SHORT).show();
                        notemodel = new NoteModel(-1, "ERROR", "0");
                    } else {
                        databaseHelper.addNewNote(notemodel);
                        Toast.makeText(NoteActivity.this, "Sparat!", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(NoteActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
                    notemodel = new NoteModel(-1, "ERROR", "0");
                }

                Intent intent = new Intent(NoteActivity.this, ListActivity.class);
                startActivity(intent);

            }
        });

        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                if ((intent.getExtras() != null) && !intent.getExtras().isEmpty()) {

                    updateId = intent.getIntExtra("id", -1);
                }
                NoteModel notemodel;
                for (NoteModel n : showAllNotes) {
                    if (n.getId() == updateId) {

                        try {
                            notemodel = new NoteModel(updateId, Objects.requireNonNull(binding.textViewAddTitle.getText()).toString(), binding.editTextNote.getText().toString());

                            databaseHelper.updateNote(notemodel, updateId);

                            Toast.makeText(NoteActivity.this, "Uppdaterat", Toast.LENGTH_SHORT).show();
                            Intent intentBack = new Intent(NoteActivity.this, MainActivity.class);
                            intentBack.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            finish();

                        } catch (Exception e) {
                            Log.d("ERROR OCCURED: ", e.getMessage());
//                            notemodel = new NoteModel(-1, "ERROR", "0");
                        }
                    }
                }
            }
        });


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                try {
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(NoteActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        binding.bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);

    }

    private boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent(NoteActivity.this, MainActivity.class);

        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.notes:
                intent.putExtra("list", "open list");
                startActivity(intent);

                overridePendingTransition(0, 0);
                break;
            case R.id.faq:
                intent.putExtra("info", "open info");
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return true;
    }

}


