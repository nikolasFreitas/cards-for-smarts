package com.example.cardsforsmarts.data.entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Deck {
    @PrimaryKey
    public long deckId;
    public String name;
    @Nullable
    public Float lastScore;
    public Boolean touched = false;
    public int totalCards = 0;
}
