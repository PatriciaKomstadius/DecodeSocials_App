package com.example.decodesocials_app.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.databinding.ActivityMainBinding;
import com.example.decodesocials_app.view.fragment.FaqFragment;
import com.example.decodesocials_app.view.fragment.HomeFragment;
import com.example.decodesocials_app.view.fragment.NoteFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("list") != null) {
            replaceFragment(new NoteFragment());
            }
        }
        if (bundle != null) {
            if (bundle.getString("info") != null) {
                replaceFragment(new FaqFragment());
            }
        }
        binding.bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contraint_layout, fragment);
        ft.commit();
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                replaceFragment(new HomeFragment());
                break;
            case R.id.notes:
                replaceFragment(new NoteFragment());
                break;
            case R.id.faq:
                replaceFragment(new FaqFragment());
                break;
//                case R.id.links:
//                    replaceFragment(new NoteFragment());
//                    break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return true;
    }
}
