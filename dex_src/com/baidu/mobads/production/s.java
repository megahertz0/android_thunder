package com.baidu.mobads.production;

import com.baidu.mobads.interfaces.IXAdContainer;
import java.util.HashMap;

class s implements Runnable {
    final /* synthetic */ IXAdContainer a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ o c;

    s(o oVar, IXAdContainer iXAdContainer, HashMap hashMap) {
        this.c = oVar;
        this.a = iXAdContainer;
        this.b = hashMap;
    }

    public void run() {
        o.i(this.c).b(this.a, this.b);
    }
}
