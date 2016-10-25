package com.xunlei.downloadprovider.download.taskDetail.a;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.frame.advertisement.a.a;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.t;

// compiled from: TaskDetailXunLeiImageAdInfo.java
public class k extends h<a> {
    private static final String c;

    static {
        c = k.class.getSimpleName();
    }

    public k(a aVar) {
        super(aVar);
    }

    public final void a(View view) {
        b("show");
    }

    public final String a() {
        return ((a) this.a).j;
    }

    public final boolean b() {
        return ((a) this.a).m.equals(c.c);
    }

    public final String c() {
        return ((a) this.a).c;
    }

    public final String e() {
        return ((a) this.a).e;
    }

    public final String f() {
        return ((a) this.a).k;
    }

    public final String g() {
        return ((a) this.a).k;
    }

    public final String h() {
        return BrothersApplication.a().getString(R.string.ad_company_name_xunlei);
    }

    public final float i() {
        float parseFloat;
        try {
            parseFloat = Float.parseFloat(((a) this.a).r);
        } catch (NumberFormatException e) {
            e.getLocalizedMessage();
            parseFloat = 4.5f;
        }
        return parseFloat <= 0.0f ? 4.5f : parseFloat;
    }

    public final String d() {
        return "xunlei";
    }

    public final String p() {
        return ((a) this.a).a;
    }

    private void b(String str) {
        Object a = com.xunlei.downloadprovider.frame.advertisement.b.c.a(str, ((a) this.a).a);
        if (!TextUtils.isEmpty(a)) {
            new t(null).a(a);
        }
    }

    public void onClick(Activity activity, View view) {
        b("click");
        String str = ((a) this.a).m;
        if (!str.equals("0")) {
            if (str.equals(c.f)) {
                if (!TextUtils.isEmpty(((a) this.a).f)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("from_key", "adv_homeflow_pic");
                    bundle.putString("ad_id", ((a) this.a).a);
                    BrowserUtil.a();
                    BrowserUtil.a(activity, ((a) this.a).f, ((a) this.a).g, 0, bundle);
                }
            } else if (str.equals(c.e)) {
                if (!TextUtils.isEmpty(((a) this.a).f)) {
                    BrowserUtil.a();
                    BrowserUtil.a(activity, 0, ((a) this.a).f, true, null, false, null);
                }
            } else if (str.equals(c.c)) {
                String str2 = ((a) this.a).h;
                if (!TextUtils.isEmpty(str2)) {
                    g gVar = new g(3, str2, null);
                    gVar.d = com.xunlei.downloadprovider.service.a.l + ((a) this.a).a;
                    if (activity instanceof ThunderTask) {
                        ((ThunderTask) activity).createLocalTask(str2, null, 0, null, null, null, 0, gVar, null, false);
                    }
                }
            }
        }
    }
}
