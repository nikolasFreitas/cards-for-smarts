package com.example.cardsforsmarts.ui.putDeck;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.data.entity.Deck;
import com.example.cardsforsmarts.databinding.FragmentPutDeckBinding;
import com.example.cardsforsmarts.data.viewModel.DeckViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class PutDeckFragment extends Fragment {
    private long deckId;
    private FragmentPutDeckBinding fragmentBinding;
    private DeckViewModel deckViewModel;
    private boolean isFormSubmitted = false;
    private FragmentActivity fragmentActivity;

    public PutDeckFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentPutDeckBinding.inflate(inflater, container, false);
        deckViewModel = new ViewModelProvider(requireActivity()).get(DeckViewModel.class);

        deckId = PutDeckFragmentArgs.fromBundle(getArguments()).getDeckId();

        setLatestDeckObservable();
        configToolbarTitle();
        fragmentBinding
                .buttonSubmitDeck
                .setOnClickListener(submitDeck);

        return fragmentBinding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            fragmentActivity = (FragmentActivity) context;
        }
    }

    private void configToolbarTitle() {
        String titleName;
        if (deckId < 0) {
            titleName = getString(R.string.toolBar_add_deck);
        } else {
            titleName = getString(R.string.toolBar_edit_deck);
        }

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(titleName);
    }

    private boolean isFormValid() {
        EditText editDeckName = fragmentBinding.textInputLayoutDeckName.getEditText();

        if (editDeckName != null && editDeckName.getText().toString().isEmpty()) {
            editDeckName.setError("Nome do deck não pode estar vazio");
            return false;
        }

        return true;
    }

    private final View.OnClickListener submitDeck = (View e) -> {
        if (isFormValid()) {
            Deck deck = new Deck();
            deck.name = fragmentBinding.textInputLayoutDeckName.getEditText().getText().toString();
            deckViewModel.insert(deck);
            isFormSubmitted = true;
        }
    };

    private void setLatestDeckObservable() {
        deckViewModel.getLatestDeck().observe(fragmentActivity, deck -> {
            if (isFormSubmitted) {
                showNavigateToCardFragmentDialog(deck);
                isFormSubmitted = false;
            }
        });
    }

    private void showNavigateToCardFragmentDialog(Deck deck) {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity);
        AlertDialog alertDialog = builder
                .setTitle(prepareInsertedMessage(deck.name))
                .setMessage("Deseja criar as cartas para este deck?")
                .setPositiveButton("Criar", (dialog, which) -> {
                    Snackbar.make(fragmentBinding.getRoot(), prepareInsertedMessage(deck.name), BaseTransientBottomBar.LENGTH_SHORT).show();
                    PutDeckFragmentDirections.ActionNavPutDeckToCardList actionToCardList = PutDeckFragmentDirections.actionNavPutDeckToCardList(deck.deckId);
                    Navigation.findNavController(fragmentBinding.getRoot()).navigate(actionToCardList);
                })
                .setNegativeButton("Não", (dialog, which) -> {
                    Snackbar.make(fragmentBinding.getRoot(), prepareInsertedMessage(deck.name), BaseTransientBottomBar.LENGTH_SHORT).show();
                    Navigation.findNavController(fragmentBinding.getRoot()).popBackStack();
                })
                .create();

        alertDialog.show();

    }

    private String prepareInsertedMessage(String deckName) {
        return new StringBuilder("Deck").append(" \"")
                .append(deckName).append("\" ")
                .append("adicionado com sucesso!")
                .toString();

    }

}