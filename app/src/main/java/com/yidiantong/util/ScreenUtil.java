package com.yidiantong.util;


import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
     * Created by Buzz on 2019/10/21.
     * Email :lmx2060918@126.com
     */
  public   class ScreenUtil {

        public static void makeStatusBarTransparent(AppCompatActivity activity, View container, final View content){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                }
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
            ViewCompat.setOnApplyWindowInsetsListener(container, new OnApplyWindowInsetsListener() {
                @Override
                public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    setMarginTop(content, windowInsetsCompat.getSystemWindowInsetTop());
                    return windowInsetsCompat.consumeSystemWindowInsets();
                }
            });
        }

        private static void setMarginTop(View view, int marginTop){
            ViewGroup.MarginLayoutParams menuLayoutParams =
                    (ViewGroup.MarginLayoutParams)view.getLayoutParams();
            menuLayoutParams.setMargins(0, marginTop, 0, 0);
            view.setLayoutParams(menuLayoutParams);
        }
}
