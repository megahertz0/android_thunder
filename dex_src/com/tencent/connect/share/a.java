package com.tencent.connect.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.tencent.open.a.f;
import com.tencent.open.utils.AsynLoadImgBack;
import com.tencent.open.utils.Util;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

// compiled from: ProGuard
public class a {

    // compiled from: ProGuard
    final class AnonymousClass_1 extends Handler {
        final /* synthetic */ AsynLoadImgBack a;

        AnonymousClass_1(Looper looper, AsynLoadImgBack asynLoadImgBack) {
            this.a = asynLoadImgBack;
            super(looper);
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case R.styleable.AppCompatTheme_buttonStyleSmall:
                    this.a.saved(0, (String) message.obj);
                case R.styleable.AppCompatTheme_checkboxStyle:
                    this.a.saved(message.arg1, null);
                default:
                    super.handleMessage(message);
            }
        }
    }

    // compiled from: ProGuard
    final class AnonymousClass_2 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ Handler b;

        AnonymousClass_2(String str, Handler handler) {
            this.a = str;
            this.b = handler;
        }

        public final void run() {
            Bitmap a = a.a(this.a, 140);
            if (a != null) {
                String a2;
                String str = Environment.getExternalStorageDirectory() + "/tmp/";
                String toString = new StringBuilder("share2qq_temp").append(Util.encrypt(this.a)).append(".jpg").toString();
                if (a.b(this.a, 140, 140)) {
                    f.b("openSDK_LOG.AsynScaleCompressImage", "out of bound,compress!");
                    a2 = a.a(a, str, toString);
                } else {
                    f.b("openSDK_LOG.AsynScaleCompressImage", "not out of bound,not compress!");
                    a2 = this.a;
                }
                f.b("openSDK_LOG.AsynScaleCompressImage", new StringBuilder("-->destFilePath: ").append(a2).toString());
                if (a2 != null) {
                    Message obtainMessage = this.b.obtainMessage(R.styleable.AppCompatTheme_buttonStyleSmall);
                    obtainMessage.obj = a2;
                    this.b.sendMessage(obtainMessage);
                    return;
                }
            }
            Message obtainMessage2 = this.b.obtainMessage(R.styleable.AppCompatTheme_checkboxStyle);
            obtainMessage2.arg1 = 3;
            this.b.sendMessage(obtainMessage2);
        }
    }

    // compiled from: ProGuard
    final class AnonymousClass_3 extends Handler {
        final /* synthetic */ AsynLoadImgBack a;

        AnonymousClass_3(Looper looper, AsynLoadImgBack asynLoadImgBack) {
            this.a = asynLoadImgBack;
            super(looper);
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case R.styleable.AppCompatTheme_buttonStyleSmall:
                    this.a.batchSaved(0, message.getData().getStringArrayList("images"));
                default:
                    super.handleMessage(message);
            }
        }
    }

    // compiled from: ProGuard
    final class AnonymousClass_4 implements Runnable {
        final /* synthetic */ ArrayList a;
        final /* synthetic */ Handler b;

        AnonymousClass_4(ArrayList arrayList, Handler handler) {
            this.a = arrayList;
            this.b = handler;
        }

        public final void run() {
            for (int i = 0; i < this.a.size(); i++) {
                String str = (String) this.a.get(i);
                if (!Util.isValidUrl(str) && Util.fileExists(str)) {
                    Bitmap a = a.a(str, 10000);
                    if (a != null) {
                        Object a2;
                        String str2 = Environment.getExternalStorageDirectory() + "/tmp/";
                        String toString = new StringBuilder("share2qzone_temp").append(Util.encrypt(str)).append(".jpg").toString();
                        if (a.b(str, 640, 10000)) {
                            f.b("openSDK_LOG.AsynScaleCompressImage", "out of bound, compress!");
                            a2 = a.a(a, str2, toString);
                        } else {
                            f.b("openSDK_LOG.AsynScaleCompressImage", "not out of bound,not compress!");
                        }
                        if (a2 != null) {
                            this.a.set(i, a2);
                        }
                    }
                }
            }
            Message obtainMessage = this.b.obtainMessage(R.styleable.AppCompatTheme_buttonStyleSmall);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("images", this.a);
            obtainMessage.setData(bundle);
            this.b.sendMessage(obtainMessage);
        }
    }

    public static final void a(Context context, String str, AsynLoadImgBack asynLoadImgBack) {
        f.b("openSDK_LOG.AsynScaleCompressImage", "scaleCompressImage");
        if (TextUtils.isEmpty(str)) {
            asynLoadImgBack.saved(1, null);
        } else if (Util.hasSDCard()) {
            new Thread(new AnonymousClass_2(str, new AnonymousClass_1(context.getMainLooper(), asynLoadImgBack))).start();
        } else {
            asynLoadImgBack.saved(XZBDevice.DOWNLOAD_LIST_RECYCLE, null);
        }
    }

    public static final void a(Context context, ArrayList<String> arrayList, AsynLoadImgBack asynLoadImgBack) {
        f.b("openSDK_LOG.AsynScaleCompressImage", "batchScaleCompressImage");
        if (arrayList == null) {
            asynLoadImgBack.saved(1, null);
        } else {
            new Thread(new AnonymousClass_4(arrayList, new AnonymousClass_3(context.getMainLooper(), asynLoadImgBack))).start();
        }
    }

    private static Bitmap a(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            width = height;
        }
        float f = ((float) i) / ((float) width);
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    protected static final String a(Bitmap bitmap, String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String toString = new StringBuffer(str).append(str2).toString();
        File file2 = new File(toString);
        if (file2.exists()) {
            file2.delete();
        }
        if (bitmap != null) {
            try {
                OutputStream fileOutputStream = new FileOutputStream(file2);
                bitmap.compress(CompressFormat.JPEG, R.styleable.AppCompatTheme_panelMenuListTheme, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                bitmap.recycle();
                return toString;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private static final boolean b(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
            return false;
        }
        int i5 = i3 > i4 ? i3 : i4;
        if (i3 >= i4) {
            i3 = i4;
        }
        f.b("openSDK_LOG.AsynScaleCompressImage", new StringBuilder("longSide=").append(i5).append("shortSide=").append(i3).toString());
        options.inPreferredConfig = Config.RGB_565;
        return i5 > i2 || i3 > i;
    }

    public static final Bitmap a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        if (i2 <= i3) {
            i2 = i3;
        }
        options.inPreferredConfig = Config.RGB_565;
        if (i2 > i) {
            options.inSampleSize = a(options, -1, i * i);
        }
        options.inJustDecodeBounds = false;
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            decodeFile = null;
        }
        if (decodeFile == null) {
            return null;
        }
        i3 = options.outWidth;
        int i4 = options.outHeight;
        if (i3 <= i4) {
            i3 = i4;
        }
        return i3 > i ? a(decodeFile, i) : decodeFile;
    }

    public static final int a(Options options, int i, int i2) {
        int b = b(options, i, i2);
        if (b > 8) {
            return ((b + 7) / 8) * 8;
        }
        int i3 = 1;
        while (i3 < b) {
            i3 <<= 1;
        }
        return i3;
    }

    private static int b(Options options, int i, int i2) {
        double d = (double) options.outWidth;
        double d2 = (double) options.outHeight;
        int ceil = i2 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / ((double) i2)));
        int min = i == -1 ? AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS : (int) Math.min(Math.floor(d / ((double) i)), Math.floor(d2 / ((double) i)));
        if (min < ceil) {
            return ceil;
        }
        if (i2 == -1 && i == -1) {
            return 1;
        }
        return i != -1 ? min : ceil;
    }
}
