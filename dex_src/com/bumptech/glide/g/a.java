package com.bumptech.glide.g;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.bumptech.glide.load.b;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// compiled from: ApplicationVersionSignature.java
public final class a {
    private static final ConcurrentHashMap<String, b> a;

    // compiled from: RequestManager.java
    public final class a {
        public final A a;
        public final Class<A> b;
        public final boolean c;
        public final /* synthetic */ com.bumptech.glide.g.a d;

        public a(com.bumptech.glide.g.a aVar, A a) {
            Class cls;
            this.d = aVar;
            this.c = true;
            this.a = a;
            if (a != null) {
                cls = a.getClass();
            } else {
                cls = null;
            }
            this.b = cls;
        }
    }

    static {
        a = new ConcurrentHashMap();
    }

    public static b a(Context context) {
        String packageName = context.getPackageName();
        b bVar = (b) a.get(packageName);
        if (bVar != null) {
            return bVar;
        }
        b b = b(context);
        bVar = (b) a.putIfAbsent(packageName, b);
        return bVar == null ? b : bVar;
    }

    private static b b(Context context) {
        String valueOf;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            valueOf = String.valueOf(packageInfo.versionCode);
        } else {
            valueOf = UUID.randomUUID().toString();
        }
        return new c(valueOf);
    }
}
