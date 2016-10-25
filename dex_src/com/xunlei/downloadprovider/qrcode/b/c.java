package com.xunlei.downloadprovider.qrcode.b;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.ClipboardManager;
import android.util.DisplayMetrics;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: QrcodeUtil.java
public final class c {
    private static c d;
    public int a;
    public int b;
    private Context c;

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (d == null) {
                d = new c(context);
            }
            cVar = d;
        }
        return cVar;
    }

    private c(Context context) {
        this.c = context;
        Context context2 = this.c;
        int[] iArr = new int[2];
        if (context2 instanceof Activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context2).getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.a = displayMetrics.widthPixels;
            this.b = displayMetrics.heightPixels;
            iArr[0] = this.a;
            iArr[1] = this.b;
        }
    }

    public final Bitmap a(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        new StringBuilder("width = ").append(this.a);
        new StringBuilder("height = ").append(this.b);
        matrix.setScale(((float) this.a) / ((float) bitmap.getWidth()), ((float) this.b) / ((float) bitmap.getHeight()));
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (bitmap == createBitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return bitmap;
        }
    }

    public static Bitmap b(Bitmap bitmap) {
        if (bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(90.0f, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (bitmap == createBitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return bitmap;
        }
    }

    public static int a(String str) {
        switch (AnonymousClass_1.a[XLFileTypeUtil.a(str).ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return R.drawable.ic_dl_video;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return R.drawable.ic_dl_music;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return R.drawable.ic_dl_text;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return R.drawable.ic_dl_apk;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return R.drawable.ic_dl_image;
            case R.styleable.Toolbar_contentInsetEnd:
                return R.drawable.ic_dl_rar;
            case R.styleable.Toolbar_contentInsetLeft:
                return R.drawable.ic_dl_torrent;
            default:
                return R.drawable.ic_dl_other;
        }
    }

    public static void a(Context context, String str) {
        ((ClipboardManager) context.getSystemService("clipboard")).setText(str);
        XLToast.a(context, XLToastType.XLTOAST_TYPE_SMILE, context.getString(2131232055));
    }
}
