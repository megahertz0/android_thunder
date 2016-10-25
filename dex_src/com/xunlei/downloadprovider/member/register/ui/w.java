package com.xunlei.downloadprovider.member.register.ui;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.tdlive.R;

// compiled from: RegisterSuccessActivity.java
final class w implements a {
    final /* synthetic */ RegisterSuccessActivity a;

    w(RegisterSuccessActivity registerSuccessActivity) {
        this.a = registerSuccessActivity;
    }

    public final void a(Message message) {
        switch (message.what) {
            case R.styleable.AppCompatTheme_seekBarStyle:
                bx bxVar = new bx();
                new StringBuilder("messageListener MSG_UPDATE_USER_PORTRAIT userLocalInfo.getPortraitPath() ===").append(bxVar.e);
                this.a.l.a(bxVar.e, bxVar.e(), this.a.d, false);
            default:
                break;
        }
    }
}
