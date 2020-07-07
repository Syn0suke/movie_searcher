package com.example.stefandy_2201789536;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import adapter.SearchViewAdapter;
import model.Film;

public class SearchFragment extends Fragment {

    private RequestQueue requestQueue;
    private List<Film> lstFilm = new ArrayList<Film>();
    private RecyclerView recyclerView;
    private Button btnSearch;
    private String search_result;
    EditText search;

    public SearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.rv_search);
        search = v.findViewById(R.id.search_movie);
        btnSearch = v.findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequest();
            }
        });
        return v;
    }

    private void jsonRequest() {
        requestQueue = Volley.newRequestQueue(getContext());

        search_result = search.getText().toString();
        String final_search = search_result.replaceFirst("\\s","+");
        String url = "http://www.omdbapi.com/?s="+final_search+"&apikey=b7fe18b4";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray array = response.getJSONArray("Search");
                    for (int i = 0; i < array.length(); i++) {
                        Film film = new Film();
                        JSONObject obj = array.getJSONObject(i);
                        film.setTitle(obj.getString("Title"));
                        film.setYear(obj.getString("Year"));
                        film.setId(obj.getString("imdbID"));
                        film.setImage_url(obj.getString("Poster"));
                        lstFilm.add(film);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setupRecyclerView(lstFilm);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    private void setupRecyclerView(List<Film> lstFilm) {

        SearchViewAdapter svAdapter = new SearchViewAdapter(getContext(),lstFilm);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        recyclerView.setAdapter(svAdapter);
    }
}