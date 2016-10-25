package com.umeng.socialize.weixin.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMWXHandler;
import com.umeng.socialize.utils.Log;

public abstract class WXCallbackActivity extends Activity implements IWXAPIEventHandler {
    private final String TAG;
    protected UMWXHandler mWxHandler;

    public WXCallbackActivity() {
        this.TAG = WXCallbackActivity.class.getSimpleName();
        this.mWxHandler = null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("create wx callback activity");
        this.mWxHandler = (UMWXHandler) UMShareAPI.get(getApplicationContext()).getHandler(SHARE_MEDIA.WEIXIN);
        Log.e(new StringBuilder("xxxx wxhandler=").append(this.mWxHandler).toString());
        this.mWxHandler.onCreate(getApplicationContext(), PlatformConfig.getPlatform(SHARE_MEDIA.WEIXIN));
        this.mWxHandler.getWXApi().handleIntent(getIntent(), this);
    }

    protected final void onNewIntent(Intent intent) {
        Log.d(this.TAG, "### WXCallbackActivity   onNewIntent");
        super.onNewIntent(intent);
        setIntent(intent);
        this.mWxHandler = (UMWXHandler) UMShareAPI.get(getApplicationContext()).getHandler(SHARE_MEDIA.WEIXIN);
        this.mWxHandler.onCreate(getApplicationContext(), PlatformConfig.getPlatform(SHARE_MEDIA.WEIXIN));
        this.mWxHandler.getWXApi().handleIntent(intent, this);
    }

    public void onResp(BaseResp baseResp) {
        if (!(this.mWxHandler == null || baseResp == null)) {
            try {
                this.mWxHandler.getWXEventHandler().onResp(baseResp);
            } catch (Exception e) {
            }
        }
        finish();
    }

    public void onReq(BaseReq baseReq) {
        if (this.mWxHandler != null) {
            this.mWxHandler.getWXEventHandler().onReq(baseReq);
        }
        finish();
    }
}
