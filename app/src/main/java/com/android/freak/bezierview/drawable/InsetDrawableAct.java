package com.android.freak.bezierview.drawable;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.freak.bezierview.R;

public class InsetDrawableAct extends AppCompatActivity {
    public static void startAction(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, InsetDrawableAct.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inset_drawable);
    }
}
