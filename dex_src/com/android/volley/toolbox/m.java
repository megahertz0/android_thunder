package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.widget.ImageView.ScaleType;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.f;
import com.android.volley.l;
import com.android.volley.n;
import com.android.volley.r;
import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.x;

// compiled from: ImageRequest.java
public final class m extends Request<Bitmap> {
    private static final Object f;
    private final b<Bitmap> a;
    private final Config b;
    private final int c;
    private final int d;
    private ScaleType e;

    protected final /* synthetic */ void deliverResponse(Object obj) {
        this.a.onResponse((Bitmap) obj);
    }

    static {
        f = new Object();
    }

    public m(String str, b<Bitmap> bVar, int i, int i2, ScaleType scaleType, Config config, a aVar) {
        super(0, str, aVar);
        setRetryPolicy(new f(1000, 2, 2.0f));
        this.a = bVar;
        this.b = config;
        this.c = i;
        this.d = i2;
        this.e = scaleType;
    }

    public final Priority getPriority() {
        return Priority.LOW;
    }

    private static int a(int i, int i2, int i3, int i4, ScaleType scaleType) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (scaleType == ScaleType.FIT_XY) {
            return i == 0 ? i3 : i;
        } else {
            if (i == 0) {
                return (int) ((((double) i2) / ((double) i4)) * ((double) i3));
            }
            if (i2 == 0) {
                return i;
            }
            double d = ((double) i4) / ((double) i3);
            return scaleType == ScaleType.CENTER_CROP ? ((double) i) * d < ((double) i2) ? (int) (((double) i2) / d) : i : ((double) i) * d > ((double) i2) ? (int) (((double) i2) / d) : i;
        }
    }

    protected final r<Bitmap> parseNetworkResponse(l lVar) {
        r<Bitmap> a;
        synchronized (f) {
            try {
                Object decodeByteArray;
                byte[] bArr = lVar.b;
                Options options = new Options();
                if (this.c == 0 && this.d == 0) {
                    options.inPreferredConfig = this.b;
                    decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                } else {
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                    int i = options.outWidth;
                    int i2 = options.outHeight;
                    int a2 = a(this.c, this.d, i, i2, this.e);
                    int a3 = a(this.d, this.c, i2, i, this.e);
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = a(i, i2, a2, a3);
                    Bitmap decodeByteArray2 = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                    if (decodeByteArray2 == null || (decodeByteArray2.getWidth() <= a2 && decodeByteArray2.getHeight() <= a3)) {
                        Bitmap bitmap = decodeByteArray2;
                    } else {
                        decodeByteArray = Bitmap.createScaledBitmap(decodeByteArray2, a2, a3, true);
                        decodeByteArray2.recycle();
                    }
                }
                if (decodeByteArray == null) {
                    a = r.a(new n(lVar));
                } else {
                    a = r.a(decodeByteArray, f.a(lVar));
                }
            } catch (Throwable e) {
                x.c("Caught OOM for %d byte image, url=%s", Integer.valueOf(lVar.b.length), getUrl());
                a = r.a(new n(e));
            }
        }
        return a;
    }

    private static int a(int i, int i2, int i3, int i4) {
        float f = 1.0f;
        while (((double) (f * 2.0f)) <= Math.min(((double) i) / ((double) i3), ((double) i2) / ((double) i4))) {
            f *= 2.0f;
        }
        return (int) f;
    }
}
