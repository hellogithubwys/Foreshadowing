package com.wys.styledemo2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wys.styledemo2.Skin.SkinUtils;

/**
 * Created by Foreshadowing on 2017/3/2.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SkinUtils.initTheme(this);
        super.onCreate(savedInstanceState);
    }
    public void startActivity(Context context, Class clazz){
        startActivity(new Intent(context,clazz));
    }
}
