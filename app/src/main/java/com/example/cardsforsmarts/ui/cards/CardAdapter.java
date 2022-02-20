package com.example.cardsforsmarts.ui.cards;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardsforsmarts.data.entity.Card;
import com.example.cardsforsmarts.databinding.AdapterCardBinding;
import com.example.cardsforsmarts.ui.PutCard.PutCardFragmentArgs;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<Card> cardList = new ArrayList<>();
    private AdapterCardBinding adapterCardBinding;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        adapterCardBinding = AdapterCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(adapterCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.cardDescription.setText(card.description.toString());
        prepareHyperLinkReferenceUI(holder, card);
        prepareEditButton(holder,card);
    }

    public void prepareEditButton(ViewHolder holder, Card card) {
        holder.editCardButton.setOnClickListener(view -> {
            CardListFragmentDirections.ActionCardListToPutCard action = CardListFragmentDirections.actionCardListToPutCard(card.deckOwnerId);
            action.setCardId(card.cardId);
            Navigation.findNavController(view).navigate(action);
        });
    }

    public void prepareHyperLinkReferenceUI(ViewHolder holder, Card card) {
        String cardLinkReference = card.reference;
        if (cardLinkReference == null) {
            return;
        }

        holder.cardLinkReference.setText(cardLinkReference);
        holder.cardLinkReference.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.cardLinkReference.setVisibility(View.VISIBLE);

        holder.cardLinkReference.setOnClickListener(view -> {
            Uri flashCardsUri = Uri.parse(cardLinkReference);

            if (!cardLinkReference.startsWith("http://") && !cardLinkReference.startsWith("https://")) {
                flashCardsUri = Uri.parse("http://" + cardLinkReference);
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, flashCardsUri);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        private TextView cardDescription;
        private TextView cardLinkReference;
        private Button editCardButton;
        private Button deleteCardButton;

        public ViewHolder(@NonNull AdapterCardBinding itemView) {
            super(itemView.getRoot());
            cardDescription = itemView.textViewQuestionDescription;
            editCardButton = itemView.buttonCardEdit;
            deleteCardButton = itemView.buttonCardDelete;
            cardLinkReference = itemView.textViewReferenceLink;
        }
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
}
