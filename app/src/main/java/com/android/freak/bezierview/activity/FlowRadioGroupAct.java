package com.android.freak.bezierview.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.freak.bezierview.R;
import com.android.freak.bezierview.widget.FlowRadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义可换行radioGroup
 */
public class FlowRadioGroupAct extends AppCompatActivity {
    private List mes;
    private FlowRadioGroup radio_group;

    public static void startAction(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, FlowRadioGroupAct.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_radio_group);
        radio_group = findViewById(R.id.radio_group);
        mes=new ArrayList();
        mes.add("一");
        mes.add("一");
        mes.add("一就是那几款是");
        mes.add("一三借款方");
        mes.add("一你沙发");
        mes.add("一卖家刊");
        mes.add("一");
        mes.add("一南石道街");
        mes.add("一");
        mes.add("一是");
        mes.add("一收到");
        mes.add("一");
        mes.add("一");
        mes.add("一");
        initRadioGroupType();
    }

    private void initRadioGroupType() {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 55, getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics());
        int marginLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
        int marginBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        int margintop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(width, height);//在此设置子view的宽和高，也可设置为wrap_content，当设置为wrap_content时，就成了可换行的不规则的radioGroup
        for (int i = 0; i < mes.size(); i++) {
            if (i % 4 == 0) {
                layoutParams.setMargins(0, 0, 0, marginBottom);
            } else {
                layoutParams.setMargins(marginLeft, margintop, 0, marginBottom);
            }
            RadioButton radioButton = (RadioButton) getLayoutInflater().inflate(R.layout.layout_radio_button_merchants_type, null);
            radioButton.setId(i);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setText(mes.get(i).toString());
            radioButton.setBackgroundResource(R.drawable.bg_radio_button_wisdom_type);
            radio_group.addView(radioButton);
        }
    }
}
