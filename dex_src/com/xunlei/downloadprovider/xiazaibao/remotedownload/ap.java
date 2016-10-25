package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import com.xunlei.xiazaibao.shoulei.XZBShouleiCallback;
import java.util.List;

// compiled from: XZBTaskControl.java
final class ap extends XZBShouleiCallback {
    final /* synthetic */ am$b a;
    final /* synthetic */ am b;

    ap(am amVar, am$b com_xunlei_downloadprovider_xiazaibao_remotedownload_am_b) {
        this.b = amVar;
        this.a = com_xunlei_downloadprovider_xiazaibao_remotedownload_am_b;
    }

    public final void cb_PauseTask(int i, int i2, XZBDevice xZBDevice, List<DownloadTaskResult> list, String str, Object obj) {
        if (this.a != null) {
            if (!(am.a(this.b) == null || i == 0)) {
                am.a(this.b, "\u6682\u505c\u4efb\u52a1\u5931\u8d25");
            }
            this.a.a(i, list);
            super.cb_PauseTask(i, i2, xZBDevice, list, str, obj);
        }
    }
}
