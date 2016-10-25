package com.xunlei.common.member.c;

import android.os.Bundle;
import com.xunlei.common.member.XLErrorCode;

// compiled from: UserSinaLoginTask.java
final class o$1 implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ o b;

    o$1(o oVar, int i) {
        this.b = oVar;
        this.a = i;
    }

    public final void run() {
        Bundle bundle = new Bundle();
        bundle.putInt("errorCode", this.a);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(this.a));
        this.b.g().a(this.b, bundle);
    }
}
