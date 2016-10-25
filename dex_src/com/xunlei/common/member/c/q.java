package com.xunlei.common.member.c;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.d;
import com.xunlei.common.member.a.m;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserTokenLoginTask.java
public final class q extends p {
    private static final int b = 43;
    private boolean a;
    private String c;
    private String d;
    private String e;
    private String f;

    public final void a() {
        super.a();
    }

    public q(m mVar) {
        super(mVar);
        this.c = a.d;
        this.d = a.d;
        this.e = a.d;
        this.f = a.d;
    }

    public final void a(boolean z) {
    }

    public final void a(String str, String str2, String str3, String str4) {
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
    }

    public final boolean b() {
        boolean z = false;
        if (a.d == f()) {
            return false;
        }
        if (TextUtils.isEmpty(this.e)) {
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.KEY_ERROR_CODE, XZBDevice.Stop);
            bundle.putString("errorDesc", "\u7b2c\u4e09\u65b9token\u4e0d\u5408\u6cd5");
            g().a((p) this, bundle);
            d(a.c);
            return false;
        }
        d(a.b);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("protocolVersion", R.styleable.AppCompatTheme_spinnerStyle);
            jSONObject.put("sequenceNo", j());
            jSONObject.put(anet.channel.strategy.dispatch.a.PLATFORM_VERSION, 1);
            jSONObject.put("isCompressed", 0);
            jSONObject.put("cmdID", b);
            jSONObject.put("businessType", g().d());
            jSONObject.put("clientVersion", g().e());
            jSONObject.put(SocialConstants.PARAM_APPNAME, new StringBuilder("ANDROID-").append(g().m()).toString());
            jSONObject.put("devicesign", v.b());
            jSONObject.put(Constants.KEY_SDK_VERSION, g().f());
            jSONObject.put("thirdType", this.c);
            jSONObject.put("thirdID", this.d);
            jSONObject.put("thirdToken", this.e);
            jSONObject.put("thirdSig", this.f);
            jSONObject.put("deviceModel", Build.MODEL);
            jSONObject.put("deviceName", d.d());
            jSONObject.put("OSVersion", VERSION.RELEASE);
            String toString = jSONObject.toString();
            XLLog.v("UserTokenLoginTask", new StringBuilder("request package = ").append(toString).toString());
            g().j().a(toString.getBytes(), (int) XZBDevice.Success, new AnonymousClass_1(this), j());
            z = true;
            return z;
        } catch (JSONException e) {
            e.printStackTrace();
            bundle = new Bundle();
            bundle.putInt(Constants.KEY_ERROR_CODE, XLErrorCode.PACKAGE_ERROR);
            bundle.putString("errorDesc", "\u7ec4\u5305\u8fc7\u7a0b\u4e2d\u51fa\u73b0\u5f02\u5e38");
            g().a((p) this, bundle);
            d(a.c);
            return z;
        }
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        int i = bundle.getInt(Constants.KEY_ERROR_CODE);
        if (e(i)) {
            d.a(g().h(), d.a.c);
        }
        if (f(i)) {
            h().clearUserData();
        }
        return xLOnUserListener.onUserTokenLogin(bundle.getInt(Constants.KEY_ERROR_CODE), h(), i(), bundle.getString("errorDesc"), j());
    }
}
