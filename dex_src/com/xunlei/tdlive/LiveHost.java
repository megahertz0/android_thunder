package com.xunlei.tdlive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.common.member.XLAvatarItem;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.homepage.HomeFragment;
import com.xunlei.downloadprovider.homepage.i;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.downloadprovidershare.d.a;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.tdlive.sdk.IClient;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.tdlive.user.DefaultXLOnUserListener;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONObject;

public class LiveHost implements IHost {
    private static final String BACK_TO_XLLIVE_TAB_ACTION = "com.xunlei.tdlive.sdk.LiveHost.BACK_TO_XLLIVE_TAB_ACTION";
    private static final String DOWNLOAD_ACTION = "com.xunlei.tdlive.sdk.LiveHost.DOWNLOAD_ACTION";
    private static final String LIMIT_SPEED_ACTION = "com.xunlei.tdlive.sdk.LiveHost.LIMIT_SPEED_ACTION";
    private static final String PAY_ACTION = "com.xunlei.tdlive.sdk.LiveHost.PAY_ACTION";
    private static final String SHARE_ACTION = "com.xunlei.tdlive.sdk.LiveHost.SHARE_ACTION";
    private static final String SHARE_RET_ACTION = "com.xunlei.tdlive.sdk.LiveHost.SHARE_RET_ACTION";
    private static final String SILENT_LOGIN_ACTION = "com.xunlei.tdlive.sdk.LiveHost.SILENT_LOGIN_ACTION";
    private static final String STAT_TRACE_ACTION = "com.xunlei.tdlive.sdk.LiveHost.STAT_TRACE_ACTION";
    private static final String TAG = "LiveHost";
    private static long sLeaveTime;
    private static String sSessionId;
    private IClient mClient;
    private XLOnUserListener mXLOnUserListener;

    class AnonymousClass_1 implements XLOnPayListener {
        final /* synthetic */ Context val$context;

        AnonymousClass_1(Context context) {
            this.val$context = context;
        }

        public void onWxPay(int i, String str, Object obj, int i2) {
            LiveHost.this.mClient.firePayEvent(this.val$context, 0, i, str, obj == null ? BuildConfig.VERSION_NAME : obj.toString(), i2);
        }

        public void onAliPay(int i, String str, Object obj, int i2) {
            LiveHost.this.mClient.firePayEvent(this.val$context, 1, i, str, obj == null ? BuildConfig.VERSION_NAME : obj.toString(), i2);
        }

        public void onNbPay(int i, String str, Object obj, int i2) {
            LiveHost.this.mClient.firePayEvent(this.val$context, SimpleLog.LOG_LEVEL_DEBUG, i, str, obj == null ? BuildConfig.VERSION_NAME : obj.toString(), i2);
        }

        public void onGetPrice(int i, String str, Object obj, int i2, String str2) {
        }

        public void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
        }
    }

    class AnonymousClass_2 implements a {
        final /* synthetic */ Context val$context;

        AnonymousClass_2(Context context) {
            this.val$context = context;
        }

        public void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
        }

        public void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
            LiveHost.this.mClient.fireShareCompleteEvent(this.val$context, i, share_media);
        }
    }

    class AnonymousClass_3 extends DefaultXLOnUserListener {
        final /* synthetic */ Context val$context;

        AnonymousClass_3(Context context) {
            this.val$context = context;
        }

        private void onLogin(int i, XLUserInfo xLUserInfo) {
            if (i == 0) {
                String stringValue = xLUserInfo.getStringValue(USERINFOKEY.UserID);
                String stringValue2 = xLUserInfo.getStringValue(USERINFOKEY.SessionID);
                String stringValue3 = xLUserInfo.getStringValue(USERINFOKEY.NickName);
                if (stringValue3 == null || stringValue3.length() <= 0) {
                    stringValue3 = xLUserInfo.getStringValue(USERINFOKEY.UserName);
                }
                if (stringValue3 == null || stringValue3.length() <= 0) {
                    stringValue3 = xLUserInfo.getStringValue(USERINFOKEY.UserNewNo);
                }
                LiveHost.this.mClient.fireLoginEvent(this.val$context, i, stringValue, stringValue2, stringValue3, xLUserInfo.getStringValue(USERINFOKEY.Sex));
            }
        }

        public boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2) {
            LiveHost.this.mClient.fireLogoutEvent(this.val$context);
            return true;
        }

        public boolean onUserGetCityInfo(int i, JSONObject jSONObject, Object obj, String str, int i2) {
            return false;
        }

        public boolean onUserSetInfo(int i, Object obj, String str, int i2) {
            XLUserInfo currentUser = XLUserUtil.getInstance().getCurrentUser();
            if (currentUser != null) {
                String stringValue = currentUser.getStringValue(USERINFOKEY.NickName);
                if (stringValue == null || stringValue.length() <= 0) {
                    stringValue = currentUser.getStringValue(USERINFOKEY.UserName);
                }
                if (stringValue == null || stringValue.length() <= 0) {
                    stringValue = currentUser.getStringValue(USERINFOKEY.UserNewNo);
                }
                LiveHost.this.mClient.fireSetUserInfoEvent(this.val$context, i, stringValue, currentUser.getStringValue(USERINFOKEY.Sex));
            }
            return super.onUserSetInfo(i, obj, str, i2);
        }

        public boolean onUserGetRecommendAvatars(int i, XLAvatarItem[] xLAvatarItemArr, Object obj, String str, int i2) {
            return false;
        }

        public boolean onUserSelectRecommendAvatar(int i, Object obj, String str, int i2) {
            return false;
        }

        public boolean onUserSetAvatar(int i, Object obj, String str, int i2) {
            LiveHost.this.mClient.fireSetUserAvatarEvent(this.val$context, i);
            return super.onUserSetAvatar(i, obj, str, i2);
        }

        public boolean onUserAqSendMessage(int i, String str, String str2, Object obj, int i2) {
            return false;
        }

        public boolean onUserAqBindMobile(int i, String str, String str2, Object obj, int i2) {
            return false;
        }

        public boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
            onLogin(i, xLUserInfo);
            return true;
        }

        public boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2) {
            onLogin(i, xLUserInfo);
            return true;
        }

        public boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4) {
            onLogin(i, xLUserInfo);
            return true;
        }

        public boolean onUserTokenLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
            onLogin(i, xLUserInfo);
            return true;
        }
    }

    public LiveHost() {
        sSessionId = UUID.randomUUID().toString();
        sLeaveTime = SystemClock.elapsedRealtime();
    }

    public void init(Context context, IClient iClient) {
        this.mClient = iClient;
        XLPayUtil.getInstance().attachListener(new AnonymousClass_1(context));
    }

    public void share(Context context, SHARE_MEDIA share_media, String str, String str2, String str3, String str4) {
        try {
            d.b().a((Activity) context, new ShareBean("xllive", XLLiveHelpers.encode(str), XLLiveHelpers.encode(str2), XLLiveHelpers.encode(str3), XLLiveHelpers.encode(str4)), share_media, new AnonymousClass_2(context));
        } catch (Throwable th) {
        }
    }

    public void login(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        intent.putExtra("login_from", "xllive");
        intent.putExtra("login_type", 1);
        context.startActivity(intent);
    }

    public void silentLogin(Context context) {
        try {
            if (this.mXLOnUserListener == null) {
                XLUserUtil instance = XLUserUtil.getInstance();
                AnonymousClass_3 anonymousClass_3 = new AnonymousClass_3(context);
                this.mXLOnUserListener = anonymousClass_3;
                instance.attachListener(anonymousClass_3);
            }
            boolean userIsLogined = XLUserUtil.getInstance().userIsLogined();
            if (userIsLogined) {
                XLUserInfo currentUser = XLUserUtil.getInstance().getCurrentUser();
                if (currentUser != null) {
                    String stringValue = currentUser.getStringValue(USERINFOKEY.UserID);
                    String stringValue2 = currentUser.getStringValue(USERINFOKEY.SessionID);
                    String stringValue3 = currentUser.getStringValue(USERINFOKEY.NickName);
                    if (stringValue3 == null || stringValue3.length() <= 0) {
                        stringValue3 = currentUser.getStringValue(USERINFOKEY.UserName);
                    }
                    if (stringValue3 == null || stringValue3.length() <= 0) {
                        stringValue3 = currentUser.getStringValue(USERINFOKEY.UserNewNo);
                    }
                    this.mClient.fireLoginEvent(context, 0, stringValue, stringValue2, stringValue3, currentUser.getStringValue(USERINFOKEY.Sex));
                    XLog.d(TAG, new StringBuilder("uifo:").append(currentUser.toString()).toString());
                }
            }
            XLog.d(TAG, new StringBuilder("logined:").append(userIsLogined).toString());
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void pay(android.content.Context r5, int r6, int r7, java.lang.String r8, int r9, java.lang.String r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.LiveHost.pay(android.content.Context, int, int, java.lang.String, int, java.lang.String):void");
        /*
        this = this;
        if (r6 != 0) goto L_0x0023;
    L_0x0002:
        r0 = new com.xunlei.common.pay.param.XLWxPayParam;	 Catch:{ Throwable -> 0x0048 }
        r0.<init>();	 Catch:{ Throwable -> 0x0048 }
        r1 = "wx3e6556568beeebdd";
        r0.mAppId = r1;	 Catch:{ Throwable -> 0x0048 }
        r0.mVasType = r7;	 Catch:{ Throwable -> 0x0048 }
        r0.mSource = r8;	 Catch:{ Throwable -> 0x0048 }
        r0.mMonth = r9;	 Catch:{ Throwable -> 0x0048 }
        r1 = com.xunlei.downloadprovider.member.login.LoginHelper.a();	 Catch:{ Throwable -> 0x0048 }
        r2 = r1.j;	 Catch:{ Throwable -> 0x0048 }
        r1 = (int) r2;	 Catch:{ Throwable -> 0x0048 }
        r0.mUserId = r1;	 Catch:{ Throwable -> 0x0048 }
        r1 = com.xunlei.common.pay.XLPayUtil.getInstance();	 Catch:{ Throwable -> 0x0048 }
        r1.userWxPay(r0, r10);	 Catch:{ Throwable -> 0x0048 }
    L_0x0022:
        return;
    L_0x0023:
        r0 = 1;
        if (r6 != r0) goto L_0x0022;
    L_0x0026:
        r0 = new com.xunlei.common.pay.param.XLAlipayParam;	 Catch:{ Throwable -> 0x0048 }
        r0.<init>();	 Catch:{ Throwable -> 0x0048 }
        r0.mVasType = r7;	 Catch:{ Throwable -> 0x0048 }
        r0.mSource = r8;	 Catch:{ Throwable -> 0x0048 }
        r0.mMonth = r9;	 Catch:{ Throwable -> 0x0048 }
        r1 = com.xunlei.downloadprovider.member.login.LoginHelper.a();	 Catch:{ Throwable -> 0x0048 }
        r2 = r1.j;	 Catch:{ Throwable -> 0x0048 }
        r1 = (int) r2;	 Catch:{ Throwable -> 0x0048 }
        r0.mUserId = r1;	 Catch:{ Throwable -> 0x0048 }
        r1 = com.xunlei.tdlive.XLLiveFragment.getHostMainActivity();	 Catch:{ Throwable -> 0x0048 }
        r0.mActivity = r1;	 Catch:{ Throwable -> 0x0048 }
        r1 = com.xunlei.common.pay.XLPayUtil.getInstance();	 Catch:{ Throwable -> 0x0048 }
        r1.userAliPay(r0, r10);	 Catch:{ Throwable -> 0x0048 }
        goto L_0x0022;
    L_0x0048:
        r0 = move-exception;
        goto L_0x0022;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void download(android.content.Context r7, java.lang.String r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.LiveHost.download(android.content.Context, java.lang.String):void");
        /*
        this = this;
        r0 = 0;
        if (r8 == 0) goto L_0x001e;
    L_0x0003:
        r1 = r8.length();	 Catch:{ Throwable -> 0x0035 }
        if (r1 <= 0) goto L_0x001e;
    L_0x0009:
        r0 = com.xunlei.downloadprovider.service.DownloadService.a();	 Catch:{ Throwable -> 0x0035 }
        r2 = 0;
        r3 = 38;
        r4 = "";
        r1 = com.xunlei.downloadprovider.app.BrothersApplication.a();	 Catch:{ Throwable -> 0x0035 }
        r5 = r1.e;	 Catch:{ Throwable -> 0x0035 }
        r1 = r8;
        r0 = r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0035 }
    L_0x001e:
        r1 = "LiveHost";
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0035 }
        r3 = "doDownloadAction ret:";
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0035 }
        r0 = r2.append(r0);	 Catch:{ Throwable -> 0x0035 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0035 }
        com.xunlei.tdlive.util.XLog.d(r1, r0);	 Catch:{ Throwable -> 0x0035 }
    L_0x0034:
        return;
    L_0x0035:
        r0 = move-exception;
        goto L_0x0034;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void limitSpeed(android.content.Context r5, int r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.LiveHost.limitSpeed(android.content.Context, int):void");
        /*
        this = this;
        if (r6 <= 0) goto L_0x0018;
    L_0x0002:
        com.xunlei.downloadprovider.service.downloads.task.d.a();	 Catch:{ Throwable -> 0x0022 }
        r0 = com.xunlei.downloadprovider.service.downloads.task.d.i();	 Catch:{ Throwable -> 0x0022 }
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = r0 / r2;
        r2 = (long) r6;	 Catch:{ Throwable -> 0x0022 }
        r0 = r0 - r2;
        r0 = (int) r0;	 Catch:{ Throwable -> 0x0022 }
        r1 = com.xunlei.downloadprovider.service.downloads.task.d.a();	 Catch:{ Throwable -> 0x0022 }
        r2 = (long) r0;	 Catch:{ Throwable -> 0x0022 }
        r1.c(r2);	 Catch:{ Throwable -> 0x0022 }
    L_0x0017:
        return;
    L_0x0018:
        r0 = com.xunlei.downloadprovider.service.downloads.task.d.a();	 Catch:{ Throwable -> 0x0022 }
        r2 = -1;
        r0.c(r2);	 Catch:{ Throwable -> 0x0022 }
        goto L_0x0017;
    L_0x0022:
        r0 = move-exception;
        goto L_0x0017;
        */
    }

    public void traceEvent(Context context, String str, String str2, String str3, Map<String, String> map) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if ("__onresume__".equals(str)) {
                    if (SystemClock.elapsedRealtime() - sLeaveTime > 300000) {
                        sSessionId = UUID.randomUUID().toString();
                        sLeaveTime = SystemClock.elapsedRealtime();
                    }
                } else if ("__onpause__".equals(str)) {
                    sLeaveTime = SystemClock.elapsedRealtime();
                } else {
                    g a = g.a("android_caomei", str, str);
                    if (!TextUtils.isEmpty(str2)) {
                        a.a("sdk_attr1", str2);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        a.a("sdk_attr2", str3);
                    }
                    a.a("sessionid", sSessionId);
                    if (map != null) {
                        for (String str4 : map.keySet()) {
                            a.a(str4, (String) map.get(str4));
                        }
                    }
                    ThunderReporter.a(a, false);
                }
            }
        } catch (Throwable th) {
        }
    }

    public void backToXLiveList(Context context) {
        HomeFragment.f = i.a().a("\u76f4\u64ad");
        Intent intent = new Intent(context, MainTabActivity.class);
        intent.addFlags(872415232);
        context.startActivity(intent);
    }

    public View newErrorView(Context context, OnClickListener onClickListener) {
        ErrorView errorView = new ErrorView(context);
        errorView.setActionButtonListener(onClickListener);
        return errorView;
    }

    public void setErrorViewType(Context context, View view, int i) {
        if (view instanceof ErrorView) {
            ((ErrorView) view).setErrorType(1);
        }
    }

    public String getPeerId() {
        return b.d();
    }

    public String getHubbleGUID() {
        return b.c();
    }

    public boolean showXLLiveTab(Context context) {
        return i.a().a("\u76f4\u64ad") != -1;
    }

    public void startMainTab(Context context) {
        Bundle bundle = new Bundle();
        bundle.putString("tab_tag", "thunder");
        Intent intent = new Intent(context, MainTabActivity.class);
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
