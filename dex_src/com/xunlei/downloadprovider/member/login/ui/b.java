package com.xunlei.downloadprovider.member.login.ui;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

// compiled from: LoginActivity.java
final class b implements OnEditorActionListener {
    final /* synthetic */ LoginActivity a;

    b(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 5 && i != 0) {
            return false;
        }
        this.a.f.requestFocus();
        return true;
    }
}
