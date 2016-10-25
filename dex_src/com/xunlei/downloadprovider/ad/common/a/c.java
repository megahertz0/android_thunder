package com.xunlei.downloadprovider.ad.common.a;

import android.view.View;
import com.baidu.mobad.feeds.NativeResponse;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;

// compiled from: BaiduProxyModel.java
public final class c extends a {
    private NativeResponse a;
    private String b;

    public c(String str, NativeResponse nativeResponse) {
        this.a = nativeResponse;
        this.b = str;
    }

    public final String a() {
        return this.a != null ? this.a.getTitle() : null;
    }

    public final String b() {
        return this.a != null ? this.a.getDesc() : null;
    }

    public final String c() {
        return this.a != null ? this.a.getIconUrl() : null;
    }

    public final String d() {
        return this.a != null ? this.a.getImageUrl() : null;
    }

    public final boolean e() {
        return (this.a == null || this.a.getAppPackage() == null || this.a.getAppPackage().trim().equals(BuildConfig.VERSION_NAME)) ? false : true;
    }

    public final AD_TYPE o() {
        return AD_TYPE.SOURCE_BAIDU_FLAG;
    }

    public final void onClick(View view) {
        super.onClick(view);
        if (this.a != null) {
            this.a.handleClick(view);
        }
    }

    public final void a(View view) {
        super.a(view);
        if (this.a != null) {
            this.a.recordImpression(view);
        }
    }

    public final String i() {
        if (this.a != null) {
            this.a.getVideoUrl();
        }
        return super.i();
    }

    public final String r() {
        return this.b;
    }

    public final String s() {
        return b(R.styleable.AppCompatTheme_buttonStyleSmall);
    }
}
