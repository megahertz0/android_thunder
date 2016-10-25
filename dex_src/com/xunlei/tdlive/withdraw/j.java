package com.xunlei.tdlive.withdraw;

import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.xllib.R;

// compiled from: VerifyCodeCheckPage.java
class j implements JsonCallBack {
    final /* synthetic */ f a;

    j(f fVar) {
        this.a = fVar;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (this.a.c()) {
            if (i != 0) {
                this.a.a_(str);
            }
            if (i != 0) {
                this.a.d(R.styleable.AppCompatTheme_buttonStyle);
                f.b(this.a, true);
            }
        }
    }
}
