package com.xunlei.downloadprovider.web.base;

import android.graphics.drawable.Drawable;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: KandanListActivity.java
final class p implements OnPreDrawListener {
    final /* synthetic */ KandanListActivity a;

    p(KandanListActivity kandanListActivity) {
        this.a = kandanListActivity;
    }

    public final boolean onPreDraw() {
        if (!this.a.v) {
            this.a.w = this.a.o.getLineCount();
            this.a.o.setMaxLines(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            this.a.v = true;
            KandanListActivity.a;
            new StringBuilder("DESMAXLINE=>").append(this.a.w);
            if (this.a.w <= 2) {
                this.a.y = false;
                this.a.o.setCompoundDrawables(null, null, null, null);
                this.a.o.setGravity(1);
            } else {
                this.a.y = true;
                Drawable drawable = this.a.getResources().getDrawable(2130837603);
                drawable.setBounds(0, 0, g.a(this.a, 9.0f), g.a(this.a, 6.0f));
                this.a.o.setCompoundDrawables(null, null, null, drawable);
                this.a.o.setGravity(0);
            }
        }
        return true;
    }
}
