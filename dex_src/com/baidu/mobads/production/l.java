package com.baidu.mobads.production;

import com.baidu.mobads.vo.d;

class l implements Runnable {
    final /* synthetic */ d a;
    final /* synthetic */ a b;

    l(a aVar, d dVar) {
        this.b = aVar;
        this.a = dVar;
    }

    public void run() {
        this.b.b(this.a);
    }
}
