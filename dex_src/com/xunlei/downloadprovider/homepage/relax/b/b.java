package com.xunlei.downloadprovider.homepage.relax.b;

import android.os.Looper;
import android.os.Message;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType;
import com.xunlei.downloadprovider.homepage.relax.e.a;
import com.xunlei.downloadprovider.model.protocol.b.d;
import com.xunlei.downloadprovider.model.protocol.b.j;
import com.xunlei.tdlive.im.ChatMessage;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: GetDataTask.java
public final class b extends com.xunlei.downloadprovider.util.b<a> {
    final /* synthetic */ a a;

    public b(a aVar, a aVar2, Looper looper) {
        this.a = aVar;
        super(aVar2, looper);
    }

    public final /* synthetic */ void a(Object obj, Message message) {
        a aVar = (a) obj;
        switch (message.what) {
            case ChatMessage.FLAG_SYS_NOTIFY:
                a.k;
                j jVar = (j) message.obj;
                if (jVar.d == 0) {
                    this.a.a(1, d.a(this.a.c), this.a.a, this.a.b, null);
                } else if (jVar.e instanceof List) {
                    a.k;
                    List a = a.a(jVar.e);
                    a.k;
                    if (aVar.b == GuestureType.TOP) {
                        int a2 = a.a(this.a.c);
                        if (a2 > 0) {
                            a.k;
                            com.xunlei.downloadprovider.homepage.relax.a.a.a().a(a, a2);
                            a.k;
                        }
                    } else if (aVar.b == GuestureType.BOTTOM) {
                        RelaxDataManager a3 = RelaxDataManager.a();
                        if (!(a == null || a.isEmpty())) {
                            a3.a = a;
                        }
                    }
                    this.a.a(0, d.a(this.a.c), this.a.a, this.a.b, a.a(a, this.a.a, this.a.b, this.a.g));
                } else {
                    this.a.a(SimpleLog.LOG_LEVEL_DEBUG, d.a(this.a.c), this.a.a, this.a.b, null);
                }
                a.k;
            default:
                break;
        }
    }
}
