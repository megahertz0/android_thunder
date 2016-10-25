package com.nostra13.universalimageloader.a.b.a;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.a.b.a;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;

// compiled from: LruMemoryCache.java
public final class b implements a {
    private final LinkedHashMap<String, Bitmap> a;
    private final int b;
    private int c;

    public b(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.b = i;
        this.a = new LinkedHashMap(0, 0.75f, true);
    }

    public final Bitmap a(String str) {
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        Bitmap bitmap;
        synchronized (this) {
            bitmap = (Bitmap) this.a.get(str);
        }
        return bitmap;
    }

    public final boolean a(String str, Bitmap bitmap) {
        if (str == null || bitmap == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.c += a(bitmap);
            Bitmap bitmap2 = (Bitmap) this.a.put(str, bitmap);
            if (bitmap2 != null) {
                this.c -= a(bitmap2);
            }
        }
        a(this.b);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.a.b.a.b.a(int):void");
        /*
        this = this;
    L_0x0000:
        monitor-enter(r3);
        r0 = r3.c;	 Catch:{ all -> 0x0033 }
        if (r0 < 0) goto L_0x0011;
    L_0x0005:
        r0 = r3.a;	 Catch:{ all -> 0x0033 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x0036;
    L_0x000d:
        r0 = r3.c;	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x0036;
    L_0x0011:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0033 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0033 }
        r1.<init>();	 Catch:{ all -> 0x0033 }
        r2 = r3.getClass();	 Catch:{ all -> 0x0033 }
        r2 = r2.getName();	 Catch:{ all -> 0x0033 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0033 }
        r2 = ".sizeOf() is reporting inconsistent results!";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0033 }
        r1 = r1.toString();	 Catch:{ all -> 0x0033 }
        r0.<init>(r1);	 Catch:{ all -> 0x0033 }
        throw r0;	 Catch:{ all -> 0x0033 }
    L_0x0033:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0033 }
        throw r0;
    L_0x0036:
        r0 = r3.c;	 Catch:{ all -> 0x0033 }
        if (r0 <= r4) goto L_0x0042;
    L_0x003a:
        r0 = r3.a;	 Catch:{ all -> 0x0033 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x0044;
    L_0x0042:
        monitor-exit(r3);	 Catch:{ all -> 0x0033 }
    L_0x0043:
        return;
    L_0x0044:
        r0 = r3.a;	 Catch:{ all -> 0x0033 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0033 }
        r0 = r0.iterator();	 Catch:{ all -> 0x0033 }
        r0 = r0.next();	 Catch:{ all -> 0x0033 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0033 }
        if (r0 != 0) goto L_0x0058;
    L_0x0056:
        monitor-exit(r3);	 Catch:{ all -> 0x0033 }
        goto L_0x0043;
    L_0x0058:
        r1 = r0.getKey();	 Catch:{ all -> 0x0033 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0033 }
        r0 = r0.getValue();	 Catch:{ all -> 0x0033 }
        r0 = (android.graphics.Bitmap) r0;	 Catch:{ all -> 0x0033 }
        r2 = r3.a;	 Catch:{ all -> 0x0033 }
        r2.remove(r1);	 Catch:{ all -> 0x0033 }
        r1 = r3.c;	 Catch:{ all -> 0x0033 }
        r0 = a(r0);	 Catch:{ all -> 0x0033 }
        r0 = r1 - r0;
        r3.c = r0;	 Catch:{ all -> 0x0033 }
        monitor-exit(r3);	 Catch:{ all -> 0x0033 }
        goto L_0x0000;
        */
    }

    public final Bitmap b(String str) {
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        Bitmap bitmap;
        synchronized (this) {
            bitmap = (Bitmap) this.a.remove(str);
            if (bitmap != null) {
                this.c -= a(bitmap);
            }
        }
        return bitmap;
    }

    public final Collection<String> a() {
        Collection hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.a.keySet());
        }
        return hashSet;
    }

    public final void b() {
        a(-1);
    }

    private static int a(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public final synchronized String toString() {
        return String.format("LruCache[maxSize=%d]", new Object[]{Integer.valueOf(this.b)});
    }
}
