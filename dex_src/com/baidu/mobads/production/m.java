package com.baidu.mobads.production;

import com.baidu.mobads.interfaces.IXAdContainerContext;

class m implements Runnable {
    final /* synthetic */ IXAdContainerContext a;
    final /* synthetic */ a b;

    m(a aVar, IXAdContainerContext iXAdContainerContext) {
        this.b = aVar;
        this.a = iXAdContainerContext;
    }

    public void run() {
        this.b.a(this.a);
    }
}
