package com.tencent.mm.sdk.diffdev.a;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Looper;
import com.tencent.mm.sdk.b.d;
import com.tencent.mm.sdk.diffdev.IDiffDevOAuth;
import com.tencent.mm.sdk.diffdev.OAuthListener;
import java.util.ArrayList;
import java.util.List;

public final class a implements IDiffDevOAuth {
    private d ac;
    private List<OAuthListener> ad;
    private d ae;
    private OAuthListener af;

    public a() {
        this.ac = null;
        this.ad = new ArrayList();
        this.af = new b(this);
    }

    public final void addListener(OAuthListener oAuthListener) {
        if (!this.ad.contains(oAuthListener)) {
            this.ad.add(oAuthListener);
        }
    }

    public final boolean auth(String str, String str2, String str3, String str4, String str5, OAuthListener oAuthListener) {
        if (str == null || str.length() <= 0 || str2 == null || str2.length() <= 0) {
            String.format("auth fail, invalid argument, appId = %s, scope = %s", new Object[]{str, str2});
            return false;
        }
        if (this.ac == null) {
            this.ac = new d(Looper.getMainLooper());
        }
        addListener(oAuthListener);
        if (this.ae != null) {
            return true;
        }
        this.ae = new d(str, str2, str3, str4, str5, this.af);
        d dVar = this.ae;
        if (VERSION.SDK_INT >= 11) {
            dVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            dVar.execute(new Void[0]);
        }
        return true;
    }

    public final void detach() {
        this.ad.clear();
        stopAuth();
    }

    public final void removeAllListeners() {
        this.ad.clear();
    }

    public final void removeListener(OAuthListener oAuthListener) {
        this.ad.remove(oAuthListener);
    }

    public final boolean stopAuth() {
        boolean q;
        try {
            q = this.ae == null ? true : this.ae.q();
        } catch (Exception e) {
            new StringBuilder("stopAuth fail, ex = ").append(e.getMessage());
            q = false;
        }
        this.ae = null;
        return q;
    }
}
