package com.example.rateus;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class RateUsDialog extends Dialog {

    private float userRate = 0;

    public RateUsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rateus);

        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        final ImageView ratingImage = findViewById(R.id.ratingImage);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                if (rating <= 1) {
                    ratingImage.setImageResource(R.drawable.onestar);
                } else if (rating <= 2) {
                    ratingImage.setImageResource(R.drawable.twostar);
                } else if (rating <= 3) {
                    ratingImage.setImageResource(R.drawable.threestar);
                } else if (rating <= 4) {
                    ratingImage.setImageResource(R.drawable.fourstar);
                } else if (rating <= 5) {
                    ratingImage.setImageResource(R.drawable.fivestar);
                }

                animateImage(ratingImage);

                userRate = rating;
            }
        });
    }

    private void animateImage(ImageView ratingImage) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingImage.setAnimation(scaleAnimation);
    }
}
