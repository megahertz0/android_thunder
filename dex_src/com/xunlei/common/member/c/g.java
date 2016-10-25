package com.xunlei.common.member.c;

import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.HextoChar;
import com.xunlei.common.encrypt.MD5;
import com.xunlei.common.encrypt.RsaEncode;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.d;
import com.xunlei.common.member.a.m;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserLoginTask.java
public final class g extends p {
    private static String a = "pubKey";
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;

    public g(m mVar) {
        super(mVar);
        this.b = a.d;
        this.c = a.d;
        this.d = a.d;
        this.e = a.d;
        this.f = a.d;
        this.g = 0;
    }

    public final void a() {
        super.a();
        this.b = a.d;
        this.c = a.d;
        this.e = a.d;
        this.f = a.d;
    }

    public final void a(String str, boolean z) {
        this.b = str;
        this.g = z ? 1 : 0;
        XLLog.v("UserLoginTask", new StringBuilder("put user name = ").append(str).append("#is auto login(1->auto) = ").append(this.g).toString());
    }

    public final void a(String str, String str2) {
        this.d = str2;
        XLLog.v("UserLoginTask", new StringBuilder("putPassword encrypt = ").append(str).toString());
        if (this.g == 0) {
            this.c = MD5.encrypt(str);
        } else {
            this.c = str;
        }
        h().a(USERINFOKEY.EncryptedPassword, this.c);
    }

    public final void b(String str, String str2) {
        this.e = str;
        this.f = str2;
        if (str != null) {
            XLLog.v("UserLoginTask", new StringBuilder("user verify code info: key = ").append(str).append(" #code = ").append(str2).toString());
        }
    }

    private String d() {
        return g().h().getSharedPreferences("pubKey", 0).getString("Moudle", "AC69F5CCC8BDE47CD3D371603748378C9CFAD2938A6B021E0E191013975AD683F5CBF9ADE8BD7D46B4D2EC2D78AF146F1DD2D50DC51446BB8880B8CE88D476694DFC60594393BEEFAA16F5DBCEBE22F89D640F5336E42F587DC4AFEDEFEAC36CF007009CCCE5C1ACB4FF06FBA69802A8085C2C54BADD0597FC83E6870F1E36FD");
    }

    private String o() {
        return g().h().getSharedPreferences("pubKey", 0).getString("Exponent", "010001");
    }

    public final void c(String str, String str2) {
        Editor edit = g().h().getSharedPreferences("pubKey", 0).edit();
        edit.putString("Exponent", str2);
        edit.putString("Moudle", str);
        edit.commit();
    }

    public final String c() {
        return this.c;
    }

    private boolean p() {
        return this.d.compareTo(g().l()) == 0;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "userLoginTask") {
            return false;
        }
        XLLog.v("UserLoginTask", "before call onUserLogin");
        int i = bundle.getInt(Constants.KEY_ERROR_CODE);
        if (e(i)) {
            d.a(g().h(), d.a.c);
        }
        if (f(i)) {
            h().clearUserData();
        }
        return xLOnUserListener.onUserLogin(i, h(), i(), bundle.getString("errorDesc"), j());
    }

    public final boolean b() {
        boolean z = false;
        if (a.d == f()) {
            return false;
        }
        Bundle bundle;
        if (TextUtils.isEmpty(this.b)) {
            bundle = new Bundle();
            bundle.putString(JsInterface.KEY_ACTION, "userLoginTask");
            bundle.putInt(Constants.KEY_ERROR_CODE, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            bundle.putString("errorDesc", XLErrorCode.getErrorDesc(XZBDevice.DOWNLOAD_LIST_RECYCLE));
            g().a((p) this, bundle);
            d(a.c);
            return false;
        } else if (this.c.compareTo(MD5.encrypt(a.d)) == 0) {
            bundle = new Bundle();
            bundle.putString(JsInterface.KEY_ACTION, "userLoginTask");
            bundle.putInt(Constants.KEY_ERROR_CODE, XZBDevice.DOWNLOAD_LIST_FAILED);
            bundle.putString("errorDesc", XLErrorCode.getErrorDesc(XZBDevice.DOWNLOAD_LIST_FAILED));
            g().a((p) this, bundle);
            d(a.c);
            return false;
        } else {
            if (!(this.d == null || TextUtils.isEmpty(this.d))) {
                int i;
                if (this.d.compareTo(g().l()) == 0) {
                    i = 1;
                } else {
                    boolean z2 = false;
                }
                if (i == 0) {
                    bundle = new Bundle();
                    bundle.putString(JsInterface.KEY_ACTION, "userLoginTask");
                    bundle.putInt(Constants.KEY_ERROR_CODE, XLErrorCode.DEVICE_INVALID);
                    bundle.putString("errorDesc", XLErrorCode.getErrorDesc(XLErrorCode.DEVICE_INVALID));
                    g().a((p) this, bundle);
                    d(a.c);
                    return false;
                }
            }
            d(a.b);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("protocolVersion", R.styleable.AppCompatTheme_spinnerStyle);
                jSONObject.put("sequenceNo", j());
                jSONObject.put(anet.channel.strategy.dispatch.a.PLATFORM_VERSION, 1);
                jSONObject.put("peerID", k());
                jSONObject.put("businessType", g().d());
                jSONObject.put("clientVersion", g().e());
                jSONObject.put("isCompressed", 0);
                jSONObject.put("cmdID", 1);
                jSONObject.put("userName", this.b);
                byte[] encodeUseRSA = RsaEncode.encodeUseRSA(this.c.getBytes(), d(), o());
                jSONObject.put("passWord", new String(HextoChar.bytes_to_hex(encodeUseRSA, encodeUseRSA.length)));
                jSONObject.put("loginType", this.g);
                jSONObject.put("sessionID", a.d);
                jSONObject.put("verifyKey", this.e);
                jSONObject.put("verifyCode", this.f);
                jSONObject.put(SocialConstants.PARAM_APPNAME, new StringBuilder("ANDROID-").append(g().m()).toString());
                jSONObject.put("devicesign", v.b());
                jSONObject.put(Constants.KEY_SDK_VERSION, g().f());
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("e", o());
                jSONObject2.put(IXAdRequestInfo.AD_COUNT, d());
                jSONObject.put("rsaKey", jSONObject2);
                jSONObject.put("extensionList", a.d);
                jSONObject.put("deviceModel", Build.MODEL);
                jSONObject.put("deviceName", d.d());
                jSONObject.put("OSVersion", VERSION.RELEASE);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("user login start:account = ").append(this.b).append("#befor RSA psw = ").append(this.c).append("#psw check sum").append(this.d).append("#login type = ").append(this.g);
                XLLog.v("UserLoginTask", stringBuffer.toString());
                String toString = jSONObject.toString();
                XLLog.v("UserLoginTask", toString);
                try {
                    g().j().a(toString.getBytes("GBK"), 1, new AnonymousClass_1(this), j());
                    z = true;
                    return z;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    bundle = new Bundle();
                    bundle.putString(JsInterface.KEY_ACTION, "userLoginTask");
                    bundle.putInt(Constants.KEY_ERROR_CODE, XLErrorCode.PACKAGE_ERROR);
                    bundle.putString("errorDesc", XLErrorCode.getErrorDesc(XLErrorCode.PACKAGE_ERROR));
                    g().a((p) this, bundle);
                    d(a.c);
                    return z;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                bundle = new Bundle();
                bundle.putString(JsInterface.KEY_ACTION, "userLoginTask");
                bundle.putInt(Constants.KEY_ERROR_CODE, XLErrorCode.PACKAGE_ERROR);
                bundle.putString("errorDesc", XLErrorCode.getErrorDesc(XLErrorCode.PACKAGE_ERROR));
                g().a((p) this, bundle);
                d(a.c);
                return false;
            }
        }
    }
}
