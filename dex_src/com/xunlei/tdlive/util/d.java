package com.xunlei.tdlive.util;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

// compiled from: DipAndPix.java
public class d {
    public static float a(Context context, float f) {
        return (context.getResources().getDisplayMetrics().density * f) + 0.5f;
    }

    public static float b(Context context, float f) {
        return (context.getResources().getDisplayMetrics().scaledDensity * f) + 0.5f;
    }

    public static Point a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point(0, 0);
        windowManager.getDefaultDisplay().getSize(point);
        return point;
    }
}
