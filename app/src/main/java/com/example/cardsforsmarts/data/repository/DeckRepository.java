package com.example.cardsforsmarts.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cardsforsmarts.data.dao.DeckDAO;
import com.example.cardsforsmarts.data.entity.Deck;
import com.example.cardsforsmarts.data.roomDataBase.ApplicationRoomDatabase;

import java.util.List;

public class DeckRepository {
    private DeckDAO deckDAO;
    private final LiveData<List<Deck>> allDeck;
    private final LiveData<Deck> latestDeck;


    public DeckRepository(Application application) {
        ApplicationRoomDatabase applicationRoomDatabase = ApplicationRoomDatabase.getDatabase(application);
        deckDAO = applicationRoomDatabase.deckDAO();
        allDeck = deckDAO.getAll();
        latestDeck = deckDAO.getLatestDeck();
    }

    public LiveData<List<Deck>> getAllDecks() {
        return allDeck;
    }

    public void insert(Deck deck) {
        ApplicationRoomDatabase.databaseDeckExecutor.execute(() -> deckDAO.insert(deck));
    }

    public LiveData<Deck> getLatestDeck() {
        return latestDeck;
    }
}
