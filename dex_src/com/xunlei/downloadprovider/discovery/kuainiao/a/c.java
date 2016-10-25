package com.xunlei.downloadprovider.discovery.kuainiao.a;

import android.os.Handler;
import com.xunlei.common.accelerator.XLAccelUtil;
import com.xunlei.common.accelerator.XLOnAccelListener;
import com.xunlei.common.accelerator.bean.XLAccelBandInfo;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.d.a;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: KuaiNiaoAccelerator.java
final class c implements XLOnAccelListener {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void callBack(int i, int i2, String str) {
        switch (i) {
            case SimpleLog.LOG_LEVEL_ERROR:
                if (this.a.e && this.a.b != null) {
                    this.a.e = false;
                    XLAccelBandInfo bandInfoObject = XLAccelUtil.getAccelerator().getBandInfoObject();
                    if (bandInfoObject == null || bandInfoObject.mCurrentBandWidth == null) {
                        this.a.a = this.a.b.getResources().getString(R.string.kuainiao_original_speed);
                    } else {
                        this.a.a = a.a(bandInfoObject.mCurrentBandWidth.mDownStream * 1024);
                    }
                    if (this.a.c.empty() || !com.xunlei.downloadprovider.a.a.a(this.a.b)) {
                        b.a(this.a, this.a.a);
                        return;
                    }
                    b.a(this.a, this.a.a);
                    new Handler().postDelayed(new d(this), 3000);
                }
            default:
                this.a.e = true;
        }
    }
}
