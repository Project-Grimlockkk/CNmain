package com.example.rr;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learn.R;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        ImageView menuIcon = findViewById(R.id.menuIcon);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

        Button getStartedButton = findViewById(R.id.getStartedButton);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity
                startActivity(new Intent(welcome.this, MainActivity.class));
                finish(); // Optional: Finish the current activity
            }
        });
    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_location, popupMenu.getMenu());



        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.option1) {
                    // Handle option 1 click
                    // Replace this comment with your desired action
                    return true;
                } else if (id == R.id.option2) {
                    // Handle option 2 click
                    // Replace this comment with your desired action
                    return true;
                } else if (id == R.id.option3) {
                    // Handle option 3 click
                    // Replace this comment with your desired action
                    return true;
                } else if (id == R.id.option4) {
                    // Handle option 4 click
                    // Replace this comment with your desired action
                    return true;
                } else if (id == R.id.option5) {
                    // Handle option 5 click
                    // Replace this comment with your desired action
                    return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
