package com.xunlei.downloadprovider.download.tasklist.list.e.b;

import android.view.View;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.ad.common.d.d;
import com.xunlei.xllib.R;

// compiled from: RecommendGDTAdInfo.java
public class e extends d<NativeADDataRef> {
    private static final String c;
    protected int b;

    static {
        c = e.class.getSimpleName();
    }

    public final boolean b() {
        return ((NativeADDataRef) this.a).isAPP();
    }

    public final String c() {
        return ((NativeADDataRef) this.a).getTitle();
    }

    public final String e() {
        return ((NativeADDataRef) this.a).getDesc();
    }

    public final String f() {
        return ((NativeADDataRef) this.a).getImgUrl();
    }

    public final String g() {
        return ((NativeADDataRef) this.a).getIconUrl();
    }

    public final float i() {
        return b() ? (float) (((NativeADDataRef) this.a).getAPPScore() / 2) : 4.5f;
    }

    public final String d() {
        return SocializeProtocolConstants.PROTOCOL_KEY_TENCENT;
    }

    public final String p() {
        return SocializeProtocolConstants.PROTOCOL_KEY_TENCENT;
    }

    public void onClick(View view) {
        ((NativeADDataRef) this.a).onClicked(view);
    }

    public final void a(int i) {
    }

    public final int c_() {
        return this.b;
    }

    public final String m() {
        return d.a(this);
    }

    public final String n() {
        return SocializeProtocolConstants.PROTOCOL_KEY_TENCENT;
    }

    public final int o() {
        return R.styleable.AppCompatTheme_buttonStyle;
    }
}
