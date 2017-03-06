package com.wys.styledemo2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.wys.styledemo2.Skin.SkinUtils;

public class MainActivity1 extends BaseActivity implements View.OnClickListener{

    private Context mContext;

    private View ContentView;
    private View day;
    private View night;
    private View skinJar;
    private View jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ContentView = findViewById(R.id.activity_main);
        initView();
        initListener();
    }
    private void initView() {
        day = findViewById(R.id.text);
        night = findViewById(R.id.text1);
        skinJar = findViewById(R.id.text2);
//
        jump = findViewById(R.id.text3);

    }
    private void initListener() {
        day.setOnClickListener(this);
        night.setOnClickListener(this);
        skinJar.setOnClickListener(this);
        jump.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text:
                SkinUtils.setDayTheme(mContext,ContentView);
                break;
            case R.id.text1:
                SkinUtils.setNgithTheme(mContext,ContentView);
                break;
            case R.id.text2:
                SkinUtils.setSkinJar(mContext,ContentView);
                break;
            case R.id.text3:
                startActivity(this,MainActivity.class);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(this,MainActivity.class);
        super.onBackPressed();
    }

}
