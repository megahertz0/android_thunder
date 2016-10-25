package com.xunlei.downloadprovider.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.umeng.a;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.downloadprovidershare.az;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    public static boolean a;
    private IWXAPI b;

    static {
        a = true;
    }

    public WXEntryActivity() {
        this.b = az.a().d;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b.handleIntent(getIntent(), this);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.b.handleIntent(intent, this);
    }

    public void onReq(BaseReq baseReq) {
    }

    public void onResp(BaseResp baseResp) {
        int i = 0;
        if (!TextUtils.isEmpty(baseResp.transaction)) {
            String str = "xl_sdk_get_access_code#";
            int i2;
            if (baseResp.transaction.indexOf(str) != -1) {
                int intValue;
                String str2;
                new StringBuilder().append(getClass()).append("---xl_sdk_get_access_code---").append(Thread.currentThread().getId());
                String str3 = a.d;
                try {
                    intValue = Integer.valueOf(baseResp.transaction.substring(str.length())).intValue();
                } catch (Exception e) {
                    intValue = 0;
                }
                if (baseResp.errCode == 0) {
                    str2 = ((Resp) baseResp).code;
                    i2 = 0;
                } else {
                    i2 = 16781282;
                    str2 = str3;
                }
                if (baseResp.errCode == -2) {
                    Object obj = XLErrorCode.AUTH_USER_CANCLE;
                }
                if (baseResp.errCode == 0) {
                    Resp resp = (Resp) baseResp;
                    str2 = resp.code;
                    intValue = Integer.valueOf(resp.state).intValue();
                    d.b().d();
                } else {
                    i = i2;
                }
                XLUserUtil.getInstance().acceptWxCode(i, str2, intValue);
            } else {
                i2 = baseResp.errCode;
                if (i2 == 0) {
                    d.b().a(0, i2);
                } else if (i2 == -2) {
                    d.b().a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, i2);
                } else {
                    d.b().a(1, i2);
                }
                baseResp.transaction.indexOf("xl_sdk_contract");
            }
        }
        finish();
    }
}
