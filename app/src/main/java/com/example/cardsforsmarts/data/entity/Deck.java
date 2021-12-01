package com.example.cardsforsmarts.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Deck {
    @PrimaryKey
    public int id;

    public String name;
    public Float lastScore;
    public boolean touched = false;
    public int totalCards = 0;
}
