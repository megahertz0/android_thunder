package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
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
import com.umeng.socialize.handler.UMTencentSSOHandler.ObtainImageUrlListener;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.media.UMediaObject$MediaType;
import com.umeng.socialize.net.PlatformTokenUploadReq;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.download.proguard.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class QZoneSsoHandler extends UMTencentSSOHandler {
    private static final String TAG = "QZoneSsoHandler";
    private Context context;
    private Activity mActivity;
    private QZoneShareContent mShareContent;
    private IUiListener mUiListener;
    private QQPreferences qqPreferences;

    class AnonymousClass_1 implements UMAuthListener {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ Bundle val$bundle;
        final /* synthetic */ UMImage val$image;

        AnonymousClass_1(UMImage uMImage, Bundle bundle, Activity activity) {
            this.val$image = uMImage;
            this.val$bundle = bundle;
            this.val$activity = activity;
        }

        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            if (map != null && map.containsKey(c.f)) {
                QZoneSsoHandler.this.getBitmapUrl(this.val$image, (String) map.get(c.f), new ObtainImageUrlListener() {
                    public void onComplete(String str) {
                        if (TextUtils.isEmpty(str)) {
                            QZoneSsoHandler.this.defaultQZoneShare(AnonymousClass_1.this.val$bundle, AnonymousClass_1.this.val$activity);
                            UMediaObject media = QZoneSsoHandler.this.mShareContent.getMedia();
                            int i = AnonymousClass_1.this.val$bundle.getInt("req_type");
                            if (!QZoneSsoHandler.this.isClientInstalled(AnonymousClass_1.this.val$activity) && media != null) {
                                if (media.getMediaType() == UMediaObject$MediaType.VEDIO || media.getMediaType() == UMediaObject$MediaType.MUSIC || i == 1) {
                                    Log.e(TAG, "QQ\u7a7a\u95f4\u4e0a\u4f20\u56fe\u7247\u5931\u8d25\u5c06\u5bfc\u81f4\u65e0\u5ba2\u6237\u7aef\u5206\u4eab\u5931\u8d25\uff0c\u8bf7\u8bbe\u7f6e\u7f29\u7565\u56fe\u4e3aurl\u7c7b\u578b\u6216\u8005\u8f83\u5c0f\u7684\u672c\u5730\u56fe\u7247...");
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        AnonymousClass_1.this.val$bundle.remove("imageUrl");
                        arrayList.add(str);
                        AnonymousClass_1.this.val$bundle.putStringArrayList("imageUrl", arrayList);
                        QZoneSsoHandler.this.defaultQZoneShare(AnonymousClass_1.this.val$bundle, AnonymousClass_1.this.val$activity);
                    }
                });
            }
        }

        public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
        }

        public void onCancel(SHARE_MEDIA share_media, int i) {
        }
    }

    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ Bundle val$bundle;

        AnonymousClass_3(Bundle bundle) {
            this.val$bundle = bundle;
        }

        public void run() {
            PlatformTokenUploadReq platformTokenUploadReq = new PlatformTokenUploadReq(QZoneSsoHandler.this.getContext());
            platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_TO, "qq");
            platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_USID, this.val$bundle.getString(c.f));
            platformTokenUploadReq.addStringParams(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.val$bundle.getString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2));
            platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_REFRESH_TOKEN, this.val$bundle.getString(SocializeProtocolConstants.PROTOCOL_KEY_REFRESH_TOKEN));
            platformTokenUploadReq.addStringParams(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2, this.val$bundle.getString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2));
            platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, QZoneSsoHandler.this.config.appId);
            platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_KEY, QZoneSsoHandler.this.config.appKey);
            Log.e(new StringBuilder("upload token resp = ").append(RestAPI.uploadPlatformToken(platformTokenUploadReq)).toString());
        }
    }

    class AnonymousClass_5 implements IUiListener {
        final /* synthetic */ UMShareListener val$mShareListener;

        AnonymousClass_5(UMShareListener uMShareListener) {
            this.val$mShareListener = uMShareListener;
        }

        public void onComplete(Object obj) {
            if (this.val$mShareListener != null) {
                this.val$mShareListener.onResult(SHARE_MEDIA.QZONE);
            }
        }

        public void onError(UiError uiError) {
            if (this.val$mShareListener != null) {
                this.val$mShareListener.onError(SHARE_MEDIA.QZONE, null);
            }
        }

        public void onCancel() {
            if (this.val$mShareListener != null) {
                this.val$mShareListener.onCancel(SHARE_MEDIA.QZONE);
            }
        }
    }

    class AnonymousClass_6 implements Runnable {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ Bundle val$bundle;

        AnonymousClass_6(Activity activity, Bundle bundle) {
            this.val$activity = activity;
            this.val$bundle = bundle;
        }

        public void run() {
            QZoneSsoHandler.this.mTencent.shareToQzone(this.val$activity, this.val$bundle, QZoneSsoHandler.this.getmShareListener(QZoneSsoHandler.this.mShareListener));
        }
    }

    public QZoneSsoHandler() {
        this.mUiListener = new IUiListener() {
            public void onError(UiError uiError) {
                QZoneSsoHandler.this.mAuthListener.onError(SHARE_MEDIA.QZONE, 0, null);
            }

            public void onCancel() {
                QZoneSsoHandler.this.mAuthListener.onCancel(SHARE_MEDIA.QZONE, 0);
            }

            public void onComplete(Object obj) {
                QZoneSsoHandler.this.mAuthListener.onComplete(SHARE_MEDIA.QZONE, 0, null);
            }
        };
    }

    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
        this.qqPreferences = new QQPreferences(context, SHARE_MEDIA.QQ.toString());
    }

    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        if (uMShareListener != null) {
            this.mShareListener = uMShareListener;
        }
        if (isInstall(activity, getConfig())) {
            this.mShareContent = new QZoneShareContent(shareContent);
            shareToQZone(activity, new QZoneShareContent(shareContent));
        }
        return false;
    }

    private void shareToQZone(Activity activity, QZoneShareContent qZoneShareContent) {
        if (activity == null) {
            Log.d("UMError", "QZone share activity is null");
            return;
        }
        Bundle buildParamsQzone = this.mShareContent.buildParamsQzone();
        buildParamsQzone.putString("appName", getAppName());
        int i = buildParamsQzone.getInt("req_type");
        List stringArrayList = buildParamsQzone.getStringArrayList("imageUrl");
        String str = null;
        if (stringArrayList != null && stringArrayList.size() > 0) {
            str = (String) stringArrayList.get(0);
        }
        if (isUploadImageAsync(str, i)) {
            authorize(activity, createAuthListener(buildParamsQzone, new UMImage(activity, str), activity));
        } else {
            defaultQZoneShare(buildParamsQzone, activity);
        }
    }

    public void deleteAuth(Context context, UMAuthListener uMAuthListener) {
        this.mTencent.logout(context);
        if (this.qqPreferences != null) {
            this.qqPreferences.delete();
        }
        uMAuthListener.onComplete(SHARE_MEDIA.QZONE, 1, null);
    }

    private UMAuthListener createAuthListener(Bundle bundle, UMImage uMImage, Activity activity) {
        return new AnonymousClass_1(uMImage, bundle, activity);
    }

    private boolean isInstall(Context context, Platform platform) {
        if (this.mTencent.isSupportSSOLogin((Activity) context)) {
            return true;
        }
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("\u8bf7\u5b89\u88c5");
        stringBuilder.append("qq");
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

    public void authorize(Activity activity, UMAuthListener uMAuthListener) {
        if (activity == null) {
            Log.d("UMError", "QZone share activity is null");
            return;
        }
        this.mActivity = activity;
        if (isInstall(activity, getConfig())) {
            this.mAuthListener = uMAuthListener;
            loginDeal();
        }
    }

    private void loginDeal() {
        Log.i(TAG, "QQ oauth login...");
        this.mTencent.login(this.mActivity, "all", getAuthlistener(this.mAuthListener));
    }

    private IUiListener getAuthlistener(UMAuthListener uMAuthListener) {
        return new IUiListener() {
            public void onError(UiError uiError) {
                if (uiError != null) {
                    Log.d(TAG, new StringBuilder("\u6388\u6743\u5931\u8d25! ==> errorCode = ").append(uiError.errorCode).append(", errorMsg = ").append(uiError.errorMessage).append(", detail = ").append(uiError.errorDetail).toString());
                }
                QZoneSsoHandler.this.mAuthListener.onError(SHARE_MEDIA.QQ, 0, new Throwable(new StringBuilder("\u6388\u6743\u5931\u8d25! ==> errorCode = ").append(uiError.errorCode).append(", errorMsg = ").append(uiError.errorMessage).append(", detail = ").append(uiError.errorDetail).toString()));
            }

            public void onCancel() {
                QZoneSsoHandler.this.mAuthListener.onCancel(SHARE_MEDIA.QQ, 0);
            }

            public void onComplete(Object obj) {
                SocializeUtils.safeCloseDialog(QZoneSsoHandler.this.mProgressDialog);
                Bundle parseOauthData = QZoneSsoHandler.this.parseOauthData(obj);
                QZoneSsoHandler.this.qqPreferences.setAuthData(parseOauthData).commit();
                QZoneSsoHandler.this.initOpenidAndToken((JSONObject) obj);
                if (QZoneSsoHandler.this.mAuthListener != null) {
                    QZoneSsoHandler.this.mAuthListener.onComplete(SHARE_MEDIA.QQ, 0, QZoneSsoHandler.this.bundleTomap(parseOauthData));
                }
                QZoneSsoHandler.this.uploadAuthData(parseOauthData);
                if (parseOauthData != null && !TextUtils.isEmpty(parseOauthData.getString("ret"))) {
                }
            }
        };
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

    public int getRequestCode() {
        return 10104;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 10104) {
            Tencent.onActivityResultData(i, i2, intent, getmShareListener(this.mShareListener));
        }
        if (i == 11101) {
            Tencent.onActivityResultData(i, i2, intent, getAuthlistener(this.mAuthListener));
        }
    }

    public IUiListener getmShareListener(UMShareListener uMShareListener) {
        return new AnonymousClass_5(uMShareListener);
    }

    private void defaultQZoneShare(Bundle bundle, Activity activity) {
        Log.d(TAG, "invoke Tencent.shareToQzone method...");
        if (bundle != null) {
            QueuedWork.runInMain(new AnonymousClass_6(activity, bundle));
        }
    }
}
