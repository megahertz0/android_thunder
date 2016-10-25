package com.alipay.android.phone.mrpc.core;

import android.text.format.Time;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class k {
    private static final Pattern a;
    private static final Pattern b;

    private static class a {
        int a;
        int b;
        int c;

        a(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    static {
        a = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");
        b = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");
    }

    public static long a(String str) {
        int c;
        int d;
        a e;
        int i;
        int i2 = 1;
        Matcher matcher = a.matcher(str);
        int b;
        if (matcher.find()) {
            b = b(matcher.group(1));
            c = c(matcher.group(XZBDevice.DOWNLOAD_LIST_RECYCLE));
            d = d(matcher.group(XZBDevice.DOWNLOAD_LIST_FAILED));
            e = e(matcher.group(XZBDevice.DOWNLOAD_LIST_ALL));
            i = b;
        } else {
            Matcher matcher2 = b.matcher(str);
            if (matcher2.find()) {
                c = c(matcher2.group(1));
                b = b(matcher2.group(XZBDevice.DOWNLOAD_LIST_RECYCLE));
                a e2 = e(matcher2.group(XZBDevice.DOWNLOAD_LIST_FAILED));
                d = d(matcher2.group(XZBDevice.DOWNLOAD_LIST_ALL));
                e = e2;
                i = b;
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (d >= 2038) {
            d = 2038;
            c = 0;
        } else {
            i2 = i;
        }
        Time time = new Time("UTC");
        time.set(e.c, e.b, e.a, i2, c, d);
        return time.toMillis(false);
    }

    private static int b(String str) {
        return str.length() == 2 ? ((str.charAt(0) - 48) * 10) + (str.charAt(1) - 48) : str.charAt(0) - 48;
    }

    private static int c(String str) {
        switch (((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(XZBDevice.DOWNLOAD_LIST_RECYCLE))) - 291) {
            case XZBDevice.Pause:
                return XZBDevice.Success;
            case XZBDevice.Stop:
                return 1;
            case R.styleable.Toolbar_logoDescription:
                return 0;
            case R.styleable.AppCompatTheme_actionMenuTextColor:
                return R.styleable.Toolbar_contentInsetLeft;
            case R.styleable.AppCompatTheme_actionModeBackground:
                return 2;
            case R.styleable.AppCompatTheme_actionModeCutDrawable:
                return XZBDevice.DOWNLOAD_LIST_FAILED;
            case R.styleable.AppCompatTheme_actionModeSelectAllDrawable:
                return XZBDevice.Pause;
            case R.styleable.AppCompatTheme_actionModeShareDrawable:
                return XZBDevice.DOWNLOAD_LIST_ALL;
            case XZBDevice.WaitInServer:
                return XZBDevice.Wait;
            case R.styleable.AppCompatTheme_textAppearanceLargePopupMenu:
                return R.styleable.Toolbar_contentInsetEnd;
            case R.styleable.AppCompatTheme_dialogTheme:
                return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
            case R.styleable.AppCompatTheme_homeAsUpIndicator:
                return XZBDevice.Stop;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static int d(String str) {
        if (str.length() != 2) {
            return str.length() == 3 ? ((((str.charAt(0) - 48) * 100) + ((str.charAt(1) - 48) * 10)) + (str.charAt(XZBDevice.DOWNLOAD_LIST_RECYCLE) - 48)) + 1900 : str.length() == 4 ? ((((str.charAt(0) - 48) * 1000) + ((str.charAt(1) - 48) * 100)) + ((str.charAt(XZBDevice.DOWNLOAD_LIST_RECYCLE) - 48) * 10)) + (str.charAt(XZBDevice.DOWNLOAD_LIST_FAILED) - 48) : 1970;
        } else {
            int charAt = ((str.charAt(0) - 48) * 10) + (str.charAt(1) - 48);
            return charAt >= 70 ? charAt + 1900 : charAt + 2000;
        }
    }

    private static a e(String str) {
        int i;
        int charAt = str.charAt(0) - 48;
        if (str.charAt(1) != ':') {
            charAt *= 10;
            i = XZBDevice.DOWNLOAD_LIST_RECYCLE;
            charAt += str.charAt(1) - 48;
        } else {
            i = 1;
        }
        i++;
        int i2 = i + 1;
        i = ((str.charAt(i) - 48) * 10) + (str.charAt(i2) - 48);
        i2 = (i2 + 1) + 1;
        return new a(charAt, i, ((str.charAt(i2) - 48) * 10) + (str.charAt(i2 + 1) - 48));
    }
}
