package com.example.cardsforsmarts.ui.deck;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.databinding.FragmentDeckBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DeckFragment extends Fragment {
    private FragmentDeckBinding binding;
    private String[] mDataset = {};

    public DeckFragment() {
        // Required empty public constructor
        initDataset();
    }

    private void initDataset() {
        int totalItems = 13;
        mDataset = new String[totalItems];
        for (int i = 0; i < totalItems; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeckBinding.inflate(inflater, container, false);
        binding.textViewEmptyMessage.setVisibility(View.VISIBLE);
        FloatingActionButton fab = binding.fabAddDeck;
        attachFabListener(fab);
        initRecycleView();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = binding.recyclerViewDeckList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setAdapter(new DeckAdapter(mDataset));
        recyclerView.setLayoutManager(layoutManager);
    }

    private void attachFabListener(FloatingActionButton button) {
        button.setOnClickListener(view -> {

            DeckFragmentDirections.ActionNavDeckToNavAddDeck action = DeckFragmentDirections.actionNavDeckToNavAddDeck();
            action.setDeckId(5);
            Navigation.findNavController(view).navigate(action);
        });
    }
}