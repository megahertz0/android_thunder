package com.inmobi.ads;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Handler;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.configs.b.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.accs.common.Constants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

// compiled from: AdUnitCache.java
public final class i implements b {
    private static final String a;
    private static final Object b;
    private static volatile i c;
    private static ConcurrentHashMap<ak, AdUnit> d;
    private static b e;

    // compiled from: AdUnitCache.java
    private class a implements a {
        private ak b;

        a(ak akVar) {
            this.b = akVar;
        }

        public void a() {
            Logger.a(InternalLogLevel.INTERNAL, a, "onAdLoadSucceeded called");
        }

        public void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("onAdLoadFailed called. Status:").append(inMobiAdRequestStatus.getMessage()).toString());
            d.remove(this.b);
            if (inMobiAdRequestStatus.getStatusCode() == StatusCode.NO_FILL) {
                Map hashMap = new HashMap();
                hashMap.put(JsInterface.FUNPLAY_AD_TRPE, this.b.e());
                com.inmobi.commons.core.c.a.a().a("ads", "PreLoadServerNoFill", hashMap);
            }
        }

        public void b() {
        }

        public void c() {
        }

        public void a(Map<Object, Object> map) {
        }

        public void d() {
        }

        public void b(Map<Object, Object> map) {
        }
    }

    static {
        a = i.class.getSimpleName();
        b = new Object();
    }

    public static i a() {
        i iVar = c;
        if (iVar == null) {
            synchronized (b) {
                iVar = c;
                if (iVar == null) {
                    iVar = new i();
                    c = iVar;
                }
            }
        }
        return iVar;
    }

    private i() {
        d = new ConcurrentHashMap(8, 0.9f, 3);
        e = new b();
        com.inmobi.commons.core.configs.b.a().a(e, (b) this);
        f();
        com.inmobi.commons.core.c.a.a().a(e.a(), e.m());
    }

    @TargetApi(14)
    private void f() {
        Application application = (Application) com.inmobi.commons.a.a.b();
        if (VERSION.SDK_INT >= 14) {
            application.registerComponentCallbacks(new ComponentCallbacks2() {
                public void onTrimMemory(int i) {
                    if (i == 80 || i == 15) {
                        i.this.g();
                    }
                }

                public void onConfigurationChanged(Configuration configuration) {
                }

                public void onLowMemory() {
                }
            });
        } else {
            application.registerComponentCallbacks(new ComponentCallbacks() {
                public void onConfigurationChanged(Configuration configuration) {
                }

                public void onLowMemory() {
                    i.this.g();
                }
            });
        }
    }

    private void g() {
        new Handler(com.inmobi.commons.a.a.b().getMainLooper()).post(new Runnable() {
            public void run() {
                Logger.a(InternalLogLevel.INTERNAL, a, "Flushing ad unit cache due to low memory.");
                Iterator it = d.entrySet().iterator();
                while (it.hasNext()) {
                    ((AdUnit) ((Entry) it.next()).getValue()).t();
                    it.remove();
                }
            }
        });
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        e = (b) aVar;
        com.inmobi.commons.core.c.a.a().a(e.a(), e.m());
    }

    public final void b() {
        h();
        i();
        j();
    }

    public final void c() {
        h();
        i();
    }

    final AdUnit a(ak akVar) {
        if (e.n().b()) {
            c(akVar);
            AdUnit adUnit = (AdUnit) d.get(akVar);
            if (adUnit == null) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("No cached ad unit found for pid:").append(akVar.c()).append(" tp:").append(akVar.d()).toString());
                a("Other", akVar.e());
                return null;
            } else if (a(adUnit)) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Expired cached ad unit found for pid:").append(akVar.c()).append(" tp:").append(akVar.d()).toString());
                adUnit.t();
                d.remove(akVar);
                a("AdUnitExpired", akVar.e());
                return null;
            } else {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Cached ad unit found for pid:").append(akVar.c()).append(" tp:").append(akVar.d()).toString());
                d.remove(akVar);
                a(akVar.e());
                return adUnit;
            }
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("No cached ad unit found as config is disabled. pid:").append(akVar.c()).append(" tp:").append(akVar.d()).toString());
        return null;
    }

    private void a(String str) {
        Map hashMap = new HashMap();
        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, str);
        com.inmobi.commons.core.c.a.a().a("ads", "PreLoadCacheHit", hashMap);
    }

    private void a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put(Constants.KEY_ERROR_CODE, str);
        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, str2);
        com.inmobi.commons.core.c.a.a().a("ads", "PreLoadCacheMiss", hashMap);
    }

    private void c(ak akVar) {
        List arrayList = new ArrayList();
        arrayList.add(akVar);
        int a = al.a().a(arrayList, e.n().c());
        if (a > 0) {
            Map hashMap = new HashMap();
            hashMap.put(ParamKey.COUNT, Integer.valueOf(a));
            com.inmobi.commons.core.c.a.a().a("ads", "PreLoadPidOverflow", hashMap);
        }
    }

    private void h() {
        if (e.n().b()) {
            int a = al.a().a(e.n().a());
            if (a > 0) {
                Map hashMap = new HashMap();
                hashMap.put(ParamKey.COUNT, Integer.valueOf(a));
                com.inmobi.commons.core.c.a.a().a("ads", "PreLoadPidExpiry", hashMap);
            }
        }
    }

    private void i() {
        Iterator it = d.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            AdUnit adUnit = (AdUnit) entry.getValue();
            if (a(adUnit)) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("cleanUpExpiredCachedAdUnits. pid:").append(((ak) entry.getKey()).c()).append(" tp:").append(((ak) entry.getKey()).d()).toString());
                adUnit.t();
                it.remove();
            }
        }
    }

    private boolean a(AdUnit adUnit) {
        if (adUnit.g() != AdState.STATE_LOADING) {
            return System.currentTimeMillis() - adUnit.i() > e.a(adUnit.a()).e() * 1000;
        } else {
            Logger.a(InternalLogLevel.INTERNAL, a, "hasAdUnitExpired. Ad unit in loading state.");
            return false;
        }
    }

    private void j() {
        if (e.n().b()) {
            ArrayList arrayList = (ArrayList) l();
            for (int i = 0; i < arrayList.size(); i++) {
                b((ak) arrayList.get(i));
            }
        }
    }

    public final void b(ak akVar) {
        k();
        if (e.n().b() && !d.containsKey(akVar)) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("preLoadAdUnit. pid:").append(akVar.c()).append(" tp:").append(akVar.d()).toString());
            if (akVar.a() == null && akVar.d() != null) {
                Map hashMap = new HashMap();
                hashMap.put(IXAdRequestInfo.PHONE_TYPE, akVar.d());
                akVar.a(hashMap);
            }
            o oVar = new o(com.inmobi.commons.a.a.b(), akVar.c(), new a(akVar));
            oVar.a(akVar.b());
            oVar.a(akVar.a());
            oVar.a(true);
            d.put(akVar, oVar);
            oVar.o();
            Map hashMap2 = new HashMap();
            hashMap2.put(JsInterface.FUNPLAY_AD_TRPE, oVar.a());
            com.inmobi.commons.core.c.a.a().a("ads", "AdPreLoadRequested", hashMap2);
        }
    }

    private void k() {
        if (d.size() >= e.n().c()) {
            ArrayList arrayList = (ArrayList) al.a().b();
            Iterator it = d.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (!arrayList.contains(entry.getKey())) {
                    ((AdUnit) entry.getValue()).t();
                    it.remove();
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Removing extra ad unit from ad unit cache. Pid:").append(((ak) entry.getKey()).c()).append(" tp:").append(((ak) entry.getKey()).d()).toString());
                }
            }
        }
    }

    public static String a(Map<String, String> map) {
        return map == null ? null : (String) map.get(IXAdRequestInfo.PHONE_TYPE);
    }

    private List<ak> l() {
        return al.a().b();
    }
}
