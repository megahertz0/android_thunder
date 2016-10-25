package com.xunlei.downloadprovider.homepage.choiceness.a;

import android.content.SharedPreferences;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: ChoicenessSpHelper.java
public final class m {
    private static m b;
    public SharedPreferences a;

    static {
        b = new m();
    }

    private m() {
        this.a = BrothersApplication.a().getSharedPreferences("choiceness", 0);
    }

    public static m a() {
        return b;
    }

    public final void a(long j) {
        this.a.edit().putLong(MsgConstant.KEY_TS, j).apply();
    }

    public final void b(long j) {
        this.a.edit().putLong("ChoicenessLastRefreshTime", j).apply();
    }

    public final void c(long j) {
        this.a.edit().putLong("SummaryMovieLastRefreshTime", j).apply();
    }

    public final long b() {
        return this.a.getLong("SummaryMovieLastRefreshTime", 0);
    }
}
