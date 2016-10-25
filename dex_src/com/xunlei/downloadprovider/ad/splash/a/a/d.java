package com.xunlei.downloadprovider.ad.splash.a.a;

import android.view.View;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.loading.a.b;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;

// compiled from: BaseInMobiAdInfo.java
public class d extends a<b> {
    public d(b bVar) {
        super(bVar);
    }

    public final boolean g() {
        return ((b) this.b).c.equals("install") || ((b) this.b).c.equals("\u4e0b\u8f7d");
    }

    public final String h() {
        return ((b) this.b).a;
    }

    public final String i() {
        return BuildConfig.VERSION_NAME;
    }

    public final String j() {
        return ((b) this.b).d;
    }

    public final float k() {
        return 0.0f;
    }

    public final String l() {
        return ((b) this.b).b;
    }

    public void onClick(BaseActivity baseActivity, View view) {
    }

    public final void a(View view) {
    }

    public final int c_() {
        return this.c;
    }

    public final String n() {
        return "inmobi";
    }

    public final int o() {
        return R.styleable.AppCompatTheme_checkboxStyle;
    }

    public final String d() {
        return "inmobi";
    }

    public final String p() {
        return "inmobi";
    }

    public final String m() {
        return com.xunlei.downloadprovider.ad.common.d.d.a(this.c, R.styleable.AppCompatTheme_checkboxStyle);
    }
}
