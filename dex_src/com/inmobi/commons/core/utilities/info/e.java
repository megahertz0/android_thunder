package com.inmobi.commons.core.utilities.info;

import android.location.Location;
import android.support.v4.widget.ExploreByTouchHelper;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.b.c;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.HashMap;
import java.util.Locale;

// compiled from: PublisherProvidedUserInfoDao.java
public class e {
    private static int a;
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static int h;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static int m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static String r;
    private static Location s;

    static {
        a = Integer.MIN_VALUE;
        h = Integer.MIN_VALUE;
        m = Integer.MIN_VALUE;
    }

    public static String a() {
        return c.a("user_info_store");
    }

    public static void b() {
        a(a);
        a(b);
        b(c);
        c(d);
        d(e);
        e(f);
        f(g);
        b(h);
        g(i);
        h(j);
        i(k);
        j(l);
        c(m);
        k(n);
        l(o);
        m(p);
        n(q);
        e(r);
        a(s);
    }

    public static void a(int i) {
        if (!a.a() || i == Integer.MIN_VALUE) {
            a = i;
        } else {
            c.b("user_info_store").a("user_age", i);
        }
    }

    private static int h() {
        return a != Integer.MIN_VALUE ? a : c.b("user_info_store").b("user_age", (int) ExploreByTouchHelper.INVALID_ID);
    }

    public static void a(String str) {
        if (!a.a() || str == null) {
            b = str;
        } else {
            c.b("user_info_store").a("user_age_group", str);
        }
    }

    private static String i() {
        return b != null ? b : c.b("user_info_store").b("user_age_group", null);
    }

    public static void b(String str) {
        if (!a.a() || str == null) {
            c = str;
        } else {
            c.b("user_info_store").a("user_area_code", str);
        }
    }

    private static String j() {
        return c != null ? c : c.b("user_info_store").b("user_area_code", null);
    }

    public static void c(String str) {
        if (!a.a() || str == null) {
            d = str;
        } else {
            c.b("user_info_store").a("user_post_code", str);
        }
    }

    public static String c() {
        return d != null ? d : c.b("user_info_store").b("user_post_code", null);
    }

    public static void d(String str) {
        if (!a.a() || str == null) {
            e = str;
        } else {
            c.b("user_info_store").a("user_city_code", str);
        }
    }

    private static String k() {
        return e != null ? e : c.b("user_info_store").b("user_city_code", null);
    }

    private static String l() {
        return f != null ? f : c.b("user_info_store").b("user_state_code", null);
    }

    public static void e(String str) {
        if (!a.a() || str == null) {
            f = str;
        } else {
            c.b("user_info_store").a("user_state_code", str);
        }
    }

    public static void f(String str) {
        if (!a.a() || str == null) {
            g = str;
        } else {
            c.b("user_info_store").a("user_country_code", str);
        }
    }

    private static String m() {
        return g != null ? g : c.b("user_info_store").b("user_country_code", null);
    }

    public static void b(int i) {
        if (!a.a() || i == Integer.MIN_VALUE) {
            h = i;
        } else {
            c.b("user_info_store").a("user_yob", i);
        }
    }

    private static int n() {
        return h != Integer.MIN_VALUE ? h : c.b("user_info_store").b("user_yob", (int) ExploreByTouchHelper.INVALID_ID);
    }

    public static void g(String str) {
        if (!a.a() || str == null) {
            i = str;
        } else {
            c.b("user_info_store").a("user_gender", str);
        }
    }

    private static String o() {
        return i != null ? i : c.b("user_info_store").b("user_gender", null);
    }

    public static void h(String str) {
        if (!a.a() || str == null) {
            j = str;
        } else {
            c.b("user_info_store").a("user_ethnicity", str);
        }
    }

    private static String p() {
        return j != null ? j : c.b("user_info_store").b("user_ethnicity", null);
    }

    public static void i(String str) {
        if (!a.a() || str == null) {
            k = str;
        } else {
            c.b("user_info_store").a("user_education", str);
        }
    }

    private static String q() {
        return k != null ? k : c.b("user_info_store").b("user_education", null);
    }

    private static String r() {
        return l != null ? l : c.b("user_info_store").b("user_language", null);
    }

    public static void j(String str) {
        if (!a.a() || str == null) {
            l = str;
        } else {
            c.b("user_info_store").a("user_language", str);
        }
    }

    public static void c(int i) {
        if (!a.a() || i == Integer.MIN_VALUE) {
            m = i;
        } else {
            c.b("user_info_store").a("user_income", i);
        }
    }

    private static int s() {
        return m != Integer.MIN_VALUE ? m : c.b("user_info_store").b("user_income", (int) ExploreByTouchHelper.INVALID_ID);
    }

    public static void k(String str) {
        if (!a.a() || str == null) {
            n = str;
        } else {
            c.b("user_info_store").a("user_house_income", str);
        }
    }

    private static String t() {
        return n != null ? n : c.b("user_info_store").b("user_house_income", null);
    }

    public static void l(String str) {
        if (!a.a() || str == null) {
            o = str;
        } else {
            c.b("user_info_store").a("user_interest", str);
        }
    }

    private static String u() {
        return o != null ? o : c.b("user_info_store").b("user_interest", null);
    }

    public static void m(String str) {
        if (!a.a() || str == null) {
            p = str;
        } else {
            c.b("user_info_store").a("user_nationality", str);
        }
    }

    private static String v() {
        return p != null ? p : c.b("user_info_store").b("user_nationality", null);
    }

    public static String d() {
        return q != null ? q : c.b("user_info_store").b("user_login_id", null);
    }

    public static void n(String str) {
        if (!a.a() || str == null) {
            q = str;
        } else {
            c.b("user_info_store").a("user_login_id", str);
        }
    }

    public static String e() {
        return r != null ? r : c.b("user_info_store").b("user_session_id", null);
    }

    public static void o(String str) {
        if (!a.a() || str == null) {
            r = str;
        } else {
            c.b("user_info_store").a("user_session_id", str);
        }
    }

    public static Location f() {
        if (s != null) {
            return s;
        }
        String b = c.b("user_info_store").b("user_location", null);
        if (b == null) {
            return null;
        }
        Location location = new Location(com.umeng.a.d);
        try {
            String[] split = b.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            location.setLatitude(Double.parseDouble(split[0]));
            location.setLongitude(Double.parseDouble(split[1]));
            location.setAccuracy(Float.parseFloat(split[2]));
            location.setTime(Long.parseLong(split[3]));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            location = null;
        } catch (ArrayIndexOutOfBoundsException e2) {
            e2.printStackTrace();
            location = null;
        }
        return location;
    }

    public static void a(Location location) {
        if (!a.a() || location == null) {
            s = location;
            return;
        }
        c.b("user_info_store").a("user_location", b(location));
    }

    public static HashMap<String, String> g() {
        HashMap<String, String> hashMap = new HashMap();
        int h = h();
        if (h != Integer.MIN_VALUE && h > 0) {
            hashMap.put("u-age", String.valueOf(h));
        }
        h = n();
        if (h != Integer.MIN_VALUE && h > 0) {
            hashMap.put("u-yearofbirth", String.valueOf(h));
        }
        h = s();
        if (h != Integer.MIN_VALUE && h > 0) {
            hashMap.put("u-income", String.valueOf(h));
        }
        String a = a(k(), l(), m());
        if (!(a == null || a.trim().length() == 0)) {
            hashMap.put("u-location", a);
        }
        a = i();
        if (a != null) {
            hashMap.put("u-agegroup", a.toString().toLowerCase(Locale.ENGLISH));
        }
        a = j();
        if (a != null) {
            hashMap.put("u-areacode", a);
        }
        a = c();
        if (a != null) {
            hashMap.put("u-postalcode", a);
        }
        a = o();
        if (a != null) {
            hashMap.put("u-gender", a);
        }
        a = p();
        if (a != null) {
            hashMap.put("u-ethnicity", a);
        }
        a = q();
        if (a != null) {
            hashMap.put("u-education", a);
        }
        a = r();
        if (a != null) {
            hashMap.put("u-language", a);
        }
        a = t();
        if (a != null) {
            hashMap.put("u-householdincome", a);
        }
        a = u();
        if (a != null) {
            hashMap.put("u-interests", a);
        }
        a = v();
        if (a != null) {
            hashMap.put("u-nationality", a);
        }
        return hashMap;
    }

    private static String a(String str, String str2, String str3) {
        String str4 = com.umeng.a.d;
        if (!(str == null || str.trim().length() == 0)) {
            str4 = str.trim();
        }
        if (!(str2 == null || str2.trim().length() == 0)) {
            str4 = str4 + SocializeConstants.OP_DIVIDER_MINUS + str2.trim();
        }
        return (str3 == null || str3.trim().length() == 0) ? str4 : str4 + SocializeConstants.OP_DIVIDER_MINUS + str3.trim();
    }

    private static String b(Location location) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location.getLatitude());
        stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        stringBuilder.append(location.getLongitude());
        stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        stringBuilder.append((int) location.getAccuracy());
        stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        stringBuilder.append(location.getTime());
        return stringBuilder.toString();
    }
}
