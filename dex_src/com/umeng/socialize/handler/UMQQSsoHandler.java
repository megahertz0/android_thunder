package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.media.Constant;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.net.PlatformTokenUploadReq;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.BitmapUtils;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.download.proguard.c;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.impl.SimpleLog;
import org.json.JSONException;
import org.json.JSONObject;

public class UMQQSsoHandler extends UMTencentSSOHandler {
    private static final String TAG = "UMQQSsoHandler";
    private boolean GOTO_SHARE_ACTIVITY;
    private Activity mActivity;
    private Bundle mParams;
    private IUiListener mShareListener;
    private QQShareContent msharecontent;
    private QQPreferences qqPreferences;

    class AnonymousClass_1 implements IUiListener {
        final /* synthetic */ UMShareListener val$listener;

        AnonymousClass_1(UMShareListener uMShareListener) {
            this.val$listener = uMShareListener;
        }

        public void onError(UiError uiError) {
            if (uiError != null) {
                Log.e(new StringBuilder("xxxx qqerror").append(uiError.errorDetail).append(uiError.errorMessage).append(uiError.errorCode).toString());
            }
            this.val$listener.onError(SHARE_MEDIA.QQ, null);
        }

        public void onCancel() {
            Log.e("xxxx qqcancle");
            this.val$listener.onCancel(SHARE_MEDIA.QQ);
        }

        public void onComplete(Object obj) {
            Log.e("xxxx qqcomplete");
            this.val$listener.onResult(SHARE_MEDIA.QQ);
        }
    }

    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ Bundle val$bundle;

        AnonymousClass_3(Bundle bundle) {
            this.val$bundle = bundle;
        }

        public void run() {
            if (UMQQSsoHandler.this.getContext() != null && this.val$bundle != null && UMQQSsoHandler.this.config != null) {
                PlatformTokenUploadReq platformTokenUploadReq = new PlatformTokenUploadReq(UMQQSsoHandler.this.getContext());
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_TO, "qq");
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_USID, this.val$bundle.getString(c.f));
                platformTokenUploadReq.addStringParams(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.val$bundle.getString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2));
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_REFRESH_TOKEN, this.val$bundle.getString(SocializeProtocolConstants.PROTOCOL_KEY_REFRESH_TOKEN));
                platformTokenUploadReq.addStringParams(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2, this.val$bundle.getString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2));
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, UMQQSsoHandler.this.config.appId);
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_KEY, UMQQSsoHandler.this.config.appKey);
                Log.e(new StringBuilder("upload token resp = ").append(RestAPI.uploadPlatformToken(platformTokenUploadReq)).toString());
            }
        }
    }

    class AnonymousClass_4 implements UMAuthListener {
        final /* synthetic */ UMImage val$image;

        AnonymousClass_4(UMImage uMImage) {
            this.val$image = uMImage;
        }

        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            if (map != null && map.containsKey(c.f) && !TextUtils.isEmpty(this.val$image.asUrlImage())) {
                UMQQSsoHandler.this.mParams.putString("imageUrl", this.val$image.asUrlImage());
                UMQQSsoHandler.this.mParams.remove("imageLocalUrl");
                UMQQSsoHandler.this.defaultShareToQQ(UMQQSsoHandler.this.msharecontent);
            }
        }

        public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
        }

        public void onCancel(SHARE_MEDIA share_media, int i) {
        }
    }

    class AnonymousClass_6 implements IUiListener {
        final /* synthetic */ UMAuthListener val$listener;

        AnonymousClass_6(UMAuthListener uMAuthListener) {
            this.val$listener = uMAuthListener;
        }

        public void onCancel() {
        }

        public void onComplete(Object obj) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                Map hashMap = new HashMap();
                hashMap.put("screen_name", jSONObject.optString("nickname"));
                hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_GENDER, jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_GENDER));
                hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_ICON, jSONObject.optString("figureurl_qq_2"));
                hashMap.put("is_yellow_year_vip", jSONObject.optString("is_yellow_year_vip"));
                hashMap.put("yellow_vip_level", jSONObject.optString("yellow_vip_level"));
                hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MSG, jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
                hashMap.put("city", jSONObject.optString("city"));
                hashMap.put("vip", jSONObject.optString("vip"));
                hashMap.put("level", jSONObject.optString("level"));
                hashMap.put("province", jSONObject.optString("province"));
                hashMap.put("is_yellow_vip", jSONObject.optString("is_yellow_vip"));
                if (UMQQSsoHandler.this.qqPreferences != null) {
                    hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_OPENID, UMQQSsoHandler.this.qqPreferences.getuid());
                }
                this.val$listener.onComplete(SHARE_MEDIA.QQ, SimpleLog.LOG_LEVEL_DEBUG, hashMap);
            } catch (JSONException e) {
                this.val$listener.onComplete(SHARE_MEDIA.QQ, SimpleLog.LOG_LEVEL_DEBUG, null);
            }
        }

        public void onError(UiError uiError) {
            this.val$listener.onError(SHARE_MEDIA.QQ, SimpleLog.LOG_LEVEL_DEBUG, new Throwable(uiError.toString()));
        }
    }

    public UMQQSsoHandler() {
        this.GOTO_SHARE_ACTIVITY = false;
    }

    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
        if (context != null) {
            this.qqPreferences = new QQPreferences(context, SHARE_MEDIA.QQ.toString());
        }
    }

    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        if (activity == null) {
            Log.d("UMError", "qq share activity is null");
        } else {
            Log.e("xxxxxxxxx");
            this.mParams = null;
            this.mActivity = activity;
            this.mShareListener = getSharelistener(uMShareListener);
            if (this.mShareListener == null) {
                Log.d("listen", "listener is null");
            }
            this.msharecontent = new QQShareContent(shareContent);
            shareToQQ(activity);
        }
        return false;
    }

    private IUiListener getSharelistener(UMShareListener uMShareListener) {
        return new AnonymousClass_1(uMShareListener);
    }

    public boolean isAuthorize(Context context) {
        return (context == null || this.qqPreferences == null) ? false : this.qqPreferences.isAuthValid();
    }

    public void authorize(Activity activity, UMAuthListener uMAuthListener) {
        if (activity == null) {
            Log.d("UMError", "qq authorize activity is null");
            return;
        }
        this.mActivity = activity;
        this.mAuthListener = uMAuthListener;
        loginDeal();
    }

    private IUiListener getAuthlistener(UMAuthListener uMAuthListener) {
        return new IUiListener() {
            public void onError(UiError uiError) {
                if (uiError != null) {
                    Log.d(TAG, new StringBuilder("\u6388\u6743\u5931\u8d25! ==> errorCode = ").append(uiError.errorCode).append(", errorMsg = ").append(uiError.errorMessage).append(", detail = ").append(uiError.errorDetail).toString());
                }
                UMQQSsoHandler.this.mAuthListener.onError(SHARE_MEDIA.QQ, 0, new Throwable(new StringBuilder("\u6388\u6743\u5931\u8d25! ==> errorCode = ").append(uiError.errorCode).append(", errorMsg = ").append(uiError.errorMessage).append(", detail = ").append(uiError.errorDetail).toString()));
            }

            public void onCancel() {
                if (UMQQSsoHandler.this.mAuthListener != null) {
                    UMQQSsoHandler.this.mAuthListener.onCancel(SHARE_MEDIA.QQ, 0);
                }
            }

            public void onComplete(Object obj) {
                SocializeUtils.safeCloseDialog(UMQQSsoHandler.this.mProgressDialog);
                Bundle parseOauthData = UMQQSsoHandler.this.parseOauthData(obj);
                if (UMQQSsoHandler.this.qqPreferences == null && UMQQSsoHandler.this.mActivity != null) {
                    UMQQSsoHandler.this.qqPreferences = new QQPreferences(UMQQSsoHandler.this.mActivity, SHARE_MEDIA.QQ.toString());
                }
                if (UMQQSsoHandler.this.qqPreferences != null) {
                    UMQQSsoHandler.this.qqPreferences.setAuthData(parseOauthData).commit();
                }
                UMQQSsoHandler.this.initOpenidAndToken((JSONObject) obj);
                if (UMQQSsoHandler.this.mAuthListener != null) {
                    UMQQSsoHandler.this.mAuthListener.onComplete(SHARE_MEDIA.QQ, 0, UMQQSsoHandler.this.bundleTomap(parseOauthData));
                }
                UMQQSsoHandler.this.uploadAuthData(parseOauthData);
                if (parseOauthData != null && Integer.valueOf(parseOauthData.getString("ret")).intValue() != 0) {
                }
            }
        };
    }

    public boolean isInstall(Context context) {
        return this.mTencent.isSupportSSOLogin((Activity) context);
    }

    public void deleteAuth(Context context, UMAuthListener uMAuthListener) {
        this.mTencent.logout(context);
        if (this.qqPreferences != null) {
            this.qqPreferences.delete();
        }
        uMAuthListener.onComplete(SHARE_MEDIA.QQ, 1, null);
    }

    private boolean isInstall(Context context, Platform platform) {
        if (this.mTencent.isSupportSSOLogin((Activity) context)) {
            return true;
        }
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("\u8bf7\u5b89\u88c5");
        stringBuilder.append(ResContainer.getString(context, platform.getName().toSnsPlatform().mShowWord));
        stringBuilder.append("\u5ba2\u6237\u7aef");
        Log.v(stringBuilder.toString());
        if (Config.IsToastTip) {
            Toast.makeText(context, stringBuilder, 1).show();
        }
        return false;
    }

    public boolean isSupportAuth() {
        return true;
    }

    private void loginDeal() {
        Log.i(TAG, "QQ oauth login...");
        if (isInstall(this.mActivity)) {
            Log.d("qq", "installed qq");
            this.mTencent.login(this.mActivity, "all", getAuthlistener(this.mAuthListener));
            return;
        }
        Log.d("qq", "didn't install qq");
        this.mTencent.loginServerSide(this.mActivity, "all", getAuthlistener(this.mAuthListener));
    }

    public void initOpenidAndToken(JSONObject jSONObject) {
        try {
            Object string = jSONObject.getString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2);
            Object string2 = jSONObject.getString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2);
            Object string3 = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_OPENID);
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                this.mTencent.setAccessToken(string, string2);
                this.mTencent.setOpenId(string3);
            }
        } catch (Exception e) {
        }
    }

    private void uploadAuthData(Bundle bundle) throws SocializeException {
        new Thread(new AnonymousClass_3(bundle)).start();
    }

    private Map<String, String> bundleTomap(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return null;
        }
        Set<String> keySet = bundle.keySet();
        Map<String, String> hashMap = new HashMap();
        for (String str : keySet) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public void canOpenShareActivity(boolean z) {
        this.GOTO_SHARE_ACTIVITY = z;
    }

    private void gotoShare() {
    }

    public void shareToQQ(Context context) {
        if (validTencent()) {
            String str = (String) this.msharecontent.mExtraData.get(Constant.IMAGE_PATH_LOCAL);
            if (isLoadImageAsync(context)) {
                loadImage(context, (String) this.msharecontent.mExtraData.get(Constant.IMAGE_PATH_URL));
                return;
            } else if (isUploadImageAsync(str, this.msharecontent.mShareType, context)) {
                UMImage uMImage = new UMImage(context, new File(str));
                Log.w(TAG, "\u672a\u5b89\u88c5QQ\u5ba2\u6237\u7aef\u7684\u60c5\u51b5\u4e0b\uff0cQQ\u4e0d\u652f\u6301\u97f3\u9891\uff0c\u56fe\u6587\u662f\u4e3a\u672c\u5730\u56fe\u7247\u7684\u5206\u4eab\u3002\u6b64\u65f6\u5c06\u4e0a\u4f20\u672c\u5730\u56fe\u7247\u5230\u76f8\u518c\uff0c\u8bf7\u786e\u4fdd\u5728QQ\u4e92\u8054\u7533\u8bf7\u4e86upload_pic\u6743\u9650.");
                authorize(this.mActivity, createUploadAuthListener(uMImage));
                return;
            } else {
                Log.d("shareQQ", "share to qq");
                defaultShareToQQ(this.msharecontent);
                return;
            }
        }
        Log.d(TAG, "QQ\u5e73\u53f0\u8fd8\u6ca1\u6709\u6388\u6743");
        authorize(this.mActivity, null);
    }

    private UMAuthListener createUploadAuthListener(UMImage uMImage) {
        return new AnonymousClass_4(uMImage);
    }

    protected boolean isUploadImageAsync(String str, int i, Context context) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        SHARE_MEDIA name = this.config.getName();
        boolean isClientInstalled = isClientInstalled(context);
        if (str.startsWith("http://") || str.startsWith("https://")) {
            boolean z = false;
        } else {
            int i2 = 1;
        }
        if (isClientInstalled || !r2) {
            return false;
        }
        if (name == SHARE_MEDIA.QQ && (i == 2 || i == 1)) {
            return true;
        }
        if (name == SHARE_MEDIA.QZONE) {
            return i == 1 || i == 2;
        } else {
            return false;
        }
    }

    private boolean isLoadImageAsync(Context context) {
        return this.msharecontent.mShareType == 5 && isClientInstalled(context) && !TextUtils.isEmpty((String) this.msharecontent.mExtraData.get(Constant.IMAGE_PATH_URL)) && TextUtils.isEmpty((String) this.msharecontent.mExtraData.get(Constant.IMAGE_PATH_LOCAL));
    }

    private void loadImage(Context context, String str) {
        BitmapUtils.getBitmapFromFile(str);
        SocializeUtils.safeCloseDialog(this.mProgressDialog);
        this.mParams.putString("imageLocalUrl", BitmapUtils.getFileName(str));
        this.mParams.remove("imageUrl");
        defaultShareToQQ(this.msharecontent);
    }

    private void defaultShareToQQ(QQShareContent qQShareContent) {
        this.mParams = this.msharecontent.buildParams();
        this.mParams.putString("appName", getAppName());
        if (this.mParams != null) {
            QueuedWork.runInMain(new Runnable() {
                public void run() {
                    UMQQSsoHandler.this.mTencent.shareToQQ(UMQQSsoHandler.this.mActivity, UMQQSsoHandler.this.mParams, UMQQSsoHandler.this.mShareListener);
                }
            });
        }
    }

    public int getRequestCode() {
        return 10103;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 10103) {
            Tencent.onActivityResultData(i, i2, intent, this.mShareListener);
        }
        if (i == 11101) {
            Tencent.onActivityResultData(i, i2, intent, getAuthlistener(this.mAuthListener));
        }
    }

    public void getPlatformInfo(Activity activity, UMAuthListener uMAuthListener) {
        if (isAuthorize(activity)) {
            try {
                Object obj = this.qqPreferences.getmAccessToken();
                Object expiresIn = QQPreferences.getExpiresIn();
                Object obj2 = this.qqPreferences.getmUID();
                if (!(activity == null || this.qqPreferences == null)) {
                    obj = this.qqPreferences.getmAccessToken();
                    expiresIn = QQPreferences.getExpiresIn();
                    obj2 = this.qqPreferences.getmUID();
                }
                if (!(TextUtils.isEmpty(obj) || TextUtils.isEmpty(expiresIn) || TextUtils.isEmpty(obj2))) {
                    this.mTencent.setAccessToken(obj, expiresIn);
                    this.mTencent.setOpenId(obj2);
                }
            } catch (Exception e) {
            }
        }
        if (activity == null) {
            Log.d("UMError", "QQ getPlatFormInfo activity is null");
        } else {
            new UserInfo(activity, this.mTencent.getQQToken()).getUserInfo(new AnonymousClass_6(uMAuthListener));
        }
    }
}
