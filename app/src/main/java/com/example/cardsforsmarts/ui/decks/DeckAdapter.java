package com.example.cardsforsmarts.ui.decks;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardsforsmarts.data.entity.Deck;
import com.example.cardsforsmarts.databinding.AdapterDeckBinding;

import java.util.ArrayList;
import java.util.List;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.ViewHolder> {
    private List<Deck> deckList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterDeckBinding binding =  AdapterDeckBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Deck deck = deckList.get(position);
        TextView textView = holder.getTextViewDeckName();

        configDeckNameView(textView, deck.name);
    }

    private void configDeckNameView(TextView textView, String deckName) {
        textView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView.setText(deckName);
    }

    @Override
    public int getItemCount() {
        return deckList.size();
    }

    public void setDeck(List<Deck> newDecks) {
        deckList = newDecks;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewDeckName;

        public ViewHolder(AdapterDeckBinding binding) {
            super(binding.getRoot());

            textViewDeckName = binding.textViewDeckName;
        }

        public TextView getTextViewDeckName() {
            return textViewDeckName;
        }
    }
}
