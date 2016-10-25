package com.xunlei.downloadprovider.homepage;

import android.content.SharedPreferences;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: LoginHeaderViewSharedPreferenceHelper.java
public final class n {
    public SharedPreferences a;

    public n() {
        this.a = BrothersApplication.a().getSharedPreferences("login_header_view", 0);
    }
}
