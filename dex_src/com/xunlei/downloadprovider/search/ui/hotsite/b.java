package com.xunlei.downloadprovider.search.ui.hotsite;

import android.os.Message;
import com.xunlei.downloadprovider.b.c.a.a;
import java.util.List;
import java.util.Map;

// compiled from: HotSiteHelper.java
final class b implements a {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, Object obj, Map<String, List<String>> map) {
        new StringBuilder().append(getClass()).append("---onComplete---errCode---obj---headers---loader---").append(i).append("---").append(obj).append("---").append(map).append("---").append(Thread.currentThread().getId());
        if (this.a.b != null && i == 0) {
            try {
                Message message = new Message();
                message.obj = obj;
                message.what = 3001;
                this.a.b.sendMessage(message);
            } catch (Exception e) {
            }
        }
    }
}
