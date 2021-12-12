package com.example.cardsforsmarts.ui.cards;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cardsforsmarts.data.entity.Card;
import com.example.cardsforsmarts.data.repository.CardRepository;

import java.util.List;

public class CardViewModel extends AndroidViewModel {
    private LiveData<List<Card>> cardList;
    private CardRepository cardRepository;
    public CardViewModel(@NonNull Application application) {
        super(application);
        cardRepository = new CardRepository(application);
        cardList = cardRepository.getAllCards();
    }

    public LiveData<List<Card>> getAllCards() {
        return cardList;
    }

    public LiveData<List<Card>> getCardsByDeckId(long deckId) {
        return  cardRepository.getCardsLinkedByDeck(deckId);
    }
}
