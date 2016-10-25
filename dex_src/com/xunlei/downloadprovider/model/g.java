package com.xunlei.downloadprovider.model;

import com.umeng.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: DownloadReportInfo.java
public final class g {
    public int a;
    public String b;
    public String c;
    public String d;
    public int e;
    public String f;
    public String g;

    public g(int i, String str, String str2) {
        this(0, i, str, str2);
    }

    public g(int i, int i2, String str, String str2) {
        this.a = 3;
        this.e = 0;
        this.e = i;
        this.a = i2;
        this.b = str;
        this.c = str2;
    }

    public g() {
        this.a = 3;
        this.e = 0;
    }

    public static String a(int i) {
        String str = a.d;
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "browser/browser";
            case R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu:
                return "nearby/nearby";
            default:
                return str;
        }
    }
}
