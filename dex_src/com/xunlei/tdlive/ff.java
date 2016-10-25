package com.xunlei.tdlive;

import com.umeng.a;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.usercenter.UserCenterActivity;

// compiled from: WebBrowserActivity.java
class ff extends a {
    final /* synthetic */ WebBrowserActivity a;

    ff(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        JsonWrapper jsonWrapper = new JsonWrapper(str);
        JsonWrapper object = jsonWrapper.getObject("user_info", "{}");
        UserCenterActivity.a(this.a, object.getString("userid", a.d), object.getString("nickname", a.d), jsonWrapper.getString("from", "webview"));
        return null;
    }
}
