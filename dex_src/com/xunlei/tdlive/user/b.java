package com.xunlei.tdlive.user;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.umeng.common.inter.ITagManager;
import com.umeng.message.ALIAS_TYPE;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.tdlive.b.a;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.TnetStatusCode;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: GlobalXLOnUserListener.java
public class b extends DefaultXLOnUserListener {
    private Context a;
    private String b;
    private int c;

    public b(Context context) {
        this.c = -100;
        this.a = context;
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
            f.a().a(false, i, stringValue, currentUser.getStringValue(USERINFOKEY.Sex));
        }
        return super.onUserSetInfo(i, obj, str, i2);
    }

    public boolean onUserSetAvatar(int i, Object obj, String str, int i2) {
        f.a().a(true, i, null, null);
        return super.onUserSetAvatar(i, obj, str, i2);
    }

    public boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4) {
        super.onUserThirdLogin(i, xLUserInfo, i2, i3, obj, str, i4);
        a(i, i2, xLUserInfo);
        if (i2 == 3) {
            f.a().b(false);
        }
        if (i == 0) {
            this.c = i2;
            a();
        }
        return true;
    }

    public boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        super.onUserLogin(i, xLUserInfo, obj, str, i2);
        a(i, 0, xLUserInfo);
        if (i == 0) {
            this.c = 0;
            a();
        }
        return true;
    }

    public boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2) {
        onUserLogin(i, xLUserInfo, obj, str, i2);
        return true;
    }

    public boolean onUserTokenLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        onUserLogin(i, xLUserInfo, obj, str, i2);
        return true;
    }

    public boolean onUserQRCodeLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
        onUserLogin(i, xLUserInfo, obj, str, i2);
        return true;
    }

    public boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2) {
        super.onUserLogout(i, xLUserInfo, obj, i2);
        if (this.c == -100) {
            XLog.e("GlobalXLOnUserListener", "onUserLogout is called twice");
        } else {
            a(this.a);
            a.a(this.a).b(this.b);
            a(i, xLUserInfo);
            this.c = -100;
            f.b = -100;
            this.b = null;
            f.a().a(false, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME);
        }
        return true;
    }

    private void a(Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancelAll();
    }

    private void a() {
        XLUserInfo currentUser = XLUserUtil.getInstance().getCurrentUser();
        Object stringValue = currentUser.getStringValue(USERINFOKEY.UserID);
        String stringValue2 = currentUser.getStringValue(USERINFOKEY.SessionID);
        String stringValue3 = currentUser.getStringValue(USERINFOKEY.NickName);
        if (stringValue3 == null || stringValue3.length() <= 0) {
            stringValue3 = currentUser.getStringValue(USERINFOKEY.UserName);
        }
        if (stringValue3 == null || stringValue3.length() <= 0) {
            stringValue3 = currentUser.getStringValue(USERINFOKEY.UserNewNo);
        }
        if (TextUtils.isEmpty(stringValue)) {
            new Handler().post(new c(this));
        } else {
            a(stringValue, stringValue2, stringValue3, currentUser.getStringValue(USERINFOKEY.Sex));
        }
    }

    private void a(String str, String str2, String str3, String str4) {
        XLog.d("GlobalXLOnUserListener", new StringBuilder("onLogin uid = ").append(str).append(", sessionId = ").append(str2).toString());
        if (!TextUtils.isEmpty(str)) {
            XLUserUtil.getInstance().userGetInfo(null, null, "get_user_info");
            q.a(str);
            f.a().g();
            a.a(this.a).a(str);
            com.xunlei.tdlive.a.a(this.b, f.a().l());
            f.a().a(true, str, str2, str3, str4);
        }
    }

    private void a(int i, int i2, XLUserInfo xLUserInfo) {
        String str = null;
        switch (f.b) {
            case TnetStatusCode.EASY_REQ_STAGE_NOT_SEND:
                str = "auto";
                break;
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                str = SocializeProtocolConstants.PROTOCOL_NORMAL_SHARE;
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
            case MqttConnectOptions.MQTT_VERSION_3_1:
            case SimpleLog.LOG_LEVEL_ERROR:
                str = "third";
                break;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(AgooConstants.MESSAGE_TYPE, a(i2));
        hashMap.put("network", String.valueOf(ac.b()));
        hashMap.put("get_duration", String.valueOf(SystemClock.elapsedRealtime() - f.a));
        hashMap.put("errorcode", String.valueOf(i));
        if (i == 0) {
            hashMap.put("userid", xLUserInfo.getStringValue(USERINFOKEY.UserID));
            hashMap.put("level", xLUserInfo.getStringValue(USERINFOKEY.Rank));
        } else {
            hashMap.put("userid", BuildConfig.VERSION_NAME);
            hashMap.put("level", BuildConfig.VERSION_NAME);
        }
        q.a("login_return_result", str, i == 0 ? "success" : ITagManager.FAIL, hashMap);
        f.a = 0;
        f.b = -100;
    }

    private void a(int i, XLUserInfo xLUserInfo) {
        String a = a(this.c);
        Map hashMap = new HashMap();
        hashMap.put("userid", xLUserInfo.getStringValue(USERINFOKEY.UserID));
        hashMap.put("level", xLUserInfo.getStringValue(USERINFOKEY.Rank));
        hashMap.put(AgooConstants.MESSAGE_TYPE, a);
        q.a("user_logout", a, i == 0 ? "success" : ITagManager.FAIL, hashMap);
    }

    private String a(int i) {
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return "thunder";
            case SimpleLog.LOG_LEVEL_DEBUG:
                return "weibo";
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return "weichat";
            case SimpleLog.LOG_LEVEL_ERROR:
                return ALIAS_TYPE.QQ;
            default:
                XLog.e("GlobalXLOnUserListener", new StringBuilder("invalid account type: ").append(i).toString());
                return null;
        }
    }
}
