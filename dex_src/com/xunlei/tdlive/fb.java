package com.xunlei.tdlive;

import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.tdlive.modal.JsonWrapper;

// compiled from: WebBrowserActivity.java
class fb extends a {
    final /* synthetic */ WebBrowserActivity a;

    fb(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        boolean z = true;
        JsonWrapper jsonWrapper = new JsonWrapper(str);
        WebBrowserActivity webBrowserActivity = this.a;
        String string = jsonWrapper.getString(SocialConstants.PARAM_SEND_MSG, a.d);
        if (jsonWrapper.getInt("cancelable", 1) == 0) {
            z = false;
        }
        webBrowserActivity.showLoadingDialog(string, z);
        return null;
    }
}
