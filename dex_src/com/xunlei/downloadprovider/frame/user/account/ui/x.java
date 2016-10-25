package com.xunlei.downloadprovider.frame.user.account.ui;

import android.content.SharedPreferences.Editor;
import com.xunlei.downloadprovider.frame.user.account.l;

// compiled from: UserAccountPortraitSettingActivity.java
final class x implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ UserAccountPortraitSettingActivity e;

    x(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity, int i, String str, String str2, String str3) {
        this.e = userAccountPortraitSettingActivity;
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public final void run() {
        l a = l.a();
        int i = this.a;
        if (a.a != null) {
            Editor edit = a.a.edit();
            edit.putInt("user_account_third_tag", i);
            edit.apply();
        }
        a = l.a();
        i = this.a;
        String str = this.b;
        String str2 = this.c;
        String str3 = this.d;
        new StringBuilder("xlThirdTypeId, thirdHeadUrl = ").append(i).append(", ").append(str);
        if (!(str == null || a.a == null)) {
            Editor edit2 = a.a.edit();
            edit2.putString(l.a(i, "head_url"), str);
            edit2.apply();
        }
        a.b(i, str2);
        new StringBuilder("xlThirdTypeId, gender = ").append(i).append(", ").append(str3);
        if (str3 != null && a.a != null) {
            edit = a.a.edit();
            edit.putString(l.a(i, "gender"), str3);
            edit.apply();
        }
    }
}
