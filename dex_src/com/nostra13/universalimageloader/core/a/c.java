package com.nostra13.universalimageloader.core.a;

import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

// compiled from: ImageDecodingInfo.java
public final class c {
    final String a;
    final String b;
    final com.nostra13.universalimageloader.core.assist.c c;
    final ImageScaleType d;
    final ViewScaleType e;
    final ImageDownloader f;
    final Object g;
    final boolean h;
    final Options i;
    private final String j;

    public c(String str, String str2, String str3, com.nostra13.universalimageloader.core.assist.c cVar, ViewScaleType viewScaleType, ImageDownloader imageDownloader, com.nostra13.universalimageloader.core.c cVar2) {
        this.a = str;
        this.b = str2;
        this.j = str3;
        this.c = cVar;
        this.d = cVar2.j;
        this.e = viewScaleType;
        this.f = imageDownloader;
        this.g = cVar2.n;
        this.h = cVar2.m;
        this.i = new Options();
        Options options = cVar2.k;
        Options options2 = this.i;
        options2.inDensity = options.inDensity;
        options2.inDither = options.inDither;
        options2.inInputShareable = options.inInputShareable;
        options2.inJustDecodeBounds = options.inJustDecodeBounds;
        options2.inPreferredConfig = options.inPreferredConfig;
        options2.inPurgeable = options.inPurgeable;
        options2.inSampleSize = options.inSampleSize;
        options2.inScaled = options.inScaled;
        options2.inScreenDensity = options.inScreenDensity;
        options2.inTargetDensity = options.inTargetDensity;
        options2.inTempStorage = options.inTempStorage;
        if (VERSION.SDK_INT >= 10) {
            options2.inPreferQualityOverSpeed = options.inPreferQualityOverSpeed;
        }
        if (VERSION.SDK_INT >= 11) {
            options2.inBitmap = options.inBitmap;
            options2.inMutable = options.inMutable;
        }
    }
}
