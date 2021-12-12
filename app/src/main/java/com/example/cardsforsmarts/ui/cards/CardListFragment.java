package com.example.cardsforsmarts.ui.cards;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cardsforsmarts.data.entity.Card;
import com.example.cardsforsmarts.databinding.FragmentCardListBinding;
import com.example.cardsforsmarts.ui.decks.DeckAdapter;
import com.example.cardsforsmarts.ui.decks.DeckFragmentDirections;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CardListFragment extends Fragment {
    FragmentCardListBinding fragmentCardListBinding;
    private CardViewModel deckViewModel;
    private int SPLASH_TIME_OUT = 1500;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCardListBinding = FragmentCardListBinding.inflate(inflater, container, false);
        deckViewModel = new ViewModelProvider(getActivity()).get(CardViewModel.class);
        return fragmentCardListBinding.getRoot();
    }

    private void setAllCardObservable(CardAdapter cardAdapter) {
        deckViewModel.getAllCards().observe(getActivity(), cardAdapter::setCardList);
    }

    private void setCardByDeckObservable(CardAdapter deckAdapter, long deckId) {
        deckViewModel.getCardsByDeckId(deckId).observe(getActivity(), deckAdapter::setCardList);
    }

    private void loadingHandler() {
        new Handler().postDelayed(() -> {
            fragmentCardListBinding.progressBarCardLoading.setVisibility(View.GONE);
//            Pegar deckId pelo safe args
//            updateUiByDeckQuantity(deckViewModel.getCardsByDeckId().getValue());
        }, SPLASH_TIME_OUT);
    }

    private void updateUiByDeckQuantity(List<Card> decks) {
        if (decks == null || decks.isEmpty()) {
            fragmentCardListBinding.textViewEmptyMessage.setVisibility(View.VISIBLE);
        } else {
            fragmentCardListBinding.recyclerViewCardList.setVisibility(View.VISIBLE);
            fragmentCardListBinding.textViewEmptyMessage.setVisibility(View.GONE);
        }
    }

    private void initRecycleView() {
        RecyclerView recyclerView = fragmentCardListBinding.recyclerViewCardList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        CardAdapter deckAdapter = new CardAdapter();

        setAllCardObservable(deckAdapter);
        recyclerView.setAdapter(deckAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void attachFabListener(FloatingActionButton button) {
        button.setOnClickListener(view -> {
            DeckFragmentDirections.ActionNavDeckToNavAddDeck action = DeckFragmentDirections.actionNavDeckToNavAddDeck();
            Navigation.findNavController(view).navigate(action);
        });
    }

}