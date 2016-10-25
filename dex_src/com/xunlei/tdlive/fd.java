package com.xunlei.tdlive;

import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.tdlive.modal.JsonWrapper;

// compiled from: WebBrowserActivity.java
class fd extends a {
    final /* synthetic */ WebBrowserActivity a;

    fd(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        JsonWrapper jsonWrapper = new JsonWrapper(str);
        this.a.showToast(jsonWrapper.getString(SocialConstants.PARAM_SEND_MSG, a.d), jsonWrapper.getInt("duration", 0), R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
        return null;
    }
}
