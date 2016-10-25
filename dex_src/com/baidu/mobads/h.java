package com.baidu.mobads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alipay.sdk.sys.a;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.e.d;

class h extends Handler {
    final /* synthetic */ AppActivity a;

    h(AppActivity appActivity, Looper looper) {
        this.a = appActivity;
        super(looper);
    }

    public void handleMessage(Message message) {
        m.a().m();
        IXAdURIUitls i = m.a().i();
        StringBuilder stringBuilder = new StringBuilder(new StringBuilder("type=").append(message.what).append(a.b).toString());
        String str = com.umeng.a.d;
        if (message.what == AppActivity.G) {
            str = this.a.A.toString();
        }
        try {
            d dVar = new d(i.addParameters(new StringBuilder("https://mobads-logs.baidu.com/dz.zb?").append(stringBuilder.append(str).toString()).toString(), null), com.umeng.a.d);
            dVar.e = 1;
            new com.baidu.mobads.openad.e.a().a(dVar);
        } catch (Exception e) {
            this.a.D.d(AppActivity.o, e.getMessage());
        }
    }
}
