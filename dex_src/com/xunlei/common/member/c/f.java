package com.xunlei.common.member.c;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.d;
import com.xunlei.common.member.a.m;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserKeyLoginTask.java
public final class f extends p {
    private String a;
    private String b;
    private String c;

    public f(m mVar) {
        super(mVar);
        this.a = a.d;
        this.b = a.d;
        this.c = a.d;
    }

    public final void a() {
        super.a();
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "userKeyLoginTask") {
            return false;
        }
        return xLOnUserListener.onUserLogin(bundle.getInt(Constants.KEY_ERROR_CODE), h(), i(), bundle.getString("errorDesc"), j());
    }

    public final void a(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final boolean b() {
        boolean z = false;
        if (a.d == f()) {
            return false;
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
            jSONObject.put("cmdID", R.styleable.AppCompatTheme_actionDropDownStyle);
            jSONObject.put("loginType", 1);
            jSONObject.put(SocialConstants.PARAM_APPNAME, new StringBuilder("ANDROID-").append(g().m()).toString());
            jSONObject.put("devicesign", v.b());
            jSONObject.put(Constants.KEY_SDK_VERSION, g().f());
            jSONObject.put("userName", this.a);
            jSONObject.put("passWord", this.b);
            jSONObject.put("loginKey", this.c);
            jSONObject.put("deviceModel", Build.MODEL);
            jSONObject.put("deviceName", d.d());
            jSONObject.put("OSVersion", VERSION.RELEASE);
            String toString = jSONObject.toString();
            XLLog.v("UserKeyLoginTask", toString);
            try {
                g().j().a(toString.getBytes("GBK"), 1, new AnonymousClass_1(this), j());
                z = true;
                return z;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                b(XLErrorCode.PACKAGE_ERROR);
                d(a.c);
                return z;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            b(XLErrorCode.PACKAGE_ERROR);
            d(a.c);
            return false;
        }
    }

    private void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putString(JsInterface.KEY_ACTION, "userKeyLoginTask");
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }
}
