package com.example.cardsforsmarts.data.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cardsforsmarts.data.entity.Deck;
import com.example.cardsforsmarts.data.repository.CardRepository;
import com.example.cardsforsmarts.data.repository.DeckRepository;

import java.util.List;

public class DeckViewModel extends AndroidViewModel {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;
    private final LiveData<List<Deck>> allDecks;
    private final LiveData<Deck> latestDeck;

    public DeckViewModel(@NonNull Application application) {
        super(application);
        deckRepository = new DeckRepository(application);
        cardRepository = new CardRepository(application);
        allDecks = deckRepository.getAllDecks();
        latestDeck = deckRepository.getLatestDeck();
    }


    public LiveData<List<Deck>> getAllDecks() {
        return allDecks;
    }

    public void insert(Deck deck) {
        deckRepository.insert(deck);
    }

    public LiveData<Deck> getLatestDeck() {
        return latestDeck;
    }

    public void deleteDeck(Deck deck) {
        cardRepository.deleteCardsByDeckId(deck.deckId);
        deckRepository.deleteDeck(deck);
    }
}
