package com.inmobi.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

// compiled from: NativeStrandAdUnit.java
final class s extends AdUnit {
    private static final String a;
    private static Handler f;
    private final Context b;
    private final String c;
    private boolean d;
    private q e;

    // compiled from: NativeStrandAdUnit.java
    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ q a;

        AnonymousClass_1(q qVar) {
            this.a = qVar;
        }

        public void run() {
            if (!s.this.d) {
                s.this.a(AdState.STATE_LOADED);
                s.this.e = this.a;
                if (s.this.m() != null) {
                    s.this.m().a();
                }
            }
        }
    }

    // compiled from: NativeStrandAdUnit.java
    private static class a extends Thread {
        private s a;
        private String b;

        a(s sVar, String str) {
            this.a = sVar;
            this.b = str;
        }

        public void run() {
            try {
                x xVar = new x(new JSONObject(this.a.h()));
                if (xVar.h()) {
                    Map hashMap = new HashMap();
                    hashMap.put(JsInterface.FUNPLAY_AD_TRPE, this.a.a());
                    com.inmobi.commons.core.c.a.a().a("ads", "AdLoadSuccessful", hashMap);
                    this.a.a(new q(this.a.b, xVar, this.b));
                    return;
                }
                this.a.c("DataModelValidationFailed");
                this.a.D();
            } catch (Throwable e) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to parse Native strand data model", e);
                this.a.c("InternalError");
                this.a.D();
            }
        }
    }

    static {
        a = s.class.getSimpleName();
    }

    public s(Context context, long j, Integer[] numArr, a aVar) {
        super(context, j, aVar);
        this.d = false;
        this.b = context;
        this.c = a(numArr);
        f = new Handler(Looper.getMainLooper());
    }

    protected final void q() {
    }

    public final void o() {
        if (this.d) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Ad unit is already destroyed! Returning ...");
        } else {
            super.o();
        }
    }

    public final void a(a aVar) {
        if (g() != AdState.STATE_LOADING) {
            return;
        }
        if (b(aVar)) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Ad fetch successful");
            new a(this, aVar.c()).start();
            return;
        }
        c("ParsingFailed");
        a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), true);
    }

    public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
        super.a(inMobiAdRequestStatus);
    }

    public final q x() {
        q qVar = this.e;
        this.e = null;
        return qVar;
    }

    public final void y() {
        if (!this.d) {
            super.t();
            a(AdState.STATE_CREATED);
            this.d = true;
        }
    }

    protected final String a() {
        return "inlban";
    }

    protected final String c() {
        return null;
    }

    protected final PlacementType d() {
        return PlacementType.INLINE;
    }

    protected final Map<String, String> e() {
        Map hashMap = new HashMap();
        hashMap.put("a-adPositions", this.c);
        hashMap.put("a-parentViewWidth", String.valueOf(B()));
        hashMap.put("a-productVersion", C());
        hashMap.put("trackerType", "url_ping");
        return hashMap;
    }

    protected final void t() {
        k();
    }

    final boolean z() {
        return AdState.STATE_LOADING != g();
    }

    private static String a(Integer[] numArr) {
        if (numArr.length == 0) {
            return com.umeng.a.d;
        }
        String str = com.umeng.a.d;
        int length = numArr.length;
        String str2 = str;
        for (int i = 0; i < length; i++) {
            str2 = str2 + numArr[i].intValue() + MiPushClient.ACCEPT_TIME_SEPARATOR;
        }
        return str2.substring(0, str2.length() - 1);
    }

    private int B() {
        return DisplayInfo.a().b();
    }

    private String C() {
        return "NS-1.0.0-20160411";
    }

    private void a(q qVar) {
        f.post(new AnonymousClass_1(qVar));
    }

    private void D() {
        f.post(new Runnable() {
            public void run() {
                if (!s.this.d) {
                    s.this.a(AdState.STATE_FAILED);
                    s.this.m().a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                }
            }
        });
    }
}
