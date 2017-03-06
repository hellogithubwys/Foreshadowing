package com.wys.styledemo2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wys.styledemo2.Skin.SkinUtils;
import com.wys.styledemo2.adapter.ViewPagerAdapter;
import com.wys.styledemo2.fragment.HomeFragment;
import com.wys.styledemo2.fragment.NewsFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private View ContentView;
    private View day;
    private View night;
    private View skinJar;
    private View jump;
    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ContentView = findViewById(R.id.activity_main);

        initView();
        initFragment();

        initListener();
    }

    private void initFragment() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        homeFragment = new HomeFragment();
        newsFragment = new NewsFragment();
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(homeFragment);
        fragments.add(newsFragment);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(viewPagerAdapter);
        viewpager.setCurrentItem(0);

    }

    private void initView() {
        day = findViewById(R.id.text);
        night = findViewById(R.id.text1);
        skinJar = findViewById(R.id.text2);
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
        switch (v.getId()) {
            case R.id.text:
                SkinUtils.setDayTheme(mContext, ContentView);
                break;
            case R.id.text1:
                SkinUtils.setNgithTheme(mContext, ContentView);
                break;
            case R.id.text2:
                SkinUtils.setSkinJar(mContext, ContentView);
                break;
            case R.id.text3:
                startActivity(this, MainActivity1.class);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(this, MainActivity1.class);
        super.onBackPressed();
    }

    public void startActivity(Context context, Class clazz) {
        startActivity(new Intent(context, clazz));
    }
}
