package com.example.decodesocials_app.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.view.ListActivity;
import com.example.decodesocials_app.view.NoteActivity;

public class NoteFragment extends Fragment {

    public NoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.
                fragment_note, container, false);

        Button addButton = view.findViewById(R.id.button1);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NoteActivity.class);
                try {
                    startActivity(i);
                } catch (Exception e) {
                    Log.d("ERROR OCCURED: ", e.getMessage());
                }
            }
        });

        Button showNotesButton = view.findViewById(R.id.button2);

        showNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListActivity.class);
                try {
                    startActivity(i);
                } catch (Exception e) {
                    Log.d("ERROR OCCURED: ", e.getMessage());
                }
            }
        });
        return view;
    }
}