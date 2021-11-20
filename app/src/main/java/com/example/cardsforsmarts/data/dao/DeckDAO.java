package com.example.cardsforsmarts.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cardsforsmarts.data.entity.Deck;

import java.util.List;

@Dao
public interface DeckDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Deck card);

    @Query("SELECT * FROM deck")
    List<Deck> getAll();
}
