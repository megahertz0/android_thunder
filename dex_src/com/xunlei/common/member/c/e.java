package com.xunlei.common.member.c;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.a;
import com.xunlei.common.member.a.m;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserGetUserInfoTask.java
public final class e extends p {
    private int a;
    private List<USERINFOKEY> b;
    private List<USERINFOKEY> c;
    private Map<USERINFOKEY, String> d;

    public e(m mVar) {
        super(mVar);
        this.a = 2;
        this.b = new ArrayList();
        this.c = null;
        this.d = new HashMap();
    }

    private void c() {
        this.d.put(USERINFOKEY.NickName, "nickName");
        this.d.put(USERINFOKEY.Account, "account");
        this.d.put(USERINFOKEY.Rank, "rank");
        this.d.put(USERINFOKEY.Order, "order");
        this.d.put(USERINFOKEY.IsSubAccount, "isSubAccount");
        this.d.put(USERINFOKEY.Country, "country");
        this.d.put(USERINFOKEY.City, "city");
        this.d.put(USERINFOKEY.Province, "province");
        this.d.put(USERINFOKEY.IsSpecialNum, "isSpecialNum");
        this.d.put(USERINFOKEY.Role, "role");
        this.d.put(USERINFOKEY.TodayScore, "todayScore");
        this.d.put(USERINFOKEY.AllowScore, "allowScore");
        this.d.put(USERINFOKEY.PersonalSign, "personalSign");
        this.d.put(USERINFOKEY.IsVip, "isVip");
        this.d.put(USERINFOKEY.ExpireDate, "expireDate");
        this.d.put(USERINFOKEY.VasType, "vasType");
        this.d.put(USERINFOKEY.PayId, "payId");
        this.d.put(USERINFOKEY.PayName, "payName");
        this.d.put(USERINFOKEY.VipGrow, "vipGrow");
        this.d.put(USERINFOKEY.VipDayGrow, "vipDayGrow");
        this.d.put(USERINFOKEY.IsAutoDeduct, "isAutoDeduct");
        this.d.put(USERINFOKEY.IsRemind, "isRemind");
        this.d.put(USERINFOKEY.ImgURL, "imgURL");
        this.d.put(USERINFOKEY.vip_level, "vipLevel");
        this.d.put(USERINFOKEY.JumpKey, "jumpKey");
        this.d.put(USERINFOKEY.IsYear, "isYear");
        this.d.put(USERINFOKEY.Rigster, MiPushClient.COMMAND_REGISTER);
        this.d.put(USERINFOKEY.Sex, "sex");
        this.d.put(USERINFOKEY.Birthday, "birthday");
        this.d.put(USERINFOKEY.PhoneNumber, "mobile");
    }

    public final void a() {
        super.a();
        this.c = null;
        this.d.put(USERINFOKEY.NickName, "nickName");
        this.d.put(USERINFOKEY.Account, "account");
        this.d.put(USERINFOKEY.Rank, "rank");
        this.d.put(USERINFOKEY.Order, "order");
        this.d.put(USERINFOKEY.IsSubAccount, "isSubAccount");
        this.d.put(USERINFOKEY.Country, "country");
        this.d.put(USERINFOKEY.City, "city");
        this.d.put(USERINFOKEY.Province, "province");
        this.d.put(USERINFOKEY.IsSpecialNum, "isSpecialNum");
        this.d.put(USERINFOKEY.Role, "role");
        this.d.put(USERINFOKEY.TodayScore, "todayScore");
        this.d.put(USERINFOKEY.AllowScore, "allowScore");
        this.d.put(USERINFOKEY.PersonalSign, "personalSign");
        this.d.put(USERINFOKEY.IsVip, "isVip");
        this.d.put(USERINFOKEY.ExpireDate, "expireDate");
        this.d.put(USERINFOKEY.VasType, "vasType");
        this.d.put(USERINFOKEY.PayId, "payId");
        this.d.put(USERINFOKEY.PayName, "payName");
        this.d.put(USERINFOKEY.VipGrow, "vipGrow");
        this.d.put(USERINFOKEY.VipDayGrow, "vipDayGrow");
        this.d.put(USERINFOKEY.IsAutoDeduct, "isAutoDeduct");
        this.d.put(USERINFOKEY.IsRemind, "isRemind");
        this.d.put(USERINFOKEY.ImgURL, "imgURL");
        this.d.put(USERINFOKEY.vip_level, "vipLevel");
        this.d.put(USERINFOKEY.JumpKey, "jumpKey");
        this.d.put(USERINFOKEY.IsYear, "isYear");
        this.d.put(USERINFOKEY.Rigster, MiPushClient.COMMAND_REGISTER);
        this.d.put(USERINFOKEY.Sex, "sex");
        this.d.put(USERINFOKEY.Birthday, "birthday");
        this.d.put(USERINFOKEY.PhoneNumber, "mobile");
    }

    public final void b(int i) {
        this.a = i;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "userGetDetailTask") {
            return false;
        }
        return xLOnUserListener.onUserInfoCatched(bundle.getInt(Constants.KEY_ERROR_CODE), this.b, h(), i(), j());
    }

    public final void a(List<USERINFOKEY> list) {
        if (list == null) {
            this.c = new ArrayList();
            this.c.addAll(this.d.keySet());
            return;
        }
        this.c = list;
    }

    public final boolean b() {
        a aVar = null;
        if (g().q()) {
            d(a.b);
            JSONArray jSONArray = new JSONArray();
            for (USERINFOKEY userinfokey : this.c) {
                Object obj = this.d.get(userinfokey);
                if (obj != null) {
                    jSONArray.put(obj);
                }
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("protocolVersion", R.styleable.AppCompatTheme_spinnerStyle);
                jSONObject.put("sequenceNo", j());
                jSONObject.put(anet.channel.strategy.dispatch.a.PLATFORM_VERSION, 1);
                jSONObject.put("peerID", k());
                jSONObject.put("businessType", g().d());
                jSONObject.put("clientVersion", g().e());
                jSONObject.put("isCompressed", 0);
                jSONObject.put("cmdID", XZBDevice.DOWNLOAD_LIST_FAILED);
                jSONObject.put("userID", h().getLongValue(USERINFOKEY.UserID));
                jSONObject.put("sessionID", h().getStringValue(USERINFOKEY.SessionID));
                jSONObject.put(SocialConstants.PARAM_APPNAME, new StringBuilder("ANDROID-").append(g().m()).toString());
                jSONObject.put("devicesign", v.b());
                jSONObject.put(Constants.KEY_SDK_VERSION, g().f());
                jSONObject.put("vasid", this.a);
                jSONObject.put("extensionList", jSONArray);
                this.b.clear();
                String toString = jSONObject.toString();
                XLLog.v("UserGetUserInfoTask", new StringBuilder("request package = ").append(toString).toString());
                aVar = g().j();
                aVar.a(toString.getBytes(), (int) R.styleable.Toolbar_contentInsetEnd, new AnonymousClass_1(this), j());
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                this.b.clear();
                Bundle bundle = new Bundle();
                bundle.putString(JsInterface.KEY_ACTION, "userGetDetailTask");
                bundle.putInt(Constants.KEY_ERROR_CODE, XLErrorCode.PACKAGE_ERROR);
                g().a((p) this, bundle);
                return aVar;
            }
        }
        bundle = new Bundle();
        bundle.putString(JsInterface.KEY_ACTION, "userGetDetailTask");
        bundle.putInt(Constants.KEY_ERROR_CODE, XLErrorCode.OPERATION_INVALID);
        g().a((p) this, bundle);
        return false;
    }
}
