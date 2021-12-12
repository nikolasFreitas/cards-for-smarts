package com.example.cardsforsmarts.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.cardsforsmarts.data.entity.Deck;
import com.example.cardsforsmarts.data.relation.DeckWithCards;

import java.util.List;

@Dao
public interface DeckDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void insert(Deck card);

    @Query("SELECT * FROM deck")
    public LiveData<List<Deck>> getAll();

    @Transaction
    @Query("SELECT * FROM deck")
    public LiveData<List<DeckWithCards>> getDeckWithCards();

    @Query("SELECT * FROM deck ORDER BY deckId DESC LIMIT 1")
    public LiveData<Deck> getLatestDeck();
}
