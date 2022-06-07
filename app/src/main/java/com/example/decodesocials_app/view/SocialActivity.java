package com.example.decodesocials_app.view;

import static com.example.decodesocials_app.repository.DataUtilSocialContext.alternatives;
import static com.example.decodesocials_app.repository.DataUtilSocialContext.questionsForSocialContexts;
import static com.example.decodesocials_app.repository.DataUtilSocialContext.subjects;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.databinding.ActivitySocialBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SocialActivity extends AppCompatActivity implements View.OnClickListener {

    private String selectedAlternative;
    private int currContext = 0;
    private int currSubject = 0;
    ActivitySocialBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_social);

        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
        binding.button4.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("context")) {
                getNextQuestion();
            }
        }
        whenNextButtonIsClicked();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        binding.bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent(SocialActivity.this, MainActivity.class);

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

    private void whenNextButtonIsClicked() {
        binding.nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View viewNext) {

                String value = "";

                if (selectedAlternative == null) {
                    value = "Du har inte gjort några val.";
                } else {
                    value = selectedAlternative.toString();
                }

                currContext++;
                if (currContext == 4) {
                    Intent i = new Intent(SocialActivity.this, DecodedActivity.class);
                    try {
                        i.putExtra("decodeSocial", value);
                        startActivity(i);
                    } catch (Exception e) {
                        Log.d("ERROR OCCURED: ", e.getMessage());
                    }
                }
                try {
                    getNextQuestion();
                } catch (Exception e) {
                    Log.d("ERROR OCCURED: ", e.getMessage());
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void getNextQuestion() {
        binding.question.setText(questionsForSocialContexts[currContext]);
        binding.button1.setText(alternatives[currContext][0]);
        binding.button2.setText(alternatives[currContext][1]);
        binding.button3.setText(alternatives[currContext][2]);
        binding.button4.setText(alternatives[currContext][3]);
    }

    @Override
    public void onClick(View v) {
        Button clicked = (Button) v;
        if (selectedAlternative == null) {
            selectedAlternative = " ";
        }
        String subject = "";
        for (int i = 0; i < subjects.length; i++) {
            try {
                subject = subjects[currSubject];
            } catch (Exception e) {
                Log.d("ERROR OCCURED: ", e.getMessage());
            }
        }
        selectedAlternative = "\n " + subject + clicked.getText().toString()  + selectedAlternative;
        currContext++;
        currSubject++;

        //binding.answer.setText(selectedAlternative);

        try {
            getNextQuestion();
        } catch (Exception e) {
            Log.d("ERROR OCCURED: ", e.getMessage());
        }

        if (currContext == 4) {
            Intent i = new Intent(this, DecodedActivity.class);
            i.putExtra("decodeSocial", selectedAlternative.toString());
            startActivity(i);
        }
    }
}

