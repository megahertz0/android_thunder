package com.xunlei.tdlive;

import com.umeng.a;
import com.xunlei.tdlive.modal.JsonWrapper;

// compiled from: WebBrowserActivity.java
class eu extends a {
    final /* synthetic */ WebBrowserActivity a;

    eu(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        try {
            JsonWrapper jsonWrapper = new JsonWrapper(str);
            DispatcherActivity.a(this.a, new StringBuilder("tdlive://room?roomid=").append(jsonWrapper.getString("roomID", a.d)).append("&from=").append(jsonWrapper.getString("from", "webview")).toString(), 0);
        } catch (Exception e) {
        }
        return null;
    }
}
