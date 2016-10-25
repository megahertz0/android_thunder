package com.baidu.mobads;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdSettings {
    private static HashSet<String> a;
    private static JSONArray b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static String j;
    private static HashSet<String> k;
    private static JSONArray l;
    private static JSONObject m;

    public enum a {
        PRIMARY(0),
        JUNIOR(1),
        SENIOR(2),
        SPECIALTY(3),
        BACHELOR(4),
        MASTER(5),
        DOCTOR(6);
        private int h;

        static {
            a = new com.baidu.mobads.AdSettings.a("PRIMARY", 0, 0);
            b = new com.baidu.mobads.AdSettings.a("JUNIOR", 1, 1);
            c = new com.baidu.mobads.AdSettings.a("SENIOR", 2, 2);
            d = new com.baidu.mobads.AdSettings.a("SPECIALTY", 3, 3);
            e = new com.baidu.mobads.AdSettings.a("BACHELOR", 4, 4);
            f = new com.baidu.mobads.AdSettings.a("MASTER", 5, 5);
            g = new com.baidu.mobads.AdSettings.a("DOCTOR", 6, 6);
            i = new com.baidu.mobads.AdSettings.a[]{a, b, c, d, e, f, g};
        }

        private a(int i) {
            this.h = i;
        }

        public final int a() {
            return this.h;
        }
    }

    public enum b {
        UNKNOWN_PROTOCOL_TYPE(0),
        HTTP_PROTOCOL_TYPE(1),
        HTTPS_PROTOCOL_TYPE(2);
        private int d;

        static {
            a = new com.baidu.mobads.AdSettings.b("UNKNOWN_PROTOCOL_TYPE", 0, 0);
            b = new com.baidu.mobads.AdSettings.b("HTTP_PROTOCOL_TYPE", 1, 1);
            c = new com.baidu.mobads.AdSettings.b("HTTPS_PROTOCOL_TYPE", 2, 2);
            e = new com.baidu.mobads.AdSettings.b[]{a, b, c};
        }

        private b(int i) {
            this.d = i;
        }

        public final String a() {
            return this.d;
        }
    }

    public enum c {
        F0T1k(0),
        F1kT2k(1),
        F2kT3k(2),
        F3kT4k(3),
        F4kT5k(4),
        F5kT6k(5),
        F6kT7k(6),
        F7kT8k(7),
        F8kT9k(8),
        F9kT10k(9),
        F10kT15k(10),
        F15kT20k(11),
        F20(12);
        private int n;

        static {
            a = new com.baidu.mobads.AdSettings.c("F0T1k", 0, 0);
            b = new com.baidu.mobads.AdSettings.c("F1kT2k", 1, 1);
            c = new com.baidu.mobads.AdSettings.c("F2kT3k", 2, 2);
            d = new com.baidu.mobads.AdSettings.c("F3kT4k", 3, 3);
            e = new com.baidu.mobads.AdSettings.c("F4kT5k", 4, 4);
            f = new com.baidu.mobads.AdSettings.c("F5kT6k", 5, 5);
            g = new com.baidu.mobads.AdSettings.c("F6kT7k", 6, 6);
            h = new com.baidu.mobads.AdSettings.c("F7kT8k", 7, 7);
            i = new com.baidu.mobads.AdSettings.c("F8kT9k", 8, 8);
            j = new com.baidu.mobads.AdSettings.c("F9kT10k", 9, 9);
            k = new com.baidu.mobads.AdSettings.c("F10kT15k", 10, 10);
            l = new com.baidu.mobads.AdSettings.c("F15kT20k", 11, 11);
            m = new com.baidu.mobads.AdSettings.c("F20", 12, 12);
            o = new com.baidu.mobads.AdSettings.c[]{a, b, c, d, e, f, g, h, i, j, k, l, m};
        }

        private c(int i) {
            this.n = i;
        }

        public final int a() {
            return this.n;
        }
    }

    public enum d {
        MALE(0),
        FEMALE(1);
        private int c;

        static {
            a = new com.baidu.mobads.AdSettings.d("MALE", 0, 0);
            b = new com.baidu.mobads.AdSettings.d("FEMALE", 1, 1);
            d = new com.baidu.mobads.AdSettings.d[]{a, b};
        }

        private d(int i) {
            this.c = i;
        }

        public final int a() {
            return this.c;
        }
    }

    public static void setSupportHttps(boolean z) {
        if (z) {
            j = b.c.a();
        } else {
            j = b.b.a();
        }
    }

    public static String getSupportHttps() {
        return j;
    }

    static {
        a = new HashSet();
        b = new JSONArray();
        j = b.a.a();
        k = new HashSet();
        l = new JSONArray();
        m = new JSONObject();
    }

    public static JSONObject getAttr() {
        JSONObject jSONObject = new JSONObject();
        Iterator it = a.iterator();
        b = new JSONArray();
        while (it.hasNext()) {
            b.put(it.next());
        }
        it = k.iterator();
        l = new JSONArray();
        while (it.hasNext()) {
            l.put(it.next());
        }
        try {
            jSONObject.putOpt("KEY", b);
            jSONObject.putOpt("SEX", c);
            jSONObject.putOpt("BIR", d);
            jSONObject.putOpt("CITY", e);
            jSONObject.putOpt("ZIP", f);
            jSONObject.putOpt("JOB", g);
            jSONObject.putOpt("EDU", h);
            jSONObject.putOpt("SAL", i);
            jSONObject.putOpt("HOB", l);
            jSONObject.putOpt("R", m);
            jSONObject.putOpt("RPT", j);
        } catch (Exception e) {
        }
        return jSONObject;
    }

    public static void setKey(String[] strArr) {
        for (Object obj : strArr) {
            a.add(obj);
        }
    }

    public static void setKey(List<String> list) {
        a.addAll(list);
    }

    public static void setSex(d dVar) {
        if (dVar != null) {
            c = dVar.a();
        }
    }

    public static void setBirthday(Calendar calendar) {
        if (calendar != null) {
            int i = calendar.get(1);
            int i2 = calendar.get(XZBDevice.DOWNLOAD_LIST_RECYCLE) + 1;
            int i3 = calendar.get(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            d = String.valueOf(i);
            if (i2 <= 0 || i2 >= 10) {
                d += i2;
            } else {
                d += MessageService.MSG_DB_READY_REPORT + i2;
            }
            if (i3 <= 0 || i3 >= 10) {
                d += i3;
            } else {
                d += MessageService.MSG_DB_READY_REPORT + i3;
            }
        }
    }

    public static void setCity(String str) {
        e = str;
    }

    public static void setZip(String str) {
        f = str;
    }

    public static void setJob(String str) {
        g = str;
    }

    public static void setEducation(a aVar) {
        if (aVar != null) {
            h = aVar.a();
        }
    }

    public static void setSalary(c cVar) {
        if (cVar != null) {
            i = cVar.a();
        }
    }

    public static void setHob(String[] strArr) {
        for (Object obj : strArr) {
            k.add(obj);
        }
    }

    public static void setHob(List<String> list) {
        k.addAll(list);
    }

    public static void setUserAttr(String str, String str2) {
        try {
            m.put(str, str2);
        } catch (JSONException e) {
        }
    }
}
