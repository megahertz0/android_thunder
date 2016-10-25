package com.xunlei.downloadprovider.homepage.interest.a;

import android.content.SharedPreferences;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: InterestSharedPreferenceHelper.java
public final class j {
    public SharedPreferences a;

    public j() {
        this.a = BrothersApplication.a().getSharedPreferences("interest_tag", 0);
    }

    public final int a() {
        return this.a.getInt("key_show_time", 0);
    }

    public final boolean b() {
        return this.a.getBoolean("key_is_interest_tag_picked", false);
    }

    public final void c() {
        this.a.edit().putBoolean("key_is_interest_tag_picked", true).apply();
    }

    public final void a(boolean z) {
        this.a.edit().putBoolean("key_is_sync", z).apply();
    }

    public final void b(boolean z) {
        this.a.edit().putBoolean("key_is_showing", z).apply();
    }
}
