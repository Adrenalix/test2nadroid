package com.example.myapplication.ui.market;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.apiclasses.Market;
import com.example.myapplication.ui.market.api.Joke;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JokeFragment extends Fragment {

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private List<Joke> jokeList;



    // Initialize marketList to prevent null pointer exception
    private String url = "https://official-joke-api.appspot.com/random_ten";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        JokeRecyclerViewAdapter adapter = new JokeRecyclerViewAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        getData(recyclerView, adapter);
        return view;
    }
    private void getData(RecyclerView recyclerView, JokeRecyclerViewAdapter adapter) {
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getActivity().getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();
                displayResult(response, recyclerView, adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error :" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }

    private void displayResult(String response, RecyclerView recyclerView, JokeRecyclerViewAdapter adapter) {
        ObjectMapper mapper= new ObjectMapper();
        try {
            jokeList = mapper.readValue(response, new TypeReference<List<Joke>>(){});
            recyclerView.setAdapter(new JokeRecyclerViewAdapter(jokeList));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
