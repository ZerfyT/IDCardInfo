package com.centrefx.idcardinfo;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class WarningDialog extends DialogFragment
{
    private final String message;

    public WarningDialog(String msg)
    {
        this.message = msg;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(requireContext());
        ad.setTitle("Error!");
        ad.setCancelable(true);
        ad.setMessage(message);
        ad.setPositiveButton("OK", (dialog, which) -> { });
        return ad.create();
    }
}
