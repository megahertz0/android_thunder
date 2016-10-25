package com.xunlei.downloadprovider.ad.common.e;

import android.view.View;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.xllib.R;

// compiled from: GDTProxyModel.java
public final class d extends a {
    private String a;
    private NativeADDataRef b;

    public d(String str, NativeADDataRef nativeADDataRef) {
        this.a = str;
        this.b = nativeADDataRef;
    }

    public final String a() {
        return this.b != null ? this.b.getTitle() : null;
    }

    public final String b() {
        return this.b != null ? this.b.getDesc() : null;
    }

    public final String c() {
        return this.b != null ? this.b.getIconUrl() : null;
    }

    public final String d() {
        return this.b != null ? this.b.getImgUrl() : null;
    }

    public final boolean e() {
        return this.b != null ? this.b.isAPP() : false;
    }

    public final int f() {
        return this.b != null ? this.b.getAPPStatus() : 0;
    }

    public final float g() {
        return this.b != null ? (float) this.b.getAPPScore() : 0.0f;
    }

    public final AD_TYPE o() {
        return AD_TYPE.SOURCE_GDT_FLAG;
    }

    public final void onClick(View view) {
        super.onClick(view);
        if (this.b != null) {
            this.b.onClicked(view);
        }
    }

    public final void a(View view) {
        super.a(view);
        if (this.b != null) {
            this.b.onExposured(view);
        }
    }

    public final String r() {
        return this.a;
    }

    public final String s() {
        return b(R.styleable.AppCompatTheme_buttonStyle);
    }
}
