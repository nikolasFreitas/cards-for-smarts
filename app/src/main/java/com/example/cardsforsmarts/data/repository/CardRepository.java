package com.example.cardsforsmarts.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cardsforsmarts.data.dao.CardDAO;
import com.example.cardsforsmarts.data.entity.Card;
import com.example.cardsforsmarts.data.roomDataBase.ApplicationRoomDatabase;

import java.util.List;

public class CardRepository {
    private CardDAO cardDAO;
    private final LiveData<List<Card>> allCards;
    private LiveData<List<Card>> cardsLinkedByDeck;


    public CardRepository(Application application) {
        ApplicationRoomDatabase applicationRoomDatabase = ApplicationRoomDatabase.getDatabase(application);
        cardDAO = applicationRoomDatabase.cardDAO();
        allCards = cardDAO.getAll();
    }

    public LiveData<List<Card>> getAllCards() {
        return allCards;
    }
    public LiveData<List<Card>> getCardsLinkedByDeck(long deckId) {
        if (cardsLinkedByDeck == null) {
            cardsLinkedByDeck = cardDAO.getCardsByDeckId(deckId);
        }

        return cardsLinkedByDeck;
    }

    public void deleteCardsByDeckId(long deckId) {
        ApplicationRoomDatabase.databaseDeckExecutor.execute(() -> cardDAO.deleteCardsByDeckId(deckId));
    }

    public void insert(Card card) {
        ApplicationRoomDatabase.databaseDeckExecutor.execute(() -> cardDAO.insert(card));
    }
}
