package com.xunlei.common.member.c;

import android.os.Bundle;
import android.text.TextUtils;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.b;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserGetUserInfoTask.java
final class e$1 extends b {
    private /* synthetic */ e a;

    e$1(e eVar) {
        this.a = eVar;
    }

    public final void a(String str) {
        XLLog.v("UserGetUserInfoTask", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("errorCode");
            if (i == 0) {
                this.a.h().a();
                for (USERINFOKEY userinfokey : e.a(this.a)) {
                    Object obj = e.b(this.a).get(userinfokey);
                    if (obj != null && jSONObject.has((String) obj)) {
                        e.c(this.a).add(userinfokey);
                        this.a.h().a(userinfokey, jSONObject.opt((String) obj));
                    }
                }
                this.a.h().a(USERINFOKEY.VasId, Integer.valueOf(e.d(this.a)));
                e.c(this.a).add(USERINFOKEY.VasId);
                CharSequence optString = jSONObject.optString("innerErrDes");
                this.a.h().a(USERINFOKEY.OtherVipError, optString);
                e.c(this.a).add(USERINFOKEY.OtherVipError);
                if (TextUtils.isEmpty(optString)) {
                    this.a.h().a(USERINFOKEY.other_IsVip, Integer.valueOf(jSONObject.optInt("other_isVip")));
                    e.c(this.a).add(USERINFOKEY.other_IsVip);
                    this.a.h().a(USERINFOKEY.other_VipLevel, Integer.valueOf(jSONObject.optInt("other_vipLevel")));
                    e.c(this.a).add(USERINFOKEY.other_VipLevel);
                    this.a.h().a(USERINFOKEY.other_ExpireDate, jSONObject.optString("other_expireDate"));
                    e.c(this.a).add(USERINFOKEY.other_ExpireDate);
                    this.a.h().a(USERINFOKEY.other_VasType, Integer.valueOf(jSONObject.optInt("other_vasType")));
                    e.c(this.a).add(USERINFOKEY.other_VasType);
                    this.a.h().a(USERINFOKEY.other_PayName, jSONObject.optString("other_payName"));
                    e.c(this.a).add(USERINFOKEY.other_PayName);
                    this.a.h().a(USERINFOKEY.other_VipGrow, Integer.valueOf(jSONObject.optInt("other_vipGrow")));
                    e.c(this.a).add(USERINFOKEY.other_VipGrow);
                    this.a.h().a(USERINFOKEY.other_VipDayGrow, Integer.valueOf(jSONObject.optInt("other_vipDayGrow")));
                    e.c(this.a).add(USERINFOKEY.other_VipDayGrow);
                    this.a.h().a(USERINFOKEY.other_IsAutoDeduct, Integer.valueOf(jSONObject.optInt("other_isAutoDeduct")));
                    e.c(this.a).add(USERINFOKEY.other_IsAutoDeduct);
                    this.a.h().a(USERINFOKEY.other_IsRemind, Integer.valueOf(jSONObject.optInt("other_isRemind")));
                    e.c(this.a).add(USERINFOKEY.other_IsRemind);
                    this.a.h().a(USERINFOKEY.other_PayId, Integer.valueOf(jSONObject.optInt("other_payId")));
                    e.c(this.a).add(USERINFOKEY.other_PayId);
                    this.a.h().a(USERINFOKEY.other_IsYear, Integer.valueOf(jSONObject.optInt("other_isYear")));
                    e.c(this.a).add(USERINFOKEY.other_IsYear);
                    this.a.h().a(USERINFOKEY.other_Register, jSONObject.optString("other_register"));
                    e.c(this.a).add(USERINFOKEY.other_Register);
                }
                this.a.h().a(this.a.g().h());
                Bundle bundle = new Bundle();
                bundle.putInt("errorCode", 0);
                bundle.putString("action", "userGetDetailTask");
                this.a.g().a(this.a, bundle);
                return;
            }
            e.c(this.a).clear();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("errorCode", i);
            bundle2.putString("action", "userGetDetailTask");
            this.a.g().a(this.a, bundle2);
            if (i == 1) {
                this.a.g().r();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            e.c(this.a).clear();
            bundle = new Bundle();
            bundle.putInt("errorCode", 16777214);
            bundle.putString("action", "userGetDetailTask");
            this.a.g().a(this.a, bundle);
        }
    }

    public final void a(Throwable th) {
        XLLog.e("UserGetUserInfoTask", new StringBuilder("error = ").append(th.getMessage()).toString());
        e.c(this.a).clear();
        Bundle bundle = new Bundle();
        bundle.putString("action", "userGetDetailTask");
        bundle.putInt("errorCode", 16781312);
        this.a.g().a(this.a, bundle);
    }
}
