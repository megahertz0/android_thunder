package com.xunlei.tdlive.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import com.xunlei.tdlive.R;
import java.util.HashMap;
import java.util.Random;
import org.android.spdy.SpdyProtocol;

// compiled from: FloatingGenerator.java
public class e {
    private static a[] a;

    // compiled from: FloatingGenerator.java
    private static class a {
        public HashMap<Integer, Bitmap> a;
        public Bitmap b;
        public Bitmap c;
        public Rect d;

        public a(Bitmap bitmap, Bitmap bitmap2) {
            this.b = bitmap;
            this.c = bitmap2;
            this.a = new HashMap();
            this.d = new Rect(0, 0, this.b.getWidth(), this.b.getHeight());
        }
    }

    // compiled from: FloatingGenerator.java
    public enum b {
        HEART(0),
        MONKEY(1),
        MAX(2);
        private int d;

        static {
            a = new com.xunlei.tdlive.util.e.b("HEART", 0, 0);
            b = new com.xunlei.tdlive.util.e.b("MONKEY", 1, 1);
            c = new com.xunlei.tdlive.util.e.b("MAX", 2, 2);
            e = new com.xunlei.tdlive.util.e.b[]{a, b, c};
        }

        private b(int i) {
            this.d = 0;
            this.d = i;
        }

        public final int a() {
            return this.d;
        }
    }

    public static int a() {
        return new int[]{-1291899273, -1291888170, -1306359306, -1304051720, -1300824155, -1291882471, -1300824155, -1296568472, -1291899834, -1301479435}[new Random().nextInt(SpdyProtocol.PUBKEY_SEQ_OPEN)];
    }

    public static Bitmap a(Context context, int i) {
        return a(context, i, b.a);
    }

    public static Bitmap a(Context context, int i, b bVar) {
        a a = a(context, bVar);
        if (a == null) {
            return null;
        }
        Bitmap bitmap = (Bitmap) a.a.get(Integer.valueOf(i));
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = a.b.copy(Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(i);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawPaint(paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_ATOP));
        canvas.drawBitmap(a.c, a.d, a.d, paint);
        a.a.put(Integer.valueOf(i), bitmap);
        return bitmap;
    }

    private static a a(Context context, b bVar) {
        int a = bVar.a();
        if (a >= b.c.a()) {
            return null;
        }
        if (a == null) {
            a[] aVarArr = new a[b.c.a()];
            a = aVarArr;
            aVarArr[0] = new a(BitmapFactory.decodeResource(context.getResources(), R.drawable.xllive_heart), BitmapFactory.decodeResource(context.getResources(), R.drawable.xllive_heart1));
            a[1] = new a(BitmapFactory.decodeResource(context.getResources(), R.drawable.xllive_monkey), BitmapFactory.decodeResource(context.getResources(), R.drawable.xllive_monkey1));
        }
        return a[a];
    }
}
