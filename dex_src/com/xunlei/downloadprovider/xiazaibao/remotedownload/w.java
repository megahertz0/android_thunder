package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import org.android.spdy.SpdyProtocol;
import org.android.spdy.TnetStatusCode;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: RemoteDownloadModule.java
public class w implements b {
    private static final String a;
    private int b;
    private int c;

    static /* synthetic */ int b(w wVar, int i) {
        int i2 = wVar.b + i;
        wVar.b = i2;
        return i2;
    }

    static {
        a = w.class.getSimpleName();
    }

    public final void a(int i, int i2, e eVar) {
        eVar.b();
        eVar.a();
        this.b = 0;
        XZBDevice defaultDevice = XZBShouleiUtil.getInstance().getDefaultDevice();
        if (defaultDevice == null) {
            eVar.a(i, (int) TnetStatusCode.EASY_REQ_STATE_PROCESS_RSP_FAIL, "\u6ca1\u6709\u9009\u4e2d\u4efb\u4f55\u4e0b\u8f7d\u5b9d\uff0c\u65e0\u6cd5\u83b7\u53d6\u6570\u636e\uff01");
            return;
        }
        XZBShouleiUtil.getInstance().queryTaskList(defaultDevice, this.b, SpdyProtocol.PUBKEY_SEQ_OPEN, i2, "RemoteDownloadModule_loadFirst", new x(this, eVar, i, i2));
    }

    public final void b(int i, int i2, e eVar) {
        eVar.b();
        eVar.c();
        XZBDevice defaultDevice = XZBShouleiUtil.getInstance().getDefaultDevice();
        if (defaultDevice == null) {
            eVar.b(i, (int) TnetStatusCode.EASY_REQ_STATE_PROCESS_RSP_FAIL, "\u6ca1\u6709\u9009\u4e2d\u4efb\u4f55\u4e0b\u8f7d\u5b9d\uff0c\u65e0\u6cd5\u83b7\u53d6\u6570\u636e\uff01");
            return;
        }
        XZBShouleiUtil.getInstance().queryTaskList(defaultDevice, this.b, SpdyProtocol.PUBKEY_SEQ_OPEN, i2, "RemoteDownloadModule_loadMore", new y(this, eVar, i, i2));
    }

    public final void c(int i, int i2, e eVar) {
        int i3 = SpdyProtocol.PUBKEY_SEQ_OPEN;
        eVar.b();
        XZBDevice defaultDevice = XZBShouleiUtil.getInstance().getDefaultDevice();
        if (defaultDevice == null) {
            eVar.c(i, (int) TnetStatusCode.EASY_REQ_STATE_PROCESS_RSP_FAIL, "\u6ca1\u6709\u9009\u4e2d\u4efb\u4f55\u4e0b\u8f7d\u5b9d\uff0c\u65e0\u6cd5\u83b7\u53d6\u6570\u636e\uff01");
            return;
        }
        XZBShouleiUtil instance = XZBShouleiUtil.getInstance();
        if (this.b >= 10) {
            i3 = this.b;
        }
        instance.queryTaskList(defaultDevice, 0, i3, i2, "RemoteDownloadModule_loadMore", new z(this, eVar, i, i2));
    }

    public final void a(int i, e eVar) {
        eVar.b();
        XZBDevice defaultDevice = XZBShouleiUtil.getInstance().getDefaultDevice();
        if (defaultDevice == null) {
            eVar.b(i);
        } else {
            XZBShouleiUtil.getInstance().queryTaskList(defaultDevice, 0, SpdyProtocol.PUBKEY_SEQ_OPEN, SimpleLog.LOG_LEVEL_ERROR, "RemoteDownloadModule_refreshRunning", new aa(this, eVar, i));
        }
    }
}
