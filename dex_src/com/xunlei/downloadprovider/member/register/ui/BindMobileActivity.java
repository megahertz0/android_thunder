package com.xunlei.downloadprovider.member.register.ui;

import android.os.Bundle;
import android.widget.TextView;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.downloadprovider.member.register.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class BindMobileActivity extends MobileSetupActivity {
    private XLOnUserListener b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a.a(com.xunlei.downloadprovidercommon.a.a.a("android_login_third", "login_third_bind_show"));
    }

    public void onBackPressed() {
        super.onBackPressed();
        a.a("back");
    }

    protected final String a() {
        return getString(2131230887);
    }

    protected final void a(TextView textView) {
        textView.setVisibility(0);
        textView.setText(2131230885);
        textView.setTextSize(XZBDevice.DOWNLOAD_LIST_RECYCLE, 15.0f);
        textView.setTextColor(getResources().getColor(R.color.common_blue));
        textView.setOnClickListener(new a(this));
    }

    protected final void c() {
        if (g()) {
            c(2131230880);
            XLUserUtil.getInstance().userAqBindMobile(d(), e(), this.b, null);
        }
    }

    protected final void b() {
        a.a(com.xunlei.downloadprovidercommon.a.a.a("android_login_third", "login_third_bind_code"));
        if (g()) {
            if (this.b == null) {
                this.b = new b(this);
            }
            c(2131232144);
            XLUserUtil.getInstance().userAqSendMessage(d(), this.b, null);
        }
    }
}
