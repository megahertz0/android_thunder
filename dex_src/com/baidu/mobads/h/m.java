package com.baidu.mobads.h;

import android.support.v4.widget.AutoScrollHelper;
import com.baidu.mobads.a.b;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.taobao.accs.common.Constants;

class m implements IOAdEventListener {
    final /* synthetic */ double a;
    final /* synthetic */ l b;

    m(l lVar, double d) {
        this.b = lVar;
        this.a = d;
    }

    public void run(IOAdEvent iOAdEvent) {
        boolean z = true;
        this.b.a.b.l();
        if ("URLLoader.Load.Complete".equals(iOAdEvent.getType())) {
            boolean z2;
            this.b.a.b.j = new e((String) iOAdEvent.getData().get(Constants.SHARED_MESSAGE_ID_FILE));
            double a = b.a();
            float f = this.b.a.b.j().getFloat("__badApkVersion__8.27", AutoScrollHelper.RELATIVE_UNSPECIFIED);
            if (((float) this.b.a.b.j.b()) == f) {
                z2 = true;
            } else {
                z2 = false;
            }
            Boolean valueOf = Boolean.valueOf(z2);
            if (a >= this.b.a.b.j.b() || Math.floor(a) != Math.floor(this.b.a.b.j.b())) {
                z = false;
            }
            Boolean valueOf2 = Boolean.valueOf(z);
            new StringBuilder("try to download apk badVer=").append(f).append(", isBad=").append(valueOf).append(", compatible=").append(valueOf2);
            if (this.a < this.b.a.b.j.b() && this.b.a.b.j != null && this.b.a.b.j.a().booleanValue() && valueOf2.booleanValue() && !valueOf.booleanValue()) {
                this.b.a.b.a(this.b.a.b.j);
            }
        }
    }
}
