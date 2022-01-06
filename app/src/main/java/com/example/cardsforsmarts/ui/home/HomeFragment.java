package com.example.cardsforsmarts.ui.home;

import android.content.Intent;
import android.net.Uri;
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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NavController navController;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        String[] menuOptions = new String[]{getString(R.string.menu_deck), getString(R.string.menu_statistics), getString(R.string.about_flash_cards_intent)};
        View root = binding.getRoot();

        ListView listView = root.findViewById(R.id.listView_menu);
        navController = NavHostFragment.findNavController(this);

        ArrayAdapter<String> menuList = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, menuOptions);
        listView.setAdapter(menuList);

        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            String menuItem = (String) parent.getItemAtPosition(position);
            if (menuItem.equals(getString(R.string.menu_deck))) {
                navController.navigate(R.id.action_nav_home_to_deck);
            } else if (menuItem.equals(getString(R.string.menu_statistics))) {
                Snackbar.make(view, "Ainda não impolemenrtado", BaseTransientBottomBar.LENGTH_LONG).show();
            } else if(menuItem.equals(getString(R.string.about_flash_cards_intent))) {
                Uri flashCardsUri = Uri.parse("https://usm.maine.edu/agile/using-flashcards");
                Intent intent = new Intent(Intent.ACTION_VIEW, flashCardsUri);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        loginUpdate(currentUser);
    }

    private void loginUpdate(FirebaseUser currentUser) {
        if (currentUser == null) {
            NavDirections action = HomeFragmentDirections.actionNavHomeToLoginFragment();
            NavHostFragment.findNavController(this).navigate(action);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}