package com.baidu.mobads.d;

import android.content.Context;
import android.graphics.Bitmap;
import com.baidu.mobads.interfaces.download.IXAdStaticImgDownloader;
import com.baidu.mobads.openad.c.f;
import java.net.URL;

public class a extends f implements IXAdStaticImgDownloader {
    public a(Context context, URL url, String str, String str2) {
        super(context, url, str, str2, true);
    }

    protected void a() {
        super.a();
    }

    public Bitmap getBitmap() {
        return null;
    }
}
