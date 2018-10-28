package com.example.lucifesar.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.example.lucifesar.myapplication.view.FancyFlipView;

public class Lesson8Activity extends Activity {
    AnimatorSet animatorSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_8);
        FancyFlipView view = (FancyFlipView) findViewById(R.id.view);


        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 45);
        bottomFlipAnimator.setDuration(1500);

        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 270);
        flipRotationAnimator.setDuration(1500);

        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", - 45);
        topFlipAnimator.setDuration(1500);

        animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator);
        animatorSet.setStartDelay(1000);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        animatorSet.start();
        return super.onTouchEvent(event);
    }
}
