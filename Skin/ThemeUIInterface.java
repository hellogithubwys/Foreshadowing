package com.wys.styledemo2.Skin;

import android.content.res.Resources;
import android.view.View;

/**
 *  自定义控件 的接口 用于切换皮肤
 */
public interface ThemeUIInterface {

    public View getView();

    public void setTheme(Resources.Theme themeId);


}