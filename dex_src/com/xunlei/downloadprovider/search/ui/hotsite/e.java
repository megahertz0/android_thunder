package com.xunlei.downloadprovider.search.ui.hotsite;

import android.os.Message;
import com.xunlei.downloadprovider.model.b;
import com.xunlei.downloadprovider.model.i;

// compiled from: SearchTabHotSiteView.java
public final class e extends Thread {
    final /* synthetic */ SearchTabHotSiteView a;

    public e(SearchTabHotSiteView searchTabHotSiteView) {
        this.a = searchTabHotSiteView;
    }

    public final void run() {
        int i = 0;
        while (i < this.a.e.size()) {
            try {
                if (((a) this.a.e.get(i)).e == 1) {
                    b bVar = new b();
                    bVar.b = ((a) this.a.e.get(i)).b;
                    bVar.c = ((a) this.a.e.get(i)).d;
                    bVar.d = 1;
                    if (!i.a().a(bVar, this.a.b)) {
                        ((a) this.a.e.get(i)).e = 0;
                    }
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Message message = new Message();
        message.what = 3003;
        this.a.g.sendMessage(message);
    }
}
