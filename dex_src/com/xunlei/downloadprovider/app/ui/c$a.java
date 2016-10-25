package com.xunlei.downloadprovider.app.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import com.xunlei.analytics.b.c;

// compiled from: SystemBarTintManager.java
public class c$a {
    final int a;
    final boolean b;
    final int c;
    final int d;
    private final boolean e;
    private final boolean f;
    private final int g;
    private final boolean h;
    private final float i;

    private c$a(Activity activity, boolean z, boolean z2) {
        boolean z3;
        int complexToDimensionPixelSize;
        boolean z4 = true;
        Resources resources = activity.getResources();
        if (resources.getConfiguration().orientation == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.h = z3;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (VERSION.SDK_INT >= 16) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        this.i = Math.min(((float) displayMetrics.widthPixels) / displayMetrics.density, ((float) displayMetrics.heightPixels) / displayMetrics.density);
        this.a = a(resources, "status_bar_height");
        if (VERSION.SDK_INT >= 14) {
            TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(16843499, typedValue, true);
            complexToDimensionPixelSize = TypedValue.complexToDimensionPixelSize(typedValue.data, activity.getResources().getDisplayMetrics());
        } else {
            complexToDimensionPixelSize = 0;
        }
        this.g = complexToDimensionPixelSize;
        resources = activity.getResources();
        if (VERSION.SDK_INT < 14 || !a(activity)) {
            complexToDimensionPixelSize = 0;
        } else {
            String str;
            if (this.h) {
                str = "navigation_bar_height";
            } else {
                str = "navigation_bar_height_landscape";
            }
            complexToDimensionPixelSize = a(resources, str);
        }
        this.c = complexToDimensionPixelSize;
        Resources resources2 = activity.getResources();
        if (VERSION.SDK_INT < 14 || !a(activity)) {
            complexToDimensionPixelSize = 0;
        } else {
            complexToDimensionPixelSize = a(resources2, "navigation_bar_width");
        }
        this.d = complexToDimensionPixelSize;
        if (this.c <= 0) {
            z4 = false;
        }
        this.b = z4;
        this.e = z;
        this.f = z2;
    }

    @TargetApi(14)
    private static boolean a(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (identifier == 0) {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        } else {
            boolean z = resources.getBoolean(identifier);
            if (c.f.equals(c.a())) {
                return false;
            }
            return "0".equals(c.a()) ? true : z;
        }
    }

    private static int a(Resources resources, String str) {
        int identifier = resources.getIdentifier(str, "dimen", "android");
        return identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0;
    }

    public final boolean a() {
        return this.i >= 600.0f || this.h;
    }
}
