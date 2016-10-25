package com.xunlei.common.member.act;

import android.app.Activity;
import android.os.Bundle;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.b.b;
import com.xunlei.common.member.b.d;
import com.xunlei.common.member.c.a;

public class XLAlipayLoginActivity extends Activity implements d {
    private b mAlipayAuth;
    private XLAlipayParam mAlipayParam;
    private int mAlipayTaskId;

    public XLAlipayLoginActivity() {
        this.mAlipayAuth = null;
        this.mAlipayTaskId = 0;
        this.mAlipayParam = null;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlipayTaskId = getIntent().getIntExtra("ali_task", 0);
        this.mAlipayParam = (XLAlipayParam) getIntent().getParcelableExtra("ali_auth_param");
        if (this.mAlipayTaskId == 0) {
            sendResultToHostTask(268439553, null, null, null);
            return;
        }
        this.mAlipayAuth = new b(this, this.mAlipayParam, this);
        this.mAlipayAuth.a();
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onAlipayAuth(int i, String str, String str2, String str3) {
        sendResultToHostTask(i, str, str2, str3);
    }

    public void sendResultToHostTask(int i, String str, String str2, String str3) {
        a aVar = (a) m.a().a(this.mAlipayTaskId);
        if (aVar != null) {
            aVar.a(i, str, str2, str3);
        }
    }
}
