package com.android.freak.bezierview.animation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.freak.bezierview.R;


public class BaseAnimationAct extends AppCompatActivity {
    public static void startAction(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, BaseAnimationAct.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_animation);

    }

    public void bjOnclick(View view) {
        BJAnimationAct.startAction(this);
    }

    public void ObjectAnimator(View view) {
        ObjectAnimatorAct.startAction(this);
    }
}
