package com.xunlei.common.member.c.d;

import android.os.Bundle;
import com.tencent.connect.common.Constants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.c.p;
import com.xunlei.common.member.c.v;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserGetCityInfoTask.java
public class a extends p {
    private String a;
    private String b;
    private JSONObject c;

    static /* synthetic */ int a(a aVar, int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 201) {
            return XZBDevice.Upload;
        }
        if (i == 202) {
            return 1;
        }
        return i == 203 ? XLErrorCode.UNKNOWN_ERROR : XLErrorCode.UNKNOWN_ERROR;
    }

    public a(m mVar) {
        super(mVar);
        this.a = a.class.getSimpleName();
        this.b = "https://interface-account-ssl.xunlei.com:443/service/user_info?request=city";
        this.c = null;
    }

    public final boolean b() {
        if (g().q()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("userid", h().getIntValue(USERINFOKEY.UserID));
                jSONObject.put("sessionid", h().getStringValue(USERINFOKEY.SessionID));
                jSONObject.put("businesstype", g().d());
                jSONObject.put("deviceid", v.b());
                jSONObject.put("protocolversion", R.styleable.AppCompatTheme_buttonStyle);
                jSONObject.put(Constants.PARAM_PLATFORM, anet.channel.strategy.dispatch.a.ANDROID);
                String toString = jSONObject.toString();
                XLLog.v(this.a, new StringBuilder("xl user get city info buffer = ").append(toString).toString());
                g().k().post(g().h(), this.b, new Header[]{new BasicHeader("Content-Type", "application/x-www-form-urlencoded")}, toString.getBytes(), new AnonymousClass_1(this));
            } catch (JSONException e) {
                e.printStackTrace();
                b(1);
            }
        } else {
            b(XLErrorCode.OPERATION_INVALID);
        }
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        return xLOnUserListener.onUserGetCityInfo(bundle.getInt(com.taobao.accs.common.Constants.KEY_ERROR_CODE), this.c, i(), bundle.getString("errorDesc"), j());
    }

    private void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(com.taobao.accs.common.Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private static int c(int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 201) {
            return XZBDevice.Upload;
        }
        if (i == 202) {
            return 1;
        }
        return i == 203 ? XLErrorCode.UNKNOWN_ERROR : XLErrorCode.UNKNOWN_ERROR;
    }
}
