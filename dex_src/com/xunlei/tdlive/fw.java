package com.xunlei.tdlive;

import com.umeng.a;
import com.xunlei.download.proguard.y;
import com.xunlei.tdlive.modal.JsonWrapper;

// compiled from: WebBrowserActivity.java
class fw extends a {
    final /* synthetic */ WebBrowserActivity a;
    private String c;
    private String d;

    fw(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public boolean a() {
        if (this.c == null || this.c.length() <= 0) {
            return false;
        }
        this.a.callJS(this.c, new JsonWrapper(this.d));
        return true;
    }

    public String a(String str, String str2) {
        try {
            JsonWrapper jsonWrapper = new JsonWrapper(str);
            this.d = jsonWrapper.getString(y.g, "{}");
            this.c = str2;
            String string = jsonWrapper.getString("icon", a.d);
            if (string.length() <= 0) {
                this.a.setRightVisible(true);
                this.a.setRightDrawable(this.a.getResources().getDrawable(R.drawable.xllive_more));
            } else {
                com.xunlei.tdlive.util.a.a(this.a).a(string, new fx(this));
            }
        } catch (Exception e) {
        }
        return null;
    }
}
