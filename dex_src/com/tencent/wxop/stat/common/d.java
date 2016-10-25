package com.tencent.wxop.stat.common;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.taobao.accs.common.Constants;
import com.tencent.stat.DeviceInfo;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.a;
import com.tencent.wxop.stat.au;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

class d {
    String a;
    String b;
    DisplayMetrics c;
    int d;
    String e;
    String f;
    String g;
    String h;
    String i;
    String j;
    String k;
    int l;
    String m;
    String n;
    Context o;
    private String p;
    private String q;
    private String r;
    private String s;

    private d(Context context) {
        this.b = StatConstants.VERSION;
        this.d = VERSION.SDK_INT;
        this.e = Build.MODEL;
        this.f = Build.MANUFACTURER;
        this.g = Locale.getDefault().getLanguage();
        this.l = 0;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.o = context.getApplicationContext();
        this.c = k.d(this.o);
        this.a = k.j(this.o);
        this.h = StatConfig.getInstallChannel(this.o);
        this.i = k.i(this.o);
        this.j = TimeZone.getDefault().getID();
        this.l = k.o(this.o);
        this.k = k.p(this.o);
        this.m = this.o.getPackageName();
        if (this.d >= 14) {
            this.p = k.v(this.o);
        }
        this.q = k.u(this.o).toString();
        this.r = k.t(this.o);
        this.s = k.d();
        this.n = k.C(this.o);
    }

    void a(JSONObject jSONObject, Thread thread) {
        if (thread == null) {
            if (this.c != null) {
                jSONObject.put("sr", this.c.widthPixels + "*" + this.c.heightPixels);
                jSONObject.put("dpi", this.c.xdpi + "*" + this.c.ydpi);
            }
            if (a.a(this.o).e()) {
                JSONObject jSONObject2 = new JSONObject();
                q.a(jSONObject2, "bs", q.d(this.o));
                q.a(jSONObject2, "ss", q.e(this.o));
                if (jSONObject2.length() > 0) {
                    q.a(jSONObject, "wf", jSONObject2.toString());
                }
            }
            JSONArray a = q.a(this.o, (int) XZBDevice.Stop);
            if (a != null && a.length() > 0) {
                q.a(jSONObject, "wflist", a.toString());
            }
            q.a(jSONObject, "sen", this.p);
        } else {
            q.a(jSONObject, "thn", thread.getName());
            q.a(jSONObject, "qq", StatConfig.getQQ(this.o));
            q.a(jSONObject, "cui", StatConfig.getCustomUserId(this.o));
            if (k.c(this.r) && this.r.split("/").length == 2) {
                q.a(jSONObject, "fram", this.r.split("/")[0]);
            }
            if (k.c(this.s) && this.s.split("/").length == 2) {
                q.a(jSONObject, "from", this.s.split("/")[0]);
            }
            if (au.a(this.o).b(this.o) != null) {
                jSONObject.put(DeviceInfo.TAG_IMEI, au.a(this.o).b(this.o).b());
            }
            q.a(jSONObject, DeviceInfo.TAG_MID, StatConfig.getLocalMidOnly(this.o));
        }
        q.a(jSONObject, "pcn", k.q(this.o));
        q.a(jSONObject, "osn", VERSION.RELEASE);
        q.a(jSONObject, com.alipay.sdk.sys.a.k, this.a);
        q.a(jSONObject, "ch", this.h);
        q.a(jSONObject, "mf", this.f);
        q.a(jSONObject, com.alipay.sdk.sys.a.h, this.b);
        q.a(jSONObject, "osd", Build.DISPLAY);
        q.a(jSONObject, "prod", Build.PRODUCT);
        q.a(jSONObject, MsgConstant.KEY_TAGS, Build.TAGS);
        q.a(jSONObject, SocializeConstants.WEIBO_ID, Build.ID);
        q.a(jSONObject, "fng", Build.FINGERPRINT);
        q.a(jSONObject, "lch", this.n);
        q.a(jSONObject, "ov", Integer.toString(this.d));
        jSONObject.put(Constants.KEY_OS_VERSION, 1);
        q.a(jSONObject, "op", this.i);
        q.a(jSONObject, "lg", this.g);
        q.a(jSONObject, IXAdRequestInfo.TEST_MODE, this.e);
        q.a(jSONObject, "tz", this.j);
        if (this.l != 0) {
            jSONObject.put("jb", this.l);
        }
        q.a(jSONObject, "sd", this.k);
        q.a(jSONObject, "apn", this.m);
        q.a(jSONObject, "cpu", this.q);
        q.a(jSONObject, "abi", Build.CPU_ABI);
        q.a(jSONObject, "abi2", Build.CPU_ABI2);
        q.a(jSONObject, "ram", this.r);
        q.a(jSONObject, "rom", this.s);
    }
}
