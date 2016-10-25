package com.xunlei.downloadprovider.ad.splash.a.a;

import android.view.View;
import com.baidu.mobad.feeds.NativeResponse;
import com.xunlei.downloadprovider.ad.common.d.d;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;

// compiled from: BaseBaiDuAdInfo.java
public class b extends a<NativeResponse> {
    public b(NativeResponse nativeResponse) {
        super(nativeResponse);
    }

    public final boolean g() {
        return (((NativeResponse) this.b).getAppPackage() == null || ((NativeResponse) this.b).getAppPackage().trim().equals(BuildConfig.VERSION_NAME)) ? false : true;
    }

    public final String h() {
        return ((NativeResponse) this.b).getTitle();
    }

    public final String i() {
        return ((NativeResponse) this.b).getDesc();
    }

    public final String j() {
        return ((NativeResponse) this.b).getImageUrl();
    }

    public final float k() {
        return 4.5f;
    }

    public final String l() {
        return BuildConfig.VERSION_NAME;
    }

    public void onClick(BaseActivity baseActivity, View view) {
        ((NativeResponse) this.b).handleClick(view);
    }

    public final void a(View view) {
        ((NativeResponse) this.b).recordImpression(view);
    }

    public final String m() {
        return d.a(this);
    }

    public final String n() {
        return "baidu";
    }

    public final int o() {
        return R.styleable.AppCompatTheme_buttonStyleSmall;
    }

    public final String d() {
        return "baidu";
    }

    public final String p() {
        return "baidu";
    }
}
