package com.example.cardsforsmarts.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        String[] menuOptions = new String[]{"Decks", "Statistics"};
        ListView listView = binding.getRoot().findViewById(R.id.listView_menu);
        View root = binding.getRoot();

        ArrayAdapter<String> menuList = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, menuOptions);

        listView.setAdapter(menuList);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}