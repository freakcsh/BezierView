package com.android.freak.bezierview.drawable;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.freak.bezierview.R;

public class NinePatchDrawableAct extends AppCompatActivity {
    private ImageView image;
    public static void startAction(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, NinePatchDrawableAct.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_patch_drawable);
        image=findViewById(R.id.image);
        image.setBackgroundResource(R.drawable.nine_patch_drawable);
    }
}
