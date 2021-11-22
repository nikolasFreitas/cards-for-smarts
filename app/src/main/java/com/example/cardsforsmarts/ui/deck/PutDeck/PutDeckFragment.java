package com.example.cardsforsmarts.ui.deck.PutDeck;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.databinding.FragmentPutDeckBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PutDeckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PutDeckFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PutDeckFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddDeckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PutDeckFragment newInstance(String param1, String param2) {
        PutDeckFragment fragment = new PutDeckFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPutDeckBinding binding = FragmentPutDeckBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        configToolbarTitle();
        binding
                .getRoot()
                .findViewById(R.id.button_submit_deck)
                .setOnClickListener(submitDeck);

        return binding.getRoot();
    }

    private void configToolbarTitle() {
        String titleName;
        if (getArguments() == null) {
            titleName = getString(R.string.toolBar_add_deck);
        } else {
            titleName = getString(R.string.toolBar_edit_deck);
        }

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(titleName);
    }

    private boolean isFormValid() {
        EditText editDeckName = ((TextInputLayout) getView().findViewById(R.id.textInputLayout_deck_name)).getEditText();

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