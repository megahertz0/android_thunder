package com.xunlei.downloadprovider.member.login.ui;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: UserAccountInfoActivity.java
final class x implements a {
    final /* synthetic */ UserAccountInfoActivity a;

    x(UserAccountInfoActivity userAccountInfoActivity) {
        this.a = userAccountInfoActivity;
    }

    public final void a(Message message) {
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyle:
                switch (message.arg1) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        this.a.b();
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        break;
                    default:
                        break;
                }
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                long j = message.getData().getLong("flowtotal");
                long j2 = message.getData().getLong("flowused");
                CharSequence charSequence = com.umeng.a.d;
                try {
                    charSequence = this.a.a(com.xunlei.downloadprovider.d.a.a(j2, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE)) + "/" + this.a.a(com.xunlei.downloadprovider.d.a.a(j, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE));
                } catch (Exception e) {
                }
                if (this.a.B.getVisibility() == 0) {
                    if (j > 0) {
                        this.a.y.setVisibility(0);
                        this.a.x.setBackgroundResource(2130838625);
                        float width = (((float) this.a.i.getWidth()) * ((float) j2)) / ((float) j);
                        this.a.A.setText(charSequence);
                        this.a.z.setProgressBar(width);
                        return;
                    }
                    this.a.y.setVisibility(XZBDevice.Wait);
                } else if (this.a.u.getVisibility() != 0) {
                } else {
                    if (j != 0) {
                        this.a.s.setText(charSequence);
                    } else {
                        this.a.s.setText(2131233059);
                    }
                }
            default:
                break;
        }
    }
}
