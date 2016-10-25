package com.umeng.socialize.media;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.umeng.socialize.Config;
import com.umeng.socialize.utils.Log;
import com.xunlei.xllib.R;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

class UMImage$ResConvertor extends UMImage$ConfiguredConvertor {
    private Context a;
    private int b;

    public UMImage$ResConvertor(Context context, int i) {
        this.b = 0;
        this.a = context;
        this.b = i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File asFile() {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.media.UMImage$ResConvertor.asFile():java.io.File");
        /*
        this = this;
        r1 = 0;
        r0 = r7.a;	 Catch:{ IOException -> 0x0070, all -> 0x0059 }
        r0 = r0.getResources();	 Catch:{ IOException -> 0x0070, all -> 0x0059 }
        r2 = r7.b;	 Catch:{ IOException -> 0x0070, all -> 0x0059 }
        r0 = r0.openRawResourceFd(r2);	 Catch:{ IOException -> 0x0070, all -> 0x0059 }
        r2 = r0.createInputStream();	 Catch:{ IOException -> 0x0070, all -> 0x0059 }
        r0 = r7.config;	 Catch:{ IOException -> 0x0074, all -> 0x006b }
        r3 = r2.toString();	 Catch:{ IOException -> 0x0074, all -> 0x006b }
        r3 = com.umeng.socialize.net.utils.AesHelper.md5(r3);	 Catch:{ IOException -> 0x0074, all -> 0x006b }
        r0 = r0.generateCacheFile(r3);	 Catch:{ IOException -> 0x0074, all -> 0x006b }
        r3 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0074, all -> 0x006b }
        r3.<init>(r0);	 Catch:{ IOException -> 0x0074, all -> 0x006b }
        r4 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r4 = new byte[r4];	 Catch:{ IOException -> 0x0033 }
    L_0x0028:
        r5 = r2.read(r4);	 Catch:{ IOException -> 0x0033 }
        r6 = -1;
        if (r5 == r6) goto L_0x0043;
    L_0x002f:
        r3.write(r4);	 Catch:{ IOException -> 0x0033 }
        goto L_0x0028;
    L_0x0033:
        r0 = move-exception;
    L_0x0034:
        r0.printStackTrace();	 Catch:{ all -> 0x006d }
        if (r2 == 0) goto L_0x003c;
    L_0x0039:
        r2.close();	 Catch:{ IOException -> 0x0054 }
    L_0x003c:
        if (r3 == 0) goto L_0x0041;
    L_0x003e:
        r3.close();	 Catch:{ IOException -> 0x0054 }
    L_0x0041:
        r0 = r1;
    L_0x0042:
        return r0;
    L_0x0043:
        r3.flush();	 Catch:{ IOException -> 0x0033 }
        if (r2 == 0) goto L_0x004b;
    L_0x0048:
        r2.close();	 Catch:{ IOException -> 0x004f }
    L_0x004b:
        r3.close();	 Catch:{ IOException -> 0x004f }
        goto L_0x0042;
    L_0x004f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0042;
    L_0x0054:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0041;
    L_0x0059:
        r0 = move-exception;
        r2 = r1;
    L_0x005b:
        if (r2 == 0) goto L_0x0060;
    L_0x005d:
        r2.close();	 Catch:{ IOException -> 0x0066 }
    L_0x0060:
        if (r1 == 0) goto L_0x0065;
    L_0x0062:
        r1.close();	 Catch:{ IOException -> 0x0066 }
    L_0x0065:
        throw r0;
    L_0x0066:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0065;
    L_0x006b:
        r0 = move-exception;
        goto L_0x005b;
    L_0x006d:
        r0 = move-exception;
        r1 = r3;
        goto L_0x005b;
    L_0x0070:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        goto L_0x0034;
    L_0x0074:
        r0 = move-exception;
        r3 = r1;
        goto L_0x0034;
        */
    }

    public String asUrl() {
        return null;
    }

    public byte[] asBinary() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (Config.isLoadImgByCompress) {
            Options options = new Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            InputStream openRawResource = this.a.getResources().openRawResource(this.b);
            Log.e("xxxxxxxxx1");
            BitmapFactory.decodeStream(openRawResource, null, options).compress(CompressFormat.PNG, R.styleable.AppCompatTheme_buttonStyle, byteArrayOutputStream);
            Log.e("xxxxxxxxx2");
            return byteArrayOutputStream.toByteArray();
        }
        Drawable drawable;
        Resources resources = this.a.getResources();
        if (VERSION.SDK_INT >= 21) {
            drawable = resources.getDrawable(this.b, null);
        } else {
            drawable = resources.getDrawable(this.b);
        }
        UMImage.a(drawable).compress(CompressFormat.PNG, R.styleable.AppCompatTheme_buttonStyle, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public Bitmap asBitmap() {
        if (!Config.isLoadImgByCompress) {
            return BitmapFactory.decodeResource(this.a.getResources(), this.b);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Options options = new Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeStream(this.a.getResources().openRawResource(this.b), null, options);
    }
}
