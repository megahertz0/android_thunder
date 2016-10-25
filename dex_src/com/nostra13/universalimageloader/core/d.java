package com.nostra13.universalimageloader.core;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.c.b;
import com.nostra13.universalimageloader.core.d.c;

// compiled from: ImageLoader.java
public class d {
    public static final String a;
    private static volatile d e;
    public e b;
    private f c;
    private a d;

    // compiled from: ImageLoader.java
    private static class a extends c {
        public Bitmap a;

        private a() {
        }

        public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
            this.a = bitmap;
        }
    }

    static {
        a = d.class.getSimpleName();
    }

    public static d a() {
        if (e == null) {
            synchronized (d.class) {
                if (e == null) {
                    e = new d();
                }
            }
        }
        return e;
    }

    protected d() {
        this.d = new c();
    }

    public final synchronized void a(e eVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        } else if (this.b == null) {
            com.nostra13.universalimageloader.b.c.a("Initialize ImageLoader with configuration", new Object[0]);
            this.c = new f(eVar);
            this.b = eVar;
        } else {
            com.nostra13.universalimageloader.b.c.c("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
        }
    }

    private void a(String str, com.nostra13.universalimageloader.core.c.a aVar, c cVar, a aVar2) {
        a(str, aVar, cVar, null, aVar2);
    }

    public final void a(String str, com.nostra13.universalimageloader.core.c.a aVar, c cVar, com.nostra13.universalimageloader.core.assist.c cVar2, a aVar2) {
        int i = 0;
        b();
        if (aVar == null) {
            throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
        }
        a aVar3;
        c cVar3;
        if (aVar2 == null) {
            aVar3 = this.d;
        } else {
            aVar3 = aVar2;
        }
        if (cVar == null) {
            cVar3 = this.b.r;
        } else {
            cVar3 = cVar;
        }
        Resources resources;
        Drawable drawable;
        if (TextUtils.isEmpty(str)) {
            this.c.b(aVar);
            aVar3.onLoadingStarted(str, aVar.getWrappedView());
            if (!(cVar3.e == null && cVar3.b == 0)) {
                i = 1;
            }
            if (i != 0) {
                resources = this.b.a;
                if (cVar3.b != 0) {
                    drawable = resources.getDrawable(cVar3.b);
                } else {
                    drawable = cVar3.e;
                }
                aVar.setImageDrawable(drawable);
            } else {
                aVar.setImageDrawable(null);
            }
            aVar3.onLoadingComplete(str, aVar.getWrappedView(), null);
            return;
        }
        com.nostra13.universalimageloader.core.assist.c a;
        if (cVar2 == null) {
            a = com.nostra13.universalimageloader.b.a.a(aVar, this.b.a());
        } else {
            a = cVar2;
        }
        String a2 = com.nostra13.universalimageloader.b.d.a(str, a);
        this.c.e.put(Integer.valueOf(aVar.getId()), a2);
        aVar3.onLoadingStarted(str, aVar.getWrappedView());
        Bitmap a3 = this.b.n.a(a2);
        if (a3 == null || a3.isRecycled()) {
            if (!(cVar3.d == null && cVar3.a == 0)) {
                i = 1;
            }
            if (i != 0) {
                resources = this.b.a;
                if (cVar3.a != 0) {
                    drawable = resources.getDrawable(cVar3.a);
                } else {
                    drawable = cVar3.d;
                }
                aVar.setImageDrawable(drawable);
            } else if (cVar3.g) {
                aVar.setImageDrawable(null);
            }
            i iVar = new i(this.c, new h(str, aVar, a, a2, cVar3, aVar3, this.c.a(str)), a(cVar3));
            if (cVar3.s) {
                iVar.run();
                return;
            }
            f fVar = this.c;
            fVar.d.execute(new g(fVar, iVar));
            return;
        }
        com.nostra13.universalimageloader.b.c.a("Load image from memory cache [%s]", a2);
        if (cVar3.a()) {
            Runnable mVar = new m(this.c, a3, new h(str, aVar, a, a2, cVar3, aVar3, this.c.a(str)), a(cVar3));
            if (cVar3.s) {
                mVar.run();
                return;
            }
            fVar = this.c;
            fVar.a();
            fVar.c.execute(mVar);
            return;
        }
        cVar3.q.a(a3, aVar, LoadedFrom.MEMORY_CACHE);
        aVar3.onLoadingComplete(str, aVar.getWrappedView(), a3);
    }

    public final void a(String str, ImageView imageView) {
        a(str, new b(imageView), null, null);
    }

    public final void a(String str, ImageView imageView, c cVar) {
        a(str, new b(imageView), cVar, null);
    }

    public final void a(String str, ImageView imageView, a aVar) {
        a(str, new b(imageView), null, aVar);
    }

    public final void a(String str, c cVar, a aVar) {
        b(str, null, cVar, aVar);
    }

    public final void a(String str, com.nostra13.universalimageloader.core.assist.c cVar, c cVar2, a aVar) {
        b(str, cVar, cVar2, aVar);
    }

    public final void b(String str, com.nostra13.universalimageloader.core.assist.c cVar, c cVar2, a aVar) {
        b();
        if (cVar == null) {
            cVar = this.b.a();
        }
        if (cVar2 == null) {
            cVar2 = this.b.r;
        }
        a(str, new com.nostra13.universalimageloader.core.c.c(str, cVar, ViewScaleType.CROP), cVar2, aVar);
    }

    public final void b() {
        if (this.b == null) {
            throw new IllegalStateException("ImageLoader must be init with configuration before using");
        }
    }

    public final com.nostra13.universalimageloader.a.a.a c() {
        b();
        return this.b.o;
    }

    public final void a(ImageView imageView) {
        this.c.b(new b(imageView));
    }

    public final void a(String str, ImageView imageView, c cVar, a aVar) {
        a(str, new b(imageView), cVar, aVar);
    }

    private static Handler a(c cVar) {
        Handler handler = cVar.r;
        if (cVar.s) {
            return null;
        }
        return (handler == null && Looper.myLooper() == Looper.getMainLooper()) ? new Handler() : handler;
    }
}
