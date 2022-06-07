package com.example.decodesocials_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.databinding.ActivityIntrosocialBinding;


public class IntroSocialActivity extends AppCompatActivity implements View.OnClickListener {


    ActivityIntrosocialBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_introsocial);

        binding.floatingActionButtonStartDecoding.setOnClickListener(this);

        binding.bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent(IntroSocialActivity.this, MainActivity.class);

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
            Intent intent = new Intent(IntroSocialActivity.this, SocialActivity.class);
            try {
                intent.putExtra("context", "context");
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(IntroSocialActivity.this, "ERROR, couldn't load activity", Toast.LENGTH_SHORT).show();
            }
    }

}
