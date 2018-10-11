package com.android.freak.bezierview.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.freak.bezierview.view.MagicCircle;
import com.android.freak.bezierview.R;

public class MagicCircleAct extends AppCompatActivity {
    private android.widget.Button btnstart;
    private MagicCircle circle3;
    public static void startAction(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, MagicCircleAct.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_circle);
                circle3 = (MagicCircle)findViewById(R.id.circle3);
        this.btnstart = (Button) findViewById(R.id.btn_start);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                circle3.startAnimation();
            }
        });
    }
}
