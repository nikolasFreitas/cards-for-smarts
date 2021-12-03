package com.example.cardsforsmarts.ui.deck;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cardsforsmarts.data.entity.Deck;
import com.example.cardsforsmarts.data.repository.DeckRepository;

import java.util.List;

public class DeckViewModel extends AndroidViewModel {

    private final DeckRepository deckRepository;
    private final LiveData<List<Deck>> allDecks;

    public DeckViewModel(@NonNull Application application) {
        super(application);
        deckRepository = new DeckRepository(application);
        allDecks = deckRepository.getAllDecks();
    }


    public LiveData<List<Deck>> getAllDecks() {
        return allDecks;
    }

    public void insert(Deck deck) {
        deckRepository.insert(deck);
    }
}