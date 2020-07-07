package com.example.stefandy_2201789536;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.SavedViewAdapter;
import helper.DatabaseHelper;
import model.Film;

public class SavedFragment extends Fragment {

    DatabaseHelper db;
    private List<Film> lstFilm = new ArrayList<Film>();
    private RecyclerView recyclerView;
    private Button btnDelete;

    public SavedFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_saved, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.rv_saved);
        btnDelete = v.findViewById(R.id.delete_button);
        db = new DatabaseHelper(getContext());

        lstFilm =db.ViewData();
        if(lstFilm.isEmpty()) {
            Toast.makeText(getContext(), "No saved movie", Toast.LENGTH_LONG).show();
        } else {
            setupRecyclerView(lstFilm);
        }

        return v;
    }

    private void setupRecyclerView(List<Film> lstFilm) {

        SavedViewAdapter svAdapter = new SavedViewAdapter(getContext(),lstFilm);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        recyclerView.setAdapter(svAdapter);
    }
}