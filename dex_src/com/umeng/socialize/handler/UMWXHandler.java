package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.PlatformConfig.Weixin;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.HandlerRequestCode;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.PlatformTokenUploadReq;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Dummy;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.weixin.net.WXAuthUtils;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.download.proguard.c;
import com.xunlei.mediaserver.Utility;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.android.spdy.TnetStatusCode;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UMWXHandler extends UMSSOHandler {
    private static final int REFRESH_TOKEN_EXPIRES = 604800;
    private static final int REQUEST_CODE = 10086;
    private static final int RESP_TYPE_AUTH = 1;
    private static final int RESP_TYPE_SHARE = 2;
    private static final String TAG = "UMWXHandler";
    private static String sScope;
    private Weixin config;
    private boolean isToCircle;
    private UMAuthListener mAuthListener;
    private IWXAPIEventHandler mEventHandler;
    private WeiXinShareContent mShareContent;
    private SHARE_MEDIA mTarget;
    private IWXAPI mWXApi;
    private Context mcontext;
    private UMShareListener umShareListener;
    private WeixinPreferences weixinPreferences;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ StringBuilder val$authURL;
        final /* synthetic */ UMAuthListener val$listener;

        class AnonymousClass_1 implements Runnable {
            final /* synthetic */ Map val$finalMap;

            AnonymousClass_1(Map map) {
                this.val$finalMap = map;
            }

            public void run() {
                UMWXHandler.this.uploadAuthData(this.val$finalMap);
                AnonymousClass_1.this.val$listener.onComplete(SHARE_MEDIA.WEIXIN, 0, this.val$finalMap);
            }
        }

        AnonymousClass_1(StringBuilder stringBuilder, UMAuthListener uMAuthListener) {
            this.val$authURL = stringBuilder;
            this.val$listener = uMAuthListener;
        }

        public void run() {
            String request = WXAuthUtils.request(this.val$authURL.toString());
            try {
                Map jsonToMap = SocializeUtils.jsonToMap(request);
                if (jsonToMap == null || jsonToMap.size() == 0) {
                    jsonToMap = UMWXHandler.this.weixinPreferences.getmap();
                }
                UMWXHandler.this.weixinPreferences.setBundle(UMWXHandler.this.parseAuthData(request));
                QueuedWork.runInMain(new AnonymousClass_1(jsonToMap));
            } catch (Exception e) {
            }
        }
    }

    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ UMAuthListener val$listener;

        AnonymousClass_2(UMAuthListener uMAuthListener) {
            this.val$listener = uMAuthListener;
        }

        public void run() {
            this.val$listener.onComplete(SHARE_MEDIA.WEIXIN, RESP_TYPE_SHARE, null);
        }
    }

    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ UMAuthListener val$listener;
        final /* synthetic */ Map val$map;

        AnonymousClass_3(UMAuthListener uMAuthListener, Map map) {
            this.val$listener = uMAuthListener;
            this.val$map = map;
        }

        public void run() {
            this.val$listener.onComplete(SHARE_MEDIA.WEIXIN, RESP_TYPE_SHARE, this.val$map);
        }
    }

    class AnonymousClass_4 implements Runnable {
        final /* synthetic */ Map val$bundle;

        AnonymousClass_4(Map map) {
            this.val$bundle = map;
        }

        public void run() {
            if (UMWXHandler.this.mcontext != null) {
                PlatformTokenUploadReq platformTokenUploadReq = new PlatformTokenUploadReq(UMWXHandler.this.mcontext);
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_TO, "wxsession");
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_USID, (String) this.val$bundle.get(c.f));
                platformTokenUploadReq.addStringParams(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, (String) this.val$bundle.get(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2));
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_REFRESH_TOKEN, (String) this.val$bundle.get(SocializeProtocolConstants.PROTOCOL_KEY_REFRESH_TOKEN));
                platformTokenUploadReq.addStringParams(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2, (String) this.val$bundle.get(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2));
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, UMWXHandler.this.config.appId);
                platformTokenUploadReq.addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_APP_KEY, UMWXHandler.this.config.appSecret);
                Log.e(new StringBuilder("upload token resp = ").append(RestAPI.uploadPlatformToken(platformTokenUploadReq)).toString());
            }
        }
    }

    /* synthetic */ class AnonymousClass_6 {
        static final /* synthetic */ int[] $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA;

        static {
            $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA = new int[SHARE_MEDIA.values().length];
            try {
                $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA[SHARE_MEDIA.WEIXIN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA[SHARE_MEDIA.WEIXIN_CIRCLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA[SHARE_MEDIA.WEIXIN_FAVORITE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static {
        sScope = "snsapi_userinfo,snsapi_friend,snsapi_message";
    }

    public UMWXHandler() {
        this.mTarget = SHARE_MEDIA.WEIXIN;
        this.isToCircle = false;
        this.mEventHandler = new IWXAPIEventHandler() {
            public void onResp(BaseResp baseResp) {
                switch (baseResp.getType()) {
                    case RESP_TYPE_AUTH:
                        UMWXHandler.this.onAuthCallback((Resp) baseResp);
                    case RESP_TYPE_SHARE:
                        UMWXHandler.this.onShareCallback((SendMessageToWX.Resp) baseResp);
                    default:
                        break;
                }
            }

            public void onReq(BaseReq baseReq) {
            }
        };
    }

    public void setScope(String... strArr) {
        sScope = TextUtils.join(",", strArr);
    }

    public void onCreate(Context context, Platform platform) {
        this.weixinPreferences = new WeixinPreferences(context.getApplicationContext(), "weixin");
        this.config = (Weixin) platform;
        this.mWXApi = WXAPIFactory.createWXAPI(context.getApplicationContext(), this.config.appId);
        this.mWXApi.registerApp(this.config.appId);
        if (!isClientInstalled()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\u8bf7\u5b89\u88c5");
            stringBuilder.append(this.mTarget);
            stringBuilder.append("\u5ba2\u6237\u7aef");
            if (Config.IsToastTip) {
                Toast.makeText(context, stringBuilder.toString(), 0).show();
            }
        }
        this.mcontext = context;
    }

    public void authorize(Activity activity, UMAuthListener uMAuthListener) {
        this.mAuthListener = uMAuthListener;
        this.mTarget = this.config.getName();
        if (this.weixinPreferences.isAuthValid()) {
            if (this.weixinPreferences.isAccessTokenAvailable()) {
                loadOauthData(new StringBuilder("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=").append(this.config.appId).append("&grant_type=refresh_token&refresh_token=").append(this.weixinPreferences.getRefreshToken()).toString());
            }
            this.mAuthListener.onComplete(SHARE_MEDIA.WEIXIN, 0, getAuthWithRefreshToken(this.weixinPreferences.getRefreshToken()));
            return;
        }
        Req req = new Req();
        req.scope = sScope;
        req.state = Utility.NETWORK_NONE;
        this.mWXApi.sendReq(req);
    }

    public boolean isAuthorize(Context context) {
        return this.weixinPreferences.isAuth();
    }

    private void loadOauthData(String str) {
        this.weixinPreferences.setBundle(parseAuthData(WXAuthUtils.request(str)));
        this.mAuthListener.onComplete(this.config.getName(), 0, null);
    }

    private Bundle parseAuthData(String str) {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str)) {
            return bundle;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                bundle.putString(str2, jSONObject.optString(str2));
            }
            bundle.putString(c.f, bundle.getString(SocializeProtocolConstants.PROTOCOL_KEY_OPENID));
            bundle.putLong("refresh_token_expires", 604800);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bundle;
    }

    private Map<String, String> getAuthWithRefreshToken(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?");
        stringBuilder.append("appid=").append(this.config.appId);
        stringBuilder.append("&grant_type=refresh_token");
        stringBuilder.append("&refresh_token=").append(str);
        try {
            return SocializeUtils.jsonToMap(WXAuthUtils.request(stringBuilder.toString()));
        } catch (Exception e) {
            return null;
        }
    }

    private void getAuthWithCode(String str, UMAuthListener uMAuthListener) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
        stringBuilder.append("appid=").append(this.config.appId);
        stringBuilder.append("&secret=").append(this.config.appSecret);
        stringBuilder.append("&code=").append(str);
        stringBuilder.append("&grant_type=authorization_code");
        new Thread(new AnonymousClass_1(stringBuilder, uMAuthListener)).start();
    }

    public boolean isInstall(Context context) {
        return isClientInstalled();
    }

    protected void onAuthCallback(Resp resp) {
        UMAuthListener uMAuthListener = (UMAuthListener) Dummy.get(UMAuthListener.class, this.mAuthListener);
        if (resp.errCode == 0) {
            getAuthWithCode(resp.code, uMAuthListener);
        } else if (resp.errCode != -2) {
            SocializeException socializeException = new SocializeException(TextUtils.concat(new CharSequence[]{"weixin auth error (", String.valueOf(resp.errCode), "):", resp.errStr}).toString());
            if (uMAuthListener != null) {
                uMAuthListener.onError(SHARE_MEDIA.WEIXIN, 0, socializeException);
            }
        } else if (uMAuthListener != null) {
            uMAuthListener.onCancel(SHARE_MEDIA.WEIXIN, 0);
        }
    }

    public void deleteAuth(Context context, UMAuthListener uMAuthListener) {
        if (isInstall(context)) {
            this.weixinPreferences.delete();
            uMAuthListener.onComplete(SHARE_MEDIA.WEIXIN, RESP_TYPE_AUTH, null);
        }
    }

    public boolean isSupportAuth() {
        return true;
    }

    public int getRequestCode() {
        return this.isToCircle ? HandlerRequestCode.WX_CIRCLE_REQUEST_CODE : REQUEST_CODE;
    }

    private void refreshAccessToken() {
        if (this.weixinPreferences == null) {
            return;
        }
        if (this.weixinPreferences.isAuthValid()) {
            Log.d("refresh", "requesting access token with refresh");
            this.weixinPreferences.setBundle(parseAuthData(WXAuthUtils.request(new StringBuilder("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=").append(this.config.appId).append("&grant_type=refresh_token&refresh_token=").append(this.weixinPreferences.getRefreshToken()).toString())));
            return;
        }
        Log.e("refresh", "weixin refresh token is expired");
    }

    public void getPlatformInfo(Activity activity, UMAuthListener uMAuthListener) {
        Object uid = this.weixinPreferences.getUID();
        Object accessToken = this.weixinPreferences.getAccessToken();
        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(accessToken)) {
            Log.e(TAG, "please check had authed...");
            QueuedWork.runInMain(new AnonymousClass_2(uMAuthListener));
            return;
        }
        if (!this.weixinPreferences.isAccessTokenAvailable()) {
            Log.d("refresh", "getting auth with refresh token");
            refreshAccessToken();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.weixin.qq.com/sns/userinfo?access_token=");
        stringBuilder.append(accessToken).append("&openid=").append(uid);
        stringBuilder.append("&lang=zh_CN");
        QueuedWork.runInMain(new AnonymousClass_3(uMAuthListener, parseUserInfo(WXAuthUtils.request(stringBuilder.toString()))));
    }

    private void uploadAuthData(Map<String, String> map) throws SocializeException {
        new Thread(new AnonymousClass_4(map)).start();
    }

    private Map<String, String> parseUserInfo(String str) {
        Map<String, String> hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("errcode")) {
                Log.e(TAG, str);
                hashMap.put("errcode", jSONObject.getString("errcode"));
                return hashMap;
            }
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_OPENID, jSONObject.opt(SocializeProtocolConstants.PROTOCOL_KEY_OPENID).toString());
            hashMap.put("screen_name", jSONObject.opt("nickname").toString());
            hashMap.put("language", jSONObject.opt("language").toString());
            hashMap.put("city", jSONObject.opt("city").toString());
            hashMap.put("province", jSONObject.opt("province").toString());
            hashMap.put("country", jSONObject.opt("country").toString());
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_ICON, jSONObject.opt("headimgurl").toString());
            hashMap.put("unionid", jSONObject.opt("unionid").toString());
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_GENDER, jSONObject.opt("sex").toString());
            JSONArray jSONArray = jSONObject.getJSONArray("privilege");
            int length = jSONArray.length();
            if (length <= 0) {
                return hashMap;
            }
            Object obj = new Object[length];
            for (int i = 0; i < length; i++) {
                obj[i] = jSONArray.get(i).toString();
            }
            hashMap.put("privilege", obj.toString());
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        if (activity == null) {
            Log.d("UMError", "Weixin share activity is null");
            return false;
        }
        this.mTarget = this.config.getName();
        if (isClientInstalled()) {
            this.mShareContent = new WeiXinShareContent(shareContent);
            if (this.mShareContent != null) {
                this.mShareContent.parseMediaType();
                if (this.mShareContent.mShareType == WeiXinShareContent.TYPE_EMOJI && this.isToCircle) {
                    Toast.makeText(activity, "\u5fae\u4fe1\u670b\u53cb\u5708\u4e0d\u652f\u6301\u8868\u60c5\u5206\u4eab...", 0).show();
                    return false;
                }
            }
            this.umShareListener = uMShareListener;
            return shareTo(new WeiXinShareContent(shareContent));
        } else if (!Config.IsToastTip) {
            return false;
        } else {
            Toast.makeText(activity, "\u4f60\u8fd8\u6ca1\u6709\u5b89\u88c5\u5fae\u4fe1", 0).show();
            return false;
        }
    }

    public boolean isClientInstalled() {
        return this.mWXApi.isWXAppInstalled();
    }

    public boolean shareTo(WeiXinShareContent weiXinShareContent) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction(this.mShareContent.mShareType);
        req.message = weiXinShareContent.getWxMediaMessage();
        switch (AnonymousClass_6.$SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA[this.mTarget.ordinal()]) {
            case RESP_TYPE_AUTH:
                req.scene = 0;
                break;
            case RESP_TYPE_SHARE:
                req.scene = 1;
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                req.scene = 2;
                break;
            default:
                req.scene = 2;
                break;
        }
        if (req.message == null) {
            Log.e("wx,message = null");
            return false;
        } else if (req.message.mediaObject != null) {
            return this.mWXApi.sendReq(req);
        } else {
            Log.e("wx,mediaobject = null");
            return false;
        }
    }

    protected void onShareCallback(SendMessageToWX.Resp resp) {
        switch (resp.errCode) {
            case TnetStatusCode.EASY_REQ_STATE_PROCESS_RSP_FAIL:
            case TnetStatusCode.EASY_REQ_STAGE_NOT_SEND:
                if (this.umShareListener != null) {
                    this.umShareListener.onError(this.mTarget, new SocializeException(resp.errCode, resp.errStr));
                }
            case TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL:
                if (this.umShareListener != null) {
                    this.umShareListener.onCancel(this.mTarget);
                }
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                if (this.umShareListener != null) {
                    Map hashMap = new HashMap();
                    hashMap.put(c.f, resp.openId);
                    uploadAuthData(hashMap);
                    this.umShareListener.onResult(this.mTarget);
                }
            default:
                Log.d(TAG, "\u5fae\u4fe1\u53d1\u9001 -- \u672a\u77e5\u9519\u8bef.");
        }
    }

    public IWXAPIEventHandler getWXEventHandler() {
        return this.mEventHandler;
    }

    public IWXAPI getWXApi() {
        return this.mWXApi;
    }

    private String buildTransaction(String str) {
        return str == null ? String.valueOf(System.currentTimeMillis()) : str + System.currentTimeMillis();
    }
}
