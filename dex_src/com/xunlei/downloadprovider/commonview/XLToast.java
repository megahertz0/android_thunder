package com.xunlei.downloadprovider.commonview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.a;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.d;

public final class XLToast {
    private static String a;
    private static long b;
    private static Toast c;

    public enum XLToastType {
        XLTOAST_TYPE_NORMAL,
        XLTOAST_TYPE_SUC,
        XLTOAST_TYPE_ACC,
        XLTOAST_TYPE_SMILE,
        XLTOAST_TYPE_ALARM,
        XLTOAST_TYPE_NONE;

        static {
            XLTOAST_TYPE_NORMAL = new com.xunlei.downloadprovider.commonview.XLToast.XLToastType("XLTOAST_TYPE_NORMAL", 0);
            XLTOAST_TYPE_SUC = new com.xunlei.downloadprovider.commonview.XLToast.XLToastType("XLTOAST_TYPE_SUC", 1);
            XLTOAST_TYPE_ACC = new com.xunlei.downloadprovider.commonview.XLToast.XLToastType("XLTOAST_TYPE_ACC", 2);
            XLTOAST_TYPE_SMILE = new com.xunlei.downloadprovider.commonview.XLToast.XLToastType("XLTOAST_TYPE_SMILE", 3);
            XLTOAST_TYPE_ALARM = new com.xunlei.downloadprovider.commonview.XLToast.XLToastType("XLTOAST_TYPE_ALARM", 4);
            XLTOAST_TYPE_NONE = new com.xunlei.downloadprovider.commonview.XLToast.XLToastType("XLTOAST_TYPE_NONE", 5);
            a = new com.xunlei.downloadprovider.commonview.XLToast.XLToastType[]{XLTOAST_TYPE_NORMAL, XLTOAST_TYPE_SUC, XLTOAST_TYPE_ACC, XLTOAST_TYPE_SMILE, XLTOAST_TYPE_ALARM, XLTOAST_TYPE_NONE};
        }
    }

    static {
        a = a.d;
        b = 0;
        c = null;
    }

    private static void a(Context context, XLToastType xLToastType, String str, int i, int i2, int i3) {
        int i4 = 0;
        long currentTimeMillis = System.currentTimeMillis();
        if (str == null) {
            return;
        }
        if (!str.equals(a) || currentTimeMillis < b || currentTimeMillis - b > 2000) {
            int i5;
            int c;
            int d;
            a = str;
            b = currentTimeMillis;
            if (c == null) {
                c = new Toast(context);
            }
            View inflate = LayoutInflater.from(context).inflate(R.layout.xl_toast_view, null);
            TextView textView = (TextView) inflate.findViewById(R.id.xl_toast_txt);
            textView.setText(str);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_icon);
            if (xLToastType == XLToastType.XLTOAST_TYPE_ALARM) {
                i5 = R.drawable.toast_alarm_icon;
            } else if (xLToastType == XLToastType.XLTOAST_TYPE_SUC) {
                i5 = R.drawable.toast_success_icon;
            } else {
                i5 = 0;
            }
            if (i5 != 0) {
                imageView.setImageResource(i5);
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(XZBDevice.Wait);
            }
            if (i > 0) {
                textView.setMaxLines(i);
            }
            c.setView(inflate);
            if (i2 == 17) {
                c = d.c(context);
                d = d.d(context);
            } else if (i2 == 80) {
                c = g.a(context, 65.0f) * -2;
                d = d.d(context);
            } else {
                d = 0;
                c = 0;
            }
            if (context.getResources().getConfiguration().orientation != 1) {
                i4 = d;
            }
            c.setGravity(i2, i4 / 2, (-c) / 2);
            c.setDuration(i3);
            c.show();
        }
    }

    public static void a(Context context, XLToastType xLToastType, String str) {
        a(context, xLToastType, str, 0);
    }

    public static void a(Context context, XLToastType xLToastType, String str, int i) {
        a(context, xLToastType, str, i, com.xunlei.tdlive.R.styleable.Toolbar_maxButtonHeight, 0);
    }

    public static void b(Context context, XLToastType xLToastType, String str) {
        a(context, xLToastType, str, 0, com.xunlei.tdlive.R.styleable.Toolbar_maxButtonHeight, 0);
    }

    public static void c(Context context, XLToastType xLToastType, String str) {
        a(context, xLToastType, str, 0, com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme, 0);
    }

    public static void b(Context context, XLToastType xLToastType, String str, int i) {
        a(context, xLToastType, str, 0, com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme, i);
    }

    public static void d(Context context, XLToastType xLToastType, String str) {
        a(context, xLToastType, str, 0, com.xunlei.tdlive.R.styleable.Toolbar_maxButtonHeight, 1);
    }

    public static void a() {
        if (c != null) {
            c.cancel();
        }
    }
}
