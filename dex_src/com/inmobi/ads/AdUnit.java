package com.inmobi.ads;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.configs.b.b;
import com.inmobi.commons.core.configs.f;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.c;
import com.inmobi.commons.core.utilities.uid.d;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.RenderingProperties;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.inmobi.signals.o;
import com.taobao.accs.common.Constants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

abstract class AdUnit implements com.inmobi.ads.g.a, b, com.inmobi.rendering.RenderView.a {
    private static final String a;
    private AdState b;
    private Context c;
    private long d;
    private String e;
    private Map<String, String> f;
    private b g;
    private String h;
    private String i;
    private long j;
    private boolean k;
    private a l;
    private RenderView m;
    private an n;
    private long o;
    private long p;

    public enum AdState {
        STATE_CREATED,
        STATE_LOADING,
        STATE_AVAILABLE,
        STATE_FAILED,
        STATE_LOADED,
        STATE_READY,
        STATE_ATTACHED,
        STATE_RENDERED,
        STATE_ACTIVE
    }

    static interface a {
        void a();

        void a(InMobiAdRequestStatus inMobiAdRequestStatus);

        void a(Map<Object, Object> map);

        void b();

        void b(Map<Object, Object> map);

        void c();

        void d();
    }

    protected abstract String a();

    protected abstract String c();

    protected abstract PlacementType d();

    static {
        a = AdUnit.class.getSimpleName();
    }

    public void a(com.inmobi.commons.core.configs.a aVar) {
        this.g = (b) aVar;
        com.inmobi.commons.core.c.a.a().a(this.g.a(), this.g.m());
    }

    public AdUnit(Context context, long j, a aVar) {
        this.k = false;
        this.p = 0;
        this.c = context;
        this.d = j;
        this.l = aVar;
        x();
        a(AdState.STATE_CREATED);
    }

    protected String b() {
        return "json";
    }

    protected Map<String, String> e() {
        return null;
    }

    protected Context f() {
        return this.c;
    }

    public AdState g() {
        return this.b;
    }

    protected String h() {
        return this.h;
    }

    long i() {
        return this.j;
    }

    protected String j() {
        return this.i;
    }

    protected void k() {
        this.h = null;
    }

    protected void a(AdState adState) {
        this.b = adState;
    }

    protected final b l() {
        return this.g;
    }

    protected final a m() {
        return this.l;
    }

    final void a(a aVar) {
        this.l = aVar;
    }

    protected final RenderView n() {
        return this.m;
    }

    public boolean b(a aVar) {
        try {
            JSONObject jSONObject = new JSONObject(aVar.b());
            this.j = aVar.d();
            this.i = aVar.c();
            this.h = jSONObject.getString("pubContent").trim();
            if (this.h == null || this.h.length() == 0) {
                return false;
            }
            this.h = this.h.replace("@__imm_aft@", String.valueOf(System.currentTimeMillis() - this.o));
            return true;
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Exception while parsing received ad.", e);
            return false;
        } catch (Throwable e2) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Invalid Base64 encoding in received ad.", e2);
            return false;
        }
    }

    public void a(a aVar) {
        if (g() != AdState.STATE_LOADING) {
            return;
        }
        if (b(aVar)) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Ad fetch successful");
            a(AdState.STATE_AVAILABLE);
            return;
        }
        c("ParsingFailed");
        a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), true);
    }

    public void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Ad fetch failed. Status:").append(inMobiAdRequestStatus.getStatusCode()).toString());
        a(inMobiAdRequestStatus, true);
        if (inMobiAdRequestStatus.getStatusCode() == StatusCode.INTERNAL_ERROR) {
            c("InternalError");
        }
    }

    protected void a(InMobiAdRequestStatus inMobiAdRequestStatus, boolean z) {
        if (g() == AdState.STATE_LOADING && z) {
            a(AdState.STATE_FAILED);
        }
        m().a(inMobiAdRequestStatus);
        if (inMobiAdRequestStatus.getStatusCode() == StatusCode.NO_FILL) {
            c("NoFill");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.SERVER_ERROR) {
            c("ServerError");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.NETWORK_UNREACHABLE) {
            c("NetworkUnreachable");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.AD_ACTIVE) {
            c("AdActive");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.REQUEST_PENDING) {
            c("RequestPending");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.REQUEST_INVALID) {
            c("RequestInvalid");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.REQUEST_TIMED_OUT) {
            c("RequestTimedOut");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.EARLY_REFRESH_REQUEST) {
            c("EarlyRefreshRequest");
        }
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(Map<String, String> map) {
        this.f = map;
    }

    public void o() {
        Map hashMap = new HashMap();
        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, a());
        com.inmobi.commons.core.c.a.a().a("ads", "AdLoadRequested", hashMap);
        if (!c.a()) {
            a(new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE), true);
        } else if (this.b == AdState.STATE_LOADING || this.b == AdState.STATE_AVAILABLE) {
            a(new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING), false);
            Logger.a(InternalLogLevel.ERROR, a, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad");
        } else if (g() == AdState.STATE_ACTIVE) {
            a(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE), false);
            Logger.a(InternalLogLevel.ERROR, a, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad");
        } else {
            t();
            this.b = AdState.STATE_LOADING;
            o.a().i();
            q();
            s();
            a(y());
        }
    }

    protected void a(boolean z) {
        this.k = z;
    }

    protected boolean p() {
        return this.k;
    }

    protected void q() {
        this.m = new RenderView(f(), new RenderingProperties(d()));
        this.m.a((com.inmobi.rendering.RenderView.a) this, l().j(), l().k());
    }

    protected void b(String str) {
        this.p = SystemClock.elapsedRealtime();
        n().a(str);
        z();
    }

    protected void r() {
        this.m.b("inmobi.recordEvent(120,null);");
    }

    private void x() {
        this.g = new b();
        com.inmobi.commons.core.configs.b.a().a(new f(), null);
        com.inmobi.commons.core.configs.b.a().a(this.g, (b) this);
        this.n = new an(this);
        com.inmobi.commons.core.c.a.a().a(this.g.a(), this.g.m());
    }

    void s() {
        com.inmobi.commons.core.utilities.uid.c.a().e();
    }

    private h y() {
        h hVar = new h();
        hVar.b(this.e);
        hVar.a(this.f);
        hVar.a(this.d);
        hVar.c(a());
        hVar.a(l().a(a()));
        hVar.b(e());
        hVar.d(b());
        hVar.a(this.g.e());
        hVar.a(this.g.h());
        hVar.e(c());
        hVar.a(new d(this.g.o().a()));
        return hVar;
    }

    void a(h hVar) {
        this.o = System.currentTimeMillis();
        new g(hVar, this).a();
    }

    protected void t() {
        this.i = null;
        View n = n();
        if (n != null) {
            ViewParent parent = n.getParent();
            n.removeAllViews();
            if (parent != null) {
                ((ViewGroup) parent).removeView(n);
            }
            n.destroy();
        }
    }

    public void a(RenderView renderView) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Render view signaled ad ready");
    }

    public void b(RenderView renderView) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Render view signaled ad failed");
        c("RenderFailed");
    }

    public void c(RenderView renderView) {
        Logger.a(InternalLogLevel.INTERNAL, a, "RenderView completed loading ad content");
    }

    public void d(RenderView renderView) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Renderview visible");
    }

    public void e(RenderView renderView) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Ad displayed");
    }

    public void f(RenderView renderView) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Ad dismissed");
    }

    public void a(RenderView renderView, HashMap<Object, Object> hashMap) {
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Ad reward action completed. Params:").append(hashMap == null ? null : hashMap.toString()).toString());
        m().b(hashMap);
    }

    public void b(RenderView renderView, HashMap<Object, Object> hashMap) {
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Ad interaction. Params:").append(hashMap == null ? null : hashMap.toString()).toString());
        m().a((Map) hashMap);
    }

    public void g(RenderView renderView) {
        Logger.a(InternalLogLevel.INTERNAL, a, "User left application");
        m().d();
    }

    private void z() {
        u();
        this.n.sendEmptyMessageDelayed(0, (long) (l().j().i() * 1000));
    }

    protected void u() {
        this.n.removeMessages(0);
    }

    protected void v() {
        Logger.a(InternalLogLevel.INTERNAL, a, "Renderview timed out.");
        c("RenderTimeOut");
        if (g() == AdState.STATE_AVAILABLE) {
            a(AdState.STATE_FAILED);
            m().a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
        }
    }

    protected void w() {
        Map hashMap = new HashMap();
        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, a());
        hashMap.put("renderLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.p));
        com.inmobi.commons.core.c.a.a().a("ads", "AdLoadSuccessful", hashMap);
    }

    protected void c(String str) {
        Map hashMap = new HashMap();
        hashMap.put("impId", j());
        hashMap.put(Constants.KEY_ERROR_CODE, str);
        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, a());
        if (str != null) {
            if (str.trim().equalsIgnoreCase("RenderFailed") || str.trim().equalsIgnoreCase("RenderTimeOut")) {
                hashMap.put("renderLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.p));
            }
        }
        com.inmobi.commons.core.c.a.a().a("ads", "AdLoadFailed", hashMap);
    }

    protected void d(String str) {
        Map hashMap = new HashMap();
        hashMap.put("impId", j());
        hashMap.put(Constants.KEY_ERROR_CODE, str);
        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, a());
        com.inmobi.commons.core.c.a.a().a("ads", "AdShowFailed", hashMap);
    }
}
