package com.inmobi.rendering.mraid;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.util.Base64;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// compiled from: ImageProcessing.java
public class e {
    private static final String a;

    static {
        a = e.class.getSimpleName();
    }

    public static String a(Bitmap bitmap, Context context, int i) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    public static String a(Uri uri, Context context) {
        Cursor query = context.getContentResolver().query(uri, new String[]{Impl._DATA}, null, null, null);
        int columnIndexOrThrow = query.getColumnIndexOrThrow(Impl._DATA);
        query.moveToFirst();
        String string = query.getString(columnIndexOrThrow);
        query.close();
        return string;
    }

    public static Bitmap a(String str, Context context, int i, int i2) {
        int i3 = i * i2;
        try {
            Bitmap createScaledBitmap;
            InputStream fileInputStream = new FileInputStream(str);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fileInputStream, null, options);
            fileInputStream.close();
            int i4 = 1;
            while (((double) (options.outWidth * options.outHeight)) * (1.0d / Math.pow((double) i4, 2.0d)) > ((double) i3)) {
                i4++;
            }
            InputStream fileInputStream2 = new FileInputStream(str);
            if (i4 > 1) {
                i4--;
                Options options2 = new Options();
                options2.inSampleSize = i4;
                Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream2, null, options2);
                i4 = decodeStream.getHeight();
                int width = decodeStream.getWidth();
                double sqrt = Math.sqrt(((double) i3) / (((double) width) / ((double) i4)));
                createScaledBitmap = Bitmap.createScaledBitmap(decodeStream, (int) ((sqrt / ((double) i4)) * ((double) width)), (int) sqrt, true);
                decodeStream.recycle();
                System.gc();
            } else {
                createScaledBitmap = BitmapFactory.decodeStream(fileInputStream2);
            }
            fileInputStream2.close();
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("bitmap size - width: ").append(createScaledBitmap.getWidth()).append(", height: ").append(createScaledBitmap.getHeight()).toString());
            return createScaledBitmap;
        } catch (IOException e) {
            Logger.a(InternalLogLevel.INTERNAL, a, e.getMessage());
            return null;
        }
    }
}
