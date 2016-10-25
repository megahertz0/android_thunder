package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDownloadTaskSet;
import com.xunlei.xiazaibao.shoulei.XZBShouleiCallback;

// compiled from: RemoteDownloadModule.java
final class aa extends XZBShouleiCallback {
    final /* synthetic */ e a;
    final /* synthetic */ int b;
    final /* synthetic */ w c;

    aa(w wVar, e eVar, int i) {
        this.c = wVar;
        this.a = eVar;
        this.b = i;
    }

    public final void cb_QueryTaskList(int i, int i2, XZBDevice xZBDevice, XZBDownloadTaskSet xZBDownloadTaskSet, String str, Object obj) {
        if (i != 0) {
            this.a.b(this.b);
        } else if (xZBDownloadTaskSet == null) {
            this.a.b(this.b);
        } else if (xZBDownloadTaskSet.getRtn() != 0) {
            this.a.b(this.b);
        } else {
            if (xZBDownloadTaskSet.getTasks() == null) {
                this.a.b(this.b);
            }
            this.a.a(this.b, xZBDownloadTaskSet);
        }
    }
}
