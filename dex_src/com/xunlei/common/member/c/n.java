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
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserSessionidLoginTask.java
public final class n extends p {
    private boolean a;
    private int b;
    private String c;
    private int d;
    private int e;

    public final void a() {
        super.a();
    }

    public n(m mVar) {
        super(mVar);
        this.b = 0;
        this.c = a.d;
        this.d = 0;
        this.e = 1;
    }

    public final void a(int i, String str, int i2, int i3) {
        this.b = i;
        this.c = str;
        this.d = i2;
        this.e = i3;
    }

    public final void a(boolean z) {
    }

    public final boolean b() {
        if (a.d == f()) {
            return false;
        }
        d(a.b);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("protocolVersion", R.styleable.AppCompatTheme_spinnerStyle);
            jSONObject.put("sequenceNo", j());
            jSONObject.put(anet.channel.strategy.dispatch.a.PLATFORM_VERSION, 1);
            jSONObject.put("isCompressed", 0);
            jSONObject.put("cmdID", R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu);
            jSONObject.put("userID", this.b);
            jSONObject.put("businessType", g().d());
            jSONObject.put("peerID", k());
            jSONObject.put("clientVersion", g().e());
            jSONObject.put("sessionID", this.c);
            jSONObject.put("sessionType", this.e);
            jSONObject.put("sessionFromBusinesstype", this.d);
            jSONObject.put(SocialConstants.PARAM_APPNAME, new StringBuilder("ANDROID-").append(g().m()).toString());
            jSONObject.put("devicesign", v.b());
            jSONObject.put(Constants.KEY_SDK_VERSION, g().f());
            jSONObject.put("deviceModel", Build.MODEL);
            jSONObject.put("deviceName", d.d());
            jSONObject.put("OSVersion", VERSION.RELEASE);
        } catch (JSONException e) {
            Bundle bundle = new Bundle();
            bundle.putString(JsInterface.KEY_ACTION, "UserSessionidLoginTask");
            bundle.putString("errorDesc", "\u7ec4\u5305\u5f02\u5e38");
            bundle.putInt(Constants.KEY_ERROR_CODE, XLErrorCode.PACKAGE_ERROR);
            g().a((p) this, bundle);
            d(a.c);
            e.printStackTrace();
        }
        String toString = jSONObject.toString();
        XLLog.v("UserSessionidLoginTask", new StringBuilder("request package = ").append(toString).toString());
        g().j().a(toString.getBytes(), (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, new AnonymousClass_1(this), j());
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "UserSessionidLoginTask") {
            return false;
        }
        int i = bundle.getInt(Constants.KEY_ERROR_CODE);
        if (e(i)) {
            d.a(g().h(), d.a.c);
        }
        if (f(i)) {
            h().clearUserData();
        }
        return xLOnUserListener.onUserSessionidLogin(bundle.getInt(Constants.KEY_ERROR_CODE), bundle.getString("errorDesc"), h(), i(), j());
    }
}
