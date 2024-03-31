package com.example.rr;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learn.R;
import com.example.rr.RateUsDialog;

public class RateUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rateus);

        // Show the dialog
        RateUsDialog rateUsDialog = new RateUsDialog(this);
        rateUsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        rateUsDialog.setCancelable(true);
        rateUsDialog.show();

        rateUsDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish(); // Finish the activity when the dialog is dismissed
            }
        });
    }
}
