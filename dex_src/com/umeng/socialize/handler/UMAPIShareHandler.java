package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.editorpage.IEditor;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.ShareMultiFollowRequest;
import com.umeng.socialize.net.SharePostRequest;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.utils.Dummy;
import com.umeng.socialize.utils.Log;
import java.util.Stack;

public abstract class UMAPIShareHandler extends UMSSOHandler implements IEditor {
    private Stack<a> mStatStack;

    static class a {
        public ShareContent a;
        public UMShareListener b;

        a() {
        }
    }

    public abstract void authorizeCallBack(int i, int i2, Intent intent);

    public abstract SHARE_MEDIA getPlatform();

    public abstract String getUID();

    public UMAPIShareHandler() {
        this.mStatStack = new Stack();
    }

    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
    }

    public boolean isAuthorized() {
        Log.e("\u8be5\u5e73\u53f0\u4e0d\u652f\u6301\u6388\u6743\u67e5\u8be2");
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == getRequestCode()) {
            a aVar;
            if (i2 == 1000) {
                aVar = (a) this.mStatStack.pop();
                if (aVar != null) {
                    aVar.b.onCancel(getPlatform());
                }
            }
            if (intent == null || !intent.hasExtra(ShareActivity.KEY_TEXT)) {
                authorizeCallBack(i, i2, intent);
            } else if (!this.mStatStack.empty()) {
                aVar = (a) this.mStatStack.pop();
                Bundle extras = intent == null ? null : intent.getExtras();
                if (i2 == -1) {
                    QueuedWork.runInBack(new a(this, aVar, extras));
                } else if (aVar.b != null) {
                    aVar.b.onCancel(getPlatform());
                }
            }
        }
    }

    protected void saveFollow() {
    }

    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        UMShareListener uMShareListener2 = (UMShareListener) Dummy.get(UMShareListener.class, uMShareListener);
        if (isAuthorized()) {
            doShare(activity, shareContent, uMShareListener2);
        } else {
            authorize(activity, new b(this, activity, shareContent, uMShareListener2));
        }
        return false;
    }

    private void doShare(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        if (Config.OpenEditor) {
            a aVar = new a();
            aVar.a = shareContent;
            aVar.b = uMShareListener;
            this.mStatStack.push(aVar);
            Intent intent = new Intent(activity, ShareActivity.class);
            intent.putExtras(getEditable(shareContent));
            activity.startActivityForResult(intent, getRequestCode());
            return;
        }
        sendShareRequest(shareContent, uMShareListener);
    }

    private void sendShareRequest(ShareContent shareContent, UMShareListener uMShareListener) {
        SHARE_MEDIA platform = getPlatform();
        String toLowerCase = platform.toString().toLowerCase();
        String uid = getUID();
        SharePostRequest sharePostRequest = new SharePostRequest(getContext(), toLowerCase, uid, shareContent);
        sharePostRequest.setReqType(0);
        SocializeReseponse doShare = RestAPI.doShare(sharePostRequest);
        Log.e(new StringBuilder("xxxx  platform= ").append(toLowerCase).append("  uid=").append(uid).append("   share=").append(shareContent).toString());
        if (doShare == null) {
            uMShareListener.onError(platform, new SocializeException("response is null"));
            Log.e("xxxx error!!! = response is null");
        } else if (doShare.isOk()) {
            uMShareListener.onResult(platform);
            Log.e("xxxx error!!! = noerror");
        } else {
            uMShareListener.onError(platform, new SocializeException(doShare.mStCode, doShare.mMsg));
            Log.e(new StringBuilder("xxxx error!!! = ").append(doShare.mMsg).append("   ").append(doShare.mStCode).toString());
        }
        if (shareContent.mFollow != null) {
            SocializeReseponse doFollow = RestAPI.doFollow(new ShareMultiFollowRequest(getContext(), toLowerCase, uid, shareContent.mFollow));
            if (doFollow == null) {
                Log.e("follow", "resp = null");
            } else if (doFollow.isOk()) {
                saveFollow();
            } else {
                Log.e("follow", new StringBuilder("follow fail e =").append(doFollow.mMsg).toString());
            }
        }
    }
}
