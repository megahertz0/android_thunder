package com.xunlei.tdlive;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: LoginActivity.java
class dd implements OnClickListener {
    final /* synthetic */ LoginActivity a;

    dd(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public void onClick(View view) {
        this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://aq.xunlei.com/password_find.html")));
    }
}
