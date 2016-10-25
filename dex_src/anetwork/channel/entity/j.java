package anetwork.channel.entity;

import android.text.TextUtils;
import anet.channel.monitor.NetworkSpeed;
import anet.channel.statist.RequestStatistic;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.n;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import anetwork.channel.aidl.ParcelableRequest;
import anetwork.channel.b.b;
import anetwork.channel.monitor.a;
import java.util.HashMap;
import java.util.Map;

// compiled from: Taobao
public final class j {
    public ParcelableRequest a;
    public String b;
    public String c;
    public String d;
    public Map<String, String> e;
    public int f;
    public int g;
    public int h;
    public int i;
    public String j;
    public RequestStatistic k;

    public j(ParcelableRequest parcelableRequest) {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = null;
        this.k = null;
        if (parcelableRequest == null) {
            throw new IllegalArgumentException("request is null");
        }
        try {
            this.a = parcelableRequest;
            c();
            this.g = parcelableRequest.c;
            if (this.g < 0 || this.g > 3) {
                this.g = 2;
            }
            this.h = parcelableRequest.j;
            if (this.h <= 0) {
                this.h = (int) (e() * 15000.0f);
            }
            this.i = parcelableRequest.k;
            if (this.i <= 0) {
                this.i = (int) (e() * 15000.0f);
            }
        } catch (Throwable e) {
            ALog.e("ANet.RequestConfig", "RequestConfig init failed.", null, e, new Object[0]);
        }
    }

    private float e() {
        return (!n.c(this.c) && a.b() == NetworkSpeed.Fast) ? 0.8f : 1.0f;
    }

    public final String a() {
        if (this.j == null) {
            this.j = this.a.m;
        }
        return this.j;
    }

    public final boolean b() {
        return this.f < this.g;
    }

    public final void c() {
        this.b = this.a.d;
        if (b.b()) {
            if (this.a.o) {
                this.b = StrategyCenter.getInstance().getFormalizeUrl(this.b);
            }
        } else if (!TextUtils.isEmpty(this.b)) {
            this.b = this.b.replaceAll("^((?i)https:)?//", "http://");
        }
        String[] parseURL = StringUtils.parseURL(this.b);
        if (parseURL != null) {
            this.c = parseURL[1];
            this.d = parseURL[0];
        }
        this.k = new RequestStatistic(this.c, String.valueOf(this.a.l));
        this.k.url = this.b;
    }

    public final Map<String, String> d() {
        if (this.e != null) {
            return this.e;
        }
        this.e = new HashMap();
        if (this.a.g != null) {
            for (anetwork.channel.a aVar : this.a.g) {
                String a = aVar.a();
                if (!HttpConstant.HOST.equalsIgnoreCase(a) && !":host".equalsIgnoreCase(a) && !"Cookie".equalsIgnoreCase(a)) {
                    this.e.put(a, aVar.b());
                }
            }
        }
        if (this.a.n) {
            String a2 = anetwork.channel.c.a.a(this.b.toString());
            if (a2 != null) {
                this.e.put("Cookie", a2);
            }
        }
        return this.e;
    }
}
