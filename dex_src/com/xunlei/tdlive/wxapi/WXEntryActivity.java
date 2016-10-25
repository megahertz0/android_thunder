package com.xunlei.tdlive.wxapi;

import android.os.Bundle;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.umeng.socialize.weixin.view.WXCallbackActivity;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.user.k;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;

public class WXEntryActivity extends WXCallbackActivity {
    private static final String TAG = "WXEntryActivity";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(16973839);
    }

    public void onResp(BaseResp baseResp) {
        if (onXLAccountHandleResp(baseResp)) {
            finish();
            return;
        }
        try {
            super.onResp(baseResp);
        } catch (Exception e) {
            e.printStackTrace();
            if (!isFinishing()) {
                finish();
            }
        }
    }

    private boolean onXLAccountHandleResp(BaseResp baseResp) {
        XLog.d(TAG, new StringBuilder("Wx onResp() transaction = ").append(baseResp.transaction).append(", err = ").append(baseResp.errCode).append(", errStr = ").append(baseResp.errStr).toString());
        if (baseResp.transaction != null && baseResp.transaction.startsWith("xl_sdk_get_access_code#")) {
            int intValue;
            int i;
            String str;
            String str2 = BuildConfig.VERSION_NAME;
            try {
                intValue = Integer.valueOf(baseResp.transaction.substring(R.styleable.Toolbar_titleTextColor)).intValue();
            } catch (Throwable th) {
                intValue = 0;
            }
            String str3;
            if (baseResp.errCode == 0) {
                str2 = ((Resp) baseResp).code;
                f.a().b(true);
                str3 = str2;
                i = 0;
                str = str3;
            } else if (baseResp.errCode == -2) {
                f.a().e();
                str3 = str2;
                i = 16781283;
                str = str3;
            } else {
                str = str2;
                i = 16781282;
            }
            respToWxLoginTask(i, str, intValue);
            return true;
        } else if (!"transaction_xl_wx_bind".equals(baseResp.transaction)) {
            return "xl_sdk_contract".equals(baseResp.transaction);
        } else {
            k.a().a(baseResp.errCode, ((Resp) baseResp).code);
            return true;
        }
    }

    private void respToWxLoginTask(int i, String str, int i2) {
        XLUserUtil.getInstance().acceptWxCode(i, str, i2);
    }
}
