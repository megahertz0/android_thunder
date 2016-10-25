package com.xunlei.downloadprovider.member.login.ui;

import android.os.Bundle;
import android.os.Message;
import com.xunlei.downloadprovider.member.login.LoginHelper.o;
import com.xunlei.tdlive.R;

// compiled from: UserAccountInfoActivity.java
final class aa implements o {
    final /* synthetic */ UserAccountInfoActivity a;

    aa(UserAccountInfoActivity userAccountInfoActivity) {
        this.a = userAccountInfoActivity;
    }

    public final void a(int i, long j, long j2) {
        new StringBuilder("UserInfoActivity() query high speed channel flux status >> ").append(i).append("capacity  >>").append(j).append("remain >>").append(j2);
        if (i == 2 || i == 0) {
            com.xunlei.downloadprovider.util.aa.a(this.a.getApplicationContext(), "flowtotal", j);
            com.xunlei.downloadprovider.util.aa.a(this.a.getApplicationContext(), "flowused", j - j2);
            Message obtainMessage = this.a.H.obtainMessage(R.styleable.AppCompatTheme_buttonStyleSmall);
            Bundle bundle = new Bundle();
            bundle.putLong("flowtotal", j);
            bundle.putLong("flowused", j - j2);
            obtainMessage.setData(bundle);
            this.a.H.sendMessage(obtainMessage);
        }
    }
}
