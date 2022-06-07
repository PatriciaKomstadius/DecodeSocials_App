package com.example.decodesocials_app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decodesocials_app.R;
import com.example.decodesocials_app.model.NoteModel;
import com.example.decodesocials_app.repository.DatabaseHelper;
import com.example.decodesocials_app.view.NoteActivity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    static List<NoteModel> notesList;
    static Context context;
    static DatabaseHelper databaseHelper;

    int mExpandedPosition = -1;

    public RecyclerViewAdapter(List<NoteModel> notesList, Context context) {
        this.notesList = notesList;
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

//        holder.id.setText(String.valueOf(notesList.get(position).getId()));
        holder.title.setText(notesList.get(position).getTitle());
        holder.note.setText(notesList.get(position).getNote());
        holder.itemView.setTag(notesList.get(position).getId());


        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, NoteActivity.class);
                intent.putExtra("id", notesList.get(position).getId());
                intent.putExtra("noteToUpdate", notesList.get(position).getNote().toString());
                intent.putExtra("titleToUpdate", notesList.get(position).getTitle().toString());
                try {
                    context.startActivity(intent);

                } catch (Exception e) {
                    Log.d("ERROR OCCURED: ", e.getMessage());
                }
            }
        });

        final boolean isExpanded = position == mExpandedPosition;
        holder.note.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;
                notifyItemChanged(position);
            }
        });

        holder.imageButtonDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                databaseHelper.deleteNote(notesList.get(position).getId());

                notesList.remove(position);
                notifyItemRemoved(position);

                Toast.makeText(RecyclerViewAdapter.context, "DELETED NOTE " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView title;
        TextView note;
        ImageButton buttonUpdate;
        ImageButton imageButtonDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        //    id = itemView.findViewById(R.id.textViewId);
            title = itemView.findViewById(R.id.textViewNoteTitle);
            note = itemView.findViewById(R.id.textViewNote);
            buttonUpdate = itemView.findViewById(R.id.imageButtonEdit);
            imageButtonDelete = itemView.findViewById(R.id.imageButtonDelete);
        }
    }

    public void clear() {
        notesList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<NoteModel> list) {
        notesList.addAll(databaseHelper.getAllNotes());
        notifyDataSetChanged();
    }

}
