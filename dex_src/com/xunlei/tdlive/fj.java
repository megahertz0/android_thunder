package com.xunlei.tdlive;

import com.taobao.accs.common.Constants;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.user.f;

// compiled from: WebBrowserActivity.java
class fj extends a {
    final /* synthetic */ WebBrowserActivity a;

    fj(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        JsonWrapper jsonWrapper = new JsonWrapper("{}");
        jsonWrapper.putString("userid", f.a(this.a).k());
        jsonWrapper.putString("nickname", f.a(this.a).m());
        jsonWrapper.putString("avatar", f.a(this.a).o());
        JsonWrapper jsonWrapper2 = new JsonWrapper("{}");
        jsonWrapper2.putInt("isLogin", XLUserUtil.getInstance().userIsLogined() ? 1 : 0);
        jsonWrapper2.put(Constants.KEY_USER_ID, jsonWrapper);
        this.a.callJS(str2, jsonWrapper2);
        return null;
    }
}
