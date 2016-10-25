package com.xunlei.downloadprovider.homepage.choiceness;

import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.d.c;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: ChoicenessUtils.java
final class d extends c {
    final /* synthetic */ ImageView a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ int e;
    final /* synthetic */ String f;

    d(ImageView imageView, int i, String str, int i2, int i3, String str2) {
        this.a = imageView;
        this.b = i;
        this.c = str;
        this.d = i2;
        this.e = i3;
        this.f = str2;
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        int i = 0;
        this.a.setTag(this.b, this.c);
        if ((this.d == 0 || this.e == 0) && bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            new StringBuilder("width=").append(width).append(",height=").append(height).append(",url=").append(this.f).append(",imageView=").append(this.a);
            if (width != 0 && height != 0) {
                int width2;
                String str2 = this.f;
                ImageView imageView = this.a;
                if (imageView != null) {
                    width2 = imageView.getWidth();
                    i = imageView.getHeight();
                } else {
                    width2 = 0;
                }
                if (!(width == 0 || height == 0 || ((float) width) / ((float) height) >= 1.3333334f)) {
                    str2 = str2 + "?imageMogr2/thumbnail/" + width2 + "x" + i + "!|imageMogr2/blur/50x250|imageMogr2/blur/50x250|imageMogr2/blur/50x250|imageMogr2/blur/50x250|watermark/3/image/" + new String(new String(Base64.encode((str2 + "?imageMogr2/thumbnail/" + ((int) ((((float) width) * ((float) i)) / ((float) height))) + "x" + i + "!").getBytes(), SimpleLog.LOG_LEVEL_DEBUG))) + "/gravity/Center/dx/0/dy/0";
                }
                a.a(this.f, str2, this.a, width, height);
            }
        }
    }
}
