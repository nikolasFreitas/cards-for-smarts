package com.example.cardsforsmarts.ui.cards;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.data.entity.Card;
import com.example.cardsforsmarts.databinding.FragmentCardListBinding;
import com.example.cardsforsmarts.ui.decks.DeckFragmentDirections;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CardListFragment extends Fragment {
    FragmentCardListBinding fragmentCardListBinding;
    private CardViewModel cardViewModel;
    private long deckId;
    private final int SPLASH_TIME_OUT = 1500;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCardListBinding = FragmentCardListBinding.inflate(inflater, container, false);
        cardViewModel = new ViewModelProvider(getActivity()).get(CardViewModel.class);
        deckId = CardListFragmentArgs.fromBundle(getArguments()).getDeckId();
        cardViewModel.getCardsByDeckId(deckId);
        configToolbarTitle();
        attachFabListener(fragmentCardListBinding.fabAddCard);
        initRecycleView();
        startSpinnerProcess();

        return fragmentCardListBinding.getRoot();
    }

    private void setCardByDeckObservable(CardAdapter cardAdapter) {
        cardViewModel.getCardsByDeckId(deckId).observe(getActivity(), cardAdapter::setCardList);
    }

    private void startSpinnerProcess() {
        new Handler().postDelayed(() -> {
            fragmentCardListBinding.progressBarCardLoading.setVisibility(View.GONE);
            updateUiByCardQuantity(cardViewModel.getCardsByDeckId(deckId).getValue());
        }, SPLASH_TIME_OUT);
    }

    private void updateUiByCardQuantity(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            fragmentCardListBinding.textViewEmptyMessage.setVisibility(View.VISIBLE);
        } else {
            fragmentCardListBinding.recyclerViewCardList.setVisibility(View.VISIBLE);
            fragmentCardListBinding.textViewEmptyMessage.setVisibility(View.INVISIBLE);
        }
    }

    private void configToolbarTitle() {
        StringBuilder titleName = new StringBuilder(getString(R.string.fragment_title_cards))
                .append(" ").append("do deck")
                .append(" ").append(deckId);

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(titleName.toString());
    }

    private void initRecycleView() {
        RecyclerView recyclerView = fragmentCardListBinding.recyclerViewCardList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        CardAdapter deckAdapter = new CardAdapter();

        setCardByDeckObservable(deckAdapter);
        recyclerView.setAdapter(deckAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void attachFabListener(FloatingActionButton button) {
        button.setOnClickListener(view -> {
            CardListFragmentDirections.ActionCardListToPutCard action = CardListFragmentDirections.actionCardListToPutCard(deckId);
            Navigation.findNavController(view).navigate(action);
        });
    }

}