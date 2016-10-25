package com.xunlei.tdlive.user;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.c;
import com.tencent.connect.common.Constants;
import com.xunlei.a.a.a;
import com.xunlei.a.a.a$b;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.tdlive.LoginGuideActivity;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.im.IMClient;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.protocol.XLLiveGetTickerRequest;
import com.xunlei.tdlive.protocol.XLLiveGetTickerRequest.GetTickerResp;
import com.xunlei.tdlive.protocol.XLLiveGetTickerRequest.GetTickerResp.UserInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.protocol.XLLiveRsyncAccountRequest;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.tdlive.util.r;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.android.spdy.SpdyAgent;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserHelper.java
public class f implements a$b, ObjectCallBack {
    public static long a;
    public static int b;
    private static f c;
    private r d;
    private Context e;
    private UserInfo f;
    private a g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private boolean q;
    private b r;
    private d s;
    private ArrayList<b> t;
    private ArrayList<c> u;
    private a v;
    private XLLiveGetTickerRequest w;

    // compiled from: UserHelper.java
    public static interface b {
        void a(boolean z);
    }

    static {
        b = -100;
        c = null;
    }

    private f(Context context) {
        this.h = false;
        this.i = false;
        this.j = 0;
        this.k = 1;
        this.q = false;
        this.t = new ArrayList();
        this.u = new ArrayList();
        this.w = null;
        this.e = context.getApplicationContext();
        this.r = new b(context);
    }

    public static synchronized f a(Context context) {
        f fVar;
        synchronized (f.class) {
            if (c == null) {
                c = new f(context);
            }
            fVar = c;
        }
        return fVar;
    }

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (c == null) {
                throw new IllegalStateException("UserHelper is a singleton clas, call UserHelper(Context ctx) first!");
            }
            fVar = c;
        }
        return fVar;
    }

    public void a(boolean z) {
        if (!this.h) {
            this.h = true;
            this.i = z;
            if (z) {
                this.l = new StringBuilder("app-").append(ac.g()).toString();
                return;
            }
            XLUserUtil.getInstance().Init(this.e, R.styleable.AppCompatTheme_colorControlNormal, ac.d(), ac.h(), "e91aa83000867e97cbe6dd7f9944b5cf");
            XLUserUtil.getInstance().attachListener(this.r);
            this.g = new a(this.e);
            a aVar = this.g;
            aVar.c = this;
            if (aVar.a != null) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                aVar.a.registerReceiver(aVar.b, intentFilter);
            }
            h();
        }
    }

    public void a(a aVar) {
        this.v = aVar;
    }

    public void a(b bVar) {
        if (bVar != null) {
            this.t.add(bVar);
        }
    }

    public void b(b bVar) {
        if (bVar != null) {
            this.t.remove(bVar);
        }
    }

    public boolean b() {
        if (ac.j() || ac.k()) {
            return XLUserUtil.getInstance().userIsOnline();
        }
        return (TextUtils.isEmpty(this.n) || TextUtils.isEmpty(this.o)) ? false : true;
    }

    public void c() {
        if (!b() && this.s != null) {
            this.s.a(null);
        }
    }

    public void a(Context context, String str, b bVar) {
        if (this.s == null) {
            this.s = new d();
        }
        if (!b()) {
            q.e("login_start").a(str).b(new String[0]);
            this.s.a(bVar);
            XLLiveSDK.getInstance(context).host().login(context);
        } else if (bVar != null) {
            bVar.a(true);
        }
    }

    public void d() {
        XLog.d("UserHelper", "onSessionInvalid()");
        if (b()) {
            XLUserUtil.getInstance().userLogout(null, "onSessionInvalid");
            Intent intent = new Intent(this.e, LoginGuideActivity.class);
            intent.addFlags(268435456);
            intent.putExtra("LoginGuideActivity.EXTRA_SHOW_FOR_INVALID_SESSION", true);
            this.e.startActivity(intent);
        }
    }

    public void a(boolean z, String str, String str2, String str3, String str4) {
        XLog.d("UserHelper", new StringBuilder("login state changed: ").append(z).toString());
        this.n = str;
        this.o = str2;
        this.m = str3;
        this.p = str4;
        Iterator it = this.t.iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(z);
        }
    }

    public void a(boolean z, int i, String str, String str2) {
        if (i == 0) {
            this.m = str;
            this.p = str2;
        }
        Iterator it = this.u.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(z, i, str, str2);
        }
        if (i == 0 && !ac.j()) {
            new XLLiveRsyncAccountRequest(k(), l(), z ? 0 : 1).send(new g(this));
        }
    }

    public void e() {
        if (this.v != null) {
            this.v.a();
        }
    }

    public boolean f() {
        return this.q;
    }

    public void b(boolean z) {
        this.q = z;
    }

    public void g() {
        if (this.w != null) {
            XLog.d("UserHelper", "getTicker already ing...");
            return;
        }
        if (this.d != null) {
            this.d.c();
        }
        this.w = new XLLiveGetTickerRequest(k(), l());
        this.w.send(this);
        XLog.d("UserHelper", "getTicker");
    }

    public void a(String str, String str2) {
        if (this.f == null) {
            this.f = new UserInfo();
        }
        if (!TextUtils.isEmpty(str)) {
            this.f.nickname = str;
        }
        if (!TextUtils.isEmpty(str2)) {
            this.f.sign = str2;
        }
        a(null, null, null, str, null, str2);
    }

    public void a(String str) {
        if (this.f == null) {
            this.f = new UserInfo();
        }
        if (!TextUtils.isEmpty(str)) {
            this.f.avatar = str;
        }
        com.xunlei.tdlive.util.a.a(this.e).a(str, new h(this));
    }

    public void a(int i) {
        String str;
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str = "m";
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                str = "f";
                break;
            default:
                str = "u";
                break;
        }
        a(null, null, null, null, str, null);
    }

    private void a(String str, String str2, String str3, String str4, String str5, String str6) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("birthday", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("province", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("city", str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                jSONObject.put("nickname", str4);
            }
            if (!TextUtils.isEmpty(str5)) {
                jSONObject.put("sex", str5);
            }
            if (!TextUtils.isEmpty(str6)) {
                jSONObject.put("sig", str6);
            }
            XLUserUtil.getInstance().userSetInfo(jSONObject, com.umeng.a.d, com.umeng.a.d, null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean h() {
        XLog.d("UserHelper", "autoLogin()");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.e.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            this.k = 4;
            return false;
        } else if (XLUserUtil.getInstance().userIsLogined()) {
            b(-1);
            this.k = 2;
            this.j = XLUserUtil.getInstance().userAutoLogin(null, null, new i(this), "sence-auto-login");
            return true;
        } else {
            this.k = 4;
            return false;
        }
    }

    public void i() {
        XLUserUtil.getInstance().userCancelLogin(this.j);
    }

    public boolean j() {
        return this.k == 3 || this.k == 4;
    }

    public String k() {
        if (b()) {
            return (ac.j() || ac.k()) ? XLUserUtil.getInstance().getCurrentUser().getStringValue(USERINFOKEY.UserID) : this.n;
        } else {
            return this.l;
        }
    }

    public String l() {
        String stringValue;
        if (ac.j() || ac.k()) {
            stringValue = XLUserUtil.getInstance().getCurrentUser().getStringValue(USERINFOKEY.SessionID);
        } else {
            stringValue = this.o;
        }
        return stringValue == null ? com.umeng.a.d : stringValue;
    }

    public boolean b(String str) {
        String k = k();
        return k != null ? k.equals(str) : true;
    }

    public String m() {
        String str = com.umeng.a.d;
        if (!b()) {
            return !TextUtils.isEmpty(this.l) ? new StringBuilder("\u6e38\u5ba2").append(this.l.hashCode()).toString() : str;
        } else {
            if (this.f != null) {
                str = this.f.nickname;
            } else if (ac.j() || ac.k()) {
                XLUserInfo currentUser = XLUserUtil.getInstance().getCurrentUser();
                str = currentUser.getStringValue(USERINFOKEY.NickName);
                if (str == null || str.length() <= 0) {
                    str = currentUser.getStringValue(USERINFOKEY.UserName);
                }
                if (str == null || str.length() <= 0) {
                    str = currentUser.getStringValue(USERINFOKEY.UserNewNo);
                }
            } else {
                str = this.m;
            }
            return str != null ? str.trim() : str;
        }
    }

    public String n() {
        return this.f != null ? this.f.sign : com.umeng.a.d;
    }

    public String o() {
        if (b()) {
            return this.f == null ? new StringBuilder("http://img.user.kanimg.com/usrimg/").append(k()).append("/300x300").toString() : this.f.avatar;
        } else {
            return com.umeng.a.d;
        }
    }

    public boolean p() {
        return this.f != null ? this.f.isSigner() : false;
    }

    public void b(int i) {
        String str;
        String str2 = null;
        b = i;
        a = SystemClock.elapsedRealtime();
        Map hashMap = new HashMap();
        hashMap.put("network", String.valueOf(ac.b()));
        switch (b) {
            case SniffingResourceGroup.PAGETYPE_NONE:
                str = "auto";
                str2 = "thunder";
                break;
            case SpdyAgent.ACCS_TEST_SERVER:
                str = "normal";
                str2 = "thunder";
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                str = c.e;
                str2 = "weibo";
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                str = c.e;
                str2 = "weichat";
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                str = c.e;
                str2 = Constants.SOURCE_QQ;
                break;
            default:
                str = null;
                break;
        }
        q.a("login_start", str, str2, hashMap);
    }

    public void q() {
        XLog.i("UserHelper", "onScreenOn");
    }

    public void r() {
        XLog.i("UserHelper", "onScreenOff");
        XLUserUtil.getInstance().setKeepAlive(false, 0);
    }

    public void s() {
        XLog.i("UserHelper", "onUserPresent");
        XLUserUtil.getInstance().setKeepAlive(true, 0);
    }

    public void onResponse(int i, String str, Object obj) {
        XLog.d("UserHelper", new StringBuilder("get ticker ").append(str).toString());
        this.w = null;
        GetTickerResp getTickerResp = (GetTickerResp) obj;
        if (i != 0 || getTickerResp == null || getTickerResp.data == null) {
            if (this.d == null) {
                this.d = new r(0, new j(this));
                this.d.a(true);
            }
            XLog.e("UserHelper", new StringBuilder("retry get ticker ").append(this.d.a() + 3000).append("s later").toString());
            this.d.a(this.d.a() + 3000);
            this.d.d();
            return;
        }
        XLog.d("UserHelper", new StringBuilder("get userinfo from getticker: ").append(getTickerResp.data.user_info).toString());
        if (getTickerResp.data.user_info != null) {
            this.f = getTickerResp.data.user_info;
            if (this.f.status == 1) {
                e.d = true;
                return;
            } else if (this.f.status == 0) {
                e.a = this.f.mail_num;
                e.e = getTickerResp.data.roomid;
            }
        }
        String str2 = getTickerResp.data.host;
        if (XLLiveRequest.getDNSCahce() != null) {
            str2 = XLLiveRequest.getDNSCahce().hit(str2);
            if (str2 == null || str2.length() <= 0) {
                str2 = getTickerResp.data.host;
            }
        }
        IMClient a = IMClient.a(this.e, null);
        a.a(str2, getTickerResp.data.port, getTickerResp.data.ticker);
        a.a();
    }
}
