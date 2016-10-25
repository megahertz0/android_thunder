package com.xunlei.downloadprovider.frame.user.account;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: UserAccountSpHelper.java
public final class l {
    private static l b;
    public SharedPreferences a;

    private l() {
        this.a = BrothersApplication.a().getSharedPreferences("user_account_sp_name", 0);
    }

    public static l a() {
        if (b == null) {
            b = new l();
        }
        return b;
    }

    public static String a(int i, String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.append('_').append(i).append('_').append(LoginHelper.a().j);
        return stringBuilder.toString();
    }

    public final void b(int i, String str) {
        new StringBuilder("xlThirdTypeId, thirdNickname = ").append(i).append(", ").append(str);
        if (str != null && this.a != null) {
            Editor edit = this.a.edit();
            edit.putString(a(i, "nickname"), str);
            edit.apply();
        }
    }

    public final String a(int i) {
        return this.a != null ? this.a.getString(a(i, "head_url"), null) : null;
    }
}
