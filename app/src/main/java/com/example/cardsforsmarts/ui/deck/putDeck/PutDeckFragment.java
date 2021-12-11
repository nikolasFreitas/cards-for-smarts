package com.example.cardsforsmarts.ui.deck.putDeck;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.data.entity.Deck;
import com.example.cardsforsmarts.databinding.FragmentPutDeckBinding;
import com.example.cardsforsmarts.ui.deck.DeckViewModel;

public class PutDeckFragment extends Fragment {
    private long deckId;
    private FragmentPutDeckBinding fragmentBinding;
    private DeckViewModel deckViewModel;

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

        configToolbarTitle();
        fragmentBinding
                .buttonSubmitDeck
                .setOnClickListener(submitDeck);

        return fragmentBinding.getRoot();
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
        }
    };

}