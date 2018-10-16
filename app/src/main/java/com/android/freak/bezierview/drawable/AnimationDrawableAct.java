package com.android.freak.bezierview.drawable;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.freak.bezierview.R;

public class AnimationDrawableAct extends AppCompatActivity {
    public static void startAction(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AnimationDrawableAct.class);
        activity.startActivity(intent);
    }
    private ImageView img;
    private AnimationDrawable mAnimationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable);
        img=findViewById(R.id.img);
        mAnimationDrawable= (AnimationDrawable) img.getDrawable();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAnimationDrawable.start();
            }
        },300);
    }
}
