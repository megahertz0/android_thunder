package com.xunlei.common.member.c.d;

import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.umeng.a;
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

// compiled from: UserSetUserInfoTask.java
public class b extends p {
    private String a;
    private String b;
    private JSONObject c;
    private String d;
    private String e;

    static /* synthetic */ int a(b bVar, int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 201) {
            return XZBDevice.Upload;
        }
        if (i == 202) {
            return 1;
        }
        if (i == 203) {
            return XLErrorCode.UNKNOWN_ERROR;
        }
        if (i == 204) {
            return R.styleable.Toolbar_contentInsetEnd;
        }
        if (i == 205) {
            return XLErrorCode.NICK_NAME_SW;
        }
        return i == 206 ? XLErrorCode.PERSONAL_SIGN_SW : XLErrorCode.UNKNOWN_ERROR;
    }

    public b(m mVar) {
        super(mVar);
        this.a = b.class.getSimpleName();
        this.b = "https://interface-account-ssl.xunlei.com:443/service/user_info?request=set";
        this.c = null;
        this.d = a.d;
        this.e = a.d;
    }

    public final boolean b() {
        if (!g().q()) {
            b(XLErrorCode.OPERATION_INVALID);
        } else if (this.c == null) {
            b(XLErrorCode.PACKAGE_ERROR);
        } else {
            try {
                this.c.put("userid", h().getIntValue(USERINFOKEY.UserID));
                this.c.put("sessionid", h().getStringValue(USERINFOKEY.SessionID));
                this.c.put("businesstype", g().d());
                this.c.put("deviceid", v.b());
                this.c.put("protocolversion", R.styleable.AppCompatTheme_buttonStyle);
                this.c.put(Constants.PARAM_PLATFORM, anet.channel.strategy.dispatch.a.ANDROID);
                if (!(TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.e))) {
                    this.c.put("verifykey", this.d);
                    this.c.put("verifycode", this.e);
                }
                String toString = this.c.toString();
                XLLog.v(this.a, new StringBuilder("xl user set info buffer = ").append(toString).toString());
                g().k().post(g().h(), this.b, new Header[]{new BasicHeader("Content-Type", "application/x-www-form-urlencoded")}, toString.getBytes(), new AnonymousClass_1(this));
            } catch (JSONException e) {
                e.printStackTrace();
                b(1);
            }
        }
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        return (xLOnUserListener == null || bundle == null) ? false : xLOnUserListener.onUserSetInfo(bundle.getInt(com.taobao.accs.common.Constants.KEY_ERROR_CODE), i(), bundle.getString("errorDesc"), j());
    }

    public final void a(JSONObject jSONObject, String str, String str2) {
        this.c = jSONObject;
        this.d = str;
        this.e = str2;
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
        if (i == 203) {
            return XLErrorCode.UNKNOWN_ERROR;
        }
        if (i == 204) {
            return R.styleable.Toolbar_contentInsetEnd;
        }
        if (i == 205) {
            return XLErrorCode.NICK_NAME_SW;
        }
        return i == 206 ? XLErrorCode.PERSONAL_SIGN_SW : XLErrorCode.UNKNOWN_ERROR;
    }
}
