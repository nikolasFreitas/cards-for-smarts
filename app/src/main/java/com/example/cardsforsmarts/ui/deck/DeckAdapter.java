package com.example.cardsforsmarts.ui.deck;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.databinding.AdapterCardDeckBinding;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.ViewHolder> {
    private String[] deckList;

    public DeckAdapter(String[] localDataSet) {
        this.deckList = localDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterCardDeckBinding binding =  AdapterCardDeckBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        View view = binding.getRoot();
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(deckList[position]);
    }

    @Override
    public int getItemCount() {
        return deckList.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(AdapterCardDeckBinding binding) {
            super(binding.getRoot());

            textView = binding.textViewDeckName;
        }

        public TextView getTextView() {
            return textView;
        }
    }

}
