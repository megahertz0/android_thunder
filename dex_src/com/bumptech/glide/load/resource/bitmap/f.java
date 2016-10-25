package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.h.a;
import com.bumptech.glide.h.h;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType;
import com.taobao.accs.utl.UtilityImpl;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;

// compiled from: Downsampler.java
public abstract class f implements a<InputStream> {
    public static final f a;
    public static final f b;
    public static final f c;
    private static final Set<ImageType> d;
    private static final Queue<Options> e;

    protected abstract int a(int i, int i2, int i3, int i4);

    static {
        d = EnumSet.of(ImageType.JPEG, ImageType.PNG_A, ImageType.PNG);
        e = h.a(0);
        a = new g();
        b = new h();
        c = new i();
    }

    public final Bitmap a(InputStream inputStream, c cVar, int i, int i2, DecodeFormat decodeFormat) {
        int a;
        int a2;
        int i3;
        int[] iArr;
        int i4;
        Config a3;
        Bitmap b;
        Bitmap a4;
        Throwable th;
        a a5 = a.a();
        byte[] b2 = a5.b();
        byte[] b3 = a5.b();
        Options b4 = b();
        InputStream qVar = new q(inputStream, b3);
        InputStream a6 = com.bumptech.glide.h.c.a(qVar);
        InputStream fVar = new com.bumptech.glide.h.f(a6);
        try {
            Object obj;
            Matrix matrix;
            int round;
            int round2;
            a6.mark(UtilityImpl.TNET_FILE_SIZE);
            ImageHeaderParser imageHeaderParser = new ImageHeaderParser(a6);
            if (ImageHeaderParser.a(imageHeaderParser.b.a())) {
                byte[] bArr;
                while (imageHeaderParser.b.b() == (short) 255) {
                    short b5 = imageHeaderParser.b.b();
                    if (b5 == (short) 218) {
                        bArr = null;
                        break;
                    } else if (b5 == (short) 217) {
                        bArr = null;
                        break;
                    } else {
                        int a7 = imageHeaderParser.b.a() - 2;
                        if (b5 != (short) 225) {
                            long a8 = imageHeaderParser.b.a((long) a7);
                            if (a8 != ((long) a7)) {
                                if (Log.isLoggable("ImageHeaderParser", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                                    new StringBuilder("Unable to skip enough data, type: ").append(b5).append(", wanted to skip: ").append(a7).append(", but actually skipped: ").append(a8);
                                }
                                bArr = null;
                            }
                        } else {
                            byte[] bArr2 = new byte[a7];
                            a = imageHeaderParser.b.a(bArr2);
                            if (a != a7) {
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    new StringBuilder("Unable to read segment data, type: ").append(b5).append(", length: ").append(a7).append(", actually read: ").append(a);
                                }
                                bArr = null;
                            } else {
                                bArr = bArr2;
                            }
                        }
                    }
                }
                bArr = null;
                Object obj2 = (bArr == null || bArr.length <= ImageHeaderParser.a.length) ? null : 1;
                if (obj2 != null) {
                    for (a = 0; a < ImageHeaderParser.a.length; a++) {
                        if (bArr[a] != ImageHeaderParser.a[a]) {
                            obj2 = null;
                            break;
                        }
                    }
                }
                if (obj2 != null) {
                    a2 = ImageHeaderParser.a(new a(bArr));
                    try {
                        a6.reset();
                        i3 = a2;
                    } catch (IOException e) {
                        i3 = a2;
                    }
                    b4.inTempStorage = b2;
                    b4.inJustDecodeBounds = true;
                    a(fVar, qVar, b4);
                    b4.inJustDecodeBounds = false;
                    iArr = new int[]{b4.outWidth, b4.outHeight};
                    i4 = iArr[0];
                    a = iArr[1];
                    switch (i3) {
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            obj = 180;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        case R.styleable.Toolbar_contentInsetEnd:
                            obj = R.styleable.AppCompatTheme_controlBackground;
                            break;
                        case R.styleable.Toolbar_contentInsetLeft:
                        case XZBDevice.Wait:
                            obj = 270;
                            break;
                        default:
                            obj = null;
                            break;
                    }
                    if (i2 == Integer.MIN_VALUE) {
                        i2 = a;
                    }
                    if (i == Integer.MIN_VALUE) {
                        i = i4;
                    }
                    if (a2 != 90 || a2 == 270) {
                        a2 = a(a, i4, i, i2);
                    } else {
                        a2 = a(i4, a, i, i2);
                    }
                    a2 = Math.max(1, a2 != 0 ? 0 : Integer.highestOneBit(a2));
                    a3 = a(fVar, decodeFormat);
                    b4.inSampleSize = a2;
                    b4.inPreferredConfig = a3;
                    if ((b4.inSampleSize == 1 || 19 <= VERSION.SDK_INT) && a(fVar)) {
                        b = cVar.b((int) Math.ceil(((double) i4) / ((double) a2)), (int) Math.ceil(((double) a) / ((double) a2)), a3);
                        if (11 <= VERSION.SDK_INT) {
                            b4.inBitmap = b;
                        }
                    }
                    a4 = a(fVar, qVar, b4);
                    th = a6.a;
                    if (th == null) {
                        throw new RuntimeException(th);
                    }
                    b = null;
                    if (a4 != null) {
                        matrix = new Matrix();
                        switch (i3) {
                            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                matrix.setScale(-1.0f, 1.0f);
                                break;
                            case XZBDevice.DOWNLOAD_LIST_FAILED:
                                matrix.setRotate(180.0f);
                                break;
                            case XZBDevice.DOWNLOAD_LIST_ALL:
                                matrix.setRotate(180.0f);
                                matrix.postScale(-1.0f, 1.0f);
                                break;
                            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                                matrix.setRotate(90.0f);
                                matrix.postScale(-1.0f, 1.0f);
                                break;
                            case R.styleable.Toolbar_contentInsetEnd:
                                matrix.setRotate(90.0f);
                                break;
                            case R.styleable.Toolbar_contentInsetLeft:
                                matrix.setRotate(-90.0f);
                                matrix.postScale(-1.0f, 1.0f);
                                break;
                            case XZBDevice.Wait:
                                matrix.setRotate(-90.0f);
                                break;
                        }
                        if (matrix.isIdentity()) {
                            RectF rectF = new RectF(0.0f, 0.0f, (float) a4.getWidth(), (float) a4.getHeight());
                            matrix.mapRect(rectF);
                            round = Math.round(rectF.width());
                            round2 = Math.round(rectF.height());
                            a3 = t.a(a4);
                            b = cVar.a(round, round2, a3);
                            if (b == null) {
                                b = Bitmap.createBitmap(round, round2, a3);
                            }
                            matrix.postTranslate(-rectF.left, -rectF.top);
                            new Canvas(b).drawBitmap(a4, matrix, new Paint(6));
                        } else {
                            b = a4;
                        }
                        if (!(a4.equals(b) || cVar.a(a4))) {
                            a4.recycle();
                        }
                    }
                    a5.a(b2);
                    a5.a(b3);
                    a6.a();
                    a(b4);
                    return b;
                }
            }
            a2 = -1;
            a6.reset();
            i3 = a2;
        } catch (IOException e2) {
            try {
                a6.reset();
                Object obj3 = null;
            } catch (IOException e3) {
                obj3 = null;
            }
        } catch (Throwable th2) {
        }
        b4.inTempStorage = b2;
        b4.inJustDecodeBounds = true;
        a(fVar, qVar, b4);
        b4.inJustDecodeBounds = false;
        iArr = new int[]{b4.outWidth, b4.outHeight};
        i4 = iArr[0];
        a = iArr[1];
        switch (i3) {
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_ALL:
                obj = 180;
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
            case R.styleable.Toolbar_contentInsetEnd:
                obj = R.styleable.AppCompatTheme_controlBackground;
                break;
            case R.styleable.Toolbar_contentInsetLeft:
            case XZBDevice.Wait:
                obj = 270;
                break;
            default:
                obj = null;
                break;
        }
        if (i2 == Integer.MIN_VALUE) {
            i2 = a;
        }
        if (i == Integer.MIN_VALUE) {
            i = i4;
        }
        if (a2 != 90) {
        }
        a2 = a(a, i4, i, i2);
        if (a2 != 0) {
        }
        a2 = Math.max(1, a2 != 0 ? 0 : Integer.highestOneBit(a2));
        a3 = a(fVar, decodeFormat);
        b4.inSampleSize = a2;
        b4.inPreferredConfig = a3;
        b = cVar.b((int) Math.ceil(((double) i4) / ((double) a2)), (int) Math.ceil(((double) a) / ((double) a2)), a3);
        if (11 <= VERSION.SDK_INT) {
            b4.inBitmap = b;
        }
        a4 = a(fVar, qVar, b4);
        th = a6.a;
        if (th == null) {
            b = null;
            if (a4 != null) {
                matrix = new Matrix();
                switch (i3) {
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        matrix.setScale(-1.0f, 1.0f);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        matrix.setRotate(180.0f);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        matrix.setRotate(180.0f);
                        matrix.postScale(-1.0f, 1.0f);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        matrix.setRotate(90.0f);
                        matrix.postScale(-1.0f, 1.0f);
                        break;
                    case R.styleable.Toolbar_contentInsetEnd:
                        matrix.setRotate(90.0f);
                        break;
                    case R.styleable.Toolbar_contentInsetLeft:
                        matrix.setRotate(-90.0f);
                        matrix.postScale(-1.0f, 1.0f);
                        break;
                    case XZBDevice.Wait:
                        matrix.setRotate(-90.0f);
                        break;
                }
                if (matrix.isIdentity()) {
                    RectF rectF2 = new RectF(0.0f, 0.0f, (float) a4.getWidth(), (float) a4.getHeight());
                    matrix.mapRect(rectF2);
                    round = Math.round(rectF2.width());
                    round2 = Math.round(rectF2.height());
                    a3 = t.a(a4);
                    b = cVar.a(round, round2, a3);
                    if (b == null) {
                        b = Bitmap.createBitmap(round, round2, a3);
                    }
                    matrix.postTranslate(-rectF2.left, -rectF2.top);
                    new Canvas(b).drawBitmap(a4, matrix, new Paint(6));
                } else {
                    b = a4;
                }
                a4.recycle();
            }
            a5.a(b2);
            a5.a(b3);
            a6.a();
            a(b4);
            return b;
        }
        throw new RuntimeException(th);
    }

    private static boolean a(InputStream inputStream) {
        if (19 <= VERSION.SDK_INT) {
            return true;
        }
        inputStream.mark(JsInterface.MSG_JS_COLLECT_WEBSITE);
        try {
            boolean contains = d.contains(new ImageHeaderParser(inputStream).a());
            try {
                inputStream.reset();
                return contains;
            } catch (IOException e) {
                return contains;
            }
        } catch (IOException e2) {
            try {
                inputStream.reset();
            } catch (IOException e3) {
            }
            return false;
        } catch (Throwable th) {
            try {
                inputStream.reset();
            } catch (IOException e4) {
            }
        }
    }

    private static Config a(InputStream inputStream, DecodeFormat decodeFormat) {
        if (decodeFormat == DecodeFormat.ALWAYS_ARGB_8888 || decodeFormat == DecodeFormat.PREFER_ARGB_8888 || VERSION.SDK_INT == 16) {
            return Config.ARGB_8888;
        }
        boolean z = false;
        inputStream.mark(JsInterface.MSG_JS_COLLECT_WEBSITE);
        try {
            z = new ImageHeaderParser(inputStream).a().hasAlpha();
            try {
                inputStream.reset();
            } catch (IOException e) {
            }
        } catch (IOException e2) {
            try {
                if (Log.isLoggable("Downsampler", XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)) {
                    new StringBuilder("Cannot determine whether the image has alpha or not from header for format ").append(decodeFormat);
                }
                try {
                    inputStream.reset();
                } catch (IOException e3) {
                }
            } catch (Throwable th) {
                try {
                    inputStream.reset();
                } catch (IOException e4) {
                }
            }
        }
        return z ? Config.ARGB_8888 : Config.RGB_565;
    }

    private static Bitmap a(com.bumptech.glide.h.f fVar, q qVar, Options options) {
        if (options.inJustDecodeBounds) {
            fVar.mark(UtilityImpl.TNET_FILE_SIZE);
        } else {
            qVar.a();
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(fVar, null, options);
        try {
            if (options.inJustDecodeBounds) {
                fVar.reset();
            }
        } catch (IOException e) {
            if (Log.isLoggable("Downsampler", R.styleable.Toolbar_contentInsetEnd)) {
                new StringBuilder("Exception loading inDecodeBounds=").append(options.inJustDecodeBounds).append(" sample=").append(options.inSampleSize);
            }
        }
        return decodeStream;
    }

    @TargetApi(11)
    private static synchronized Options b() {
        Options options;
        synchronized (f.class) {
            try {
                synchronized (e) {
                    options = (Options) e.poll();
                }
                if (options == null) {
                    options = new Options();
                    b(options);
                }
            } catch (Throwable th) {
            }
        }
        return options;
    }

    private static void a(Options options) {
        b(options);
        synchronized (e) {
            e.offer(options);
        }
    }

    @TargetApi(11)
    private static void b(Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        if (11 <= VERSION.SDK_INT) {
            options.inBitmap = null;
            options.inMutable = true;
        }
    }
}
