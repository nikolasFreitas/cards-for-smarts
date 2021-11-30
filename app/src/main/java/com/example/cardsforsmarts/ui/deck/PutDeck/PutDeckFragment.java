package com.example.cardsforsmarts.ui.deck.PutDeck;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.databinding.FragmentPutDeckBinding;

public class PutDeckFragment extends Fragment {
    private long deckId;
    private FragmentPutDeckBinding fragmentBinding;

    public PutDeckFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentPutDeckBinding.inflate(inflater, container, false);

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
            String toastMessage = "deck salvo com sucesso! (AINDA A SER IMPLEMENTADO)";

            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
        }
    };

}