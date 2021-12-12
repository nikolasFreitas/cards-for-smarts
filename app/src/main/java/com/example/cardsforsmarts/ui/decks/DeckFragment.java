package com.example.cardsforsmarts.ui.decks;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardsforsmarts.data.entity.Deck;
import com.example.cardsforsmarts.databinding.FragmentDeckBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DeckFragment extends Fragment {
    private FragmentDeckBinding binding;
    private DeckViewModel deckViewModel;
    private int SPLASH_TIME_OUT = 1500;
    private boolean splashRunning;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeckBinding.inflate(inflater, container, false);
        deckViewModel = new ViewModelProvider(getActivity()).get(DeckViewModel.class);
        loadingHandler();
        initRecycleView();

        FloatingActionButton fab = binding.fabAddDeck;
        attachFabListener(fab);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void setDeckDataObservable(DeckAdapter deckAdapter) {
        deckViewModel.getAllDecks().observe(getActivity(),decks -> {
            deckAdapter.setDeck(decks);
            if (!splashRunning) {
                updateUiByDeckQuantity(decks);
            }
        });
    }

    private void loadingHandler() {
        splashRunning = true;
        new Handler().postDelayed(() -> {
            splashRunning = false;
            binding.progressBarDeckLoading.setVisibility(View.GONE);
            updateUiByDeckQuantity(deckViewModel.getAllDecks().getValue());
        }, SPLASH_TIME_OUT);
    }

    private void updateUiByDeckQuantity(List<Deck> decks) {
        if (decks == null || decks.isEmpty()) {
            binding.textViewEmptyMessage.setVisibility(View.VISIBLE);
            binding.recyclerViewDeckList.setVisibility(View.GONE);
        } else {
            binding.recyclerViewDeckList.setVisibility(View.VISIBLE);
            binding.textViewEmptyMessage.setVisibility(View.GONE);
        }
    }

    private void initRecycleView() {
        RecyclerView recyclerView = binding.recyclerViewDeckList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DeckAdapter deckAdapter = new DeckAdapter(deckViewModel);

        setDeckDataObservable(deckAdapter);
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