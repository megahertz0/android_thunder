package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.xllib.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadADConfig.java
public final class f {
    public static f a;
    Map<AD_TYPE, Integer> b;
    Map<AD_TYPE, Integer> c;
    Map<AD_TYPE, Integer> d;
    public List<a> e;
    public boolean f;
    public boolean g;
    Map<AD_TYPE, Integer> h;

    // compiled from: DownloadADConfig.java
    public static interface a {
        void a();

        void b();
    }

    private f() {
        this.b = new HashMap();
        this.c = new HashMap();
        this.d = new HashMap();
        this.h = new HashMap();
        this.h.put(AD_TYPE.SOURCE_BAIDU_FLAG, Integer.valueOf(SpdyProtocol.PUBKEY_SEQ_OPEN));
        this.h.put(AD_TYPE.SOURCE_GDT_FLAG, Integer.valueOf(R.styleable.AppCompatTheme_controlBackground));
        this.h.put(AD_TYPE.SOURCE_SSP_FLAG, Integer.valueOf(0));
        this.f = false;
        this.g = false;
        this.e = new ArrayList();
    }

    public static f a() {
        if (a == null) {
            a = new f();
        }
        return a;
    }
}
