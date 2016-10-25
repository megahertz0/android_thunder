package com.xunlei.downloadprovider.frame.user.account.ui;

import android.view.inputmethod.InputMethodManager;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.TimerTask;

// compiled from: UserAccountNicknameActivity.java
final class i extends TimerTask {
    final /* synthetic */ UserAccountNicknameActivity a;

    i(UserAccountNicknameActivity userAccountNicknameActivity) {
        this.a = userAccountNicknameActivity;
    }

    public final void run() {
        ((InputMethodManager) this.a.getSystemService("input_method")).toggleSoftInput(0, XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }
}
