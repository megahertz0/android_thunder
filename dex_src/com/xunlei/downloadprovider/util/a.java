package com.xunlei.downloadprovider.util;

import android.widget.ImageView;
import com.xunlei.xllib.b.b;

// compiled from: BlurUtil.java
public final class a {
    public static String a(String str, int i, int i2, ImageView imageView, int i3, int i4) {
        int width;
        int i5 = 0;
        if (imageView != null) {
            width = imageView.getWidth();
            i5 = imageView.getHeight();
        } else {
            width = 0;
        }
        if (!(width == 0 || i5 == 0)) {
            i4 = i5;
            i3 = width;
        }
        if (i == 0 || i2 == 0 || ((float) i) / ((float) i2) >= 1.3333334f) {
            return str;
        }
        return str + "?imageMogr2/thumbnail/" + i3 + "x" + i4 + "!|imageMogr2/blur/50x250|imageMogr2/blur/50x250|imageMogr2/blur/50x250|imageMogr2/blur/50x250|watermark/3/image/" + new String(b.a((str + "?imageMogr2/thumbnail/" + ((int) ((((float) i) * ((float) i4)) / ((float) i2))) + "x" + i4 + "!").getBytes())) + "/gravity/Center/dx/0/dy/0";
    }
}
