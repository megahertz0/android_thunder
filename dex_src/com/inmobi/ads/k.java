package com.inmobi.ads;

import android.content.Context;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.commons.core.c.a;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.message.MessageService;

// compiled from: BannerAdUnit.java
class k extends AdUnit {
    private static final String a;
    private InMobiBanner b;
    private boolean c;
    private boolean d;
    private int e;

    static {
        a = k.class.getSimpleName();
    }

    public k(InMobiBanner inMobiBanner, Context context, long j, a aVar) {
        super(context, j, aVar);
        this.c = true;
        this.d = false;
        this.e = 0;
        this.b = inMobiBanner;
    }

    void x() {
        this.d = true;
        if (n() != null) {
            n().n();
        }
    }

    public void b(boolean z) {
        this.c = z;
        super.o();
    }

    boolean y() {
        return this.d;
    }

    boolean z() {
        return g() == AdState.STATE_ACTIVE;
    }

    protected void q() {
        super.q();
        if (this.d) {
            n().n();
        }
    }

    protected String a() {
        return "banner";
    }

    protected String c() {
        return this.b.getFrameSizeString();
    }

    protected PlacementType d() {
        return PlacementType.INLINE;
    }

    protected Map<String, String> e() {
        Map hashMap = new HashMap();
        hashMap.put("u-rt", this.c ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT);
        hashMap.put("mk-ad-slot", this.b.getFrameSizeString());
        return hashMap;
    }

    public void a(a aVar) {
        super.a(aVar);
        if (g() == AdState.STATE_AVAILABLE) {
            b(h());
        }
    }

    public void c(RenderView renderView) {
        super.c(renderView);
        if (g() == AdState.STATE_AVAILABLE) {
            u();
            a(AdState.STATE_LOADED);
            w();
            m().a();
            r();
        }
    }

    public void d(RenderView renderView) {
        super.d(renderView);
        if (g() == AdState.STATE_LOADED) {
            a(AdState.STATE_RENDERED);
            Map hashMap = new HashMap();
            hashMap.put(JsInterface.FUNPLAY_AD_TRPE, a());
            hashMap.put("impId", j());
            a.a().a("ads", "AdRendered", hashMap);
        }
    }

    public synchronized void e(RenderView renderView) {
        super.e(renderView);
        if (g() == AdState.STATE_RENDERED) {
            this.e++;
            a(AdState.STATE_ACTIVE);
            m().b();
        } else if (g() == AdState.STATE_ACTIVE) {
            this.e++;
        }
    }

    public synchronized void f(RenderView renderView) {
        super.f(renderView);
        if (g() == AdState.STATE_ACTIVE) {
            this.e--;
            if (this.e == 0) {
                a(AdState.STATE_RENDERED);
                m().c();
            }
        }
    }
}
