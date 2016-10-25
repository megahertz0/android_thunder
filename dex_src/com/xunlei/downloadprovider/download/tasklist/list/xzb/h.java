package com.xunlei.downloadprovider.download.tasklist.list.xzb;

import android.content.Context;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.create.CreateUrlTask;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry;
import com.xunlei.xiazaibao.sdk.XZBDevice.UrlData;
import com.xunlei.xiazaibao.shoulei.XZBShouleiCallback;

// compiled from: XiaZaiBaoUtil.java
final class h extends XZBShouleiCallback {
    final /* synthetic */ Context a;
    final /* synthetic */ boolean b;
    final /* synthetic */ SaveToXZBEntry c;
    final /* synthetic */ int d;
    final /* synthetic */ e e;

    h(e eVar, Context context, boolean z, SaveToXZBEntry saveToXZBEntry, int i) {
        this.e = eVar;
        this.a = context;
        this.b = z;
        this.c = saveToXZBEntry;
        this.d = i;
    }

    public final void cb_CreateTask(int i, int i2, UrlData[] urlDataArr, String str, Object obj) {
        int i3 = 0;
        if (this.a instanceof CreateUrlTask) {
            ((CreateUrlTask) this.a).finish();
        }
        if (i != 0) {
            new StringBuilder("\u9519\u8bef\u7801\uff1a").append(i).append(" \u9519\u8bef\u4fe1\u606f\uff1a").append(str);
            if (this.b) {
                if (i == 45) {
                    e.a(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u4e0b\u8f7d\u5b9d\u521b\u5efa\u4efb\u52a1\u5931\u8d25", str);
                } else {
                    XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u4e0b\u8f7d\u5b9d\u521b\u5efa\u4efb\u52a1\u5931\u8d25");
                }
            }
            if (!this.c.equals(SaveToXZBEntry.other)) {
                if (urlDataArr == null || urlDataArr.length <= 0) {
                    XZBReporter.c(this.c, this.d);
                } else {
                    XZBReporter.c(this.c, urlDataArr.length);
                }
            }
        } else if (urlDataArr == null || urlDataArr.length <= 0) {
            if (this.b) {
                XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u4e0b\u8f7d\u5b9d\u521b\u5efa\u4efb\u52a1\u5931\u8d25");
            }
            if (!this.c.equals(SaveToXZBEntry.other)) {
                XZBReporter.c(this.c, this.d);
            }
        } else {
            int length = urlDataArr.length;
            int i4 = 0;
            while (i3 < length) {
                if (urlDataArr[i3].result == 0) {
                    i4++;
                }
                i3++;
            }
            if (i4 == 0) {
                if (this.b) {
                    XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u4e0b\u8f7d\u5b9d\u521b\u5efa\u4efb\u52a1\u5931\u8d25");
                }
                if (!this.c.equals(SaveToXZBEntry.other)) {
                    XZBReporter.c(this.c, urlDataArr.length);
                }
            } else if (i4 > 0 && i4 < urlDataArr.length) {
                if (this.b) {
                    XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, new StringBuilder("\u4e0b\u8f7d\u5b9d\u521b\u5efa").append(i4).append("\u4e2a\u6210\u529f\u4efb\u52a1\uff0c").append(urlDataArr.length - i4).append("\u4e2a\u5931\u8d25\u4efb\u52a1").toString());
                }
                if (!this.c.equals(SaveToXZBEntry.other)) {
                    XZBReporter.b(this.c, i4);
                    XZBReporter.c(this.c, urlDataArr.length - i4);
                }
            } else if (i4 == urlDataArr.length) {
                if (this.b) {
                    XLToast.b(this.a, XLToastType.XLTOAST_TYPE_SUC, "\u4e0b\u8f7d\u5b9d\u521b\u5efa\u4efb\u52a1\u6210\u529f");
                }
                if (!this.c.equals(SaveToXZBEntry.other)) {
                    XZBReporter.b(this.c, urlDataArr.length);
                }
            }
        }
    }
}
