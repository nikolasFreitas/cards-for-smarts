package com.example.cardsforsmarts.ui.dialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.cardsforsmarts.R;
import com.example.cardsforsmarts.databinding.AdapterDialogIfrsBinding;

public class AppAboutDialogFragment extends DialogFragment {
    public static String TAG = "AppAboutDialogFragment";
    private static boolean hasShowedTheImageClickDialog = false;
    private AdapterDialogIfrsBinding adapterDialogIfrsBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adapterDialogIfrsBinding = AdapterDialogIfrsBinding.inflate(inflater, container, false);
        imageUiConfig();

        adapterDialogIfrsBinding.buttonDismiss.setOnClickListener(v -> {
            if (Boolean.FALSE.equals(hasShowedTheImageClickDialog)) {
                hasShowedTheImageClickDialog = true;
                new AlertDialog
                        .Builder(adapterDialogIfrsBinding.getRoot().getContext())
                        .setMessage("Já clicou na imagem pra ver o que ocorre? :D")
                        .setNeutralButton("Ok", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            } else {
                dismiss();
            }
        });
        return adapterDialogIfrsBinding.getRoot();
    }

    private void imageUiConfig() {
        adapterDialogIfrsBinding.imageViewIfrsLogo.setOnClickListener(onImgClick);
        Animation imgAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.shake_animation);
        new Handler().postDelayed(() -> {
            adapterDialogIfrsBinding
                    .imageViewIfrsLogo
                    .startAnimation(imgAnimation);

        }, 500);
    }

    private final View.OnClickListener onImgClick = v -> {
        hasShowedTheImageClickDialog = true;
        Uri flashCardsUri = Uri.parse("https://www.poalab.net.br/");
        Intent intent = new Intent(Intent.ACTION_VIEW, flashCardsUri);
        startActivity(intent);
    };
}
