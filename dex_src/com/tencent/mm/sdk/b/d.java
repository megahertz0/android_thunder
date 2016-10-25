package com.tencent.mm.sdk.b;

import android.os.Looper;
import com.tencent.mm.sdk.b.e.a;
import com.umeng.socialize.common.SocializeConstants;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public final class d implements a {
    private e aJ;
    private ConcurrentHashMap<Runnable, WeakReference<g>> aK;
    private int aL;
    private LinkedList<WeakReference<g>> aM;

    public d() {
        this.aK = new ConcurrentHashMap();
        this.aM = new LinkedList();
        this.aJ = new e(this);
        if (this.aJ.getLooper().getThread().getName().equals("initThread")) {
            b.a("MicroMsg.MMHandler", "MMHandler can not init handler with initThread looper, stack %s", h.u());
        }
    }

    public d(Looper looper) {
        this.aK = new ConcurrentHashMap();
        this.aM = new LinkedList();
        this.aJ = new e(looper, this);
        if (looper.getThread().getName().equals("initThread")) {
            b.a("MicroMsg.MMHandler", "MMHandler can not init handler with initThread looper, stack %s", h.u());
        }
    }

    public final void a(Runnable runnable, g gVar) {
        this.aK.put(runnable, new WeakReference(gVar));
    }

    public final void b(Runnable runnable, g gVar) {
        WeakReference weakReference = (WeakReference) this.aK.get(runnable);
        if (weakReference != null && weakReference.get() != null && weakReference.get() == gVar) {
            this.aK.remove(runnable);
            if (this.aL > 0) {
                if (this.aM.size() == this.aL) {
                    this.aM.pop();
                }
                this.aM.add(weakReference);
            }
        }
    }

    public final boolean post(Runnable runnable) {
        return this.aJ.post(runnable);
    }

    public final String toString() {
        return new StringBuilder("MMHandler(").append(getClass().getName()).append(SocializeConstants.OP_CLOSE_PAREN).toString();
    }
}
