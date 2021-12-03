package com.example.cardsforsmarts.data.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.cardsforsmarts.data.entity.Card;
import com.example.cardsforsmarts.data.entity.Deck;

import java.util.List;

public class DeckWithCards {
    @Embedded public Deck deck;

    @Relation(
            parentColumn = "deckId",
            entityColumn = "deckOwnerId"
    )
    public List<Card> cards;
}
