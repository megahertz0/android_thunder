package com.xunlei.downloadprovider.model.protocol.g;

import com.umeng.message.util.HttpRequest;
import com.xunlei.common.yunbo.XLYunboMassage;
import com.xunlei.downloadprovider.b.a;
import com.xunlei.downloadprovider.b.b.d.d;
import org.apache.http.Header;

// compiled from: UrlBox.java
public final class c implements d {
    final /* synthetic */ a a;

    public c(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, Header[] headerArr, com.xunlei.downloadprovider.b.b.d dVar) {
        Object obj;
        if (headerArr == null) {
            obj = null;
        } else if (dVar.a("Content-Disposition") != null) {
            obj = dVar.e;
            i = 0;
        } else {
            obj = dVar.a(HttpRequest.l);
            if (obj != null) {
                if (obj.startsWith("application/") || obj.startsWith("audio/") || obj.startsWith("vedio/") || obj.startsWith("image/")) {
                    obj = dVar.e;
                    i = 0;
                } else {
                    obj = null;
                }
            }
        }
        if (this.a.mListener != null) {
            com.xunlei.downloadprovider.b.c cVar = new com.xunlei.downloadprovider.b.c();
            cVar.b = obj;
            cVar.a = this.a.getRunnerId();
            cVar.c = this.a.mUserData;
            this.a.mListener.obtainMessage(XLYunboMassage.MSG_TASKFINISHED, i, -1, cVar).sendToTarget();
            this.a.mListener = null;
        }
        a.cancel(this.a.getRunnerId());
    }
}
