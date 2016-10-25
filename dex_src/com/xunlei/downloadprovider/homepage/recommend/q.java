package com.xunlei.downloadprovider.homepage.recommend;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: XLOfficialAccountRecruitHeaderView.java
public final class q {
    private static SharedPreferences a;

    static {
        a = BrothersApplication.a().getSharedPreferences("xl_official_account_recruit", 0);
    }

    public static void a(Context context, ListView listView) {
        if (d()) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_xl_official_account_recruit_header_view, null);
            inflate.setOnClickListener(new r(context, listView, inflate));
            inflate.findViewById(R.id.iv_close).setOnClickListener(new t(listView, inflate));
            listView.addHeaderView(inflate);
        }
    }

    private static int e() {
        return a.getInt("key_show_time", 0);
    }

    public static int a() {
        int e = e();
        if (e <= 10) {
            a.edit().putInt("key_show_time", e + 1).apply();
        }
        return e + 1;
    }

    public static void b() {
        a.edit().putBoolean("is_closed", true).apply();
    }

    public static void c() {
        a.edit().putBoolean("is_clicked", true).apply();
    }

    public static boolean d() {
        return (a.getBoolean("is_closed", false) || a.getBoolean("is_clicked", false) || e() >= 10) ? false : true;
    }
}
