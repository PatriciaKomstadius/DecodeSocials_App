package com.example.decodesocials_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.databinding.ActivityDecodedBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class DecodedActivity extends AppCompatActivity implements View.OnClickListener {

    private String decodedSituation;
   ActivityDecodedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_decoded);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("decodeSocial") != null) {
                decodedSituation = " "
                        + bundle.getString("decodeSocial");
            }
        }

        binding.buttonSaveResultToNote.setOnClickListener(this);
        binding.buttonWriteNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d("ERROR OCCURED: ", e.getMessage());
                    Toast.makeText(DecodedActivity.this, "ERROR, couldn't start activity", Toast.LENGTH_SHORT).show();
                }
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        binding.bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
    }


    private boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent(DecodedActivity.this, MainActivity.class);

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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NoteActivity.class);
        try {
            intent.putExtra("decodeSocial", decodedSituation);
            finish();
            startActivity(intent);
        } catch (Exception e) {
            Log.d("ERROR OCCURED: ", e.getMessage());
            Toast.makeText(DecodedActivity.this, "ERROR, couldn't start activity", Toast.LENGTH_SHORT).show();
        }
    }

}




