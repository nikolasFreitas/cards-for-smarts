package com.example.cardsforsmarts.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        String[] menuOptions = new String[]{"Decks", "Statistics"};
        View root = binding.getRoot();

        ListView listView = root.findViewById(R.id.listView_menu);
        navController = NavHostFragment.findNavController(this);

        ArrayAdapter<String> menuList = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, menuOptions);
        listView.setAdapter(menuList);

        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            String menuItem = (String) parent.getItemAtPosition(position);
            if (menuItem.equals("Decks")) {
                navController.navigate(R.id.action_nav_home_to_deck);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}