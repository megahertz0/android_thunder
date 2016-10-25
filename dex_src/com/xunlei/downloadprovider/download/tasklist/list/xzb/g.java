package com.xunlei.downloadprovider.download.tasklist.list.xzb;

import android.content.Context;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.create.CreateUrlTask;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiCallback;

// compiled from: XiaZaiBaoUtil.java
final class g extends XZBShouleiCallback {
    final /* synthetic */ Context a;
    final /* synthetic */ boolean b;
    final /* synthetic */ SaveToXZBEntry c;
    final /* synthetic */ e d;

    g(e eVar, Context context, boolean z, SaveToXZBEntry saveToXZBEntry) {
        this.d = eVar;
        this.a = context;
        this.b = z;
        this.c = saveToXZBEntry;
    }

    public final void cb_createBtTask(int i, int i2, XZBDevice xZBDevice, String str, Object obj) {
        if (this.a instanceof CreateUrlTask) {
            ((CreateUrlTask) this.a).finish();
        }
        if (i != 0) {
            Object obj2 = null;
            if (this.b) {
                if (i == 45) {
                    new StringBuilder("\u9519\u8bef\u7801\uff1a").append(i).append(" \u9519\u8bef\u4fe1\u606f\uff1a").append(str);
                    e.a(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u4e0b\u8f7d\u5b9d\u521b\u5efa\u4efb\u52a1\u5931\u8d25", str);
                } else {
                    XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u4e0b\u8f7d\u5b9d\u521b\u5efa\u4efb\u52a1\u5931\u8d25");
                }
            }
        } else {
            if (this.b) {
                XLToast.b(this.a, XLToastType.XLTOAST_TYPE_SUC, "\u4e0b\u8f7d\u5b9d\u521b\u5efa\u4efb\u52a1\u6210\u529f");
            }
            int i3 = 1;
        }
        if (!this.c.equals(SaveToXZBEntry.other)) {
            if (i3 != 0) {
                XZBReporter.b(this.c, 1);
            } else {
                XZBReporter.c(this.c, 1);
            }
        }
    }
}
