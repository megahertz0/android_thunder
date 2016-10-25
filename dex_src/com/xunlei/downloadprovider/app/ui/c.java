package com.xunlei.downloadprovider.app.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Method;

// compiled from: SystemBarTintManager.java
public final class c {
    private static String f;
    public boolean a;
    public boolean b;
    public View c;
    public View d;
    public View e;
    private final a g;
    private boolean h;

    static {
        if (VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class});
                declaredMethod.setAccessible(true);
                f = (String) declaredMethod.invoke(null, new Object[]{"qemu.hw.mainkeys"});
            } catch (Throwable th) {
                f = null;
            }
        }
    }

    @TargetApi(19)
    public c(Activity activity) {
        LayoutParams layoutParams;
        Window window = activity.getWindow();
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (VERSION.SDK_INT >= 19) {
            TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(new int[]{16843759, 16843760});
            this.a = obtainStyledAttributes.getBoolean(0, false);
            this.h = obtainStyledAttributes.getBoolean(1, false);
            obtainStyledAttributes.recycle();
            WindowManager.LayoutParams attributes = window.getAttributes();
            obtainStyledAttributes = attributes.flags & 67108864;
            if (obtainStyledAttributes != null) {
                this.a = true;
            }
            if ((attributes.flags & 134217728) != 0) {
                this.h = true;
            }
        }
        this.g = new a(activity, this.a, this.h, (byte) 0);
        if (!this.g.b) {
            this.h = false;
        }
        if (this.a) {
            this.c = new View(activity);
            layoutParams = new FrameLayout.LayoutParams(-1, this.g.a);
            layoutParams.gravity = 48;
            if (this.h && !this.g.a()) {
                layoutParams.rightMargin = this.g.d;
            }
            this.c.setLayoutParams(layoutParams);
            this.c.setBackgroundColor(-1728053248);
            this.c.setVisibility(XZBDevice.Wait);
            viewGroup.addView(this.c);
            if (activity instanceof DownloadCenterActivity) {
                this.d = new View(activity);
                layoutParams = new FrameLayout.LayoutParams(-1, this.g.a);
                layoutParams.gravity = 48;
                if (this.h && !this.g.a()) {
                    layoutParams.rightMargin = this.g.d;
                }
                this.d.setLayoutParams(layoutParams);
                this.d.setBackgroundResource(2131689505);
                this.d.setAlpha(0.7f);
                this.d.setVisibility(XZBDevice.Wait);
                viewGroup.addView(this.d);
            }
        }
        if (this.h) {
            this.e = new View(activity);
            if (this.g.a()) {
                layoutParams = new FrameLayout.LayoutParams(-1, this.g.c);
                layoutParams.gravity = 80;
            } else {
                layoutParams = new FrameLayout.LayoutParams(this.g.d, -1);
                layoutParams.gravity = 5;
            }
            this.e.setLayoutParams(layoutParams);
            this.e.setBackgroundColor(-1728053248);
            this.e.setVisibility(XZBDevice.Wait);
            viewGroup.addView(this.e);
        }
    }
}
