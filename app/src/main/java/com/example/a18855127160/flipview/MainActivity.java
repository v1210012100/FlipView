package com.example.a18855127160.flipview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FrameLayout fontLayout;
    FrameLayout backLayot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fontLayout=(FrameLayout)findViewById(R.id.front);
        backLayot=(FrameLayout)findViewById(R.id.back);
        fontLayout.setOnClickListener(this);
        backLayot.setOnClickListener(this);
        init();
    }

    //设置setCameraDistance ，影响到rotateY 是Y 轴的长度。
    public void init(){
        float scale = getResources().getDisplayMetrics().density * 10000;
        fontLayout.setCameraDistance(scale);
        backLayot.setCameraDistance(scale);

    }

    //对两个Layout 作属性动画，font 布局旋转90度时，back 布局从-90度开始旋转到0度。注意设置旋转动画时的可点击性。
    public void doHideAndShowAnimation(final View fontView, final View backView){
        fontView.animate().withStartAction(new Runnable() {
            @Override
            public void run() {
                fontView.setClickable(false);
                backView.setClickable(false);
                fontView.setRotationY(0);
                fontView.setAlpha(1);
                backView.setAlpha(0);
                backView.setRotationY(-90);
            }
        }).rotationY(90).withEndAction(new Runnable() {
            @Override
            public void run() {
                fontView.setAlpha(0);
                backView.setAlpha(1);
                backView.animate().rotationY(0).setDuration(1000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        backView.setClickable(true);
                    }
                }).start();
            }
        }).setDuration(1000).start();

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.front)
            doHideAndShowAnimation(fontLayout,backLayot);
        else doHideAndShowAnimation(backLayot,fontLayout);
    }
}
