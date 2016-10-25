package com.xunlei.common.member.c;

import android.os.Bundle;
import android.text.TextUtils;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.b;
import com.xunlei.common.member.a.d;
import com.xunlei.common.member.a.d.a;
import com.xunlei.common.member.a.m;
import com.xunlei.xiazaibao.BuildConfig;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.apache.http.client.HttpResponseException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserSessionidLoginTask.java
final class n$1 extends b {
    private /* synthetic */ n a;

    n$1(n nVar) {
        this.a = nVar;
    }

    public final void a(String str) {
        XLLog.v("UserSessionidLoginTask", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("errorCode");
            if (i == 0) {
                m.a().v();
                this.a.h().clearUserData();
                this.a.h().a(jSONObject);
                Object optString = jSONObject.optString("loginKey");
                if (!TextUtils.isEmpty(optString)) {
                    d.a(new d(this.a.h().getIntValue(USERINFOKEY.UserID), BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, optString), this.a.g().h(), a.c);
                }
                this.a.h().a(this.a.g().h());
                Bundle bundle = new Bundle();
                bundle.putString("action", "UserSessionidLoginTask");
                bundle.putInt("errorCode", i);
                bundle.putString("errorDesc", BuildConfig.VERSION_NAME);
                this.a.g().a(this.a, bundle);
                this.a.d(p.a.c);
                if (this.a.f() != p.a.d) {
                    this.a.g().a(true, 0);
                    return;
                }
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("action", "UserSessionidLoginTask");
            bundle2.putInt("errorCode", i);
            String str2 = (String) jSONObject.opt("errorDesc");
            try {
                str2 = new String(new String(str2.getBytes("utf-8"), "utf-8").getBytes("gbk"), "gbk");
            } catch (Exception e) {
                e.printStackTrace();
            }
            bundle2.putString("errorDesc", str2);
            this.a.g().a(this.a, bundle2);
            this.a.d(p.a.c);
        } catch (JSONException e2) {
            bundle = new Bundle();
            bundle.putString("action", "UserSessionidLoginTask");
            bundle.putInt("errorCode", 16777214);
            bundle.putString("errorDesc", "\u89e3\u5305\u5f02\u5e38");
            this.a.g().a(this.a, bundle);
        }
    }

    public final void a(Throwable th) {
        int statusCode;
        Object obj = 16781312;
        XLLog.e("UserSessionidLoginTask", new StringBuilder("error = ").append(th.getMessage()).toString());
        if (th instanceof UnknownHostException) {
            obj = 16781311;
        }
        if (th instanceof SocketException) {
            obj = 16781310;
        }
        if (th instanceof SocketTimeoutException) {
            obj = 16781309;
        }
        if (th instanceof HttpResponseException) {
            statusCode = ((HttpResponseException) th).getStatusCode();
        }
        Bundle bundle = new Bundle();
        bundle.putString("action", "UserSessionidLoginTask");
        bundle.putInt("errorCode", statusCode);
        bundle.putString("errorDesc", th.getMessage());
        this.a.g().a(this.a, bundle);
    }
}
