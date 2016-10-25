package com.xunlei.downloadprovider.member.login.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: LoginActivity.java
final class r implements OnClickListener {
    final /* synthetic */ LoginActivity a;

    r(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public final void onClick(View view) {
        this.a.e.setText(a.d);
        this.a.e.requestFocus();
        ((InputMethodManager) this.a.getSystemService("input_method")).showSoftInput(this.a.e, XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }
}
