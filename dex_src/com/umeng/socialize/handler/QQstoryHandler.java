package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareListener;

public class QQstoryHandler extends UMTencentSSOHandler {
    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
    }

    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        return false;
    }
}
