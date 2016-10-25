package com.xunlei.downloadprovider.ad.common.e;

import android.view.View;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.xllib.R;

// compiled from: GDTVodProxyModel.java
public final class e extends com.xunlei.downloadprovider.ad.common.a<NativeMediaADData, a> {
    public a a;
    private String b;
    private NativeMediaADData c;

    // compiled from: GDTVodProxyModel.java
    public static interface a {
        void a(NativeMediaADData nativeMediaADData);

        void a(NativeMediaADData nativeMediaADData, int i);
    }

    public e(String str, NativeMediaADData nativeMediaADData) {
        this.b = str;
        this.c = nativeMediaADData;
    }

    public final String a() {
        return this.c != null ? this.c.getTitle() : null;
    }

    public final String b() {
        return this.c != null ? this.c.getDesc() : null;
    }

    public final String c() {
        return this.c != null ? this.c.getIconUrl() : null;
    }

    public final String d() {
        return this.c != null ? this.c.getImgUrl() : null;
    }

    public final boolean e() {
        return this.c != null ? this.c.isAPP() : false;
    }

    public final int f() {
        return this.c != null ? this.c.getAPPStatus() : 0;
    }

    public final float g() {
        return this.c != null ? (float) this.c.getAPPScore() : 0.0f;
    }

    public final AD_TYPE o() {
        return AD_TYPE.SOURCE_GDT_FLAG;
    }

    public final void onClick(View view) {
        super.onClick(view);
        if (this.c != null) {
            this.c.onClicked(view);
        }
    }

    public final void a(View view) {
        super.a(view);
        if (this.c != null) {
            this.c.onExposured(view);
        }
    }

    public final String r() {
        return this.b;
    }

    public final String s() {
        return b(R.styleable.AppCompatTheme_buttonStyle);
    }

    public final /* bridge */ /* synthetic */ Object q() {
        return this.a;
    }

    public final /* bridge */ /* synthetic */ Object p() {
        return this.c;
    }
}
