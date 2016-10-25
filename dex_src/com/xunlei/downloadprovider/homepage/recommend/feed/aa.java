package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: FeedItemBottomSpHelper.java
public final class aa {
    static SharedPreferences a;
    private static aa b;

    private aa() {
        a = BrothersApplication.a().getSharedPreferences("feed_movie_click_nice", 0);
    }

    public static aa a() {
        if (b == null) {
            b = new aa();
        }
        return b;
    }

    private static String g(String str) {
        return new StringBuilder("click_nice_state_").append(str).toString();
    }

    private static String h(String str) {
        return new StringBuilder("click_nice_count_").append(str).toString();
    }

    private static String i(String str) {
        return new StringBuilder("comment_count_").append(str).toString();
    }

    private static String j(String str) {
        return new StringBuilder("hot_comment_thumb_num_").append(str).toString();
    }

    public static void a(String str, int i) {
        if (str != null && a != null) {
            Editor edit = a.edit();
            edit.putBoolean(g(str), true);
            edit.putInt(h(str), i);
            edit.apply();
        }
    }

    public static boolean a(String str) {
        return (str == null || a == null || !a.getBoolean(g(str), false)) ? false : true;
    }

    public static int b(String str) {
        return (str == null || a == null) ? 0 : a.getInt(h(str), 0);
    }

    public static void b(String str, int i) {
        if (str != null && a != null) {
            Editor edit = a.edit();
            edit.putInt(i(str), i);
            edit.apply();
        }
    }

    public static int c(String str) {
        return (str == null || a == null) ? 0 : a.getInt(i(str), 0);
    }

    public static boolean d(String str) {
        return a != null && a.contains(i(str));
    }

    public static void a(String str, long j) {
        if (str != null && a != null) {
            Editor edit = a.edit();
            edit.putLong(j(str), j);
            edit.apply();
        }
    }

    public static long e(String str) {
        return (str == null || a == null) ? 0 : a.getLong(j(str), 0);
    }

    public static boolean f(String str) {
        return a != null && a.contains(j(str));
    }
}
