package com.android.freak.bezierview.drawable;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.freak.bezierview.R;

public class TransitionDrawableAct extends AppCompatActivity {
    public static void startAction(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, TransitionDrawableAct.class);
        activity.startActivity(intent);
    }
    private ImageView img;
    private TransitionDrawable mTransitionDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_drawable);
        img=findViewById(R.id.img);
        mTransitionDrawable= (TransitionDrawable) img.getDrawable();
        mTransitionDrawable.startTransition(3000);
    }
}
