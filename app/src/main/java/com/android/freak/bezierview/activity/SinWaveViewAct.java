package com.android.freak.bezierview.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.freak.bezierview.R;
import com.android.freak.bezierview.view.SinWaveView;

public class SinWaveViewAct extends AppCompatActivity {
    private SinWaveView waveViewByBezier;
    public static void startAction(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, SinWaveViewAct.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_wave_view);
        waveViewByBezier = (SinWaveView) findViewById(R.id.wave_bezier);

        waveViewByBezier.startAnimation();
    }
    @Override
    protected void onPause() {
        super.onPause();
        waveViewByBezier.pauseAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        waveViewByBezier.resumeAnimation();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        waveViewByBezier.stopAnimation();
    }
}
