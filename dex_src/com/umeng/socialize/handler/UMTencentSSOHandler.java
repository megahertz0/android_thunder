package com.umeng.socialize.handler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.tauth.Tencent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.PlatformConfig.QQZone;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.UploadImageRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.OauthHelper;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.download.proguard.c;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class UMTencentSSOHandler extends UMSSOHandler {
    private static final String PUBLIC_ACCOUNT = "100424468";
    private static final String TAG = "UMTencentSSOHandler";
    protected static Map<String, String> mImageCache;
    public QQZone config;
    private Context context;
    protected UMAuthListener mAuthListener;
    protected String mImageUrl;
    protected ProgressDialog mProgressDialog;
    protected UMShareListener mShareListener;
    protected Tencent mTencent;

    public static interface ObtainImageUrlListener {
        void onComplete(String str);
    }

    protected static interface ObtainAppIdListener {
        void onComplete();
    }

    static {
        mImageCache = new HashMap();
    }

    public UMTencentSSOHandler() {
        this.mProgressDialog = null;
        this.mImageUrl = null;
        this.config = null;
    }

    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
        this.context = context;
        this.config = (QQZone) platform;
        Log.d(OauthHelper.APP_ID, new StringBuilder("appid qq:").append(this.config.appId).toString());
        this.mTencent = Tencent.createInstance(this.config.appId, context);
        new StringBuilder("tencent=").append(this.mTencent.getOpenId());
        if (this.mTencent == null) {
            Log.e(TAG, "Tencent\u53d8\u91cf\u521d\u59cb\u5316\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u4f60\u7684app id\u8ddfAndroidManifest.xml\u6587\u4ef6\u4e2dAuthActivity\u7684scheme\u662f\u5426\u586b\u5199\u6b63\u786e");
        }
    }

    protected boolean isUploadImageAsync(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        SHARE_MEDIA name = this.config.getName();
        boolean isClientInstalled = isClientInstalled(this.context);
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

    protected Bundle parseOauthData(Object obj) {
        Bundle bundle = new Bundle();
        if (obj != null) {
            Object trim = obj.toString().trim();
            if (!TextUtils.isEmpty(trim)) {
                JSONObject jSONObject;
                try {
                    jSONObject = new JSONObject(trim);
                } catch (JSONException e) {
                    e.printStackTrace();
                    jSONObject = null;
                }
                if (jSONObject != null) {
                    bundle.putString("auth_time", jSONObject.optString("auth_time", BuildConfig.VERSION_NAME));
                    bundle.putString("pay_token", jSONObject.optString("pay_token", BuildConfig.VERSION_NAME));
                    bundle.putString("pf", jSONObject.optString("pf", BuildConfig.VERSION_NAME));
                    bundle.putString("ret", String.valueOf(jSONObject.optInt("ret", -1)));
                    bundle.putString("sendinstall", jSONObject.optString("sendinstall", BuildConfig.VERSION_NAME));
                    bundle.putString("page_type", jSONObject.optString("page_type", BuildConfig.VERSION_NAME));
                    bundle.putString(OauthHelper.APP_ID, jSONObject.optString(OauthHelper.APP_ID, BuildConfig.VERSION_NAME));
                    bundle.putString(SocializeProtocolConstants.PROTOCOL_KEY_OPENID, jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_OPENID, BuildConfig.VERSION_NAME));
                    bundle.putString(c.f, jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_OPENID, BuildConfig.VERSION_NAME));
                    bundle.putString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2, jSONObject.optString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2, BuildConfig.VERSION_NAME));
                    bundle.putString("pfkey", jSONObject.optString("pfkey", BuildConfig.VERSION_NAME));
                    bundle.putString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, jSONObject.optString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, BuildConfig.VERSION_NAME));
                }
            }
        }
        return bundle;
    }

    public boolean isClientInstalled(Context context) {
        return this.mTencent.isSupportSSOLogin((Activity) context);
    }

    protected String getAppName() {
        if (!TextUtils.isEmpty(Config.QQAPPNAME)) {
            return Config.QQAPPNAME;
        }
        String str = BuildConfig.VERSION_NAME;
        if (this.context == null) {
            return str;
        }
        CharSequence loadLabel = this.context.getApplicationInfo().loadLabel(this.context.getPackageManager());
        return !TextUtils.isEmpty(loadLabel) ? loadLabel.toString() : str;
    }

    public void getBitmapUrl(UMediaObject uMediaObject, String str, ObtainImageUrlListener obtainImageUrlListener) {
        System.currentTimeMillis();
        if (uMediaObject instanceof UMImage) {
            UMImage uMImage = (UMImage) uMediaObject;
        } else {
            uMediaObject = null;
        }
        if (uMediaObject != null) {
            String toString = uMediaObject.asFileImage().toString();
            String str2 = (String) mImageCache.get(toString);
            if (TextUtils.isEmpty(str2)) {
                Log.i(TAG, "obtain image url form server...");
                CharSequence toGetUrl = new UploadImageRequest(this.context, uMediaObject, str).toGetUrl();
                setImageUrl(toString, toGetUrl);
                if (this.context != null && TextUtils.isEmpty(toGetUrl)) {
                    Toast.makeText(this.context, "\u4e0a\u4f20\u56fe\u7247\u5931\u8d25", 0).show();
                }
                Log.i(TAG, new StringBuilder("obtain image url form server...").append(this.mImageUrl).toString());
            } else {
                this.mImageUrl = str2;
                Log.i(TAG, new StringBuilder("obtain image url form cache...").append(this.mImageUrl).toString());
            }
        }
        Log.i(TAG, "doInBackground end...");
        obtainImageUrlListener.onComplete(this.mImageUrl);
    }

    private void setImageUrl(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            mImageCache.put(str, str2);
            this.mImageUrl = str2;
        }
    }

    protected boolean validTencent() {
        return this.mTencent != null && this.mTencent.getAppId().equals(this.config.appId);
    }
}
