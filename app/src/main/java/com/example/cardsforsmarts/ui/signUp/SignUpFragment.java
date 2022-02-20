package com.example.cardsforsmarts.ui.signUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFragment extends Fragment {
    FragmentSignUpBinding fragmentSignUpBinding;
    FirebaseAuth firebaseAuth;
    private final String TAG = "SINGUP_FRAMGENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSignUpBinding = FragmentSignUpBinding.inflate(inflater, container, false);
        fragmentSignUpBinding.buttonSubmitLogin.setOnClickListener(view -> {
            if(isFormValid()) {
                createAccount(
                        fragmentSignUpBinding
                                .textInputLayoutAccount
                                .getEditText()
                                .getText()
                                .toString(),
                        fragmentSignUpBinding
                                .textInputLayoutPassword
                                .getEditText()
                                .getText()
                                .toString());

            }
        });
        return fragmentSignUpBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private boolean isFormValid() {
        EditText editAccount = fragmentSignUpBinding.textInputLayoutAccount.getEditText();
        EditText editPassword = fragmentSignUpBinding.textInputLayoutPassword.getEditText();

        if (editPassword.getText().toString().isEmpty() || editPassword.getText().toString().length() < 6) {
            editPassword.setError("Campo não pode estar vazio ou não pode ser menor do que 6 digitos");
            return false;
        }

        if(editAccount.getText().toString().isEmpty()) {
            editAccount.setError("Campo não pode estar vazio");
            return false;
        }

        return true;
    }

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            onAccountCreted();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Snackbar.make(getView(), "Authentication failed.",
                                    BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END create_user_with_email]
    }

    private void onAccountCreted(){
        NavHostFragment.findNavController(this).popBackStack(R.id.splashFragment, false);
    };
}