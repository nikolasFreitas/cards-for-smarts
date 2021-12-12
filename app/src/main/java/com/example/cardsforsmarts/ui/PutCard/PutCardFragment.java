package com.example.cardsforsmarts.ui.PutCard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.databinding.FragmentPutCardBinding;


public class PutCardFragment extends Fragment {
    private FragmentPutCardBinding fragmentPutCardBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPutCardBinding = FragmentPutCardBinding.inflate(inflater, container, false);
        return inflater.inflate(R.layout.fragment_put_card, container, false);
    }
}