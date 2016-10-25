package com.inmobi.commons.core.utilities.info;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;
import com.inmobi.commons.a.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyAgent;

public class DisplayInfo {

    public enum ORIENTATION_VALUES {
        PORTRAIT(1),
        REVERSE_PORTRAIT(2),
        LANDSCAPE(3),
        REVERSE_LANDSCAPE(4);
        private int a;

        private ORIENTATION_VALUES(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }
    }

    private static String d() {
        c a = a();
        return a.b() + "X" + a.a();
    }

    public static int a(int i) {
        return Math.round(a().c() * ((float) i));
    }

    private static String e() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) a.b().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        return i + "x" + displayMetrics.heightPixels;
    }

    public static c a() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) a.b().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.density;
        return new c(Math.round(((float) displayMetrics.widthPixels) / f), Math.round(((float) displayMetrics.heightPixels) / f), f);
    }

    public static int b(int i) {
        return Math.round(((float) i) / a().c());
    }

    public static int b() {
        Context b = a.b();
        int rotation = ((WindowManager) b.getSystemService("window")).getDefaultDisplay().getRotation();
        switch (b.getResources().getConfiguration().orientation) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return (rotation == 1 || rotation == 2) ? ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue() : ORIENTATION_VALUES.PORTRAIT.getValue();
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return (rotation == 0 || rotation == 1) ? ORIENTATION_VALUES.LANDSCAPE.getValue() : ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue();
            default:
                return ORIENTATION_VALUES.PORTRAIT.getValue();
        }
    }

    private static float f() {
        return new TextView(a.b()).getTextSize();
    }

    public static Map<String, String> c() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("d-device-screen-density", String.valueOf(a().c()));
        hashMap.put("d-device-screen-size", d());
        hashMap.put("d-density-dependent-screen-size", e());
        hashMap.put("d-orientation", String.valueOf(b()));
        hashMap.put("d-textsize", String.valueOf(f()));
        return hashMap;
    }
}
