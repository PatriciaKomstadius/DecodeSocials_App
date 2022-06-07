package com.example.decodesocials_app.view.informationpage;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.databinding.ActivityInfopageAboutastBinding;

public class ActivityInfoPageAboutAsd extends AppCompatActivity {

ActivityInfopageAboutastBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_infopage_aboutast);

        binding.buttonBackToFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
