package com.inmobi.ads;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import com.alipay.sdk.packet.d;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.c.e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.inmobi.rendering.a.c;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import org.json.JSONObject;

// compiled from: NativeAdUnit.java
class p extends AdUnit implements a {
    private static final String a;
    private Map<p, WeakReference<View>> b;
    private WeakHashMap<View, p> c;
    private String d;
    private String e;
    private n f;
    private URL g;
    private String h;
    private int i;
    private long j;

    static {
        a = p.class.getSimpleName();
    }

    public p(long j, a aVar) {
        super(a.b(), j, aVar);
        this.b = new HashMap();
        this.c = new WeakHashMap();
        this.i = 0;
        this.j = 0;
    }

    public void o() {
        if (this.j != 0) {
            int f = l().f();
            if (SystemClock.elapsedRealtime() - this.j < ((long) (f * 1000))) {
                a(new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST).setCustomMessage(new StringBuilder("Ad cannot be refreshed before ").append(f).append(" seconds").toString()), false);
                Logger.a(InternalLogLevel.ERROR, a, new StringBuilder("Ad cannot be refreshed before ").append(f).append(" seconds").toString());
                return;
            }
        }
        this.j = SystemClock.elapsedRealtime();
        super.o();
        this.f = new n(l().l(), new am(l().l()), this);
    }

    public void x() {
        if (AdState.STATE_ATTACHED == g()) {
            WeakReference weakReference = (WeakReference) this.b.get(this);
            if (weakReference != null) {
                View view = (View) weakReference.get();
                if (this.f != null && view != null) {
                    this.f.a(view, this, l().l());
                }
            }
        }
    }

    public void y() {
        if (AdState.STATE_ATTACHED == g()) {
            WeakReference weakReference = (WeakReference) this.b.get(this);
            if (weakReference != null) {
                View view = (View) weakReference.get();
                if (this.f != null && view != null) {
                    this.f.a(view);
                }
            }
        }
    }

    public boolean b(a aVar) {
        if (!super.b(aVar)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(aVar.b());
            this.d = jSONObject.getString("contextCode");
            this.e = jSONObject.getString(d.h);
            return (this.d == null || this.d.trim().length() == 0 || this.e == null || this.e.trim().length() == 0) ? false : true;
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Exception while parsing ad.", e);
            return false;
        }
    }

    public void a(a aVar) {
        super.a(aVar);
        if (AdState.STATE_AVAILABLE == g()) {
            b(this.d);
        }
    }

    public Object z() {
        return h();
    }

    public void a(View view, URL url, String str) {
        View view2;
        boolean z = true;
        Map hashMap = new HashMap();
        hashMap.put("customScript", Boolean.valueOf(str != null));
        String str2 = "customUrl";
        if (url == null) {
            z = false;
        }
        hashMap.put(str2, Boolean.valueOf(z));
        com.inmobi.commons.core.c.a.a().a("ads", "TrackImpression", hashMap);
        WeakReference weakReference = (WeakReference) this.b.get(this);
        if (weakReference != null) {
            view2 = (View) weakReference.get();
        } else {
            view2 = null;
        }
        if (!view.equals(view2)) {
            if (AdState.STATE_LOADED == g() || AdState.STATE_ATTACHED == g()) {
                a(view2);
                a(view);
                this.b.put(this, new WeakReference(view));
                this.c.put(view, this);
                this.g = url;
                this.h = str;
                this.f.a(view, this, l().l());
                a(AdState.STATE_ATTACHED);
            } else if (g() != AdState.STATE_RENDERED && g() != AdState.STATE_ACTIVE) {
                Logger.a(InternalLogLevel.ERROR, a, "Please wait for the ad to finish loading before making a call to bind.");
            }
        }
    }

    public void a(View view) {
        if (view != null && AdState.STATE_ATTACHED == g()) {
            a(AdState.STATE_LOADED);
            InMobiNative.sMappedAdUnits.remove(view);
            this.f.a(view);
            this.b.remove(this);
            p pVar = (p) this.c.remove(view);
            if (pVar != null) {
                pVar.a(AdState.STATE_LOADED);
                this.b.remove(pVar);
            }
        }
    }

    public void a(View view, Object obj) {
        A();
    }

    void A() {
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Impression record requested for Ad unit (").append(hashCode()).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (AdState.STATE_ATTACHED == g()) {
            a(AdState.STATE_RENDERED);
            n().a(true);
            n().b(this.e + "recordEvent(18)");
            if (this.h != null) {
                n().b(this.h);
            }
            if (this.g != null) {
                c.a().a(this.g.toExternalForm(), true);
            }
        }
    }

    void a(Map<String, String> map, URL url, String str) {
        boolean z = false;
        Logger.a(InternalLogLevel.INTERNAL, a, "Click record requested");
        Map hashMap = new HashMap();
        hashMap.put("customScript", Boolean.valueOf(str != null));
        String str2 = "customUrl";
        if (url != null) {
            z = true;
        }
        hashMap.put(str2, Boolean.valueOf(z));
        com.inmobi.commons.core.c.a.a().a("ads", "ReportClick", hashMap);
        if (AdState.STATE_ATTACHED == g() || AdState.STATE_RENDERED == g()) {
            n().d();
            n().b(b((Map) map));
            if (str != null) {
                n().b(str);
            }
            if (url != null) {
                c.a().a(url.toExternalForm(), true);
                return;
            }
            return;
        }
        com.inmobi.commons.core.c.a.a().a(new e("ads", "InvalidClickReport"));
        Logger.a(InternalLogLevel.ERROR, a, "reportAdClick call made in wrong state");
    }

    private String b(Map<String, String> map) {
        com.inmobi.commons.core.utilities.c.a((Map) map);
        if (map == null || map.isEmpty()) {
            return this.e + "recordEvent(8)";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.e + "recordEvent(8, ");
        stringBuilder.append(new JSONObject(map).toString());
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

    void B() {
        Logger.a(InternalLogLevel.INTERNAL, a, "Open landing page requested");
        com.inmobi.commons.core.c.a.a().a(new e("ads", "OpenLandingPage"));
        if (AdState.STATE_RENDERED == g() || AdState.STATE_ATTACHED == g()) {
            n().b(this.e + "openLandingPage()");
        }
    }

    protected void t() {
        k();
        this.b.clear();
        this.c.clear();
        if (this.f != null) {
            this.f.e();
        }
        this.h = null;
        this.g = null;
        if (!(n() == null || n().getParent() == null)) {
            ((ViewGroup) n().getParent()).removeView(n());
        }
        super.t();
    }

    protected String a() {
        return "native";
    }

    protected String c() {
        return null;
    }

    protected PlacementType d() {
        return PlacementType.INLINE;
    }

    public void c(RenderView renderView) {
        super.c(renderView);
        if (AdState.STATE_AVAILABLE == g()) {
            u();
            a(AdState.STATE_LOADED);
            w();
            m().a();
            r();
        }
    }

    public void e(RenderView renderView) {
        super.e(renderView);
        if (AdState.STATE_RENDERED == g() || AdState.STATE_ATTACHED == g()) {
            this.i++;
            a(AdState.STATE_ACTIVE);
            m().b();
        } else if (g() == AdState.STATE_ACTIVE) {
            this.i++;
        }
    }

    public void f(RenderView renderView) {
        super.f(renderView);
        if (AdState.STATE_ACTIVE == g()) {
            this.i--;
            if (this.i == 0) {
                a(AdState.STATE_RENDERED);
                Map hashMap = new HashMap();
                hashMap.put(JsInterface.FUNPLAY_AD_TRPE, a());
                hashMap.put("impId", j());
                com.inmobi.commons.core.c.a.a().a("ads", "AdRendered", hashMap);
                m().c();
            }
        }
    }
}
