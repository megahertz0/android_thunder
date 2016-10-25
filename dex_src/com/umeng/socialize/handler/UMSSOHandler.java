package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.view.UMFriendListener;

public abstract class UMSSOHandler {
    private static final String TAG = "UMSSOHandler";
    private String mCaller;
    private Platform mConfig;
    private Context mContext;
    public String mTargetUrl;
    protected int mThumbLimit;

    public abstract boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener);

    public UMSSOHandler() {
        this.mContext = null;
        this.mConfig = null;
        this.mThumbLimit = 32768;
    }

    public void onCreate(Context context, Platform platform) {
        this.mContext = context.getApplicationContext();
        this.mConfig = platform;
    }

    public String getCaller() {
        return this.mCaller;
    }

    public void setCaller(String str) {
        this.mCaller = str;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Platform getConfig() {
        return this.mConfig;
    }

    public void authorize(Activity activity, UMAuthListener uMAuthListener) {
    }

    public void deleteAuth(Context context, UMAuthListener uMAuthListener) {
    }

    public void getfriend(Activity activity, UMFriendListener uMFriendListener) {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void getPlatformInfo(Activity activity, UMAuthListener uMAuthListener) {
        Log.d("'getPlatformInfo', it works!");
    }

    public boolean isInstall(Context context) {
        Log.e("\u8be5\u5e73\u53f0\u4e0d\u652f\u6301\u67e5\u8be2");
        return true;
    }

    public boolean isAuthorize(Context context) {
        Log.e("\u8be5\u5e73\u53f0\u4e0d\u652f\u6301\u67e5\u8be2");
        return true;
    }

    public int getRequestCode() {
        return 0;
    }

    public boolean isSupportAuth() {
        return false;
    }

    public void onNewIntent(Intent intent) {
    }
}
