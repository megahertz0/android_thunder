package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDownloadTaskSet;
import com.xunlei.xiazaibao.shoulei.XZBShouleiCallback;
import java.util.List;

// compiled from: RemoteDownloadModule.java
final class x extends XZBShouleiCallback {
    final /* synthetic */ e a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ w d;

    x(w wVar, e eVar, int i, int i2) {
        this.d = wVar;
        this.a = eVar;
        this.b = i;
        this.c = i2;
    }

    public final void cb_QueryTaskList(int i, int i2, XZBDevice xZBDevice, XZBDownloadTaskSet xZBDownloadTaskSet, String str, Object obj) {
        boolean z = true;
        if (i != 0) {
            this.a.a(this.b, i, "\u83b7\u53d6\u5217\u8868\u5931\u8d25");
        } else if (xZBDownloadTaskSet == null) {
            this.a.a(this.b, i, "\u83b7\u53d6\u5217\u8868\u5931\u8d25");
        } else if (xZBDownloadTaskSet.getRtn() != 0) {
            this.a.a(this.b, i, "\u83b7\u53d6\u5217\u8868\u5931\u8d25");
        } else {
            int dowloadingNum = xZBDownloadTaskSet.getDowloadingNum();
            int completeNum = xZBDownloadTaskSet.getCompleteNum();
            int serverFailNum = xZBDownloadTaskSet.getServerFailNum();
            if (this.c == 5) {
                this.d.c = dowloadingNum + serverFailNum;
            } else if (this.c == 1) {
                this.d.c = completeNum;
            } else {
                this.d.c = (dowloadingNum + completeNum) + serverFailNum;
            }
            List tasks = xZBDownloadTaskSet.getTasks();
            if (tasks == null) {
                this.a.a(this.b, -1, "\u83b7\u53d6\u5217\u8868\u5931\u8d25");
            }
            w.b(this.d, tasks.size());
            w.a;
            e eVar = this.a;
            serverFailNum = this.b;
            if (tasks.size() < 10) {
                z = false;
            }
            eVar.a(serverFailNum, xZBDownloadTaskSet, z);
            this.a.a(this.b);
        }
    }
}
