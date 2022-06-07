package com.example.decodesocials_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.adapter.RecyclerViewAdapter;
import com.example.decodesocials_app.databinding.ActivityListBinding;
import com.example.decodesocials_app.model.NoteModel;
import com.example.decodesocials_app.repository.DatabaseHelper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    List<NoteModel> showAllNotes;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter mAdapter;

    private SwipeRefreshLayout swipeContainer;

    ActivityListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        databaseHelper = new DatabaseHelper(ListActivity.this);

        showAllNotes = new ArrayList<NoteModel>();
        showAllNotes = databaseHelper.getAllNotes();

        recyclerView = (RecyclerView) findViewById(R.id.list_Notes);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ListActivity.this);


        recyclerView.setLayoutManager(layoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new RecyclerViewAdapter(showAllNotes, ListActivity.this);


        recyclerView.setAdapter(mAdapter);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData(0);
            }
        });


        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        binding.bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent(ListActivity.this, MainActivity.class);

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


    public void fetchData(int page) {
        mAdapter.clear();
        mAdapter.addAll(showAllNotes);
        swipeContainer.setRefreshing(false);

    }

    public void onFailure(Throwable e) {
        Log.d("DEBUG", "Fetch timeline error: " + e.toString());
    }


}
