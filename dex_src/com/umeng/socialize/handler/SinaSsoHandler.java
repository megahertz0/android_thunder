package com.umeng.socialize.handler;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.sina.sso.RemoteSSO;
import com.sina.sso.RemoteSSO.Stub;
import com.sina.weibo.sdk.api.share.BaseRequest;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.tencent.connect.common.Constants;
import com.umeng.a;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.PlatformConfig.SinaWeibo;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UMLocation;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.net.PlatformTokenUploadReq;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.ShareFriendsRequest;
import com.umeng.socialize.net.ShareFriendsResponse;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.view.UMFriendListener;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.android.spdy.SpdyAgent;

public class SinaSsoHandler extends UMAPIShareHandler {
    private static final int REQUEST_CODE = 5659;
    public static final String SCOPE = "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write";
    private static final String TAG = "SinaSsoHandler";
    private static final String waiturl = "sina2/main?uid";
    private SinaWeibo config;
    private AuthInfo mAuthInfo;
    private AuthListener mAuthListener;
    private Context mContext;
    private SsoHandler mSsoHandler;
    private IWeiboShareAPI mWeiboShareAPI;
    private UMShareListener shareListener;
    private SinaPreferences sinaPreferences;

    class AnonymousClass_1 implements RequestListener {
        final /* synthetic */ UMAuthListener val$listener;

        AnonymousClass_1(UMAuthListener uMAuthListener) {
            this.val$listener = uMAuthListener;
        }

        public void onComplete(String str) {
            HashMap hashMap = new HashMap();
            this.val$listener.onComplete(SHARE_MEDIA.SINA, XZBDevice.DOWNLOAD_LIST_RECYCLE, SocializeUtils.jsonToMap(str));
        }

        public void onWeiboException(WeiboException weiboException) {
            this.val$listener.onError(SHARE_MEDIA.SINA, XZBDevice.DOWNLOAD_LIST_RECYCLE, new Throwable(weiboException));
        }
    }

    class AnonymousClass_2 implements UMAuthListener {
        final /* synthetic */ Activity val$act;
        final /* synthetic */ UMAuthListener val$listener;

        AnonymousClass_2(Activity activity, UMAuthListener uMAuthListener) {
            this.val$act = activity;
            this.val$listener = uMAuthListener;
        }

        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            QueuedWork.runInBack(new AnonymousClass_1(this));
        }

        public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
            Log.e("xxxx \u6388\u6743\u5931\u8d25");
        }

        public void onCancel(SHARE_MEDIA share_media, int i) {
            Log.e("xxxx \u6388\u6743\u53d6\u6d88");
        }
    }

    class AnonymousClass_3 implements WeiboAuthListener {
        final /* synthetic */ UMShareListener val$listener;

        AnonymousClass_3(UMShareListener uMShareListener) {
            this.val$listener = uMShareListener;
        }

        public void onWeiboException(WeiboException weiboException) {
            Log.d("sina_share", "weibo share exception");
            if (this.val$listener != null) {
                this.val$listener.onError(SHARE_MEDIA.SINA, new Throwable(weiboException));
            }
        }

        public void onComplete(Bundle bundle) {
            SinaSsoHandler.this.uploadAuthData(bundle);
            if (this.val$listener != null) {
                this.val$listener.onResult(SHARE_MEDIA.SINA);
            }
            SinaSsoHandler.this.sinaPreferences.setAuthData(bundle).commit();
        }

        public void onCancel() {
            Log.d("sina_share", "weibo share cancel");
            if (this.val$listener != null) {
                this.val$listener.onCancel(SHARE_MEDIA.SINA);
            }
        }
    }

    class AnonymousClass_4 implements UMAuthListener {
        final /* synthetic */ Activity val$act;
        final /* synthetic */ UMFriendListener val$listener;

        AnonymousClass_4(Activity activity, UMFriendListener uMFriendListener) {
            this.val$act = activity;
            this.val$listener = uMFriendListener;
        }

        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            QueuedWork.runInBack(new AnonymousClass_1(this, map));
        }

        public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
            Log.e("auth fail");
        }

        public void onCancel(SHARE_MEDIA share_media, int i) {
            Log.e("auth cancle");
        }
    }

    class AnonymousClass_5 implements Runnable {
        final /* synthetic */ Bundle val$bundle;

        AnonymousClass_5(Bundle bundle) {
            this.val$bundle = bundle;
        }

        public void run() {
            PlatformTokenUploadReq platformTokenUploadReq = new PlatformTokenUploadReq(SinaSsoHandler.this.getContext());
            platformTokenUploadReq.addStringParams("to", "sina");
            platformTokenUploadReq.addStringParams("usid", this.val$bundle.getString(ParamKey.UID));
            platformTokenUploadReq.addStringParams(Constants.PARAM_ACCESS_TOKEN, this.val$bundle.getString(Constants.PARAM_ACCESS_TOKEN));
            platformTokenUploadReq.addStringParams(Oauth2AccessToken.KEY_REFRESH_TOKEN, this.val$bundle.getString(Oauth2AccessToken.KEY_REFRESH_TOKEN));
            platformTokenUploadReq.addStringParams(Constants.PARAM_EXPIRES_IN, this.val$bundle.getString(Constants.PARAM_EXPIRES_IN));
            platformTokenUploadReq.addStringParams("app_id", SinaSsoHandler.this.config.appKey);
            platformTokenUploadReq.addStringParams("app_secret", SinaSsoHandler.this.config.appSecret);
            RestAPI.uploadPlatformToken(platformTokenUploadReq);
        }
    }

    class AuthListener implements WeiboAuthListener {
        private UMAuthListener mListener;

        AuthListener(UMAuthListener uMAuthListener) {
            this.mListener = null;
            this.mListener = uMAuthListener;
        }

        public void onComplete(Bundle bundle) {
            SinaSsoHandler.this.sinaPreferences.setAuthData(bundle).commit();
            SinaSsoHandler.this.uploadAuthData(bundle);
            if (this.mListener != null) {
                this.mListener.onComplete(SHARE_MEDIA.SINA, 0, SinaSsoHandler.this.bundleTomap(bundle));
            }
        }

        public void onCancel() {
            if (this.mListener != null) {
                this.mListener.onCancel(SHARE_MEDIA.SINA, 0);
            }
        }

        public void onWeiboException(WeiboException weiboException) {
            if (this.mListener != null) {
                this.mListener.onError(SHARE_MEDIA.SINA, 0, new Throwable(weiboException));
            }
        }
    }

    class AuthListenerWrapper implements UMAuthListener {
        private UMAuthListener mListener;

        AuthListenerWrapper(UMAuthListener uMAuthListener) {
            this.mListener = null;
            this.mListener = uMAuthListener;
        }

        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        }

        public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
            if (this.mListener != null) {
                this.mListener.onError(share_media, i, th);
            }
        }

        public void onCancel(SHARE_MEDIA share_media, int i) {
            if (this.mListener != null) {
                this.mListener.onCancel(share_media, i);
            }
        }
    }

    static class SinaConnection implements ServiceConnection {
        private static final String AUTH_SERVICE_NAME = "com.sina.weibo.business.RemoteSSOService";
        private static final String REDIRECT_URL = "http://sns.whalecloud.com";
        private static final String WEIBO_SIGNATURE = "30820295308201fea00302010202044b4ef1bf300d06092a864886f70d010105050030818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c74643020170d3130303131343130323831355a180f32303630303130323130323831355a30818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c746430819f300d06092a864886f70d010101050003818d00308189028181009d367115bc206c86c237bb56c8e9033111889b5691f051b28d1aa8e42b66b7413657635b44786ea7e85d451a12a82a331fced99c48717922170b7fc9bc1040753c0d38b4cf2b22094b1df7c55705b0989441e75913a1a8bd2bc591aa729a1013c277c01c98cbec7da5ad7778b2fad62b85ac29ca28ced588638c98d6b7df5a130203010001300d06092a864886f70d0101050500038181000ad4b4c4dec800bd8fd2991adfd70676fce8ba9692ae50475f60ec468d1b758a665e961a3aedbece9fd4d7ce9295cd83f5f19dc441a065689d9820faedbb7c4a4c4635f5ba1293f6da4b72ed32fb8795f736a20c95cda776402099054fccefb4a1a558664ab8d637288feceba9508aa907fc1fe2b1ae5a0dec954ed831c0bea4";
        public String ActivityName;
        public boolean IsConnected;
        public boolean IsOK;
        public String PackageName;
        private WeakReference<Activity> mActivity;
        private String mAppId;
        private String[] mPermissions;

        public SinaConnection(Activity activity, String str) {
            this.IsOK = false;
            this.IsConnected = false;
            this.PackageName = null;
            this.ActivityName = null;
            this.mActivity = null;
            this.mAppId = null;
            this.mPermissions = null;
            this.mActivity = new WeakReference(activity);
            this.mAppId = str;
        }

        public void setPermissions(String[] strArr) {
            this.mPermissions = strArr;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.IsConnected = false;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.IsConnected = true;
            RemoteSSO asInterface = Stub.asInterface(iBinder);
            try {
                this.PackageName = asInterface.getPackageName();
                this.ActivityName = asInterface.getActivityName();
                this.IsOK = startSingleSignOn((Activity) this.mActivity.get(), REQUEST_CODE);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        private boolean startSingleSignOn(Activity activity, int i) {
            Object obj = 1;
            Intent intent = new Intent();
            intent.setClassName(this.PackageName, this.ActivityName);
            intent.putExtra(com.taobao.accs.common.Constants.KEY_APP_KEY, this.mAppId);
            intent.putExtra(WBConstants.SSO_REDIRECT_URL, REDIRECT_URL);
            if (this.mPermissions != null && this.mPermissions.length > 0) {
                intent.putExtra(Constants.PARAM_SCOPE, TextUtils.join(MiPushClient.ACCEPT_TIME_SEPARATOR, this.mPermissions));
            }
            if (!validateAppSignatureForIntent(activity, intent)) {
                return false;
            }
            boolean z;
            try {
                activity.startActivityForResult(intent, i);
            } catch (ActivityNotFoundException e) {
                z = false;
            }
            if (this.IsConnected) {
                this.IsConnected = isServiceAlive(activity);
                if (this.IsConnected) {
                    activity.getApplication().unbindService(this);
                }
            }
            return z;
        }

        private boolean validateAppSignatureForIntent(Context context, Intent intent) {
            PackageManager packageManager = context.getPackageManager();
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
            if (resolveActivity == null) {
                return false;
            }
            try {
                Signature[] signatureArr = packageManager.getPackageInfo(resolveActivity.activityInfo.packageName, R.styleable.AppCompatTheme_imageButtonStyle).signatures;
                int length = signatureArr.length;
                for (int i = 0; i < length; i++) {
                    if (WEIBO_SIGNATURE.equals(signatureArr[i].toCharsString())) {
                        return true;
                    }
                }
                return false;
            } catch (NameNotFoundException e) {
                return false;
            }
        }

        private boolean isServiceAlive(Context context) {
            List runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(R.styleable.AppCompatTheme_buttonStyle);
            if (runningServices.size() <= 0) {
                return false;
            }
            for (int i = 0; i < runningServices.size(); i++) {
                if (((RunningServiceInfo) runningServices.get(i)).service.getClassName().equals(AUTH_SERVICE_NAME)) {
                    return true;
                }
            }
            return false;
        }
    }

    public SinaSsoHandler() {
        this.config = null;
    }

    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
        this.mContext = context.getApplicationContext();
        this.config = (SinaWeibo) platform;
        this.sinaPreferences = new SinaPreferences(this.mContext, "sina");
        this.mAuthInfo = new AuthInfo(context, ((SinaWeibo) platform).appKey, Config.REDIRECT_URL, SCOPE);
        if (context instanceof Activity) {
            this.mSsoHandler = new SsoHandler((Activity) context, this.mAuthInfo);
            this.mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(context, this.config.appKey);
            this.mWeiboShareAPI.registerApp();
            Log.d("sina", "onCreate");
        }
    }

    public IWeiboShareAPI getmWeiboShareAPI() {
        return this.mWeiboShareAPI;
    }

    public boolean isAuthorized() {
        return this.sinaPreferences.isAuthorized();
    }

    public boolean isInstall(Context context) {
        return isClientInstalled();
    }

    public boolean isAuthorize(Context context) {
        return isAuthorized();
    }

    public SHARE_MEDIA getPlatform() {
        return SHARE_MEDIA.SINA;
    }

    public String getUID() {
        return this.sinaPreferences.getUID();
    }

    public boolean isClientInstalled() {
        return this.mWeiboShareAPI.isWeiboAppInstalled();
    }

    public void authorize(Activity activity, UMAuthListener uMAuthListener) {
        this.mAuthListener = new AuthListener(uMAuthListener);
        this.mSsoHandler.authorize(this.mAuthListener);
    }

    protected void requestAsync(String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener, String str3) {
        if (str3 == null || TextUtils.isEmpty(str) || weiboParameters == null || TextUtils.isEmpty(str2) || requestListener == null) {
            LogUtil.e(TAG, "Argument error!");
            return;
        }
        weiboParameters.put(Constants.PARAM_ACCESS_TOKEN, str3);
        new AsyncWeiboRunner(this.mContext).requestAsync(str, weiboParameters, str2, requestListener);
    }

    public void getPlatformInfo(Activity activity, UMAuthListener uMAuthListener) {
        if (this.sinaPreferences.getUID() != null) {
            WeiboParameters weiboParameters = new WeiboParameters(this.config.appKey);
            weiboParameters.put(ParamKey.UID, this.sinaPreferences.getUID());
            requestAsync("https://api.weibo.com/2/users/show.json", weiboParameters, Constants.HTTP_GET, new AnonymousClass_1(uMAuthListener), this.sinaPreferences.getmAccessToken());
            return;
        }
        authorize(activity, new AnonymousClass_2(activity, uMAuthListener));
    }

    public void deleteAuth(Context context, UMAuthListener uMAuthListener) {
        this.sinaPreferences.delete();
        uMAuthListener.onComplete(SHARE_MEDIA.SINA, 1, null);
    }

    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        if (activity == null) {
            Log.d("UMError", "Sina share activity is null");
            return false;
        }
        SinaShareContent sinaShareContent = new SinaShareContent(shareContent);
        sinaShareContent.SetContext(activity);
        BaseRequest sendMultiMessageToWeiboRequest = new SendMultiMessageToWeiboRequest();
        sendMultiMessageToWeiboRequest.transaction = String.valueOf(System.currentTimeMillis());
        sendMultiMessageToWeiboRequest.multiMessage = sinaShareContent.getMessage();
        AuthInfo authInfo = new AuthInfo(activity, this.config.appKey, Config.REDIRECT_URL, SCOPE);
        String str = a.d;
        if (this.sinaPreferences != null) {
            str = this.sinaPreferences.getmAccessToken();
        }
        this.shareListener = uMShareListener;
        this.mWeiboShareAPI.sendRequest(activity, sendMultiMessageToWeiboRequest, authInfo, str, new AnonymousClass_3(uMShareListener));
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mSsoHandler != null) {
            this.mSsoHandler.authorizeCallBack(i, i2, intent);
        }
        this.mSsoHandler = null;
    }

    public boolean isSupportAuth() {
        return true;
    }

    public int getRequestCode() {
        return REQUEST_CODE;
    }

    public void setScope(String[] strArr) {
    }

    private boolean bindRemoteSSOService(Activity activity, String str) {
        ServiceConnection sinaConnection = new SinaConnection(activity, str);
        Context applicationContext = activity.getApplicationContext();
        Intent intent = new Intent("com.sina.weibo.remotessoservice");
        List queryIntentServices = activity.getPackageManager().queryIntentServices(intent, 0);
        ComponentName componentName = null;
        if (queryIntentServices != null && queryIntentServices.size() > 0) {
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentServices.get(0);
            componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        }
        intent.setComponent(componentName);
        return applicationContext.bindService(intent, sinaConnection, 1);
    }

    public void authorizeCallBack(int i, int i2, Intent intent) {
        if (i == 5659) {
            SocializeException socializeException;
            if (i2 == -1) {
                String stringExtra = intent.getStringExtra("error");
                if (stringExtra == null) {
                    stringExtra = intent.getStringExtra("error_type");
                }
                if (stringExtra == null) {
                    this.mAuthListener.onComplete(intent.getExtras());
                } else if (stringExtra.equals("access_denied") || stringExtra.equals("OAuthAccessDeniedException")) {
                    Log.d("Weibo-authorize", "Login canceled by user.");
                    this.mAuthListener.onCancel();
                } else {
                    String stringExtra2 = intent.getStringExtra("error_description");
                    if (stringExtra2 != null) {
                        stringExtra = stringExtra + ":" + stringExtra2;
                    }
                    Log.d("Weibo-authorize", new StringBuilder("Login failed: ").append(stringExtra).toString());
                    socializeException = new SocializeException(i2, stringExtra);
                }
            } else if (i2 != 0) {
            } else {
                if (intent != null) {
                    Log.d("Weibo-authorize", new StringBuilder("Login failed: ").append(intent.getStringExtra("error")).toString());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(intent.getStringExtra("error"));
                    stringBuilder.append(" : ");
                    stringBuilder.append(intent.getStringExtra("failing_url"));
                    socializeException = new SocializeException(intent.getIntExtra("error_code", -1), stringBuilder.toString());
                    return;
                }
                Log.d("Weibo-authorize", "Login canceled by user.");
                this.mAuthListener.onCancel();
            }
        }
    }

    public Bundle getEditable(ShareContent shareContent) {
        Bundle bundle = new Bundle();
        bundle.putString(ShareActivity.KEY_PLATFORM, SHARE_MEDIA.SINA.toString());
        bundle.putString(WebBrowserActivity.EXTRA_TITLE, "\u5206\u4eab\u5230\u65b0\u6d6a\u5fae\u535a");
        bundle.putString(ShareActivity.KEY_TEXT, shareContent.mText);
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMImage)) {
            File asFileImage = ((UMImage) shareContent.mMedia).asFileImage();
            if (asFileImage != null) {
                bundle.putString(ShareActivity.KEY_PIC, asFileImage.getAbsolutePath());
            }
        }
        bundle.putBoolean(ShareActivity.KEY_AT, true);
        bundle.putBoolean(ShareActivity.KEY_LOCATION, true);
        if (shareContent.mFollow == null) {
            bundle.putBoolean(ShareActivity.KEY_FOLLOW, false);
        } else if (this.sinaPreferences.Isfollow()) {
            bundle.putBoolean(ShareActivity.KEY_FOLLOW, false);
        } else {
            bundle.putBoolean(ShareActivity.KEY_FOLLOW, true);
        }
        return bundle;
    }

    protected void saveFollow() {
        super.saveFollow();
        this.sinaPreferences.setIsfollow(true);
    }

    public ShareContent getResult(ShareContent shareContent, Bundle bundle) {
        shareContent.mText = bundle.getString(ShareActivity.KEY_TEXT);
        if (!bundle.getBoolean(ShareActivity.KEY_FOLLOW)) {
            shareContent.mFollow = null;
        }
        if (bundle.getString(ShareActivity.KEY_PIC) == null && (shareContent.mMedia instanceof UMImage)) {
            shareContent.mMedia = null;
        }
        if (bundle.getSerializable(ShareActivity.KEY_LOCATION) != null) {
            shareContent.mLocation = (UMLocation) bundle.getSerializable(ShareActivity.KEY_LOCATION);
        }
        return shareContent;
    }

    public void getfriend(Activity activity, UMFriendListener uMFriendListener) {
        if (activity == null) {
            Log.d("UMError", "Sina getFriend activity is null");
            return;
        }
        String uid = this.sinaPreferences.getUID();
        if (uid == null) {
            authorize(activity, new AnonymousClass_4(activity, uMFriendListener));
            return;
        }
        ShareFriendsResponse queryFriendsList = RestAPI.queryFriendsList(new ShareFriendsRequest(activity, SHARE_MEDIA.SINA, uid));
        if (queryFriendsList == null) {
            uMFriendListener.onError(SHARE_MEDIA.SINA, XZBDevice.DOWNLOAD_LIST_RECYCLE, new Throwable("resp = null"));
        } else if (queryFriendsList.isOk()) {
            Map hashMap = new HashMap();
            hashMap.put("friend", queryFriendsList.mFriends);
            hashMap.put("json", queryFriendsList.getJsonData());
            uMFriendListener.onComplete(SHARE_MEDIA.SINA, XZBDevice.DOWNLOAD_LIST_RECYCLE, hashMap);
        } else {
            uMFriendListener.onError(SHARE_MEDIA.SINA, XZBDevice.DOWNLOAD_LIST_RECYCLE, new Throwable(queryFriendsList.mMsg));
        }
    }

    public void onResponse(BaseResponse baseResponse) {
        switch (baseResponse.errCode) {
            case SpdyAgent.ACCS_TEST_SERVER:
                Log.d("sina_share", "weibo share error ok");
                if (isClientInstalled()) {
                    this.shareListener.onResult(SHARE_MEDIA.SINA);
                }
            case SpdyAgent.ACCS_ONLINE_SERVER:
                Log.d("sina_share", "weibo share cancel");
                this.shareListener.onCancel(SHARE_MEDIA.SINA);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                Log.d("sina_share", "weibo share fail");
                this.shareListener.onError(SHARE_MEDIA.SINA, new Throwable(baseResponse.errMsg));
            default:
                break;
        }
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

    private void uploadAuthData(Bundle bundle) throws SocializeException {
        new Thread(new AnonymousClass_5(bundle)).start();
    }
}
