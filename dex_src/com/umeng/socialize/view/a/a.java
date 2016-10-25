package com.umeng.socialize.view.a;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;

// compiled from: ACProgressBaseDialog.java
public abstract class a extends Dialog {
    protected a(Context context) {
        super(context);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    protected int a(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (VERSION.SDK_INT < 13) {
            return Math.min(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        }
        Point point = new Point();
        defaultDisplay.getSize(point);
        return Math.min(point.x, point.y);
    }
}
