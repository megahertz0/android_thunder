package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.c.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.message.MessageService;

// compiled from: InterstitialAdUnit.java
class o extends AdUnit {
    private static final String a;
    private int b;
    private boolean c;
    private int d;
    private long e;

    static {
        a = o.class.getSimpleName();
    }

    public o(Context context, long j, a aVar) {
        super(context, j, aVar);
        this.b = 0;
        this.c = false;
        this.d = -1;
        this.e = 0;
    }

    protected void q() {
        super.q();
        if (this.c) {
            n().n();
        }
    }

    public void o() {
        if (this.e != 0) {
            int f = l().f();
            if (SystemClock.elapsedRealtime() - this.e < ((long) (f * 1000))) {
                a(new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST).setCustomMessage(new StringBuilder("Ad cannot be refreshed before ").append(f).append(" seconds").toString()), false);
                Logger.a(InternalLogLevel.ERROR, a, new StringBuilder("Ad cannot be refreshed before ").append(f).append(" seconds").toString());
                return;
            }
        }
        if (g() == AdState.STATE_RENDERED) {
            a(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE), false);
            Logger.a(InternalLogLevel.ERROR, a, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad.");
            return;
        }
        this.e = SystemClock.elapsedRealtime();
        super.o();
    }

    protected Map<String, String> e() {
        Map hashMap = new HashMap();
        hashMap.put("preload-request", p() ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT);
        return hashMap;
    }

    protected void t() {
        super.t();
        this.d = -1;
    }

    public void x() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                Map hashMap = new HashMap();
                String j = o.this.j();
                if (j != null) {
                    hashMap.put("impId", j);
                }
                a.a().a("ads", "ShowInt", hashMap);
                if (o.this.z()) {
                    hashMap.put(JsInterface.FUNPLAY_AD_TRPE, o.this.a());
                    a.a().a("ads", "AdRendered", hashMap);
                    o.this.a(AdState.STATE_RENDERED);
                    o.this.y();
                    return;
                }
                Logger.a(InternalLogLevel.ERROR, a, "Ad Load is not complete. Please wait for the Ad to be in a ready state before calling show.");
                o.this.d("ShowIntBeforeReady");
            }
        });
    }

    public void a(int i, int i2) {
        try {
            f().getResources().getAnimation(i);
            f().getResources().getAnimation(i2);
            this.d = i;
            n().setFullScreenExitAnimation(i2);
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.ERROR, a, "The supplied resource id with show for animations is invalid", e);
        }
        x();
    }

    void y() {
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder(">>> Starting ").append(InMobiAdActivity.class.getSimpleName()).append(" to display interstitial ad ...").toString());
        int a = InMobiAdActivity.a(n());
        Intent intent = new Intent(f(), InMobiAdActivity.class);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_RENDERVIEW_INDEX", a);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", R.styleable.AppCompatTheme_checkboxStyle);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", true);
        com.inmobi.commons.a.a.a(f(), intent);
        if ((f() instanceof Activity) && this.d != -1) {
            ((Activity) f()).overridePendingTransition(this.d, 0);
        }
    }

    public boolean z() {
        return g() == AdState.STATE_READY;
    }

    void A() {
        this.c = true;
        if (n() != null) {
            n().n();
        }
    }

    protected String a() {
        return "int";
    }

    protected String c() {
        return null;
    }

    protected PlacementType d() {
        return PlacementType.FULL_SCREEN;
    }

    public void a(a aVar) {
        super.a(aVar);
        if (g() == AdState.STATE_AVAILABLE) {
            b(h());
        }
    }

    public void a(RenderView renderView) {
        super.a(renderView);
        if (g() == AdState.STATE_LOADED) {
            u();
            a(AdState.STATE_READY);
            w();
            m().a();
            r();
        }
    }

    public void c(RenderView renderView) {
        super.c(renderView);
        if (g() == AdState.STATE_AVAILABLE) {
            a(AdState.STATE_LOADED);
        }
    }

    public void b(RenderView renderView) {
        super.b(renderView);
        if (g() == AdState.STATE_LOADED) {
            u();
            a(AdState.STATE_FAILED);
            m().a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
        }
    }

    public synchronized void e(RenderView renderView) {
        super.e(renderView);
        if (g() == AdState.STATE_RENDERED) {
            this.b++;
            if (this.b == 1) {
                m().b();
            } else {
                a(AdState.STATE_ACTIVE);
            }
        } else if (g() == AdState.STATE_ACTIVE) {
            this.b++;
        }
    }

    public synchronized void f(RenderView renderView) {
        super.f(renderView);
        if (g() == AdState.STATE_ACTIVE) {
            this.b--;
            if (this.b == 1) {
                a(AdState.STATE_RENDERED);
            }
        } else if (g() == AdState.STATE_RENDERED) {
            this.b--;
            a(AdState.STATE_CREATED);
            Map hashMap = new HashMap();
            hashMap.put("impId", j());
            a.a().a("ads", "IntClosed", hashMap);
            m().c();
            t();
        }
    }

    protected void v() {
        Logger.a(InternalLogLevel.INTERNAL, a, "Renderview timed out.");
        c("RenderTimeOut");
        if (g() == AdState.STATE_LOADED) {
            a(AdState.STATE_FAILED);
            m().a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
        }
    }
}
