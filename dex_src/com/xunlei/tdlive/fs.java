package com.xunlei.tdlive;

// compiled from: WebBrowserActivity.java
class fs implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ WebBrowserActivity b;

    fs(WebBrowserActivity webBrowserActivity, String str) {
        this.b = webBrowserActivity;
        this.a = str;
    }

    public void run() {
        super.setTitle(this.a);
    }
}
