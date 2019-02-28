package com.rayt.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageButton;

import com.rayt.myapplication.databinding.ShiftSwapLayoutBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ShiftSwapActivity extends AppCompatActivity {

    ShiftModel shiftModel;
    private ObjectAnimator shiftWorkerCardAnimation;
    private ObjectAnimator userCardAnimation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getExtras() != null) {
            shiftModel = getIntent().getExtras().getParcelable("SHIFT_MODEL");
        }
        ShiftSwapLayoutBinding shiftSwapLayoutBinding = DataBindingUtil.setContentView(this,R.layout.shift_swap_layout);
        shiftSwapLayoutBinding.setShiftModel(shiftModel);
    }


    public void switchShifts(View v){
        AnimatorSet animationSet = new AnimatorSet();
        View userCard = findViewById(R.id.user_card);
        View shiftWorkerCard = findViewById(R.id.current_shift_worker_card);

        shiftWorkerCardAnimation = ObjectAnimator.ofFloat(shiftWorkerCard, "y", userCard.getY());
        userCardAnimation = ObjectAnimator.ofFloat(userCard, "y", shiftWorkerCard.getY());

        if(userCard.getY() < shiftWorkerCard.getY()){
            shiftWorkerCardAnimation = ObjectAnimator.ofFloat(shiftWorkerCard, "y", userCard.getY() );
            userCardAnimation = ObjectAnimator.ofFloat(userCard, "y",  shiftWorkerCard.getY());
        }
        userCardAnimation.setDuration(500);
        shiftWorkerCardAnimation.setDuration(500);
        animationSet.playTogether(shiftWorkerCardAnimation, userCardAnimation);

       v.animate().rotation(v.getRotation()-180).setDuration(500).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                v.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.setEnabled(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).withStartAction(new Runnable() {
           @Override
           public void run() {
               animationSet.start();
           }
       }).start();
    }



}
