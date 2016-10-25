package com.xunlei.downloadprovider.member.login.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class XLTwoButtonDialogActivity extends Activity {
    private d a;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new d(this);
        Intent intent = getIntent();
        this.a.setTitle((CharSequence) "\u6389\u7ebf\u901a\u77e5");
        this.a.b((CharSequence) "\u7531\u4e8e\u51fa\u73b0\u5f02\u5e38\uff0c\u60a8\u7684\u8d26\u53f7\u5df2\u6389\u7ebf\uff0c\u5efa\u8bae\u91cd\u65b0\u767b\u5f55\u7ee7\u7eed\u4eab\u53d7\u5404\u9879\u670d\u52a1\u3002");
        this.a.setCanceledOnTouchOutside(false);
        switch (intent.getIntExtra(JsInterface.FUNPLAY_AD_TRPE, 1)) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.a.b(new ae(this));
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.a.b(new af(this));
                break;
        }
        this.a.a(new ag(this));
        this.a.d("\u91cd\u65b0\u767b\u5f55");
        this.a.c("\u9000\u51fa\u767b\u5f55");
    }

    protected void onResume() {
        super.onResume();
        this.a.show();
    }

    static /* synthetic */ void a(String str) {
        g gVar = new g();
        gVar.a = "android_user_login";
        gVar.c = "login_try_click";
        gVar.b = "login_try_click";
        gVar.a("clickid", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        ThunderReporter.a(gVar, true);
    }
}
