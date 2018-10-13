package com.android.freak.bezierview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.freak.bezierview.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sinOnclick(View view) {
        SinWaveViewAct.startAction(this);
    }

    public void heartOnclick(View view) {
        HeartViewAct.startAction(this);
    }

    public void BezierOnclick(View view) {
        BezierViewAct.startAction(this);
    }

    public void cosOnclick(View view) {

    }

    public void testOnclick(View view) {
        TestViewAct.startAction(this);
    }

    public void bitmapOnclick(View view) {
        BitmapViewAct.startAction(this);
    }

    public void pathOnclick(View view) {
        PathOperationAct.startAction(this);
    }

    public void pathMeasureViewOnclick(View view) {
        PathMeasureViewAct.startAction(this);
    }

    public void svgOnclick(View view) {
        SvgAct.startAction(this);
    }

    public void FlowRadioGroupOnclick(View view) {
        FlowRadioGroupAct.startAction(this);
    }
}
