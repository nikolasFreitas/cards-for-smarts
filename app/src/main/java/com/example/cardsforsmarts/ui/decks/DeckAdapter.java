package com.example.cardsforsmarts.ui.decks;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardsforsmarts.data.entity.Deck;
import com.example.cardsforsmarts.databinding.AdapterDeckBinding;

import java.util.ArrayList;
import java.util.List;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.ViewHolder> {
    private List<Deck> deckList = new ArrayList<>();
    private DeckViewModel deckViewModel;

    public DeckAdapter(DeckViewModel deckViewModel) {
        this.deckViewModel = deckViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterDeckBinding binding =  AdapterDeckBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Deck deck = deckList.get(position);
        configDeckNameView(holder.getTextViewDeckName(), deck.name);

        holder.editButton.setOnClickListener(v -> {
            DeckFragmentDirections.ActionNavDeckToCardList actionToPutDeck = DeckFragmentDirections.actionNavDeckToCardList(deck.deckId);
            Navigation.findNavController(v).navigate(actionToPutDeck);
        });
        holder.deleteButton.setOnClickListener(v -> {
            deckViewModel.deleteDeck(deck);
            this.notifyItemRemoved(position);
        });

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
        deckList.clear();
        deckList.addAll(newDecks);

        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewDeckName;
        private final Button editButton;
        private  final Button deleteButton;
        private final Button startStudy;

        public ViewHolder(AdapterDeckBinding binding) {
            super(binding.getRoot());

            textViewDeckName = binding.textViewDeckName;
            editButton = binding.buttonDeckEdit;
            deleteButton = binding.buttonDeckDelete;
            startStudy = binding.buttonDeckStart;
        }

        public TextView getTextViewDeckName() {
            return textViewDeckName;
        }
    }
}
