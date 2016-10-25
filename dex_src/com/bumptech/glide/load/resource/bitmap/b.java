package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;
import com.bumptech.glide.h.d;
import com.bumptech.glide.h.h;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.j;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.OutputStream;

// compiled from: BitmapEncoder.java
public final class b implements e<Bitmap> {
    private CompressFormat a;
    private int b;

    public final /* synthetic */ boolean a(Object obj, OutputStream outputStream) {
        CompressFormat compressFormat;
        Bitmap bitmap = (Bitmap) ((j) obj).a();
        long a = d.a();
        if (this.a != null) {
            compressFormat = this.a;
        } else if (bitmap.hasAlpha()) {
            compressFormat = CompressFormat.PNG;
        } else {
            compressFormat = CompressFormat.JPEG;
        }
        bitmap.compress(compressFormat, this.b, outputStream);
        if (Log.isLoggable("BitmapEncoder", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            new StringBuilder("Compressed with type: ").append(compressFormat).append(" of size ").append(h.a(bitmap)).append(" in ").append(d.a(a));
        }
        return true;
    }

    public b() {
        this((byte) 0);
    }

    private b(byte b) {
        this.a = null;
        this.b = 90;
    }

    public final String a() {
        return "BitmapEncoder.com.bumptech.glide.load.resource.bitmap";
    }
}
