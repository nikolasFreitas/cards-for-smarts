package com.example.cardsforsmarts.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cardsforsmarts.data.entity.Card;

import java.util.List;

@Dao
public interface CardDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Card card);

    @Query("SELECT * FROM Card")
    List<Card> getAll();
}
