package com.xunlei.downloadprovider.ad.home.a;

import android.text.TextUtils;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.choiceness.a.a;
import java.util.Iterator;

// compiled from: RemoveItemExecutor.java
final class n implements Runnable {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    public final void run() {
        a a = a.a(BrothersApplication.a());
        Object obj = this.a.d;
        if (!TextUtils.isEmpty(obj)) {
            Iterator it = a.d.iterator();
            while (it.hasNext()) {
                com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) it.next();
                if (obj.equals(aVar.d)) {
                    a.d.remove(aVar);
                    break;
                }
            }
            a.a();
        }
        if (this.a.e != null) {
            this.a.e.a(this.a.d);
        }
    }
}
