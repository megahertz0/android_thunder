package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.a.a.a.a.h;
import com.tencent.stat.DeviceInfo;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.au;
import com.tencent.wxop.stat.common.a;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.q;
import com.umeng.message.MsgConstant;
import org.android.agoo.message.MessageService;
import org.json.JSONObject;

public abstract class e {
    protected static String j;
    private StatSpecifyReportedInfo a;
    protected String b;
    protected long c;
    protected int d;
    protected a e;
    protected int f;
    protected String g;
    protected String h;
    protected String i;
    protected boolean k;
    protected Context l;

    static {
        j = null;
    }

    e(Context context, int i, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.b = null;
        this.e = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.k = false;
        this.a = null;
        this.l = context;
        this.c = System.currentTimeMillis() / 1000;
        this.d = i;
        this.h = StatConfig.getInstallChannel(context);
        this.i = k.j(context);
        this.b = StatConfig.getAppKey(context);
        if (statSpecifyReportedInfo != null) {
            this.a = statSpecifyReportedInfo;
            if (k.c(statSpecifyReportedInfo.getAppKey())) {
                this.b = statSpecifyReportedInfo.getAppKey();
            }
            if (k.c(statSpecifyReportedInfo.getInstallChannel())) {
                this.h = statSpecifyReportedInfo.getInstallChannel();
            }
            if (k.c(statSpecifyReportedInfo.getVersion())) {
                this.i = statSpecifyReportedInfo.getVersion();
            }
            this.k = statSpecifyReportedInfo.isImportant();
        }
        this.g = StatConfig.getCustomUserId(context);
        this.e = au.a(context).b(context);
        if (a() != f.i) {
            this.f = k.s(context).intValue();
        } else {
            this.f = -f.i.a();
        }
        if (!h.c(j)) {
            String localMidOnly = StatConfig.getLocalMidOnly(context);
            j = localMidOnly;
            if (!k.c(localMidOnly)) {
                j = MessageService.MSG_DB_READY_REPORT;
            }
        }
    }

    public abstract f a();

    public abstract boolean a(JSONObject jSONObject);

    public boolean b(JSONObject jSONObject) {
        try {
            q.a(jSONObject, "ky", this.b);
            jSONObject.put("et", a().a());
            if (this.e != null) {
                jSONObject.put(DeviceInfo.TAG_IMEI, this.e.b());
                q.a(jSONObject, DeviceInfo.TAG_MAC, this.e.c());
                int d = this.e.d();
                jSONObject.put("ut", d);
                if (d == 0 && k.w(this.l) == 1) {
                    jSONObject.put("ia", 1);
                }
            }
            q.a(jSONObject, "cui", this.g);
            if (a() != f.b) {
                q.a(jSONObject, com.alipay.sdk.sys.a.k, this.i);
                q.a(jSONObject, "ch", this.h);
            }
            if (this.k) {
                jSONObject.put("impt", 1);
            }
            q.a(jSONObject, DeviceInfo.TAG_MID, j);
            jSONObject.put("idx", this.f);
            jSONObject.put("si", this.d);
            jSONObject.put(MsgConstant.KEY_TS, this.c);
            jSONObject.put("dts", k.a(this.l, false));
            return a(jSONObject);
        } catch (Throwable th) {
            return false;
        }
    }

    public long c() {
        return this.c;
    }

    public StatSpecifyReportedInfo d() {
        return this.a;
    }

    public Context e() {
        return this.l;
    }

    public boolean f() {
        return this.k;
    }

    public String g() {
        try {
            JSONObject jSONObject = new JSONObject();
            b(jSONObject);
            return jSONObject.toString();
        } catch (Throwable th) {
            return com.umeng.a.d;
        }
    }
}
