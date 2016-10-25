package com.baidu.mobads.interfaces.utils;

import android.content.Context;
import android.view.View;

public interface IXAdViewUtils {
    public static final int SHOW_STATE_LOWER_THAN_MIN_SHOWPERCENT = 3;
    public static final int SHOW_STATE_NOT_ENOUGH_BIG = 6;
    public static final int SHOW_STATE_NOT_VISIBLE = 1;
    public static final int SHOW_STATE_SCREEN_OFF = 4;
    public static final int SHOW_STATE_SHOW = 0;

    int getViewState(View view);

    int getVisiblePercent(View view, Context context);

    boolean isAdViewOutsideScreen(View view);

    boolean isAdViewShown(View view);

    boolean isAdViewTooSmall(View view);

    boolean isScreenOn(Context context);

    boolean isVisible(View view, int i);
}
