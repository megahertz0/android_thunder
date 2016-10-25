package com.xunlei.tdlive;

import com.umeng.a;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.util.q;
import java.util.HashMap;
import java.util.Map;

// compiled from: WebBrowserActivity.java
class ez extends a {
    final /* synthetic */ WebBrowserActivity a;

    ez(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        try {
            JsonWrapper jsonWrapper = new JsonWrapper(str);
            q.a(jsonWrapper.getString(NotificationCompatApi21.CATEGORY_EVENT, a.d), jsonWrapper.getString("attr1", a.d), jsonWrapper.getString("attr2", a.d), (Map) jsonWrapper.getObject("extdata", "{}").toObject(HashMap.class));
        } catch (Exception e) {
        }
        return null;
    }
}
