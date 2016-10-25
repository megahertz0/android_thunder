package com.umeng.socialize.media;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler.Response;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.SinaSsoHandler;
import com.umeng.socialize.utils.Log;

public class WBShareCallBackActivity extends Activity implements Response {
    protected SinaSsoHandler sinaSsoHandler;

    public WBShareCallBackActivity() {
        this.sinaSsoHandler = null;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("create wx callback activity");
        this.sinaSsoHandler = (SinaSsoHandler) UMShareAPI.get(getApplicationContext()).getHandler(SHARE_MEDIA.SINA);
        this.sinaSsoHandler.onCreate(this, PlatformConfig.getPlatform(SHARE_MEDIA.SINA));
        if (getIntent() != null) {
            this.sinaSsoHandler.getmWeiboShareAPI().handleWeiboResponse(getIntent(), this);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.sinaSsoHandler = (SinaSsoHandler) UMShareAPI.get(getApplicationContext()).getHandler(SHARE_MEDIA.SINA);
        this.sinaSsoHandler.onCreate(this, PlatformConfig.getPlatform(SHARE_MEDIA.SINA));
        this.sinaSsoHandler.getmWeiboShareAPI().handleWeiboResponse(intent, this);
    }

    public void onResponse(BaseResponse baseResponse) {
        if (this.sinaSsoHandler != null) {
            this.sinaSsoHandler.onResponse(baseResponse);
        }
        finish();
    }
}
