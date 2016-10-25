package com.xunlei.downloadprovider.frame.user.account.ui;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.tdlive.R;

// compiled from: UserAccountInfoActivityNew.java
final class g implements a {
    final /* synthetic */ UserAccountInfoActivityNew a;

    g(UserAccountInfoActivityNew userAccountInfoActivityNew) {
        this.a = userAccountInfoActivityNew;
    }

    public final void a(Message message) {
        switch (message.what) {
            case R.styleable.AppCompatTheme_radioButtonStyle:
                UserAccountInfoActivityNew.i(this.a);
            default:
                break;
        }
    }
}
