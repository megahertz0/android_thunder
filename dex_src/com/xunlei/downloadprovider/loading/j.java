package com.xunlei.downloadprovider.loading;

import com.xunlei.downloadprovider.b.c.a.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import java.util.Map;

// compiled from: LoadingImageHelper.java
final class j implements a {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public final void a(int i, Object obj, Map<String, List<String>> map) {
        if (this.a.b != null) {
            this.a.b.obtainMessage(5000, i, this.a.c, obj).sendToTarget();
            if (i == 0) {
                this.a.setStatus(XZBDevice.DOWNLOAD_LIST_FAILED);
            } else {
                this.a.setStatus(XZBDevice.DOWNLOAD_LIST_ALL);
            }
        }
    }
}
