package com.wys.styledemo2.Skin;

import android.content.res.Resources;
import android.view.View;

/**
 *
 */
public interface ThemeUIInterface {

    public View getView();

    public void setTheme(Resources.Theme themeId);
}