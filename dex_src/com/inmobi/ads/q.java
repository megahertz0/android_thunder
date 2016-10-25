package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import anet.channel.util.HttpConstant;
import com.inmobi.ads.NativeStrandAsset.AssetInteractionMode;
import com.inmobi.ads.b.f;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.c;
import com.inmobi.rendering.InMobiAdActivity;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: NativeStrandAd.java
final class q implements a, b {
    private static final String a;
    private static final ab k;
    private final Context b;
    private final x c;
    private final String d;
    private final ac e;
    private Map<Integer, Integer> f;
    private List<NativeStrandAsset> g;
    private boolean h;
    private boolean i;
    private a j;

    // compiled from: NativeStrandAd.java
    static interface a {
        void a();

        void b();
    }

    static {
        a = q.class.getSimpleName();
        k = new ab();
    }

    q(Context context, x xVar, String str) {
        this.f = new HashMap();
        this.g = new ArrayList();
        this.i = false;
        this.b = context;
        this.c = xVar;
        this.d = str;
        this.e = new ac(context, xVar, this, this);
        this.h = false;
        this.i = false;
        this.c.a().a(System.currentTimeMillis());
        Map b = b(this.c.a());
        c(this.c.a(), b);
        d(this.c.a(), b);
    }

    public final void a(a aVar) {
        this.j = aVar;
    }

    public final void a(NativeStrandAsset nativeStrandAsset) {
        d();
        Map b = b(nativeStrandAsset);
        b(nativeStrandAsset, b);
        f(nativeStrandAsset, b);
    }

    public final void a(int i, NativeStrandAsset nativeStrandAsset) {
        if (this.f.get(Integer.valueOf(i)) == null && !b()) {
            d();
            a(i, (v) nativeStrandAsset);
        }
    }

    private void a(int i, v vVar) {
        this.f.put(Integer.valueOf(i), Integer.valueOf(1));
        vVar.a(System.currentTimeMillis());
        if (this.h) {
            e(vVar, b((NativeStrandAsset) vVar));
        } else {
            this.g.add(vVar);
        }
    }

    public final View a(View view, ViewGroup viewGroup, f fVar) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Creating ad-view from data model");
        if (view == null) {
            view = this.e.a(null, viewGroup);
        } else if (b(view)) {
            ag agVar = (ag) view;
            if (a(agVar)) {
                view = this.e.a(agVar, viewGroup);
            } else {
                Logger.a(InternalLogLevel.INTERNAL, a, "Already showing same ad, ignoring getAdView");
            }
        } else {
            Logger.a(InternalLogLevel.INTERNAL, a, "InMobiNativeStrand.getStrandView called with Non Strand View.");
            view = this.e.a(null, viewGroup);
        }
        view.a(this);
        if (!this.h) {
            k.a((Activity) this.b, fVar, view, this);
        }
        return view;
    }

    public final void a(View view) {
        this.g.clear();
        view.setOnClickListener(null);
    }

    public final x a() {
        return this.c;
    }

    public final boolean b() {
        return this.i;
    }

    public final void c() {
        if (!this.i) {
            this.j = null;
            this.i = true;
            this.e.b();
            this.g.clear();
            k.a((Activity) this.b, this);
        }
    }

    private void d() {
        v a = this.c.a(0);
        if (this.f.get(Integer.valueOf(0)) == null && a != null) {
            a(0, a);
        }
    }

    private boolean a(ag agVar) {
        q a = agVar.a();
        return a == null || this != a;
    }

    static boolean b(View view) {
        return view instanceof ag;
    }

    private void a(NativeStrandAsset nativeStrandAsset, Map<String, String> map) {
        Map hashMap = new HashMap();
        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "inlban");
        hashMap.put("impId", this.d);
        com.inmobi.commons.core.c.a.a().a("ads", "AdRendered", hashMap);
        Logger.a(InternalLogLevel.INTERNAL, a, "Impression record requested");
        nativeStrandAsset.a(a.d, (Map) map);
        if (this.j != null) {
            this.j.a();
        }
    }

    final void c(View view) {
        if (!this.h && !b()) {
            this.h = true;
            a(this.c.a(), b(this.c.a()));
            d();
            for (NativeStrandAsset nativeStrandAsset : this.g) {
                e(nativeStrandAsset, b(nativeStrandAsset));
            }
            this.g.clear();
        }
    }

    private void b(NativeStrandAsset nativeStrandAsset, Map<String, String> map) {
        com.inmobi.commons.core.c.a.a().a("ads", "ReportClick", new HashMap());
        Logger.a(InternalLogLevel.INTERNAL, a, "Click impression record requested");
        nativeStrandAsset.a(a.f, (Map) map);
        if (this.j != null) {
            this.j.b();
        }
    }

    private void c(NativeStrandAsset nativeStrandAsset, Map<String, String> map) {
        nativeStrandAsset.a(a.b, (Map) map);
    }

    private void d(NativeStrandAsset nativeStrandAsset, Map<String, String> map) {
        nativeStrandAsset.a(a.c, (Map) map);
    }

    private Map<String, String> b(NativeStrandAsset nativeStrandAsset) {
        Map<String, String> hashMap = new HashMap(3);
        hashMap.put("$LTS", String.valueOf(this.c.a().m()));
        v a = this.c.a(nativeStrandAsset);
        long currentTimeMillis = System.currentTimeMillis();
        if (!(a == null || 0 == a.m())) {
            currentTimeMillis = a.m();
        }
        hashMap.put("$STS", String.valueOf(currentTimeMillis));
        hashMap.put("$TS", String.valueOf(System.currentTimeMillis()));
        return hashMap;
    }

    private void e(NativeStrandAsset nativeStrandAsset, Map<String, String> map) {
        if (nativeStrandAsset == null) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Cannot report page view impression on null page container! Ignoring ...");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SocializeConstants.WEIBO_ID, nativeStrandAsset.f());
            jSONObject.put("asset", nativeStrandAsset.e());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Map hashMap = new HashMap();
        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "inlban");
        hashMap.put("impId", this.d);
        hashMap.put("pageJson", jSONObject);
        com.inmobi.commons.core.c.a.a().a("ads", "PageRendered", hashMap);
        Logger.a(InternalLogLevel.INTERNAL, a, "Page-view impression record request");
        nativeStrandAsset.a(a.e, (Map) map);
    }

    private void f(NativeStrandAsset nativeStrandAsset, Map<String, String> map) {
        if (nativeStrandAsset.g()) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Asset interaction requested");
            AssetInteractionMode h = nativeStrandAsset.h();
            if (AssetInteractionMode.ASSET_INTERACTION_MODE_NO_ACTION != h) {
                String i = nativeStrandAsset.i();
                if (a(i)) {
                    i = c.a(i, (Map) map);
                    if (AssetInteractionMode.ASSET_INTERACTION_MODE_IN_APP == h) {
                        if (!i.startsWith(HttpConstant.HTTP) || i.contains("play.google.com") || i.contains("market.android.com") || i.contains("market%3A%2F%2F")) {
                            a(i, null);
                            return;
                        } else {
                            b(i);
                            return;
                        }
                    } else if (AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER == h) {
                        a(i, null);
                        return;
                    } else {
                        a(i, nativeStrandAsset.j());
                        return;
                    }
                }
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Invalid url:").append(i).toString());
            }
        }
    }

    private boolean a(String str) {
        return (str == null || str.length() == 0) ? false : true;
    }

    private void b(String str) {
        InMobiAdActivity.c(null);
        Intent intent = new Intent(this.b, InMobiAdActivity.class);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", R.styleable.AppCompatTheme_buttonStyle);
        if (!(this.b instanceof Activity)) {
            intent.setFlags(268435456);
        }
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL", str);
        this.b.startActivity(intent);
    }

    private void a(String str, String str2) {
        while (true) {
            try {
                Intent parseUri = Intent.parseUri(str, 0);
                parseUri.setFlags(268435456);
                this.b.startActivity(parseUri);
                return;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (Throwable e2) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Deep linking failed for url:").append(str).toString(), e2);
                if (a(str2)) {
                    str = str2;
                    str2 = null;
                }
            }
            str = str2;
            str2 = null;
        }
    }
}
