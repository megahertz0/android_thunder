package com.xunlei.tdlive;

import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.tdlive.modal.JsonWrapper;

// compiled from: WebBrowserActivity.java
class ew extends a {
    final /* synthetic */ WebBrowserActivity a;

    ew(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        try {
            JsonWrapper jsonWrapper = new JsonWrapper(str);
            WebBrowserActivity.start(this.a, jsonWrapper.getString(SocialConstants.PARAM_URL, a.d), jsonWrapper.getString(WebBrowserActivity.EXTRA_TITLE, a.d), jsonWrapper.getBoolean("showBtn", false));
        } catch (Exception e) {
        }
        return null;
    }
}
