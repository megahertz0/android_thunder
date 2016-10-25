package com.umeng.socialize.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.umeng.socialize.common.ImageFormat;
import com.umeng.socialize.utils.BitmapUtils;
import java.io.File;

class UMImage$FileConvertor extends UMImage$ConfiguredConvertor {
    private File a;

    public UMImage$FileConvertor(File file) {
        this.a = file;
    }

    public File asFile() {
        return this.a;
    }

    public String asUrl() {
        return null;
    }

    public byte[] asBinary() {
        return a(this.a);
    }

    public Bitmap asBitmap() {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(this.a.toString(), options);
        options.inSampleSize = BitmapUtils.calculateInSampleSize(options, BitmapUtils.MAX_WIDTH, BitmapUtils.MAX_HEIGHT);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(this.a.getAbsolutePath(), options);
    }

    private byte[] a(File file) {
        if (file == null || !file.getAbsoluteFile().exists()) {
            return null;
        }
        byte[] b = b(file);
        if (b == null || b.length <= 0) {
            return null;
        }
        return !ImageFormat.FORMAT_NAMES[1].equals(ImageFormat.checkFormat(b)) ? UMImage.a(b) : b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] b(java.io.File r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.media.UMImage$FileConvertor.b(java.io.File):byte[]");
        /*
        r0 = 0;
        r3 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0050, all -> 0x0037 }
        r3.<init>(r6);	 Catch:{ Exception -> 0x0050, all -> 0x0037 }
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0054, all -> 0x0048 }
        r2.<init>();	 Catch:{ Exception -> 0x0054, all -> 0x0048 }
        r1 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r1 = new byte[r1];	 Catch:{ Exception -> 0x001b }
    L_0x000f:
        r4 = r3.read(r1);	 Catch:{ Exception -> 0x001b }
        r5 = -1;
        if (r4 == r5) goto L_0x002a;
    L_0x0016:
        r5 = 0;
        r2.write(r1, r5, r4);	 Catch:{ Exception -> 0x001b }
        goto L_0x000f;
    L_0x001b:
        r1 = move-exception;
    L_0x001c:
        r1.printStackTrace();	 Catch:{ all -> 0x004c }
        if (r3 == 0) goto L_0x0024;
    L_0x0021:
        r3.close();	 Catch:{ IOException -> 0x004e }
    L_0x0024:
        if (r2 == 0) goto L_0x0029;
    L_0x0026:
        r2.close();	 Catch:{ IOException -> 0x004e }
    L_0x0029:
        return r0;
    L_0x002a:
        r0 = r2.toByteArray();	 Catch:{ Exception -> 0x001b }
        r3.close();	 Catch:{ IOException -> 0x0035 }
        r2.close();	 Catch:{ IOException -> 0x0035 }
        goto L_0x0029;
    L_0x0035:
        r1 = move-exception;
        goto L_0x0029;
    L_0x0037:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x003b:
        if (r3 == 0) goto L_0x0040;
    L_0x003d:
        r3.close();	 Catch:{ IOException -> 0x0046 }
    L_0x0040:
        if (r2 == 0) goto L_0x0045;
    L_0x0042:
        r2.close();	 Catch:{ IOException -> 0x0046 }
    L_0x0045:
        throw r0;
    L_0x0046:
        r1 = move-exception;
        goto L_0x0045;
    L_0x0048:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x003b;
    L_0x004c:
        r0 = move-exception;
        goto L_0x003b;
    L_0x004e:
        r1 = move-exception;
        goto L_0x0029;
    L_0x0050:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x001c;
    L_0x0054:
        r1 = move-exception;
        r2 = r0;
        goto L_0x001c;
        */
    }
}
