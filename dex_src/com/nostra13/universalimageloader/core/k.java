package com.nostra13.universalimageloader.core;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.FailReason.FailType;
import com.nostra13.universalimageloader.core.c.a;

// compiled from: LoadAndDisplayImageTask.java
final class k implements Runnable {
    final /* synthetic */ FailType a;
    final /* synthetic */ Throwable b;
    final /* synthetic */ i c;

    k(i iVar, FailType failType, Throwable th) {
        this.c = iVar;
        this.a = failType;
        this.b = th;
    }

    public final void run() {
        Object obj;
        c cVar = this.c.d;
        if (cVar.f == null && cVar.c == 0) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            Drawable drawable;
            a aVar = this.c.c;
            cVar = this.c.d;
            Resources resources = this.c.a.a;
            if (cVar.c != 0) {
                drawable = resources.getDrawable(cVar.c);
            } else {
                drawable = cVar.f;
            }
            aVar.setImageDrawable(drawable);
        }
        this.c.e.onLoadingFailed(this.c.b, this.c.c.getWrappedView(), new FailReason(this.a, this.b));
    }
}
