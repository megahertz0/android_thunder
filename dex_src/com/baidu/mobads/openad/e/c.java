package com.baidu.mobads.openad.e;

import com.baidu.mobads.j.j;

class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public void run() {
        try {
            if (this.a.g != null && this.a.e.getAndSet(false)) {
                this.a.g.disconnect();
                this.a.g = null;
            }
        } catch (Throwable e) {
            j.a().e(e);
        }
    }
}
