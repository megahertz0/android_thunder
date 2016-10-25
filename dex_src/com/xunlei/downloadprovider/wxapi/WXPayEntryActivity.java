package com.xunlei.downloadprovider.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.pay.XLPayUtil;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI a;

    public WXPayEntryActivity() {
        this.a = null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = WXAPIFactory.createWXAPI(this, a.d);
        this.a.handleIntent(getIntent(), this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.a.handleIntent(intent, this);
    }

    public void onReq(BaseReq baseReq) {
    }

    public void onResp(BaseResp baseResp) {
        XLLog.v("MicroMsg.SDKSample.WXPayEntryActivity", new StringBuilder("onPayFinish, errCode = ").append(baseResp.errCode).toString());
        if (baseResp.getType() == 5) {
            XLPayUtil.getInstance().acceptWxPayResult(Integer.valueOf(((PayResp) baseResp).extData).intValue(), baseResp.errCode);
        }
        finish();
    }
}
