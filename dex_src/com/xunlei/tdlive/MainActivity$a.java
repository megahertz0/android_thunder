package com.xunlei.tdlive;

import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.im.MessageDispatcher.ConnectCallback;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;

class MainActivity$a extends ConnectCallback {
    boolean a;
    final /* synthetic */ MainActivity b;

    MainActivity$a(MainActivity mainActivity) {
        this.b = mainActivity;
        this.a = false;
    }

    public void onIMConnected(int i, String str) {
        XLog.d("MainActivity", new StringBuilder("onIMConnected:").append(i).append(", msg:").append(str).toString());
        if (i == 0 && !this.a) {
            this.a = true;
            a();
        } else if (i == 4 || i == 5) {
            f.a(this.b).g();
        }
        if (i == 0) {
            MainActivity.a(this.b);
        }
    }

    private void a() {
        if (e.e != null && e.e.length() > 0) {
            new c(this.b, null, "\u6b22\u8fce\u56de\u6765\uff0c\u7ee7\u7eed\u76f4\u64ad\u5417\uff1f", "\u53d6\u6d88", new String[]{"\u786e\u5b9a"}).b(new dq(this));
        }
    }
}
