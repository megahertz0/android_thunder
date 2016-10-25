package com.xunlei.downloadprovider.download.taskDetail.a;

import android.app.Activity;
import android.view.View;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: TaskDetailGDTImageAdInfo.java
public final class g extends h<NativeADDataRef> {
    public g(NativeADDataRef nativeADDataRef) {
        super(nativeADDataRef);
    }

    public final void onClick(Activity activity, View view) {
        ((NativeADDataRef) this.a).onClicked(view);
    }

    public final void a(View view) {
        ((NativeADDataRef) this.a).onExposured(view);
    }

    public final String a() {
        return BuildConfig.VERSION_NAME;
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

    public final String h() {
        return BrothersApplication.a().getString(R.string.ad_company_name_gdt);
    }

    public final float i() {
        float aPPScore;
        if (b()) {
            aPPScore = (float) (((NativeADDataRef) this.a).getAPPScore() / 2);
        } else {
            aPPScore = 4.5f;
        }
        return aPPScore <= 0.0f ? 4.5f : aPPScore;
    }

    public final String d() {
        return SocializeProtocolConstants.PROTOCOL_KEY_TENCENT;
    }

    public final String p() {
        return SocializeProtocolConstants.PROTOCOL_KEY_TENCENT;
    }
}
