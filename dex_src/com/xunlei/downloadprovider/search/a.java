package com.xunlei.downloadprovider.search;

import android.content.Context;
import com.qq.e.ads.cfg.MultiProcessFlag;
import com.qq.e.ads.nativ.NativeAD;
import com.qq.e.ads.nativ.NativeAD.NativeAdListener;
import com.qq.e.ads.nativ.NativeADDataRef;
import java.util.Stack;

// compiled from: SearchAdHelper.java
public class a {
    public NativeAD a;
    public boolean b;
    public Stack<a> c;
    private NativeAdListener d;

    // compiled from: SearchAdHelper.java
    public static interface a {
        void a();

        void a(NativeADDataRef nativeADDataRef);
    }

    public a(Context context) {
        this.c = new Stack();
        this.d = new b(this);
        if (this.a == null) {
            MultiProcessFlag.setMultiProcess(true);
            this.a = new NativeAD(context, "1104872693", "2030907712339486", this.d);
        }
        this.b = true;
    }
}
