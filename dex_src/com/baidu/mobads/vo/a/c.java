package com.baidu.mobads.vo.a;

import com.baidu.mobads.command.XAdCommandExtraInfo;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class c extends a {
    public long A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public String n;
    public int o;
    public int p;
    public int q;
    public AtomicInteger r;
    public int s;
    public int t;
    public long u;
    public int v;
    public int w;
    public int x;
    public int y;
    public long z;

    public c(XAdCommandExtraInfo xAdCommandExtraInfo) {
        super(xAdCommandExtraInfo);
        this.r = new AtomicInteger(0);
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.H = 0;
    }

    protected HashMap<String, String> b() {
        HashMap<String, String> hashMap = new HashMap();
        if (this.n.length() > 1024) {
            hashMap.put("obj", this.n.substring(0, JsInterface.MSG_JS_SET_HOT_KEY));
        } else {
            hashMap.put("obj", this.n);
        }
        hashMap.put("order", this.o);
        hashMap.put("height", this.p);
        hashMap.put(NotificationCompatApi21.CATEGORY_PROGRESS, this.q);
        hashMap.put("moves", this.r.get());
        hashMap.put("clicks", this.s);
        hashMap.put("urlclicks", this.s);
        hashMap.put("lploadtime", this.t);
        hashMap.put("duration", this.u);
        hashMap.put("_lpWebStartLoad", this.z);
        hashMap.put("_lpWebFinishLoad", this.A);
        hashMap.put("e75", this.v);
        hashMap.put("e75_3", this.w);
        hashMap.put("from", this.x);
        hashMap.put("maxTabs", this.y);
        hashMap.put("b_cancel", this.F);
        hashMap.put("b_refresh", this.D);
        hashMap.put("b_copy", this.E);
        hashMap.put("b_goback", this.B);
        hashMap.put("b_threeP", this.C);
        hashMap.put("b_home", this.H);
        hashMap.put("b_osgoback", this.G);
        return hashMap;
    }
}
