package com.xunlei.downloadprovider.ad.splash.a.a;

import android.view.View;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.ad.common.d.d;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;

// compiled from: BaseGDTAdInfo.java
public abstract class c extends a<NativeADDataRef> {
    public c(NativeADDataRef nativeADDataRef) {
        super(nativeADDataRef);
    }

    public final boolean g() {
        return ((NativeADDataRef) this.b).isAPP();
    }

    public final String h() {
        return ((NativeADDataRef) this.b).getTitle();
    }

    public final String i() {
        return ((NativeADDataRef) this.b).getDesc();
    }

    public final String j() {
        return ((NativeADDataRef) this.b).getImgUrl();
    }

    public final float k() {
        return g() ? (float) (((NativeADDataRef) this.b).getAPPScore() / 2) : 4.5f;
    }

    public final String l() {
        return BuildConfig.VERSION_NAME;
    }

    public final String d() {
        return SocializeProtocolConstants.PROTOCOL_KEY_TENCENT;
    }

    public final String p() {
        return SocializeProtocolConstants.PROTOCOL_KEY_TENCENT;
    }

    public void onClick(BaseActivity baseActivity, View view) {
        ((NativeADDataRef) this.b).onClicked(view);
    }

    public final void a(View view) {
        ((NativeADDataRef) this.b).onExposured(view);
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

    public String toString() {
        return new StringBuilder("BaseGDTAdInfo{mServerPositionId=").append(this.c).append("title: ").append(h()).append("desc: ").append(i()).append('}').toString();
    }
}
