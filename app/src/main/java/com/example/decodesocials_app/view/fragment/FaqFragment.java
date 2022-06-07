package com.example.decodesocials_app.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.view.informationpage.ActivityInfoPageAboutAsd;
import com.example.decodesocials_app.view.informationpage.ActivityInfoPageConflicts;
import com.example.decodesocials_app.view.informationpage.ActivityInfoPageTips;


public class FaqFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_faq, container, false);

        ImageView buttonAST = view.findViewById(R.id.imageViewAST);
        ImageView buttonTips = view.findViewById(R.id.imageViewTips);
        ImageView buttonConflicts = view.findViewById(R.id.imageViewConflicts);


        buttonAST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityInfoPageAboutAsd.class);
                startActivity(intent);
            }
        });
        buttonTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityInfoPageTips.class);
                startActivity(intent);
            }
        });
        buttonConflicts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityInfoPageConflicts.class);
                startActivity(intent);
            }
        });

        return view;
    }
}