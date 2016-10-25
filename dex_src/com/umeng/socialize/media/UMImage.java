package com.umeng.socialize.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.umeng.socialize.media.UMediaObject.MediaType;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Log;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class UMImage extends BaseMediaObject {
    private ConfiguredConvertor i;
    private ConvertConfig j;
    private WeakReference<Bitmap> k;

    public UMImage(Context context, File file) {
        this.i = null;
        this.k = new WeakReference(null);
        a(context, file);
    }

    public UMImage(Context context, String str) {
        super(str);
        this.i = null;
        this.k = new WeakReference(null);
        a((Context) new WeakReference(context).get(), str);
    }

    public UMImage(Context context, int i) {
        this.i = null;
        this.k = new WeakReference(null);
        a(context, Integer.valueOf(i));
    }

    public UMImage(Context context, byte[] bArr) {
        this.i = null;
        this.k = new WeakReference(null);
        a(context, bArr);
    }

    public UMImage(Context context, Bitmap bitmap) {
        this.i = null;
        this.k = new WeakReference(null);
        a(context, bitmap);
    }

    private void a(Context context, Object obj) {
        if (obj instanceof File) {
            this.i = new FileConvertor((File) obj);
        } else if (obj instanceof String) {
            this.i = new UrlConvertor((String) obj);
        } else if (obj instanceof Integer) {
            this.i = new ResConvertor(context, ((Integer) obj).intValue());
        } else if (obj instanceof byte[]) {
            this.i = new BinaryConvertor((byte[]) obj);
        } else if (obj instanceof Bitmap) {
            this.i = new BitmapConvertor((Bitmap) obj);
        } else {
            throw new RuntimeException("Don't support type");
        }
        this.i.setConfig(new ConvertConfig(context));
    }

    public byte[] toByte() {
        return asBinImage();
    }

    public final Map<String, Object> toUrlExtraParams() {
        Map<String, Object> hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
        }
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] b(byte[] r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.media.UMImage.b(byte[]):byte[]");
        /*
        r0 = 0;
        r1 = com.umeng.socialize.utils.BitmapUtils.getBitmapOptions(r6);	 Catch:{ Exception -> 0x0027, all -> 0x0031 }
        r2 = 0;
        r3 = r6.length;	 Catch:{ Exception -> 0x0027, all -> 0x0031 }
        r2 = android.graphics.BitmapFactory.decodeByteArray(r6, r2, r3, r1);	 Catch:{ Exception -> 0x0027, all -> 0x0031 }
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0027, all -> 0x0031 }
        r1.<init>();	 Catch:{ Exception -> 0x0027, all -> 0x0031 }
        if (r2 == 0) goto L_0x001f;
    L_0x0012:
        r3 = android.graphics.Bitmap.CompressFormat.PNG;	 Catch:{ Exception -> 0x0041, all -> 0x003f }
        r4 = 100;
        r2.compress(r3, r4, r1);	 Catch:{ Exception -> 0x0041, all -> 0x003f }
        r2.recycle();	 Catch:{ Exception -> 0x0041, all -> 0x003f }
        java.lang.System.gc();	 Catch:{ Exception -> 0x0041, all -> 0x003f }
    L_0x001f:
        r0 = r1.toByteArray();	 Catch:{ Exception -> 0x0041, all -> 0x003f }
        r1.close();	 Catch:{ IOException -> 0x003b }
    L_0x0026:
        return r0;
    L_0x0027:
        r1 = move-exception;
        r1 = r0;
    L_0x0029:
        if (r1 == 0) goto L_0x0026;
    L_0x002b:
        r1.close();	 Catch:{ IOException -> 0x002f }
        goto L_0x0026;
    L_0x002f:
        r1 = move-exception;
        goto L_0x0026;
    L_0x0031:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0035:
        if (r1 == 0) goto L_0x003a;
    L_0x0037:
        r1.close();	 Catch:{ IOException -> 0x003d }
    L_0x003a:
        throw r0;
    L_0x003b:
        r1 = move-exception;
        goto L_0x0026;
    L_0x003d:
        r1 = move-exception;
        goto L_0x003a;
    L_0x003f:
        r0 = move-exception;
        goto L_0x0035;
    L_0x0041:
        r2 = move-exception;
        goto L_0x0029;
        */
    }

    public MediaType getMediaType() {
        return MediaType.IMAGE;
    }

    public void resize(int i, int i2) {
    }

    public boolean isMultiMedia() {
        return true;
    }

    public File asFileImage() {
        Log.e(new StringBuilder("xxxxx convor=").append(this.i.asFile()).toString());
        return this.i == null ? null : this.i.asFile();
    }

    public String asUrlImage() {
        return this.i == null ? null : this.i.asUrl();
    }

    public byte[] asBinImage() {
        return this.i == null ? null : this.i.asBinary();
    }

    public Bitmap asBitmap() {
        return this.i == null ? null : this.i.asBitmap();
    }

    static Bitmap a(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }
}
