package com.example.cardsforsmarts.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cardsforsmarts.data.dao.DeckDAO;
import com.example.cardsforsmarts.data.entity.Deck;
import com.example.cardsforsmarts.data.roomDataBase.DeckRoomDatabase;

import java.util.List;

public class DeckRepository {
    private DeckDAO deckDAO;
    private final LiveData<List<Deck>> allDeck;


    public DeckRepository(Application application) {
        DeckRoomDatabase deckRoomDatabase = DeckRoomDatabase.getDatabase(application);
        deckDAO = deckRoomDatabase.deckDAO();
        allDeck = deckDAO.getAll();
    }

    public LiveData<List<Deck>> getAllDecks() {
        return allDeck;
    }

    public void insert(Deck deck) {
        DeckRoomDatabase.databaseDeckExecutor.execute(() -> deckDAO.insert(deck));
    }
}
