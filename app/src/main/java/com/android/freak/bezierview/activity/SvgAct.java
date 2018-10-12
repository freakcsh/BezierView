package com.android.freak.bezierview.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.freak.bezierview.R;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;

public class SvgAct extends AppCompatActivity {
    public static void startAction(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, SvgAct.class);
        activity.startActivity(intent);
    }
    private SVGImageView svg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        svg=findViewById(R.id.svg);
        svg.setImageAsset("7doodle.svg");

        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0f,1f);
        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatCount(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float progress = (Float) animation.getAnimatedValue();
                // Calculate the correct stroke-dashoffset at this point in the animation (ANIM_PATH_LENGTH -> 0)
                int   dashOffset = Math.round(5000 * (1 - progress));
                // Update the CSS of the SVGImageView. Will result in the SVG being updated.
                String css = String.format("#doodle { stroke-dasharray: %d %d; stroke-dashoffset: %d; }", 5000, 5000, dashOffset);
                svg.setCSS(css);
            }
        });
      valueAnimator.start();
    }
}
