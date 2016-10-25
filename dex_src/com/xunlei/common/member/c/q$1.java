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
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.client.HttpResponseException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserTokenLoginTask.java
final class q$1 extends b {
    private /* synthetic */ q a;

    q$1(q qVar) {
        this.a = qVar;
    }

    public final void a(String str) {
        XLLog.v("UserTokenLoginTask", str);
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
                bundle.putInt("errorCode", 0);
                bundle.putString("errorDesc", BuildConfig.VERSION_NAME);
                this.a.g().a(this.a, bundle);
                if (this.a.f() != p.a.d) {
                    this.a.g().a(true, 0);
                }
            } else if (i == 7) {
                bundle = new Bundle();
                bundle.putInt("errorCode", i);
                bundle.putString("errorDesc", jSONObject.optString("errorDesc"));
                this.a.g().a(this.a, bundle);
                return;
            } else if (i == 6) {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("errorCode", SimpleLog.LOG_LEVEL_FATAL);
                bundle2.putString("errorDesc", jSONObject.optString("errorDesc"));
                this.a.g().a(this.a, bundle2);
                return;
            } else {
                bundle = new Bundle();
                bundle.putInt("errorCode", i);
                bundle.putString("errorDesc", jSONObject.optString("errorDesc"));
                this.a.g().a(this.a, bundle);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            bundle = new Bundle();
            bundle.putInt("errorCode", 16777214);
            bundle.putString("errorDesc", "\u670d\u52a1\u5668\u8fd4\u56de\u6570\u636e\u89e3\u5305\u8fc7\u7a0b\u51fa\u73b0\u5f02\u5e38");
            this.a.g().a(this.a, bundle);
        }
        this.a.d(p.a.c);
    }

    public final void a(Throwable th) {
        int statusCode;
        XLLog.e("UserTokenLoginTask", new StringBuilder("error = ").append(th.getMessage()).toString());
        Object obj = 16781312;
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
        bundle.putInt("errorCode", statusCode);
        bundle.putString("errorDesc", "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u7a0d\u540e\u767b\u9646");
        this.a.g().a(this.a, bundle);
        this.a.d(p.a.c);
    }
}
