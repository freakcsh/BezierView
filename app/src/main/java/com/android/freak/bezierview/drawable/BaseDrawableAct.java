package com.android.freak.bezierview.drawable;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.freak.bezierview.R;
import com.android.freak.bezierview.activity.TestViewAct;

public class BaseDrawableAct extends AppCompatActivity {
    public static void startAction(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, BaseDrawableAct.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_draeable);
    }

    public void ninePatchDrawableActOnclick(View view) {
        NinePatchDrawableAct.startAction(this);
    }

    public void InsetDrawableAOnclick(View view) {
        InsetDrawableAct.startAction(this);
    }

    public void ClipDrawableOnclick(View view) {
        ClipDrawableAct.startAction(this);
    }

    public void AnimationDrawableOnclick(View view) {
        AnimationDrawableAct.startAction(this);
    }

    public void LayerDrawableOnclick(View view) {
        LayerDrawableAct.startAction(this);
    }

    public void TransitionDrawableOnclick(View view) {
        TransitionDrawableAct.startAction(this);
    }
}
