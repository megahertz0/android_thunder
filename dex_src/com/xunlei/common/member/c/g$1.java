package com.xunlei.common.member.c;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.MD5;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.b;
import com.xunlei.common.member.a.d;
import com.xunlei.common.member.a.d.a;
import com.xunlei.common.member.a.m;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.client.HttpResponseException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserLoginTask.java
final class g$1 extends b {
    private /* synthetic */ g a;

    g$1(g gVar) {
        this.a = gVar;
    }

    public final void a(String str) {
        XLLog.v("UserLoginTask", str);
        try {
            Bundle bundle;
            JSONObject jSONObject = new JSONObject(str);
            XLLog.v("UserLoginTask", new StringBuilder("result json objet = ").append(jSONObject.toString()).toString());
            int i = jSONObject.getInt("errorCode");
            if (i == 0) {
                m.a().v();
                this.a.h().clearUserData();
                XLLog.v("UserLoginTask", "start to obtain xl user info.");
                jSONObject.put("passwordCheckNum", this.a.g().l());
                jSONObject.put("encryptedPassword", this.a.c());
                this.a.h().a(jSONObject);
                this.a.h().dump();
                CharSequence optString = jSONObject.optString("loginKey");
                XLLog.v("UserLoginTask", "save auto login info.");
                d dVar = new d(this.a.h().getIntValue(USERINFOKEY.UserID), this.a.h().getStringValue(USERINFOKEY.EncryptedPassword), this.a.h().getStringValue(USERINFOKEY.PasswordCheckNum), optString);
                int i2 = a.c;
                if (TextUtils.isEmpty(optString)) {
                    i2 = a.a;
                }
                d.a(dVar, this.a.g().h(), i2);
                this.a.h().a(this.a.g().h());
                bundle = new Bundle();
                bundle.putString("action", "userLoginTask");
                bundle.putInt("errorCode", 0);
                bundle.putString("errorDesc", XLErrorCode.getErrorDesc(0));
                this.a.g().a(this.a, bundle);
                if (this.a.f() != p.a.d) {
                    this.a.g().a(true, 0);
                }
            } else if (i == 7) {
                bundle = new Bundle();
                bundle.putString("action", "userLoginTask");
                bundle.putInt("errorCode", i);
                bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
                this.a.g().a(this.a, bundle);
                return;
            } else if (i == 6) {
                optString = jSONObject.optString("verifyType");
                if (!TextUtils.isEmpty(optString)) {
                    this.a.g().c(optString);
                }
                XLLog.v("UserLoginTask", new StringBuilder("get verify code type = ").append(optString).toString());
                bundle = new Bundle();
                bundle.putString("action", "userLoginTask");
                bundle.putInt("errorCode", SimpleLog.LOG_LEVEL_FATAL);
                bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
                this.a.g().a(this.a, bundle);
                return;
            } else {
                if (i == 9) {
                    jSONObject = jSONObject.getJSONObject("rsaKey");
                    String optString2 = jSONObject.optString("e");
                    String optString3 = jSONObject.optString("n");
                    optString = jSONObject.optString("md5");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(optString2);
                    stringBuilder.append(optString3);
                    if (optString.compareTo(MD5.encrypt(stringBuilder.toString())) == 0) {
                        Editor edit = this.a.g().h().getSharedPreferences("pubKey", 0).edit();
                        edit.putString("Exponent", optString2);
                        edit.putString("Moudle", optString3);
                        edit.commit();
                        this.a.b();
                        return;
                    }
                }
                bundle = new Bundle();
                bundle.putString("action", "userLoginTask");
                bundle.putInt("errorCode", i);
                bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
                this.a.g().a(this.a, bundle);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            bundle = new Bundle();
            bundle.putString("action", "userLoginTask");
            bundle.putInt("errorCode", 16777214);
            bundle.putString("errorDesc", XLErrorCode.getErrorDesc(16777214));
            this.a.g().a(this.a, bundle);
        }
        this.a.d(p.a.c);
    }

    public final void a(Throwable th) {
        int statusCode;
        XLLog.e("UserLoginTask", new StringBuilder("error = ").append(th.getMessage()).toString());
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
        bundle.putString("action", "userLoginTask");
        bundle.putInt("errorCode", statusCode);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(statusCode));
        this.a.g().a(this.a, bundle);
        this.a.d(p.a.c);
    }
}
