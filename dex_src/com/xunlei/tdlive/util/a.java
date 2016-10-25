package com.xunlei.tdlive.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.util.a.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.lang.reflect.Method;

// compiled from: BitmapDisplay.java
public class a {
    private static a a;
    private static a b;
    private com.nostra13.universalimageloader.core.assist.c c;

    // compiled from: BitmapDisplay.java
    private static class d implements a {
        private d() {
        }

        public void onLoadingStarted(String str, View view) {
        }

        public void onLoadingFailed(String str, View view, FailReason failReason) {
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        }

        public void onLoadingCancelled(String str, View view) {
        }
    }

    // compiled from: BitmapDisplay.java
    public static abstract class b<T extends View> extends d {
        public abstract void a(T t, String str, Bitmap bitmap, com.xunlei.tdlive.util.a.a aVar);

        public abstract void a(T t, String str, Drawable drawable);

        public b() {
            super();
        }

        public /* bridge */ /* synthetic */ void onLoadingCancelled(String str, View view) {
            super.onLoadingCancelled(str, view);
        }

        public /* bridge */ /* synthetic */ void onLoadingComplete(String str, View view, Bitmap bitmap) {
            super.onLoadingComplete(str, view, bitmap);
        }

        public /* bridge */ /* synthetic */ void onLoadingFailed(String str, View view, FailReason failReason) {
            super.onLoadingFailed(str, view, failReason);
        }

        public /* bridge */ /* synthetic */ void onLoadingStarted(String str, View view) {
            super.onLoadingStarted(str, view);
        }

        public void a(T t, String str, com.xunlei.tdlive.util.a.a aVar) {
        }

        public void b(T t, String str, com.xunlei.tdlive.util.a.a aVar) {
        }

        public void a(T t, Bitmap bitmap) {
            if (t instanceof ImageView) {
                ((ImageView) t).setImageBitmap(bitmap);
            } else if (VERSION.SDK_INT >= 16) {
                t.setBackground(new BitmapDrawable(t.getResources(), bitmap));
            } else {
                t.setBackgroundDrawable(new BitmapDrawable(t.getResources(), bitmap));
            }
        }

        public void a(T t, Drawable drawable) {
            if (t instanceof ImageView) {
                ((ImageView) t).setImageDrawable(drawable);
            } else if (VERSION.SDK_INT >= 16) {
                t.setBackground(drawable);
            } else {
                t.setBackgroundDrawable(drawable);
            }
        }
    }

    // compiled from: BitmapDisplay.java
    public static class c<T extends View> extends b<T> {
        public void a(T t, String str, Bitmap bitmap, com.xunlei.tdlive.util.a.a aVar) {
            a(t, bitmap);
            Animation b = aVar.b();
            if (b != null) {
                a(t, b);
            }
        }

        public void a(T t, String str, Drawable drawable) {
            a(t, drawable);
        }

        private void a(T t, Animation animation) {
            try {
                Method declaredMethod = Animation.class.getDeclaredMethod("clone", new Class[0]);
                declaredMethod.setAccessible(true);
                t.startAnimation((Animation) declaredMethod.invoke(animation, new Object[0]));
            } catch (Throwable th) {
                t.startAnimation(animation);
            }
        }
    }

    // compiled from: BitmapDisplay.java
    public static class a {
        private Animation a;
        private Drawable b;
        private Drawable c;
        private int d;

        public int a() {
            return this.d;
        }

        public Animation b() {
            return this.a;
        }

        public Drawable c() {
            return this.b;
        }

        public void a(Drawable drawable) {
            this.b = drawable;
        }

        public Drawable d() {
            return this.c;
        }

        public void b(Drawable drawable) {
            this.c = drawable;
        }
    }

    private a(Context context) {
        this.c = null;
        if (com.nostra13.universalimageloader.core.d.a().b != null) {
            boolean z = true;
        } else {
            int i = 0;
        }
        if (!z) {
            com.nostra13.universalimageloader.core.c.a aVar = new com.nostra13.universalimageloader.core.c.a();
            aVar.h = true;
            aVar.i = true;
            com.nostra13.universalimageloader.core.c b = aVar.a(Config.RGB_565).b();
            com.nostra13.universalimageloader.core.d a = com.nostra13.universalimageloader.core.d.a();
            com.nostra13.universalimageloader.core.e.a a2 = new com.nostra13.universalimageloader.core.e.a(context.getApplicationContext()).a(XZBDevice.DOWNLOAD_LIST_ALL);
            a2.h = b;
            a2.d = true;
            if (a2.g != null) {
                com.nostra13.universalimageloader.b.c.c("memoryCache() and memoryCacheSize() calls overlap each other", new Object[0]);
            }
            a2.f = (int) (((float) Runtime.getRuntime().maxMemory()) * 0.5f);
            a.a(a2.a(new com.nostra13.universalimageloader.a.a.b.c()).a());
        }
        if (this.c == null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            this.c = new com.nostra13.universalimageloader.core.assist.c(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
    }

    public static a a(Context context) {
        if (b == null) {
            b = new a(context);
        }
        return b;
    }

    public static a b(Context context) {
        if (a == null) {
            int i = ac.j() ? R.drawable.xllive_img_loding : R.drawable.xllive_img_loding_sdk;
            a = a(context, i, i);
        }
        return a;
    }

    public static a a(Context context, int i) {
        return a(context, i, i);
    }

    public static a a(Context context, int i, int i2) {
        Drawable drawable;
        Drawable drawable2 = null;
        try {
            drawable = context.getApplicationContext().getResources().getDrawable(i);
        } catch (Exception e) {
            drawable = null;
        }
        try {
            drawable2 = context.getApplicationContext().getResources().getDrawable(i2);
        } catch (Exception e2) {
        }
        return a(drawable, drawable2);
    }

    public static a a(Drawable drawable, Drawable drawable2) {
        a aVar = new a();
        aVar.b(drawable);
        aVar.a(drawable2);
        return aVar;
    }

    public File a(String str) {
        return com.nostra13.universalimageloader.core.d.a().c().a(str);
    }

    public Bitmap b(String str) {
        try {
            String a = com.nostra13.universalimageloader.b.d.a(str, this.c);
            com.nostra13.universalimageloader.core.d a2 = com.nostra13.universalimageloader.core.d.a();
            a2.b();
            return a2.b.n.a(a);
        } catch (Throwable th) {
            return null;
        }
    }

    public Bitmap c(String str) {
        try {
            com.nostra13.universalimageloader.core.d a = com.nostra13.universalimageloader.core.d.a();
            com.nostra13.universalimageloader.core.c.a a2 = new com.nostra13.universalimageloader.core.c.a().a(a.b.r);
            a2.s = true;
            com.nostra13.universalimageloader.core.c b = a2.b();
            a aVar = new a();
            a.a(str, null, b, aVar);
            return aVar.a;
        } catch (Throwable th) {
            return null;
        }
    }

    public void d(String str) {
        a(str, null, null);
    }

    public void a(String str, b<View> bVar) {
        a(str, null, (b) bVar);
    }

    public void a(String str, a aVar, b<View> bVar) {
        a(null, str, aVar, bVar);
    }

    public <T extends View> void a(T t, String str) {
        a(t, str, null, null);
    }

    public <T extends View> void a(T t, String str, a aVar) {
        a(t, str, aVar, null);
    }

    public <T extends View> void a(T t, String str, b<T> bVar) {
        a(t, str, null, bVar);
    }

    public <T extends View> void a(T t, String str, a aVar, b<T> bVar) {
        com.nostra13.universalimageloader.core.c b;
        com.nostra13.universalimageloader.core.c.a aVar2;
        if (aVar != null) {
            aVar2 = new com.nostra13.universalimageloader.core.c.a();
            aVar2.d = aVar.c();
            aVar2.e = aVar.c();
            aVar2.f = aVar.d();
            aVar2.h = true;
            aVar2.i = true;
            aVar2.m = true;
            aVar2.a(Config.RGB_565);
            if (b(str) != null) {
                aVar2.d = null;
            }
            int a = aVar.a();
            if (a > 0) {
                aVar2.q = new com.nostra13.universalimageloader.core.b.c(a);
            }
            b = aVar2.b();
        } else {
            aVar2 = new com.nostra13.universalimageloader.core.c.a();
            aVar2.h = true;
            aVar2.i = true;
            aVar2.m = true;
            b = aVar2.a(Config.RGB_565).b();
        }
        a bVar2 = new b(this, bVar, t, aVar);
        if (t != null) {
            com.nostra13.universalimageloader.core.c.a bVar3;
            if (t instanceof ImageView) {
                bVar3 = new com.nostra13.universalimageloader.core.c.b((ImageView) t);
            } else {
                bVar3 = new e(t);
            }
            com.nostra13.universalimageloader.core.d.a().a(str, bVar3, b, this.c, bVar2);
            return;
        }
        com.nostra13.universalimageloader.core.d.a().a(str, this.c, b, bVar2);
    }
}
