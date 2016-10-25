package com.xunlei.tdlive;

import android.view.inputmethod.InputMethodManager;

// compiled from: WebBrowserActivity.java
class ex extends a {
    final /* synthetic */ WebBrowserActivity a;

    ex(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        try {
            ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.mEditView.getWindowToken(), 0);
        } catch (Exception e) {
        }
        return null;
    }
}
