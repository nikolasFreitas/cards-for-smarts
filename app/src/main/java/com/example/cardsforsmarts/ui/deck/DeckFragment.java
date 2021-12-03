package com.example.cardsforsmarts.ui.deck;

import android.os.Bundle;
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

    public DeckFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeckBinding.inflate(inflater, container, false);
        deckViewModel = new ViewModelProvider(getActivity()).get(DeckViewModel.class);
        configWarningTextVisibility(deckViewModel.getAllDecks().getValue());
        FloatingActionButton fab = binding.fabAddDeck;
        attachFabListener(fab);
        initRecycleView();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void setDeckDataObservable(DeckAdapter deckAdapter) {
        deckViewModel.getAllDecks().observe(getActivity(), decks -> {
            deckAdapter.setDeck(decks);
            configWarningTextVisibility(decks);
            deckAdapter.notifyDataSetChanged();
        });
    }

    private void configWarningTextVisibility(List<Deck> decks) {
        if (decks == null || decks.isEmpty()) {
            binding.textViewEmptyMessage.setVisibility(View.VISIBLE);
        } else {
            binding.textViewEmptyMessage.setVisibility(View.GONE);
        }
    }

    private void initRecycleView() {
        RecyclerView recyclerView = binding.recyclerViewDeckList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DeckAdapter deckAdapter = new DeckAdapter();

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