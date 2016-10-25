package com.xunlei.tdlive;

import com.taobao.accs.common.Constants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.user.e;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.ac;

// compiled from: WebBrowserActivity.java
class fm extends a {
    final /* synthetic */ WebBrowserActivity a;
    private String c;

    fm(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        this.c = str2;
        JsonWrapper jsonWrapper = new JsonWrapper(str);
        int i = jsonWrapper.getInt("coin_numm", 0);
        int i2 = jsonWrapper.getInt("pay_type", -1);
        if (i > 0) {
            e.a().a(new fn(this, jsonWrapper));
            e.a().a(this.a, Integer.parseInt(f.a().k()), i2, Constants.SDK_VERSION_CODE, ac.j() ? "android_live" : "android_live_sdk", i, "sence-wx-pay");
        }
        return null;
    }
}
