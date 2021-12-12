package com.example.cardsforsmarts.ui.cards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardsforsmarts.data.entity.Card;
import com.example.cardsforsmarts.databinding.AdapterCardBinding;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<Card> cardList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterCardBinding adapterCardBinding = AdapterCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(adapterCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.cardDescription.setText(card.description);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        private TextView cardDescription;
        private Button editCardButton;
        private Button deleteCardButton;

        public ViewHolder(@NonNull AdapterCardBinding itemView) {
            super(itemView.getRoot());
            cardDescription = itemView.textViewQuestionDescription;
            editCardButton = itemView.buttonCardEdit;
            deleteCardButton = itemView.buttonCardDelete;
        }
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
}
