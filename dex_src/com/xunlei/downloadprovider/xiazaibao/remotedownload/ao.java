package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import com.xunlei.xiazaibao.shoulei.XZBShouleiCallback;
import java.util.List;

// compiled from: XZBTaskControl.java
final class ao extends XZBShouleiCallback {
    final /* synthetic */ am$c a;
    final /* synthetic */ am b;

    ao(am amVar, am$c com_xunlei_downloadprovider_xiazaibao_remotedownload_am_c) {
        this.b = amVar;
        this.a = com_xunlei_downloadprovider_xiazaibao_remotedownload_am_c;
    }

    public final void cb_StartTask(int i, int i2, XZBDevice xZBDevice, List<DownloadTaskResult> list, String str, Object obj) {
        if (this.a != null) {
            if (!(am.a(this.b) == null || i == 0)) {
                am.a(this.b, "\u5f00\u59cb\u4efb\u52a1\u5931\u8d25");
            }
            this.a.a(i, list);
            super.cb_StartTask(i, i2, xZBDevice, list, str, obj);
        }
    }
}
