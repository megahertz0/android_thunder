package com.xunlei.common.member.c;

import android.os.Bundle;
import anet.channel.strategy.dispatch.a;
import com.taobao.accs.common.Constants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.d;
import com.xunlei.common.member.a.m;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserLogoutTask.java
public final class h extends p {
    private boolean a;

    public h(m mVar) {
        super(mVar);
        this.a = true;
        this.a = true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "userLogoutTask") {
            return false;
        }
        boolean onUserLogout = xLOnUserListener.onUserLogout(bundle.getInt(Constants.KEY_ERROR_CODE), h(), i(), j());
        if (!this.a) {
            return onUserLogout;
        }
        h().clearUserData();
        this.a = false;
        return onUserLogout;
    }

    public final boolean b() {
        if (g().q()) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("protocolVersion", R.styleable.AppCompatTheme_spinnerStyle);
                jSONObject.put("sequenceNo", j());
                jSONObject.put(a.PLATFORM_VERSION, 1);
                jSONObject.put("peerID", k());
                jSONObject.put("businessType", g().d());
                jSONObject.put("clientVersion", g().e());
                jSONObject.put("isCompressed", 0);
                jSONObject.put("cmdID", XZBDevice.Pause);
                jSONObject.put("userID", h().getLongValue(USERINFOKEY.UserID));
                jSONObject.put("sessionID", h().getStringValue(USERINFOKEY.SessionID));
                g().j().a(jSONObject.toString().getBytes(), (int) XZBDevice.DOWNLOAD_LIST_FAILED, null, j());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            g().a(false, 0);
            d.a(g().h(), d.a.c);
            Bundle bundle = new Bundle();
            bundle.putString(JsInterface.KEY_ACTION, "userLogoutTask");
            bundle.putInt(Constants.KEY_ERROR_CODE, 0);
            g().a((p) this, bundle);
            XLLog.v("UserLogoutTask", "user logout!");
            return true;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString(JsInterface.KEY_ACTION, "userLogoutTask");
        bundle2.putInt(Constants.KEY_ERROR_CODE, XLErrorCode.OPERATION_INVALID);
        g().a((p) this, bundle2);
        return false;
    }
}
