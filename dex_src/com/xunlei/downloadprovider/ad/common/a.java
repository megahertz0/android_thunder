package com.xunlei.downloadprovider.ad.common;

import android.content.Context;
import android.view.View;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.web.base.WebViewNormalActivity;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: BaseAdapterModel.java
public abstract class a<T, E> extends com.xunlei.downloadprovider.ad.common.d.c.a {
    public abstract AD_TYPE o();

    public String a() {
        return BuildConfig.VERSION_NAME;
    }

    public String b() {
        return BuildConfig.VERSION_NAME;
    }

    public String c() {
        return BuildConfig.VERSION_NAME;
    }

    public String d() {
        return BuildConfig.VERSION_NAME;
    }

    public boolean e() {
        return false;
    }

    public int f() {
        return 0;
    }

    public float g() {
        return 0.0f;
    }

    public String h() {
        return BuildConfig.VERSION_NAME;
    }

    public String i() {
        return BuildConfig.VERSION_NAME;
    }

    public String j() {
        return BuildConfig.VERSION_NAME;
    }

    public String k() {
        return BuildConfig.VERSION_NAME;
    }

    public boolean l() {
        return false;
    }

    public String m() {
        return BuildConfig.VERSION_NAME;
    }

    public String n() {
        return BuildConfig.VERSION_NAME;
    }

    public final void a(Context context) {
        WebViewNormalActivity.a(context, BuildConfig.VERSION_NAME, h(), a());
    }

    public String toString() {
        return new StringBuilder("BaseAdapterModel{sourceType: ").append(o().name()).append("title: ").append(a()).append("desc: ").append(b()).append("}").toString();
    }

    public T p() {
        return null;
    }

    public E q() {
        return null;
    }

    public void onClick(View view) {
        super.a(SimpleLog.LOG_LEVEL_DEBUG);
    }

    public void a(View view) {
        super.a(1);
    }
}
