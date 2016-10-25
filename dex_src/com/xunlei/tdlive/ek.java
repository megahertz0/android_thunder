package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.protocol.DNSCache;
import com.xunlei.tdlive.protocol.XLLiveRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.IDNSCache;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import org.android.spdy.SpdyProtocol;

// compiled from: SettingActivity.java
class ek implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ SettingActivity c;

    ek(SettingActivity settingActivity, int i, ArrayList arrayList) {
        this.c = settingActivity;
        this.a = i;
        this.b = arrayList;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (i != 0) {
            if (i == 1) {
                if (this.a == 0) {
                    this.c.putInt("xllive_enable_log", 1);
                    XLog.enableLog(true);
                } else {
                    this.c.putInt("xllive_enable_log", 0);
                    XLog.enableLog(false);
                }
                this.c.showToast(this.a == 0 ? "\u5df2\u5f00\u542f\u65e5\u5fd7" : "\u5df2\u5173\u95ed\u65e5\u5fd7", 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
            } else if (i == 2) {
                e.m = 999999;
            } else if (i == 3) {
                WebBrowserActivity.start(this.c, "about:blank", null, true, true);
            } else if (i == this.b.size()) {
                this.c.putString("xllive_dns_cache", BuildConfig.VERSION_NAME);
                XLLiveRequest.setDNSCahce(null);
                f.a(this.c).g();
                this.c.showToast("\u5207\u6362\u5230\u6b63\u5f0f\u73af\u5883", 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
            } else {
                IDNSCache iDNSCache = DNSCache.getDNSCaches()[(i - this.b.size()) - 1];
                this.c.putString("xllive_dns_cache", iDNSCache.did());
                XLLiveRequest.setDNSCahce(iDNSCache);
                f.a(this.c).g();
                this.c.showToast(new StringBuilder("\u5207\u6362\u5230").append(iDNSCache.disc()).toString(), 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
            }
        }
    }
}
