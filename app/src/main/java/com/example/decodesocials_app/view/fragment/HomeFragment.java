package com.example.decodesocials_app.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.view.IntroSocialActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonStart);

        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getActivity(), IntroSocialActivity.class);
        try {
            i.putExtra("context", "context");
            startActivity(i);
        } catch (Exception e) {
            Log.d("ERROR OCCURED: ", e.getMessage());
        }
    }

}