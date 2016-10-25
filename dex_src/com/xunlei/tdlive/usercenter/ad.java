package com.xunlei.tdlive.usercenter;

import com.xunlei.tdlive.R;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;

// compiled from: UserListFragment2.java
class ad implements JsonCallBack {
    final /* synthetic */ ac a;

    ad(ac acVar) {
        this.a = acVar;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0) {
            y.a(this.a.c).c(this.a.b);
        } else {
            this.a.c.a("\u5220\u9664\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u5c1d\u8bd5", 0, R.layout.xllive_toast_uiserinfo_edit, R.id.text, com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
        }
    }
}
