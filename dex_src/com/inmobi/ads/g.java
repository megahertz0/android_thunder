package com.inmobi.ads;

import android.os.SystemClock;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.accs.common.Constants;
import com.xunlei.download.DownloadManager;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: AdStore.java
final class g implements com.inmobi.ads.d.a {
    private static final String a;
    private h b;
    private a c;
    private c d;
    private boolean e;
    private long f;

    // compiled from: AdStore.java
    public static interface a {
        void a(InMobiAdRequestStatus inMobiAdRequestStatus);

        void a(a aVar);
    }

    static {
        a = g.class.getSimpleName();
    }

    public g(h hVar, a aVar) {
        this.e = false;
        this.f = 0;
        this.b = hVar;
        this.c = aVar;
        this.d = c.a();
    }

    public final void a() {
        this.e = false;
        this.d.a(this.b.h(), this.b.f().e());
        int a = this.d.a(this.b.c(), this.b.k());
        if (a == 0) {
            a(this.b);
        } else if (a <= this.b.f().d()) {
            a(this.b.c(), this.b.k());
            a(this.b);
        } else {
            a(this.b.c(), this.b.k());
        }
    }

    private void a(long j, String str) {
        this.e = true;
        this.c.a(this.d.b(j, str));
    }

    private void a(h hVar) {
        e eVar = new e(hVar.a(), hVar.c(), hVar.g());
        eVar.d(hVar.d());
        eVar.a(hVar.e());
        eVar.a(hVar.h());
        eVar.b(hVar.i());
        eVar.a(hVar.f().c());
        eVar.b(hVar.j());
        eVar.b(hVar.i());
        eVar.c(hVar.k());
        eVar.b(hVar.b() * 1000);
        eVar.c(hVar.b() * 1000);
        this.f = SystemClock.elapsedRealtime();
        new d(eVar, this).a();
    }

    public final void a(f fVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        List c = c(fVar);
        if (c == null) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Could not parse ad response:").append(fVar.c()).toString());
            if (!this.e) {
                this.c.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
            }
        } else if (c.size() == 0) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Ad response received but no ad available:").append(fVar.c()).toString());
            Map hashMap = new HashMap();
            hashMap.put(JsInterface.FUNPLAY_AD_TRPE, this.b.h());
            hashMap.put("loadLatency", Long.valueOf(elapsedRealtime - this.f));
            com.inmobi.commons.core.c.a.a().a("ads", "ServerNoFill", hashMap);
            if (!this.e) {
                this.c.a(new InMobiAdRequestStatus(StatusCode.NO_FILL));
            }
        } else {
            Map hashMap2 = new HashMap();
            hashMap2.put(JsInterface.FUNPLAY_AD_TRPE, this.b.h());
            hashMap2.put(ParamKey.COUNT, Integer.valueOf(c.size()));
            hashMap2.put("loadLatency", Long.valueOf(elapsedRealtime - this.f));
            com.inmobi.commons.core.c.a.a().a("ads", "ServerFill", hashMap2);
            if (!this.e) {
                this.c.a((a) c.get(0));
                c.remove(0);
            }
            this.d.a(c, this.b.f().b(), this.b.h());
        }
    }

    private List<a> c(f fVar) {
        List<a> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(fVar.c());
            String trim = jSONObject.getString("requestId").trim();
            JSONArray jSONArray = jSONObject.getJSONArray("ads");
            if (jSONArray == null) {
                return arrayList;
            }
            int min = Math.min(fVar.b().d(), jSONArray.length());
            for (int i = 0; i < min; i++) {
                arrayList.add(new a(fVar, jSONArray.getJSONObject(i).toString(), trim + "_" + i));
            }
            return arrayList;
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while parsing ad response.", e);
            Map hashMap = new HashMap();
            hashMap.put(JsInterface.FUNPLAY_AD_TRPE, this.b.h());
            hashMap.put(Constants.KEY_ERROR_CODE, "ParsingError");
            hashMap.put(DownloadManager.COLUMN_REASON, e.getLocalizedMessage());
            com.inmobi.commons.core.c.a.a().a("ads", "ServerError", hashMap);
            return null;
        }
    }

    public final void b(f fVar) {
        if (!this.e) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Map hashMap = new HashMap();
            hashMap.put(JsInterface.FUNPLAY_AD_TRPE, this.b.h());
            hashMap.put(Constants.KEY_ERROR_CODE, String.valueOf(fVar.d().a().getValue()));
            hashMap.put(DownloadManager.COLUMN_REASON, fVar.d().b());
            hashMap.put("loadLatency", Long.valueOf(elapsedRealtime - this.f));
            com.inmobi.commons.core.c.a.a().a("ads", "ServerError", hashMap);
            this.c.a(fVar.a());
        }
    }
}
