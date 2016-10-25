package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.bumptech.glide.e.a;
import com.bumptech.glide.f.a.e;
import com.bumptech.glide.f.b.j;
import com.bumptech.glide.f.d;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.f;
import com.bumptech.glide.manager.h;
import com.bumptech.glide.manager.n;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: GenericRequestBuilder.java
public class c<ModelType, DataType, ResourceType, TranscodeType> implements Cloneable {
    private boolean A;
    private Drawable B;
    private int C;
    protected final Class<ModelType> a;
    protected final Context b;
    protected final e c;
    protected final Class<TranscodeType> d;
    protected final n e;
    protected final h f;
    private a<ModelType, DataType, ResourceType, TranscodeType> g;
    private ModelType h;
    private b i;
    private boolean j;
    private int k;
    private int l;
    private d<? super ModelType, TranscodeType> m;
    private Float n;
    private c<?, ?, ?, TranscodeType> o;
    private Float p;
    private Drawable q;
    private Drawable r;
    private Priority s;
    private boolean t;
    private com.bumptech.glide.f.a.d<TranscodeType> u;
    private int v;
    private int w;
    private DiskCacheStrategy x;
    private f<ResourceType> y;
    private boolean z;

    // compiled from: GenericRequestBuilder.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[ScaleType.values().length];
            try {
                a[ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.FIT_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ScaleType.FIT_START.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return g();
    }

    c(Context context, Class<ModelType> cls, com.bumptech.glide.e.f<ModelType, DataType, ResourceType, TranscodeType> fVar, Class<TranscodeType> cls2, e eVar, n nVar, h hVar) {
        a aVar = null;
        this.i = com.bumptech.glide.g.b.a();
        this.p = Float.valueOf(1.0f);
        this.s = null;
        this.t = true;
        this.u = e.a();
        this.v = -1;
        this.w = -1;
        this.x = DiskCacheStrategy.RESULT;
        this.y = com.bumptech.glide.load.resource.d.b();
        this.b = context;
        this.a = cls;
        this.d = cls2;
        this.c = eVar;
        this.e = nVar;
        this.f = hVar;
        if (fVar != null) {
            aVar = new a(fVar);
        }
        this.g = aVar;
        if (context == null) {
            throw new NullPointerException("Context can't be null");
        } else if (cls != null && fVar == null) {
            throw new NullPointerException("LoadProvider must not be null");
        }
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> a(com.bumptech.glide.load.d<DataType, ResourceType> dVar) {
        if (this.g != null) {
            this.g.a = dVar;
        }
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> a(com.bumptech.glide.load.a<DataType> aVar) {
        if (this.g != null) {
            this.g.b = aVar;
        }
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(DiskCacheStrategy diskCacheStrategy) {
        this.x = diskCacheStrategy;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> a(f<ResourceType>... fVarArr) {
        this.z = true;
        if (fVarArr.length == 1) {
            this.y = fVarArr[0];
        } else {
            this.y = new com.bumptech.glide.load.c(fVarArr);
        }
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> h() {
        return a(e.a());
    }

    final c<ModelType, DataType, ResourceType, TranscodeType> a(com.bumptech.glide.f.a.d<TranscodeType> dVar) {
        if (dVar == null) {
            throw new NullPointerException("Animation factory must not be null!");
        }
        this.u = dVar;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(int i) {
        this.k = i;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> a(int i) {
        this.l = i;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(d<? super ModelType, TranscodeType> dVar) {
        this.m = dVar;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> a(boolean z) {
        this.t = !z;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> a(int i, int i2) {
        if (com.bumptech.glide.h.h.a(i, i2)) {
            this.w = i;
            this.v = i2;
            return this;
        }
        throw new IllegalArgumentException("Width and height must be Target#SIZE_ORIGINAL or > 0");
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Signature must not be null");
        }
        this.i = bVar;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(ModelType modelType) {
        this.h = modelType;
        this.j = true;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> g() {
        try {
            c<ModelType, DataType, ResourceType, TranscodeType> cVar = (c) super.clone();
            cVar.g = this.g != null ? this.g.g() : null;
            return cVar;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public final <Y extends j<TranscodeType>> Y a(Y y) {
        com.bumptech.glide.h.h.a();
        if (this.j) {
            n nVar;
            com.bumptech.glide.f.b e = y.e();
            if (e != null) {
                e.c();
                nVar = this.e;
                nVar.a.remove(e);
                nVar.b.remove(e);
                e.a();
            }
            if (this.s == null) {
                this.s = Priority.NORMAL;
            }
            e = a((j) y, null);
            y.a(e);
            this.f.a(y);
            nVar = this.e;
            nVar.a.add(e);
            if (nVar.c) {
                nVar.b.add(e);
            } else {
                e.b();
            }
            return y;
        }
        throw new IllegalArgumentException("You must first set a model (try #load())");
    }

    public j<TranscodeType> a(ImageView imageView) {
        com.bumptech.glide.h.h.a();
        if (imageView == null) {
            throw new IllegalArgumentException("You must pass in a non null View");
        }
        j dVar;
        if (!(this.z || imageView.getScaleType() == null)) {
            switch (AnonymousClass_1.a[imageView.getScaleType().ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    f();
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    e();
                    break;
            }
        }
        Class cls = this.d;
        if (com.bumptech.glide.load.resource.a.b.class.isAssignableFrom(cls)) {
            dVar = new com.bumptech.glide.f.b.d(imageView);
        } else if (Bitmap.class.equals(cls)) {
            dVar = new com.bumptech.glide.f.b.b(imageView);
        } else if (Drawable.class.isAssignableFrom(cls)) {
            dVar = new com.bumptech.glide.f.b.c(imageView);
        } else {
            throw new IllegalArgumentException(new StringBuilder("Unhandled class: ").append(cls).append(", try .as*(Class).transcode(ResourceTranscoder)").toString());
        }
        return a(dVar);
    }

    void f() {
    }

    void e() {
    }

    private Priority a() {
        if (this.s == Priority.LOW) {
            return Priority.NORMAL;
        }
        return this.s == Priority.NORMAL ? Priority.HIGH : Priority.IMMEDIATE;
    }

    private com.bumptech.glide.f.b a(j<TranscodeType> jVar, com.bumptech.glide.f.f fVar) {
        if (this.o != null) {
            if (this.A) {
                throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
            }
            if (this.o.u.equals(e.a())) {
                this.o.u = this.u;
            }
            if (this.o.s == null) {
                this.o.s = a();
            }
            if (com.bumptech.glide.h.h.a(this.w, this.v) && !com.bumptech.glide.h.h.a(this.o.w, this.o.v)) {
                this.o.a(this.w, this.v);
            }
            com.bumptech.glide.f.f fVar2 = new com.bumptech.glide.f.f(fVar);
            com.bumptech.glide.f.b a = a(jVar, this.p.floatValue(), this.s, fVar2);
            this.A = true;
            com.bumptech.glide.f.b a2 = this.o.a((j) jVar, fVar2);
            this.A = false;
            fVar2.a(a, a2);
            return fVar2;
        } else if (this.n == null) {
            return a(jVar, this.p.floatValue(), this.s, fVar);
        } else {
            com.bumptech.glide.f.b fVar3 = new com.bumptech.glide.f.f(fVar);
            fVar3.a(a(jVar, this.p.floatValue(), this.s, fVar3), a(jVar, this.n.floatValue(), a(), fVar3));
            return fVar3;
        }
    }

    private com.bumptech.glide.f.b a(j<TranscodeType> jVar, float f, Priority priority, com.bumptech.glide.f.c cVar) {
        return com.bumptech.glide.f.a.a(this.g, this.h, this.i, this.b, priority, jVar, f, this.q, this.k, this.r, this.l, this.B, this.C, this.m, cVar, this.c.a, this.y, this.d, this.t, this.u, this.w, this.v, this.x);
    }
}
