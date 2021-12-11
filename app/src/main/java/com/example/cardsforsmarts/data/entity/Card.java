package com.example.cardsforsmarts.data.entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Card {
    @PrimaryKey(autoGenerate = true)
    public long cardId;
    public long deckOwnerId;
    public CardAnswer cardAnswer;
    public String description;

    @Nullable
    public String reference;
}
