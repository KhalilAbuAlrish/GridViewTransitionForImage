package com.example.gridviewtransitionforimage.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gridviewtransitionforimage.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    public static final int DURATION=800;
    ColorDrawable colorDrawable;

    FrameLayout frameLayout;
    TextView textViewTitle;
    ImageView imageViewPic;

    int mTopThumbnail;
    int mLeftThumbnail;
    int mHeightThumbnail;
    int mWidthThumbnail;

    int mTop;
    int mLeft;

    float mWidthScale;
    float mHeightScale;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        frameLayout=findViewById(R.id.frame_layout);
        textViewTitle=findViewById(R.id.title_details_activity);
        imageViewPic=findViewById(R.id.image_details_activity);

        Bundle bundle=getIntent().getExtras();

        mTopThumbnail=bundle.getInt("top");
        mLeftThumbnail=bundle.getInt("left");
        mHeightThumbnail=bundle.getInt("height");
        mWidthThumbnail=bundle.getInt("width");

        String title=bundle.getString("title");
        String image=bundle.getString("image");

        textViewTitle.setText(title);
        Picasso.with(this).load(image).into(imageViewPic);

        colorDrawable=new ColorDrawable(Color.BLACK);
        frameLayout.setBackground(colorDrawable);


        if(savedInstanceState == null){

            ViewTreeObserver viewTreeObserver=imageViewPic.getViewTreeObserver();
            viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    imageViewPic.getViewTreeObserver().removeOnPreDrawListener(this);

                    int[]screenLocation=new int[2];
                    imageViewPic.getLocationOnScreen(screenLocation);

                    mLeft=mLeftThumbnail-screenLocation[0];
                    mTop=mTopThumbnail-screenLocation[1];
                    mWidthScale=(float) mWidthThumbnail/imageViewPic.getWidth();
                    mHeightScale=(float) mHeightThumbnail/imageViewPic.getHeight();

                    enterAnimation();



                    return false;
                }
            });


        }





    }


    public void enterAnimation(){

        imageViewPic.setPivotX(0);
        imageViewPic.setPivotY(0);
        imageViewPic.setScaleX(mWidthScale);
        imageViewPic.setScaleY(mHeightScale);
        imageViewPic.setTranslationX(mLeft);
        imageViewPic.setTranslationY(mTop);

        TimeInterpolator sDecelerator=new DecelerateInterpolator();
        imageViewPic.animate().setDuration(DURATION).scaleX(1).scaleY(1)
                .translationX(0).translationY(0).setInterpolator(sDecelerator);

        ObjectAnimator objectAnimator=ObjectAnimator.ofInt(colorDrawable,"alpha",0,255);
        objectAnimator.setDuration(DURATION);
        objectAnimator.start();

    }



    public void exitAnimation(final Runnable endAction){

        TimeInterpolator sAccelerate=new AccelerateInterpolator();
        imageViewPic.animate().setDuration(DURATION).scaleX(mWidthScale).scaleY(mHeightScale)
                .translationX(mLeft).translationY(mTop)
                .setInterpolator(sAccelerate).withEndAction(endAction);

        ObjectAnimator objectAnimator=ObjectAnimator.ofInt(colorDrawable,"alpha",0);
        objectAnimator.setDuration(DURATION);
        objectAnimator.start();




    }

    @Override
    public void onBackPressed() {

        exitAnimation(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });

    }
}