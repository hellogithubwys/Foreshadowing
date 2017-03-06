package com.wys.styledemo2.Skin;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wys.styledemo2.R;

import static com.wys.styledemo2.Skin.Config.DAYTHEME;
import static com.wys.styledemo2.Skin.Config.NIGHTTHEME;

/**
 * Created by Foreshadowing on 2017/3/2.
 */

public class SkinUtils {

    private static SharedPreferences sp;

    public static void initTheme(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences(Config.APPSKIN, Context.MODE_PRIVATE);
        int appSkin = sp.getInt(Config.APPTHEMETYPE, DAYTHEME);
        if (appSkin == DAYTHEME) {
            context.setTheme(R.style.dayTheme);
        } else if (appSkin == NIGHTTHEME) {
            context.setTheme(R.style.nightTheme);
        } else {
//            否则是第三方主題，加載我們主題apk里的資源
            int resourceId = SkinUtils.getResourceId(SkinUtils.getPackageContext(
                    context, "com.wys.skin_1"), "style", "AppTheme");
            if (resourceId != -1) {
                context.setTheme(resourceId);
            }
            Toast.makeText(context, "资源id为:" + resourceId, Toast.LENGTH_SHORT).show();
        }
    }

    public static void setSkinJar(Context context, View ContentView) {
        Context packageContext = SkinUtils.getPackageContext(
                context, "com.wys.skin_1");
        int resourceId = SkinUtils.getResourceId(packageContext, "style", "AppTheme");
        if (resourceId != -1) {
            context.setTheme(resourceId);
        }
        Toast.makeText(context, "资源id为:" + resourceId, Toast.LENGTH_SHORT).show();
        changeTheme(ContentView, context.getTheme());
        saveSkinType(context,Config.SKINJAR);
    }

    public static void setNgithTheme(Context context, View ContentView) {
        context.setTheme(R.style.nightTheme);
        changeTheme(ContentView, context.getTheme());
        saveSkinType(context,Config.NIGHTTHEME);
    }

    public static void setDayTheme(Context context, View ContentView) {
        context.setTheme(R.style.dayTheme);
        changeTheme(ContentView, context.getTheme());
        saveSkinType(context,Config.DAYTHEME);
    }

    public static void saveSkinType(Context context,int type){
        if (sp == null)
            sp = context.getSharedPreferences(Config.APPSKIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(Config.APPTHEMETYPE, type);
        edit.commit();
    }


    //获取对应包名APP的Context
    public static Context getPackageContext(Context context, String packageName) {
        try {
            return context.createPackageContext(packageName, Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取指定context的 type里面的 name属性
    public static int getResourceId(Context context, String type, String name) {
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

    /**
     * 切换应用主题
     *
     * @param rootView
     */
    public static void changeTheme(View rootView, Resources.Theme theme) {
        //這裡邏輯很簡單 就是递归调用changeTheme-----递归调用setTheme了。
        //注意 你们如果是listview也包含在里面的话 listview自定义实现接口的时候要稍微复杂一些，看你们需要不需要也刷新listview里的item了
        //这里为了简单 我就不写那么复杂了，就这一个逻辑：先set自己的theme 然后遍历自己的子控件 逐一set

        if (rootView instanceof ViewGroup) {
            int count = ((ViewGroup) rootView).getChildCount();
            for (int i = 0; i < count; i++) {
                changeTheme(((ViewGroup) rootView).getChildAt(i), theme);
            }
        }
        if (rootView instanceof ThemeUIInterface) {
            ((ThemeUIInterface) rootView).setTheme(theme);
        }
    }

}
