package com.inmobi.ads;

import android.content.Context;
import java.lang.ref.WeakReference;

// compiled from: NativeStrandRootContainerLayout.java
final class ag extends NativeStrandContainerLayout {
    private WeakReference<q> a;

    ag(Context context) {
        super(context);
    }

    final void a(q qVar) {
        this.a = new WeakReference(qVar);
    }

    final q a() {
        return (q) this.a.get();
    }
}
