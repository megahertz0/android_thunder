package com.xunlei.tdlive.play.view;

import android.content.Context;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.play.view.ah.a;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: UserInfoWindowHelper.java
class ap implements ObjectCallBack {
    final /* synthetic */ a a;
    final /* synthetic */ boolean b;
    final /* synthetic */ ah c;

    ap(ah ahVar, a aVar, boolean z) {
        this.c = ahVar;
        this.a = aVar;
        this.b = z;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0) {
            this.a.g = this.b;
            ah.b(this.c, false);
            if (this.a.h != null) {
                this.a.h.a(this.b);
            }
            a aVar = (a) this.c.e();
            if (aVar.i) {
                aVar.d += (long) (aVar.g ? 1 : -1);
                ah.a(this.c, aVar.d, false, aVar.i);
                return;
            }
            return;
        }
        String str2 = (this.b ? "\u5173\u6ce8" : "\u5df2\u5173\u6ce8") + "\u5931\u8d25\uff0c" + str;
        Context context = (Context) this.c.b.get();
        if (context != null) {
            n.a(context, str2);
        }
    }
}
