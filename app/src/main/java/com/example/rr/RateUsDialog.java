package com.example.rateusdialog;

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
    private float userRate=0;
    public RateUsDialog(@NonNull Context context) {

        super(context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate);
        final AppCompatButton ratenowbtn=findViewById(R.id.ratenowbtn);
        final AppCompatButton ratelater=findViewById(R.id.ratelater);
        final RatingBar ratingBar=findViewById(R.id.ratingBar);
        final ImageView ratingimage=findViewById(R.id.ratingimage);

        ratenowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ratelater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating<=1){
                    ratingimage.setImageResource(R.drawable.onestar);
                }
                else if(rating<=2){
                    ratingimage.setImageResource(R.drawable.twostar);
                }
                else if(rating<=3){
                    ratingimage.setImageResource(R.drawable.threestar);
                }
                else if(rating<=4){
                    ratingimage.setImageResource(R.drawable.fourstar);
                }
                else if(rating<=5){
                    ratingimage.setImageResource(R.drawable.fivestar);
                }
                animateImage(ratingimage);

                userRate=rating;
            }
        });

    }

    private void animateImage(ImageView ratingimage)
    {
        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1f,0,1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingimage.startAnimation(scaleAnimation);
    }
}
