package com.xunlei.downloadprovider.bho;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ScanCodeResultActivity.java
final class j implements OnClickListener {
    final /* synthetic */ ScanCodeResultActivity a;

    j(ScanCodeResultActivity scanCodeResultActivity) {
        this.a = scanCodeResultActivity;
    }

    public final void onClick(View view) {
        c.a();
        c.a(this.a.q, this.a.G);
        this.a.G.sendEmptyMessageDelayed(XZBDevice.DOWNLOAD_LIST_RECYCLE, 1000);
    }
}
