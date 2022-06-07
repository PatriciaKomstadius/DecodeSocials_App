package com.example.decodesocials_app.view.informationpage;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.databinding.ActivityInfopageConflictsBinding;

public class ActivityInfoPageConflicts extends AppCompatActivity {

    ActivityInfopageConflictsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_infopage_conflicts);

        binding.buttonBackToFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
