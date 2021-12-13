package com.example.cardsforsmarts.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.cardsforsmarts.data.entity.Card;

import java.util.List;

@Dao
public interface CardDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void insert(Card card);

    @Transaction
    @Query("SELECT * FROM Card")
    public LiveData<List<Card>> getAll();

    @Transaction
    @Query("SELECT * FROM Card WHERE deckOwnerId = :deckId")
    public LiveData<List<Card>> getCardsByDeckId(long deckId);

    @Query("DELETE FROM Card WHERE deckOwnerId=:deckId")
    public void deleteCardsByDeckId(long deckId);
}
