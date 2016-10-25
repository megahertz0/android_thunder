package com.xunlei.common.member.a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.a;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.encrypt.Base64;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

// compiled from: XLUserInfoImpl.java
public final class l implements XLUserInfo, Serializable {
    private static final long a = 6388415557093841477L;
    private static final String b = "xl-acc-user";
    private Map<USERINFOKEY, Object> c;

    public l() {
        this.c = new HashMap();
    }

    public final void a(JSONObject jSONObject) {
        a(USERINFOKEY.UserID, Integer.valueOf(jSONObject.optInt("userID")));
        a(USERINFOKEY.SessionID, jSONObject.opt("sessionID"));
        a(USERINFOKEY.JumpKey, jSONObject.opt("jumpKey"));
        a(USERINFOKEY.UserName, jSONObject.opt("userName"));
        a(USERINFOKEY.UserNewNo, Integer.valueOf(jSONObject.optInt("userNewNo")));
        a(USERINFOKEY.NickName, jSONObject.opt("nickName"));
        a(USERINFOKEY.Account, Integer.valueOf(jSONObject.optInt("account")));
        a(USERINFOKEY.ImgURL, jSONObject.opt("imgURL"));
        a(USERINFOKEY.IsSubAccount, Integer.valueOf(jSONObject.optInt("isSubAccount")));
        a(USERINFOKEY.IsRemind, Integer.valueOf(jSONObject.optInt("isRemind")));
        a(USERINFOKEY.IsAutoDeduct, Integer.valueOf(jSONObject.optInt("isAutoDeduct")));
        a(USERINFOKEY.IsSpecialNum, Integer.valueOf(jSONObject.optInt("isSpecialNum")));
        a(USERINFOKEY.Role, Integer.valueOf(jSONObject.optInt("role")));
        a(USERINFOKEY.Rank, Integer.valueOf(jSONObject.optInt("rank")));
        a(USERINFOKEY.Order, Integer.valueOf(jSONObject.optInt("order")));
        a(USERINFOKEY.TodayScore, Integer.valueOf(jSONObject.optInt("todayScore")));
        a(USERINFOKEY.AllowScore, Integer.valueOf(jSONObject.optInt("allowScore")));
        a(USERINFOKEY.VipDayGrow, Integer.valueOf(jSONObject.optInt("vipDayGrow")));
        a(USERINFOKEY.VipGrow, Integer.valueOf(jSONObject.optInt("vipGrow")));
        a(USERINFOKEY.IsYear, Integer.valueOf(jSONObject.optInt("isYear")));
        a(USERINFOKEY.Rigster, jSONObject.opt(MiPushClient.COMMAND_REGISTER));
        a(USERINFOKEY.Sex, jSONObject.opt("sex"));
        a(USERINFOKEY.PhoneNumber, jSONObject.opt("mobile"));
        a(USERINFOKEY.Country, jSONObject.opt("country"));
        a(USERINFOKEY.Province, jSONObject.opt("province"));
        a(USERINFOKEY.City, jSONObject.opt("city"));
        a(USERINFOKEY.PersonalSign, jSONObject.opt("personalSign"));
        a(USERINFOKEY.vip_level, jSONObject.opt("vipLevel"));
        a(USERINFOKEY.PasswordCheckNum, jSONObject.opt("passwordCheckNum"));
        a(USERINFOKEY.EncryptedPassword, jSONObject.opt("encryptedPassword"));
        int optInt = jSONObject.optInt("payId");
        int optInt2 = jSONObject.optInt("isVip");
        a(USERINFOKEY.IsVip, Integer.valueOf(optInt2));
        a(USERINFOKEY.VasType, Integer.valueOf(jSONObject.optInt("vasType")));
        a(USERINFOKEY.ExpireDate, jSONObject.opt("expireDate"));
        a(USERINFOKEY.PayId, Integer.valueOf(optInt));
        if (optInt2 != 1 || optInt <= 1000) {
            a(USERINFOKEY.isExpVip, Integer.valueOf(0));
        } else {
            a(USERINFOKEY.isExpVip, Integer.valueOf(1));
        }
        a(USERINFOKEY.PayName, jSONObject.opt("payName"));
        a(USERINFOKEY.VasId, Integer.valueOf(jSONObject.optInt("vasId")));
        CharSequence optString = jSONObject.optString("innerErrDes");
        a(USERINFOKEY.OtherVipError, optString);
        if (TextUtils.isEmpty(optString)) {
            a(USERINFOKEY.other_IsVip, Integer.valueOf(jSONObject.optInt("other_isVip")));
            a(USERINFOKEY.other_VipLevel, Integer.valueOf(jSONObject.optInt("other_vipLevel")));
            a(USERINFOKEY.other_ExpireDate, jSONObject.opt("other_expireDate"));
            a(USERINFOKEY.other_VasType, Integer.valueOf(jSONObject.optInt("other_vasType")));
            a(USERINFOKEY.other_PayName, jSONObject.opt("other_payName"));
            a(USERINFOKEY.other_VipGrow, Integer.valueOf(jSONObject.optInt("other_vipGrow")));
            a(USERINFOKEY.other_VipDayGrow, Integer.valueOf(jSONObject.optInt("other_vipDayGrow")));
            a(USERINFOKEY.other_IsAutoDeduct, Integer.valueOf(jSONObject.optInt("other_isAutoDeduct")));
            a(USERINFOKEY.other_IsRemind, Integer.valueOf(jSONObject.optInt("other_isRemind")));
            a(USERINFOKEY.other_PayId, Integer.valueOf(jSONObject.optInt("other_payId")));
            a(USERINFOKEY.other_IsYear, Integer.valueOf(jSONObject.optInt("other_isYear")));
            a(USERINFOKEY.other_Register, Integer.valueOf(jSONObject.optInt("other_register")));
        }
    }

    public final synchronized void a() {
        for (Entry entry : this.c.entrySet()) {
            Object obj;
            USERINFOKEY userinfokey = (USERINFOKEY) entry.getKey();
            if (userinfokey == USERINFOKEY.EncryptedPassword || userinfokey == USERINFOKEY.PasswordCheckNum || userinfokey == USERINFOKEY.SessionID || userinfokey == USERINFOKEY.JumpKey || userinfokey == USERINFOKEY.UserID || userinfokey == USERINFOKEY.UserName || userinfokey == USERINFOKEY.UserNewNo) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj == null) {
                this.c.put(entry.getKey(), null);
            }
        }
    }

    public final int getIntValue(USERINFOKEY userinfokey) {
        Object obj = this.c.get(userinfokey);
        return obj == null ? 0 : Integer.parseInt(obj.toString());
    }

    public final String getStringValue(USERINFOKEY userinfokey) {
        Object obj = this.c.get(userinfokey);
        return obj == null ? a.d : obj.toString();
    }

    public final long getLongValue(USERINFOKEY userinfokey) {
        Object obj = this.c.get(userinfokey);
        return obj == null ? 0 : Long.parseLong(obj.toString());
    }

    public final void a(USERINFOKEY userinfokey, Object obj) {
        if (obj != null) {
            Object obj2;
            Object transformGBKString = a(userinfokey) ? XLUtilTools.transformGBKString((String) obj) : obj;
            if (userinfokey == USERINFOKEY.PersonalSign || userinfokey == USERINFOKEY.Country) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj2 != null) {
                transformGBKString = XLUtilTools.transformUTF8String((String) transformGBKString);
            }
            this.c.put(userinfokey, transformGBKString);
        }
    }

    private static boolean a(USERINFOKEY userinfokey) {
        return userinfokey == USERINFOKEY.NickName || userinfokey == USERINFOKEY.PayName || userinfokey == USERINFOKEY.Province || userinfokey == USERINFOKEY.City || userinfokey == USERINFOKEY.other_PayName;
    }

    private static boolean b(USERINFOKEY userinfokey) {
        return userinfokey == USERINFOKEY.PersonalSign || userinfokey == USERINFOKEY.Country;
    }

    private static boolean c(USERINFOKEY userinfokey) {
        return userinfokey == USERINFOKEY.EncryptedPassword || userinfokey == USERINFOKEY.PasswordCheckNum || userinfokey == USERINFOKEY.SessionID || userinfokey == USERINFOKEY.JumpKey;
    }

    private static boolean d(USERINFOKEY userinfokey) {
        return userinfokey == USERINFOKEY.EncryptedPassword || userinfokey == USERINFOKEY.PasswordCheckNum || userinfokey == USERINFOKEY.SessionID || userinfokey == USERINFOKEY.JumpKey || userinfokey == USERINFOKEY.UserID || userinfokey == USERINFOKEY.UserName || userinfokey == USERINFOKEY.UserNewNo;
    }

    private final void b(USERINFOKEY userinfokey, Object obj) {
        if (obj != null) {
            this.c.put(userinfokey, obj);
        }
    }

    private boolean b() {
        return (TextUtils.isEmpty(getStringValue(USERINFOKEY.SessionID)) || getLongValue(USERINFOKEY.UserID) == 0) ? false : true;
    }

    public final void a(l lVar) {
        if (hashCode() != lVar.hashCode()) {
            this.c.clear();
            this.c.putAll(lVar.c);
        }
    }

    public final void clearUserData() {
        this.c.clear();
        c(m.a().h());
    }

    public final void dump() {
        XLLog.v("XLUserInfo", "--------------------dump user info start----------------------");
        XLLog.v("XLUserInfo", new StringBuilder("user info id = ").append(this.c.hashCode()).append(" #object id = ").append(hashCode()).toString());
        XLLog.v("XLUserInfo", new StringBuilder("user info size = ").append(this.c.size()).toString());
        for (Entry entry : this.c.entrySet()) {
            XLLog.v("XLUserInfo", new StringBuilder("key = ").append(entry.getKey()).toString());
            Object value = entry.getValue();
            String str = "null";
            if (value != null) {
                str = value.toString();
            }
            XLLog.v("XLUserInfo", new StringBuilder("value = ").append(str).toString());
        }
        XLLog.v("XLUserInfo", "--------------------dump user info end----------------------");
    }

    public final void a(Context context) {
        int i;
        if (TextUtils.isEmpty(getStringValue(USERINFOKEY.SessionID)) || getLongValue(USERINFOKEY.UserID) == 0) {
            i = 0;
        } else {
            i = 1;
        }
        if (i != 0) {
            c(context);
            d a = d.a(context);
            if (a == null) {
                XLLog.v("XLUserInfo", "save userinfo XLAutoLoginParcel null");
                return;
            }
            if (a.a == 0 || (TextUtils.isEmpty(a.b) && TextUtils.isEmpty(a.d))) {
                i = 0;
            } else {
                i = 1;
            }
            if (i == 0) {
                XLLog.v("XLUserInfo", "save userinfo isAutoLoginAble false");
                return;
            }
            Editor edit = context.getSharedPreferences(b, 0).edit();
            for (Entry entry : this.c.entrySet()) {
                int i2;
                USERINFOKEY userinfokey = (USERINFOKEY) entry.getKey();
                if (userinfokey == USERINFOKEY.EncryptedPassword || userinfokey == USERINFOKEY.PasswordCheckNum || userinfokey == USERINFOKEY.SessionID || userinfokey == USERINFOKEY.JumpKey) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (i2 == 0) {
                    Object value = entry.getValue();
                    if (value != null) {
                        String encode;
                        String toString = value.toString();
                        if (a((USERINFOKEY) entry.getKey())) {
                            encode = Base64.encode(toString.getBytes());
                        } else {
                            encode = toString;
                        }
                        edit.putString(((USERINFOKEY) entry.getKey()).toString(), encode);
                    }
                }
            }
            edit.commit();
            XLLog.v("XLUserInfo", "----------------end to save user info ----------------");
        }
    }

    public final void b(Context context) {
        Map all = context.getSharedPreferences(b, 0).getAll();
        if (all != null) {
            for (Entry entry : all.entrySet()) {
                Object str;
                USERINFOKEY userinfokey = (USERINFOKEY) Enum.valueOf(USERINFOKEY.class, (String) entry.getKey());
                String str2 = (String) entry.getValue();
                if (a(userinfokey)) {
                    str = new String(Base64.decode(str2));
                }
                if (str != null) {
                    this.c.put(userinfokey, str);
                }
            }
        }
    }

    private static void c(Context context) {
        Editor edit = context.getSharedPreferences(b, 0).edit();
        edit.clear();
        edit.commit();
    }
}
