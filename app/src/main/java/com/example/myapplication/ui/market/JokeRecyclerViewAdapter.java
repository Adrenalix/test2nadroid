package com.example.myapplication.ui.market;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.apiclasses.Market;
import com.example.myapplication.databinding.FragmentJokeItemBinding;
import com.example.myapplication.ui.market.api.Joke;


import java.util.List;

public class JokeRecyclerViewAdapter extends RecyclerView.Adapter<JokeRecyclerViewAdapter.ViewHolder> {

    private List<Joke> jokeList;

    public JokeRecyclerViewAdapter(List<Joke> jokeList) {

        this.jokeList = jokeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_market, parent, false);
       // return new ViewHolder(view);

        // Inflate the layout for a single item view here
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // get an inflated view using binding
        FragmentJokeItemBinding binding = FragmentJokeItemBinding.inflate(inflater, parent, false);

        // Create a new ViewHolder and pass the inflated view to it
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Joke joke = this.jokeList.get(position);

        if (joke.getSetup() != null) {
            holder.setup.setText(joke.getSetup());
        }
        if (joke.getPunchline() != null) {
            holder.punchline.setText(joke.getPunchline());
        }
        System.out.println(getItemCount());
    }

    @Override
    public int getItemCount() {
        return this.jokeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView setup;
        public TextView punchline;

        public ViewHolder(FragmentJokeItemBinding binding) {
            super(binding.getRoot());
            setup = binding.setup;
            punchline = binding.punchline;
        }
    }

}
