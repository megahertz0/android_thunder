package com.xunlei.tdlive.a;

import com.xunlei.tdlive.modal.JsonWrapper;

// compiled from: GiftAdapter.java
class e implements Runnable {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public void run() {
        JsonWrapper jsonWrapper = new JsonWrapper("[]");
        try {
            jsonWrapper = JsonWrapper.loadFromStream(c.a(this.a).openFileInput(".gift.cache.dat"), "[]");
        } catch (Throwable th) {
        }
        synchronized (c.b(this.a)) {
            if (!c.b(this.a).booleanValue()) {
                this.a.a(jsonWrapper);
                c.a(this.a, Boolean.valueOf(true));
                if (c.c(this.a) != null) {
                    c.d(this.a).post(new f(this));
                }
            }
        }
    }
}
