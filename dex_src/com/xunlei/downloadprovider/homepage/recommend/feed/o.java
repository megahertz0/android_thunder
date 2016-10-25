package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.android.volley.p;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.c.a.e;
import java.util.ArrayList;
import java.util.List;

// compiled from: FeedDataManager.java
public final class o {
    private static o n;
    public p a;
    public SharedPreferences b;
    a c;
    public List<ao> d;
    public List<ao> e;
    public long f;
    public long g;
    public long h;
    public long i;
    public long j;
    public long k;
    public long l;
    public boolean m;
    private Context o;

    // compiled from: FeedDataManager.java
    public static interface a {
        void a(boolean z, String str);
    }

    private o() {
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = true;
        this.d = new ArrayList();
        this.e = new ArrayList();
        BrothersApplication a = BrothersApplication.a();
        this.o = a;
        this.a = com.xunlei.downloadprovider.j.a.c();
        this.b = a.getSharedPreferences("feed_movie", 0);
    }

    public static o a() {
        if (n == null) {
            n = new o();
        }
        return n;
    }

    public final void b() {
        new StringBuilder("before saveData---").append(System.currentTimeMillis());
        if (this.b != null && this.e != null) {
            String a = ao.a(this.e);
            Editor edit = this.b.edit();
            edit.putLong("refresh_t1", this.g);
            edit.putLong("refresh_t2", this.h);
            edit.putLong("nextpage_t1", this.i);
            edit.putLong("nextpage_t2", this.j);
            edit.putLong("ts", this.f);
            if (a != null) {
                edit.putString("newest_feed_video_item_list", a);
            }
            edit.commit();
            new StringBuilder("after saveData---").append(System.currentTimeMillis());
        }
    }

    public final void a(String str, String str2) {
        com.xunlei.downloadprovider.homepage.recommend.b.a.a(BrothersApplication.a).a(str, str2, b.d(), new w(this, str), new x(this));
    }

    static /* synthetic */ void a(o oVar, List list, ag agVar) {
        com.xunlei.downloadprovider.c.a aVar = new com.xunlei.downloadprovider.c.a();
        aVar.d = 20;
        for (int i = 0; i < list.size(); i++) {
            ao aoVar = (ao) list.get(i);
            aVar.a(new e(aoVar.q, String.valueOf(aoVar.a)));
            aVar.a(new q(oVar, aoVar, i, list, agVar));
        }
    }
}
