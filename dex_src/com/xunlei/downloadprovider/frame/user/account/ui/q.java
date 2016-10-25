package com.xunlei.downloadprovider.frame.user.account.ui;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.tdlive.R;

// compiled from: UserAccountPortraitSettingActivity.java
final class q implements a {
    final /* synthetic */ UserAccountPortraitSettingActivity a;

    q(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity) {
        this.a = userAccountPortraitSettingActivity;
    }

    public final void a(Message message) {
        switch (message.what) {
            case R.styleable.AppCompatTheme_ratingBarStyleIndicator:
                bx bxVar = new bx();
                this.a.e.a(bxVar.e, bxVar.e(), this.a.c, false);
            default:
                break;
        }
    }
}
