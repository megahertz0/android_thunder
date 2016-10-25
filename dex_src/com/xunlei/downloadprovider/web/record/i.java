package com.xunlei.downloadprovider.web.record;

import android.widget.Toast;
import com.umeng.a;
import com.xunlei.downloadprovider.web.record.RecordPageView.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: FavorAndHistroyActivity.java
final class i implements d {
    final /* synthetic */ FavorAndHistroyActivity a;

    i(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void a(long j, boolean z, int i) {
        if (i == 1) {
            if (z) {
                this.a.E.setText("(\u6709\u66f4\u65b0)");
                this.a.v.setVisibility(0);
                this.a.v.setText("(\u6709\u66f4\u65b0)");
            }
        } else if (i == 2) {
            this.a.a(j);
            this.a.w.clearAnimation();
            this.a.E.setText(a.d);
            this.a.v.setText(a.d);
            this.a.v.setVisibility(XZBDevice.Wait);
            this.a.a(System.currentTimeMillis(), null);
            this.a.z.setText(new StringBuilder("\u4e0a\u6b21\u540c\u6b65:").append(FavorAndHistroyActivity.c(System.currentTimeMillis())).toString());
            this.a.a();
        } else if (i == 4) {
            this.a.a(j);
            this.a.E.setText(a.d);
            this.a.v.setText(a.d);
            this.a.v.setVisibility(XZBDevice.Wait);
            this.a.w.clearAnimation();
            this.a.a(System.currentTimeMillis(), null);
            this.a.z.setText(new StringBuilder("\u4e0a\u6b21\u540c\u6b65:").append(FavorAndHistroyActivity.c(System.currentTimeMillis())).toString());
            Toast.makeText(this.a, "\u540c\u6b65\u6210\u529f", 0).show();
            this.a.a();
        }
    }

    public final void a(int i) {
        if (i == 1) {
            this.a.v.setVisibility(XZBDevice.Wait);
        } else if (i == 2) {
            this.a.w.clearAnimation();
        } else if (i == 4) {
            this.a.w.clearAnimation();
            Toast.makeText(this.a, "\u540c\u6b65\u5931\u8d25", 0).show();
        }
    }
}
