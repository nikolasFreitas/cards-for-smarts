package com.example.cardsforsmarts.ui.PutCard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.data.entity.Card;
import com.example.cardsforsmarts.data.entity.CardAnswer;
import com.example.cardsforsmarts.data.viewModel.CardViewModel;
import com.example.cardsforsmarts.databinding.FragmentPutCardBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class PutCardFragment extends Fragment {
    private FragmentPutCardBinding fragmentPutCardBinding;
    final private int TRUE_OPTION_INDEX = 0;
    final private int FALSE_OPTION_INDEX = 1;
    private long cardId;
    private long deckId;
    private CardViewModel cardViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPutCardBinding = FragmentPutCardBinding.inflate(inflater, container, false);
        cardId = PutCardFragmentArgs.fromBundle(getArguments()).getCardId();
        deckId = PutCardFragmentArgs.fromBundle(getArguments()).getDeckId();

        cardViewModel = new ViewModelProvider(requireActivity()).get(CardViewModel.class);

        setUiMode();
        configDropdownUi();
        setCardByDeckObservable();
        fragmentPutCardBinding.buttonSubmitCard.setOnClickListener(submitDeck);
        return fragmentPutCardBinding.getRoot();
    }

    private void setUiMode() {
        String titleName;
        String buttonText;
        if (isCreationMode()) {
            titleName = getString(R.string.toolBar_add_card);
            buttonText = "Criar carta";
        } else {
            titleName = getString(R.string.toolBar_edit_card);
            buttonText = getString(R.string.toolBar_edit_card);
        }

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(titleName);
        fragmentPutCardBinding.buttonSubmitCard.setText(buttonText);
    }

    private void setCardByDeckObservable() {
        if (isCreationMode()) {
            return;
        }
        cardViewModel.getCardsByCardId(cardId).observe(getActivity(), this::fillFormItems);
    }

    private void fillFormItems(Card card) {
        if (card == null) {
            return;
        }
        fragmentPutCardBinding.TextInputLayoutCardDescription.getEditText().setText(card.description);
        fragmentPutCardBinding.textInputLayoutCardReference.getEditText().setText(card.reference);

        int spinnerSelectedPos = FALSE_OPTION_INDEX;
        if (card.cardAnswer.equals(CardAnswer.YES)) {
            spinnerSelectedPos = TRUE_OPTION_INDEX;
        }
        fragmentPutCardBinding.spinnerCardAnswer.setSelection(spinnerSelectedPos);
    }

    private void configDropdownUi() {
        //get the spinner from the xml.
        Spinner dropdown = fragmentPutCardBinding.spinnerCardAnswer;
        //create a list of items for the spinner.
        String[] items = getResources().getStringArray(R.array.card_answer_options);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }
    private boolean isFormValid() {
        EditText EditTextDescription = fragmentPutCardBinding.TextInputLayoutCardDescription.getEditText();
        String inputDescription = EditTextDescription.getText().toString();
        if (inputDescription.isEmpty()) {
            fragmentPutCardBinding.textViewCardDescription.setError("Nome do card não pode estar vazio");
            return false;
        }

        return true;
    }

    private final View.OnClickListener submitDeck = (View e) -> {
        if (isFormValid()) {
            Card card = new Card();
            card.deckOwnerId = deckId;
            card.description = fragmentPutCardBinding.TextInputLayoutCardDescription.getEditText().getText().toString();
            card.reference = fragmentPutCardBinding.textInputLayoutCardReference.getEditText().getText().toString();
            String cardSpinnerAnswer = fragmentPutCardBinding.spinnerCardAnswer.getSelectedItem().toString();
            String trueCardAnswerOption = getResources().getStringArray(R.array.card_answer_options)[TRUE_OPTION_INDEX];
            if (cardSpinnerAnswer.equals(trueCardAnswerOption)) {
                card.cardAnswer = CardAnswer.YES;
            } else {
                card.cardAnswer = CardAnswer.NO;
            }

            if(isCreationMode()) {
                cardViewModel.insert(card);
            } else {
                card.cardId = cardId;
                cardViewModel.update(card);
            }

            Snackbar.make(fragmentPutCardBinding.getRoot(), prepareInsertedMessage(), BaseTransientBottomBar.LENGTH_LONG).show();
            Navigation.findNavController(e).popBackStack();
        }
    };

    private boolean isCreationMode() {
        return cardId < 0;
    }

    private String prepareInsertedMessage() {
        if (isCreationMode()) {
            return "Card " +
                    "adicionado com sucesso!";
        }

        return  "Card" +
                "EDITADO com sucesso";
    }
}