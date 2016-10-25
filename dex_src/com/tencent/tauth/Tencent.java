package com.tencent.tauth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.avatar.QQAvatar;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.connect.share.QzoneShare;
import com.tencent.open.GameAppOperation;
import com.tencent.open.LocationApi;
import com.tencent.open.SocialApi;
import com.tencent.open.SocialConstants;
import com.tencent.open.TaskGuide;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.open.wpa.WPA;
import com.tencent.open.yyb.AppbarAgent;
import com.umeng.a;
import java.io.IOException;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class Tencent {
    public static final int REQUEST_LOGIN = 10001;
    private static final String TAG = "openSDK_LOG.Tencent";
    private static Tencent sInstance;
    private AppbarAgent mAgent;
    private LocationApi mLocationApi;
    private final QQAuth mQQAuth;

    private Tencent(String str, Context context) {
        this.mQQAuth = QQAuth.createInstance(str, context);
    }

    public static synchronized Tencent createInstance(String str, Context context) {
        Tencent tencent;
        synchronized (Tencent.class) {
            Global.setContext(context.getApplicationContext());
            f.c(TAG, new StringBuilder("createInstance()  -- start, appId = ").append(str).toString());
            if (sInstance == null) {
                sInstance = new Tencent(str, context);
            } else if (!str.equals(sInstance.getAppId())) {
                sInstance.logout(context);
                sInstance = new Tencent(str, context);
            }
            if (checkManifestConfig(context, str)) {
                f.c(TAG, "createInstance()  -- end");
                tencent = sInstance;
            } else {
                tencent = null;
            }
        }
        return tencent;
    }

    private static boolean checkManifestConfig(Context context, String str) {
        boolean z = false;
        try {
            context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
            try {
                context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
                z = true;
                return true;
            } catch (NameNotFoundException e) {
                f.e(TAG, new StringBuilder("AndroidManifest.xml \u6ca1\u6709\u68c0\u6d4b\u5230com.tencent.connect.common.AssistActivity\n").append("\u6ca1\u6709\u5728AndroidManifest.xml\u4e2d\u68c0\u6d4b\u5230com.tencent.connect.common.AssistActivity,\u8bf7\u52a0\u4e0acom.tencent.connect.common.AssistActivity,\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770b\u5b98\u7f51\u6587\u6863." + "\n\u914d\u7f6e\u793a\u4f8b\u5982\u4e0b: \n<activity\n     android:name=\"com.tencent.connect.common.AssistActivity\"\n     android:screenOrientation=\"behind\"\n     android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"\n     android:configChanges=\"orientation|keyboardHidden\">\n</activity>").toString());
                return z;
            }
        } catch (NameNotFoundException e2) {
            f.e(TAG, new StringBuilder("AndroidManifest.xml \u6ca1\u6709\u68c0\u6d4b\u5230com.tencent.tauth.AuthActivity").append(new StringBuilder("\u6ca1\u6709\u5728AndroidManifest.xml\u4e2d\u68c0\u6d4b\u5230com.tencent.tauth.AuthActivity,\u8bf7\u52a0\u4e0acom.tencent.tauth.AuthActivity,\u5e76\u914d\u7f6e<data android:scheme=\"tencent").append(str).append("\" />,\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770b\u5b98\u7f51\u6587\u6863.").toString() + "\n\u914d\u7f6e\u793a\u4f8b\u5982\u4e0b: \n<activity\n     android:name=\"com.tencent.tauth.AuthActivity\"\n     android:noHistory=\"true\"\n     android:launchMode=\"singleTask\">\n<intent-filter>\n    <action android:name=\"android.intent.action.VIEW\" />\n    <category android:name=\"android.intent.category.DEFAULT\" />\n    <category android:name=\"android.intent.category.BROWSABLE\" />\n    <data android:scheme=\"tencent" + str + "\" />\n</intent-filter>\n</activity>").toString());
            return false;
        }
    }

    public int login(Activity activity, String str, IUiListener iUiListener) {
        f.c(TAG, new StringBuilder("login() with activity, scope is ").append(str).toString());
        return this.mQQAuth.login(activity, str, iUiListener);
    }

    public int login(Fragment fragment, String str, IUiListener iUiListener) {
        f.c(TAG, new StringBuilder("login() with fragment, scope is ").append(str).toString());
        return this.mQQAuth.login(fragment, str, iUiListener, a.d);
    }

    public int loginServerSide(Activity activity, String str, IUiListener iUiListener) {
        f.c(TAG, new StringBuilder("loginServerSide() with activity, scope = ").append(str).append(",server_side").toString());
        return this.mQQAuth.login(activity, str + ",server_side", iUiListener);
    }

    public int loginServerSide(Fragment fragment, String str, IUiListener iUiListener) {
        f.c(TAG, new StringBuilder("loginServerSide() with fragment, scope = ").append(str).append(",server_side").toString());
        return this.mQQAuth.login(fragment, str + ",server_side", iUiListener, a.d);
    }

    public int loginWithOEM(Activity activity, String str, IUiListener iUiListener, String str2, String str3, String str4) {
        f.c(TAG, new StringBuilder("loginWithOEM() with activity, scope = ").append(str).toString());
        return this.mQQAuth.loginWithOEM(activity, str, iUiListener, str2, str3, str4);
    }

    public void logout(Context context) {
        f.c(TAG, "logout()");
        this.mQQAuth.getQQToken().setAccessToken(null, MessageService.MSG_DB_READY_REPORT);
        this.mQQAuth.getQQToken().setOpenId(null);
    }

    public int reAuth(Activity activity, String str, IUiListener iUiListener) {
        f.c(TAG, new StringBuilder("reAuth() with activity, scope = ").append(str).toString());
        return this.mQQAuth.reAuth(activity, str, iUiListener);
    }

    public void reportDAU() {
        this.mQQAuth.reportDAU();
    }

    public void checkLogin(IUiListener iUiListener) {
        f.c(TAG, "checkLogin()");
        this.mQQAuth.checkLogin(iUiListener);
    }

    public int invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "invite()");
        new SocialApi(this.mQQAuth.getQQToken()).invite(activity, bundle, iUiListener);
        return 0;
    }

    public int story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "story()");
        new SocialApi(this.mQQAuth.getQQToken()).story(activity, bundle, iUiListener);
        return 0;
    }

    public int gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "gift()");
        new SocialApi(this.mQQAuth.getQQToken()).gift(activity, bundle, iUiListener);
        return 0;
    }

    public int ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "ask()");
        new SocialApi(this.mQQAuth.getQQToken()).ask(activity, bundle, iUiListener);
        return 0;
    }

    public void requestAsync(String str, Bundle bundle, String str2, IRequestListener iRequestListener, Object obj) {
        f.c(TAG, "requestAsync()");
        HttpUtils.requestAsync(this.mQQAuth.getQQToken(), Global.getContext(), str, bundle, str2, iRequestListener);
    }

    public JSONObject request(String str, Bundle bundle, String str2) throws IOException, JSONException, NetworkUnavailableException, HttpStatusException {
        f.c(TAG, "request()");
        return HttpUtils.request(this.mQQAuth.getQQToken(), Global.getContext(), str, bundle, str2);
    }

    public void shareToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "shareToQQ()");
        new QQShare(activity, this.mQQAuth.getQQToken()).shareToQQ(activity, bundle, iUiListener);
    }

    public void shareToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "shareToQzone()");
        new QzoneShare(activity, this.mQQAuth.getQQToken()).shareToQzone(activity, bundle, iUiListener);
    }

    public void publishToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "publishToQzone()");
        new QzonePublish(activity, this.mQQAuth.getQQToken()).publishToQzone(activity, bundle, iUiListener);
    }

    public void releaseResource() {
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        f.c(TAG, "onActivityResult() deprecated, will do nothing");
        return false;
    }

    public static boolean onActivityResultData(int i, int i2, Intent intent, IUiListener iUiListener) {
        boolean z;
        boolean z2 = true;
        String str = TAG;
        StringBuilder append = new StringBuilder("onActivityResultData() reqcode = ").append(i).append(", resultcode = ").append(i2).append(", data = null ? ");
        if (intent == null) {
            z = true;
        } else {
            z = false;
        }
        StringBuilder append2 = append.append(z).append(", listener = null ? ");
        if (iUiListener != null) {
            z2 = false;
        }
        f.c(str, append2.append(z2).toString());
        return UIListenerManager.getInstance().onActivityResult(i, i2, intent, iUiListener);
    }

    public boolean isSessionValid() {
        return this.mQQAuth.isSessionValid();
    }

    public String getAppId() {
        return this.mQQAuth.getQQToken().getAppId();
    }

    public String getAccessToken() {
        return this.mQQAuth.getQQToken().getAccessToken();
    }

    public long getExpiresIn() {
        return this.mQQAuth.getQQToken().getExpireTimeInSecond();
    }

    public String getOpenId() {
        return this.mQQAuth.getQQToken().getOpenId();
    }

    @Deprecated
    public void handleLoginData(Intent intent, IUiListener iUiListener) {
        boolean z;
        boolean z2 = true;
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder("handleLoginData() data = null ? ");
        if (intent == null) {
            z = true;
        } else {
            z = false;
        }
        StringBuilder append = stringBuilder.append(z).append(", listener = null ? ");
        if (iUiListener != null) {
            z2 = false;
        }
        f.c(str, append.append(z2).toString());
        UIListenerManager.getInstance().handleDataToListener(intent, iUiListener);
    }

    public static void handleResultData(Intent intent, IUiListener iUiListener) {
        boolean z;
        boolean z2 = true;
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder("handleResultData() data = null ? ");
        if (intent == null) {
            z = true;
        } else {
            z = false;
        }
        StringBuilder append = stringBuilder.append(z).append(", listener = null ? ");
        if (iUiListener != null) {
            z2 = false;
        }
        f.c(str, append.append(z2).toString());
        UIListenerManager.getInstance().handleDataToListener(intent, iUiListener);
    }

    public void setAccessToken(String str, String str2) {
        f.a(TAG, new StringBuilder("setAccessToken(), expiresIn = ").append(str2).toString());
        this.mQQAuth.setAccessToken(str, str2);
    }

    public void setOpenId(String str) {
        f.a(TAG, "setOpenId() --start");
        this.mQQAuth.setOpenId(Global.getContext(), str);
        f.a(TAG, "setOpenId() --end");
    }

    public boolean isReady() {
        return isSessionValid() && getOpenId() != null;
    }

    public QQToken getQQToken() {
        return this.mQQAuth.getQQToken();
    }

    public boolean isSupportSSOLogin(Activity activity) {
        if (!Util.isTablet(activity) || SystemUtils.getAppVersionName(activity, Constants.PACKAGE_QQ_PAD) == null) {
            return SystemUtils.getAppVersionName(activity, Constants.PACKAGE_QQ) == null ? false : SystemUtils.checkMobileQQ(activity);
        } else {
            return true;
        }
    }

    public void makeFriend(Activity activity, Bundle bundle) {
        new GameAppOperation(getQQToken()).makeFriend(activity, bundle);
    }

    public void bindQQGroup(Activity activity, Bundle bundle) {
        new GameAppOperation(getQQToken()).bindQQGroup(activity, bundle);
    }

    public void addToQQFavorites(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new GameAppOperation(getQQToken()).addToQQFavorites(activity, bundle, iUiListener);
    }

    public void sendToMyComputer(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new GameAppOperation(getQQToken()).sendToMyComputer(activity, bundle, iUiListener);
    }

    public void shareToTroopBar(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new GameAppOperation(getQQToken()).shareToTroopBar(activity, bundle, iUiListener);
    }

    public int startWPAConversation(Activity activity, String str, String str2) {
        return startWPAConversation(activity, WPA.CHAT_TYPE_WPA, str, str2);
    }

    public int startWPAConversation(Activity activity, String str, String str2, String str3) {
        f.c(TAG, "startWPAConversation()");
        return new WPA(getQQToken()).startWPAConversation(activity, str, str2, str3);
    }

    public void getWPAUserOnlineState(String str, IUiListener iUiListener) {
        f.c(TAG, "getWPAUserOnlineState()");
        new WPA(getQQToken()).getWPAUserOnlineState(str, iUiListener);
    }

    public int reactive(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "reactive()");
        new SocialApi(this.mQQAuth.getQQToken()).reactive(activity, bundle, iUiListener);
        return 0;
    }

    public int searchNearby(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "searchNearby()");
        if (this.mLocationApi == null) {
            this.mLocationApi = new LocationApi(this.mQQAuth.getQQToken());
        }
        this.mLocationApi.searchNearby(activity, bundle, iUiListener);
        return 0;
    }

    public int deleteLocation(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "deleteLocation()");
        if (this.mLocationApi == null) {
            this.mLocationApi = new LocationApi(this.mQQAuth.getQQToken());
        }
        this.mLocationApi.deleteLocation(activity, bundle, iUiListener);
        return 0;
    }

    public int brag(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "brag()");
        new SocialApi(this.mQQAuth.getQQToken()).brag(activity, bundle, iUiListener);
        return 0;
    }

    public int challenge(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "challenge()");
        new SocialApi(this.mQQAuth.getQQToken()).challenge(activity, bundle, iUiListener);
        return 0;
    }

    public void setAvatar(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "setAvatar()");
        String string = bundle.getString(SocialConstants.PARAM_AVATAR_URI);
        new QQAvatar(this.mQQAuth.getQQToken()).setAvatar(activity, Uri.parse(string), iUiListener, bundle.getInt("exitAnim"));
    }

    public void setAvatar(Activity activity, Bundle bundle, IUiListener iUiListener, int i, int i2) {
        f.c(TAG, "setAvatar()");
        bundle.putInt("exitAnim", i2);
        activity.overridePendingTransition(i, 0);
        setAvatar(activity, bundle, iUiListener);
    }

    public void grade(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "grade()");
        new SocialApi(this.mQQAuth.getQQToken()).grade(activity, bundle, iUiListener);
    }

    public void voice(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "voice()");
        new SocialApi(this.mQQAuth.getQQToken()).voice(activity, bundle, iUiListener);
    }

    public void showTaskGuideWindow(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "showTaskGuideWindow()");
        new TaskGuide(activity, this.mQQAuth.getQQToken()).showTaskGuideWindow(activity, bundle, iUiListener);
    }

    public void startAppbar(Activity activity, String str) {
        f.c(TAG, "startAppbar()");
        if (this.mAgent == null) {
            this.mAgent = new AppbarAgent(this.mQQAuth.getQQToken());
        }
        this.mAgent.startAppbar(activity, str);
    }

    public void startAppbarLabel(Activity activity, String str) {
        f.c(TAG, "startAppbarLabel()");
        if (this.mAgent == null) {
            this.mAgent = new AppbarAgent(this.mQQAuth.getQQToken());
        }
        this.mAgent.startAppbarLabel(activity, str);
    }

    public void sharePrizeToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "sharePrizeToQQ()");
        new GameAppOperation(getQQToken()).sharePrizeToQQ(activity, bundle, iUiListener);
    }

    public void queryUnexchangePrize(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "queryUnexchangePrize()");
        new GameAppOperation(getQQToken()).queryUnexchangePrize(activity, bundle, iUiListener);
    }

    public void exchangePrize(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "exchangePrize()");
        new GameAppOperation(getQQToken()).exchangePrize(activity, bundle, iUiListener);
    }

    public void checkActivityAvailable(Activity activity, String str, IUiListener iUiListener) {
        f.c(TAG, "checkActivityAvailable()");
        new GameAppOperation(getQQToken()).isActivityAvailable(activity, str, iUiListener);
    }

    public boolean checkPrizeByIntent(Activity activity, Intent intent) {
        f.c(TAG, "checkPrizeByIntent()");
        if (intent != null) {
            return intent.getBooleanExtra(AuthActivity.ACTION_SHARE_PRIZE, false);
        }
        f.e(TAG, "-->check by prize by intent, intent is null.");
        return false;
    }

    public void startAppbarThread(Activity activity, String str) {
        f.c(TAG, "startAppbarThread()");
        if (this.mAgent == null) {
            this.mAgent = new AppbarAgent(this.mQQAuth.getQQToken());
        }
        this.mAgent.startAppbarThread(activity, str);
    }

    public boolean joinQQGroup(Activity activity, String str) {
        f.c(TAG, "joinQQGroup()");
        Intent intent = new Intent();
        intent.setData(Uri.parse(new StringBuilder("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D").append(str).toString()));
        try {
            activity.startActivity(intent);
            d.a().a(this.mQQAuth.getQQToken().getOpenId(), this.mQQAuth.getQQToken().getAppId(), Constants.VIA_JOIN_GROUP, Constants.VIA_REPORT_TYPE_JOININ_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_READY_REPORT);
            return true;
        } catch (Exception e) {
            d.a().a(this.mQQAuth.getQQToken().getOpenId(), this.mQQAuth.getQQToken().getAppId(), Constants.VIA_JOIN_GROUP, Constants.VIA_REPORT_TYPE_JOININ_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
            return false;
        }
    }
}
