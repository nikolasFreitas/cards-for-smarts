package com.example.cardsforsmarts;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.cardsforsmarts.databinding.FragmentSplashBinding;
import com.example.cardsforsmarts.ui.home.HomeFragmentDirections;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashFragment extends Fragment {
    private FragmentSplashBinding fragmentSplashBinding;
    private FirebaseAuth firebaseAuth;
    private final int LOGIN_DELAY_MILLISECONDS = 4200;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false);
        animateLogo();
        new Handler().postDelayed(() -> {
            signIn();
        }, LOGIN_DELAY_MILLISECONDS);

        // Inflate the layout for this fragment
        return fragmentSplashBinding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.secondary_dark, getActivity().getTheme()));
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark, getActivity().getTheme()));
    }

    private void signIn() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        loginUpdate(currentUser);
    }

    private void loginUpdate(FirebaseUser currentUser) {
        NavDirections action;
        if (currentUser == null) {
            action = SplashFragmentDirections.actionSplashFragmentToLoginFragment();
            Navigation.findNavController(getView()).navigate(action);
        } else {
            action = SplashFragmentDirections.actionSplashFragmentToNavHome();
            Navigation.findNavController(getView()).navigate(action);
        }

    }

    private void animateLogo() {
        Animation imgAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.pulse_animation);
        fragmentSplashBinding
                .imageViewLogo
                .startAnimation(imgAnimation);
    }
}