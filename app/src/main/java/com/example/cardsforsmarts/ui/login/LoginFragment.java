package com.example.cardsforsmarts.ui.login;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.databinding.FragmentLoginBinding;
import com.example.cardsforsmarts.databinding.FragmentPutCardBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding fragmentPutCardBinding;
    private FirebaseAuth firebaseAuth;
    private String TAG = "LoginFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPutCardBinding = FragmentLoginBinding.inflate(inflater, container, false);

        fragmentPutCardBinding.textViewSignup.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        fragmentPutCardBinding.textViewSignup.setOnClickListener(view -> {
            NavHostFragment.findNavController(this).navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment());
        });

        fragmentPutCardBinding.buttonSubmitLogin.setOnClickListener(view -> {
            if(isFormValid()) {
                signIn(
                        fragmentPutCardBinding
                                .textInputLayoutAccount
                                .getEditText()
                                .getText()
                                .toString(),
                        fragmentPutCardBinding
                                .textInputLayoutPassword
                                .getEditText()
                                .getText()
                                .toString());

            }
        });
        return fragmentPutCardBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    private boolean isFormValid() {
        EditText editAccount = fragmentPutCardBinding.textInputLayoutAccount.getEditText();
        EditText editPassword = fragmentPutCardBinding.textInputLayoutPassword.getEditText();

        if (editPassword.getText().toString().isEmpty()) {
            editPassword.setError("Campo não pode estar vazio");
            return false;
        }

        if(editAccount.getText().toString().isEmpty()) {
            editAccount.setError("Campo não pode estar vazio");
            return false;
        }

        return true;
    }

    public void signIn(String email, String password) {
        Log.d(TAG, "E-mail do meliante " + email);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            NavHostFragment.findNavController(LoginFragment.this).popBackStack();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(fragmentPutCardBinding.getRoot().getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}