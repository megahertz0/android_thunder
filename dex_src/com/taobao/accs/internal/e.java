package com.taobao.accs.internal;

import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.taobao.accs.utl.ALog;

// compiled from: Taobao
class e implements Runnable {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    public void run() {
        ALog.w("ElectionServiceImpl", "time out, onReportComplete", IXAdCommonUtils.PKGS_PREF_DOWNLOAD_KEY, this.a.c);
        this.a.e();
    }
}
