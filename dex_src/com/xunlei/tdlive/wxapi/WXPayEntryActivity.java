package com.xunlei.tdlive.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.xiazaibao.BuildConfig;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;

    public WXPayEntryActivity() {
        this.api = null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.api = WXAPIFactory.createWXAPI(this, BuildConfig.VERSION_NAME);
        this.api.handleIntent(getIntent(), this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.api.handleIntent(intent, this);
    }

    public void onReq(BaseReq baseReq) {
    }

    public void onResp(BaseResp baseResp) {
        XLog.d(TAG, new StringBuilder("onPayFinish, errCode = ").append(baseResp.errCode).toString());
        if (baseResp.getType() == 5) {
            XLPayUtil.getInstance().acceptWxPayResult(Integer.valueOf(((PayResp) baseResp).extData).intValue(), baseResp.errCode);
        }
        finish();
    }
}
