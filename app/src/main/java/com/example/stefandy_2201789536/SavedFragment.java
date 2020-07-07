package com.example.stefandy_2201789536;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

import model.Film;

public class SavedFragment extends Fragment {

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

        return v;
    }
}