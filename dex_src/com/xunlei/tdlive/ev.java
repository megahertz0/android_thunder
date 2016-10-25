package com.xunlei.tdlive;

// compiled from: WebBrowserActivity.java
class ev extends a {
    final /* synthetic */ WebBrowserActivity a;

    ev(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        try {
            DispatcherActivity.a(this.a, "tdlive://recharge", 0);
        } catch (Exception e) {
        }
        return null;
    }
}
