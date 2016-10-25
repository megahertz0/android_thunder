package com.tencent.open.wpa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.Base64;
import com.sina.weibo.sdk.component.GameManager;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelbase.BaseResp.ErrCode;
import com.tencent.open.TDialog;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.open.yyb.AppbarJsBridge;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import org.android.agoo.message.MessageService;

// compiled from: ProGuard
public class WPA extends BaseApi {
    public static final String CHAT_TYPE_GROUP = "group";
    public static final String CHAT_TYPE_WPA = "wpa";

    public WPA(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public WPA(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public WPA(QQToken qQToken) {
        super(qQToken);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getWPAUserOnlineState(java.lang.String r9, com.tencent.tauth.IUiListener r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.wpa.WPA.getWPAUserOnlineState(java.lang.String, com.tencent.tauth.IUiListener):void");
        /*
        this = this;
        r7 = 0;
        if (r9 != 0) goto L_0x005a;
    L_0x0003:
        r0 = com.tencent.open.b.d.a();	 Catch:{ Exception -> 0x002b }
        r1 = r8.mToken;	 Catch:{ Exception -> 0x002b }
        r1 = r1.getOpenId();	 Catch:{ Exception -> 0x002b }
        r2 = r8.mToken;	 Catch:{ Exception -> 0x002b }
        r2 = r2.getAppId();	 Catch:{ Exception -> 0x002b }
        r3 = "ANDROIDSDK.WPASTATE.XX";
        r4 = "15";
        r5 = "18";
        r6 = "1";
        r0.a(r1, r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x002b }
        r0 = new java.lang.Exception;	 Catch:{ Exception -> 0x002b }
        r1 = "uin null";
        r0.<init>(r1);	 Catch:{ Exception -> 0x002b }
        throw r0;	 Catch:{ Exception -> 0x002b }
    L_0x002b:
        r0 = move-exception;
        if (r10 == 0) goto L_0x003a;
    L_0x002e:
        r0 = new com.tencent.tauth.UiError;
        r1 = -5;
        r2 = "\u4f20\u5165\u53c2\u6570\u6709\u8bef!";
        r0.<init>(r1, r2, r7);
        r10.onError(r0);
    L_0x003a:
        r0 = com.tencent.open.b.d.a();
        r1 = r8.mToken;
        r1 = r1.getOpenId();
        r2 = r8.mToken;
        r2 = r2.getAppId();
        r3 = "ANDROIDSDK.WPASTATE.XX";
        r4 = "15";
        r5 = "18";
        r6 = "1";
        r0.a(r1, r2, r3, r4, r5, r6);
    L_0x0059:
        return;
    L_0x005a:
        r0 = r9.length();	 Catch:{ Exception -> 0x002b }
        r1 = 5;
        if (r0 >= r1) goto L_0x0089;
    L_0x0061:
        r0 = com.tencent.open.b.d.a();	 Catch:{ Exception -> 0x002b }
        r1 = r8.mToken;	 Catch:{ Exception -> 0x002b }
        r1 = r1.getOpenId();	 Catch:{ Exception -> 0x002b }
        r2 = r8.mToken;	 Catch:{ Exception -> 0x002b }
        r2 = r2.getAppId();	 Catch:{ Exception -> 0x002b }
        r3 = "ANDROIDSDK.WPASTATE.XX";
        r4 = "15";
        r5 = "18";
        r6 = "1";
        r0.a(r1, r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x002b }
        r0 = new java.lang.Exception;	 Catch:{ Exception -> 0x002b }
        r1 = "uin length < 5";
        r0.<init>(r1);	 Catch:{ Exception -> 0x002b }
        throw r0;	 Catch:{ Exception -> 0x002b }
    L_0x0089:
        r0 = 0;
    L_0x008a:
        r1 = r9.length();	 Catch:{ Exception -> 0x002b }
        if (r0 >= r1) goto L_0x00c5;
    L_0x0090:
        r1 = r9.charAt(r0);	 Catch:{ Exception -> 0x002b }
        r1 = java.lang.Character.isDigit(r1);	 Catch:{ Exception -> 0x002b }
        if (r1 != 0) goto L_0x00c2;
    L_0x009a:
        r0 = com.tencent.open.b.d.a();	 Catch:{ Exception -> 0x002b }
        r1 = r8.mToken;	 Catch:{ Exception -> 0x002b }
        r1 = r1.getOpenId();	 Catch:{ Exception -> 0x002b }
        r2 = r8.mToken;	 Catch:{ Exception -> 0x002b }
        r2 = r2.getAppId();	 Catch:{ Exception -> 0x002b }
        r3 = "ANDROIDSDK.WPASTATE.XX";
        r4 = "15";
        r5 = "18";
        r6 = "1";
        r0.a(r1, r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x002b }
        r0 = new java.lang.Exception;	 Catch:{ Exception -> 0x002b }
        r1 = "uin not digit";
        r0.<init>(r1);	 Catch:{ Exception -> 0x002b }
        throw r0;	 Catch:{ Exception -> 0x002b }
    L_0x00c2:
        r0 = r0 + 1;
        goto L_0x008a;
    L_0x00c5:
        r0 = "http://webpresence.qq.com/getonline?Type=1&";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r0 = r0.append(r9);
        r1 = ":";
        r0 = r0.append(r1);
        r2 = r0.toString();
        r5 = new com.tencent.connect.common.BaseApi$TempRequestListener;
        r5.<init>(r10);
        r0 = r8.mToken;
        r1 = com.tencent.open.utils.Global.getContext();
        r4 = "GET";
        r3 = r7;
        com.tencent.open.utils.HttpUtils.requestAsync(r0, r1, r2, r3, r4, r5);
        r0 = com.tencent.open.b.d.a();
        r1 = r8.mToken;
        r1 = r1.getOpenId();
        r2 = r8.mToken;
        r2 = r2.getAppId();
        r3 = "ANDROIDSDK.WPASTATE.XX";
        r4 = "15";
        r5 = "18";
        r6 = "0";
        r0.a(r1, r2, r3, r4, r5, r6);
        goto L_0x0059;
        */
    }

    public int startWPAConversation(Activity activity, String str, String str2) {
        return startWPAConversation(activity, CHAT_TYPE_WPA, str, str2);
    }

    public int startWPAConversation(Activity activity, String str, String str2, String str3) {
        if (str == null || (!str.equals(CHAT_TYPE_WPA) && !str.equals(CHAT_TYPE_GROUP))) {
            return AppbarJsBridge.AUTHORIZE_FAIL;
        }
        String str4 = Constants.VIA_REPORT_TYPE_START_WAP;
        if (str.equals(CHAT_TYPE_GROUP)) {
            str4 = Constants.VIA_REPORT_TYPE_START_GROUP;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (TextUtils.isEmpty(str2)) {
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, str4, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
            return -1;
        } else if (str2.length() < 5) {
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, str4, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
            return AppbarJsBridge.Code_Java_Exception;
        } else {
            int i;
            int i2 = 0;
            while (i2 < str2.length()) {
                if (Character.isDigit(str2.charAt(i2))) {
                    i2++;
                } else {
                    d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, str4, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
                    return ErrCode.ERR_AUTH_DENIED;
                }
            }
            String str5 = a.d;
            if (!TextUtils.isEmpty(str3)) {
                try {
                    str5 = Base64.encodeToString(str3.getBytes(GameManager.DEFAULT_CHARSET), XZBDevice.DOWNLOAD_LIST_RECYCLE);
                } catch (UnsupportedEncodingException e) {
                }
            }
            intent.setData(Uri.parse(String.format("mqqwpa://im/chat?chat_type=%1$s&uin=%2$s&version=1&src_type=app&attach_content=%3$s", new Object[]{str, str2, str5})));
            PackageManager packageManager = Global.getContext().getPackageManager();
            if (packageManager.queryIntentActivities(intent, AccessibilityNodeInfoCompat.ACTION_CUT).size() > 0) {
                activity.startActivity(intent);
                i = 0;
            } else {
                intent.setData(Uri.parse("http://www.myapp.com/forward/a/45592?g_f=990935"));
                if (packageManager.queryIntentActivities(intent, AccessibilityNodeInfoCompat.ACTION_CUT).size() > 0) {
                    new TDialog(activity, a.d, getCommonDownloadQQUrl(a.d), null, this.mToken).show();
                    i = 0;
                } else {
                    d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, str4, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
                    i = -2;
                }
            }
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, str4, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_READY_REPORT);
            return i;
        }
    }
}
