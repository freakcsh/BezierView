package com.android.freak.bezierview.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.freak.bezierview.R;
import com.android.freak.bezierview.view.TestView;

/**
 * 属性动画
 */
public class ObjectAnimatorAct extends AppCompatActivity {
    private TextView test;

    public static void startAction(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ObjectAnimatorAct.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        test = findViewById(R.id.test);
    }

    public void alpha(View view) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(test, "alpha", 1f, 0f, 1f);
        alpha.setDuration(5000);
        alpha.start();
    }

    public void rotation(View view) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(test, "rotation", 0f, 360f);
        alpha.setDuration(5000);
        alpha.start();
    }

    public void translationX(View view) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(test, "translationX", test.getTranslationX(), -500, test.getTranslationX());
        alpha.setDuration(5000);
        alpha.start();
    }

    public void scaleY(View view) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(test, "scaleY", 1f, 3f, 1f);
        alpha.setDuration(5000);
        alpha.start();
    }

    public void zuHeAnimation(View view) {

        ObjectAnimator moveIn = ObjectAnimator.ofFloat(test, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(test, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(test, "alpha", 1f, 0f, 1f);
        AnimatorSet animatorSet=new AnimatorSet();

        animatorSet.play(rotate).with(fadeInOut).after(moveIn);

        animatorSet.setDuration(5000);
        animatorSet.start();
    }

    public void zuHeXMLAnimation(View view) {
        @SuppressLint("ResourceType") Animator animator= AnimatorInflater.loadAnimator(this,R.anim.anim_file);
        animator.setTarget(test);
        animator.start();
    }
}
