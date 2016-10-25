package com.xunlei.XLStat.g;

import android.content.Context;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import org.android.spdy.TnetStatusCode;

public class c {
    private static String c;
    d a;
    c b;
    private HashMap<String, b> d;
    private HashMap<Integer, Integer> e;
    private HashMap<String, Integer> f;
    private a g;
    private HashMap<String, Integer> h;
    private String i;

    public class a {
        int a;
    }

    public static class b {
        public int a;
        public int b;
    }

    public class c {
    }

    public class d {
        String a;
        int b;
    }

    static {
        c = "XMLHelper";
    }

    public c(String str) {
        this.d = new HashMap();
        this.e = new HashMap();
        this.f = new HashMap();
        this.a = new d();
        this.b = new c();
        this.g = new a();
        this.h = new HashMap();
        this.i = str;
    }

    public int a(String str) {
        b bVar = null;
        if (this.d != null && this.d.size() > 0) {
            bVar = (b) this.d.get(str);
        }
        return bVar == null ? -1 : bVar.a;
    }

    public int b(String str) {
        b bVar = null;
        if (this.d != null && this.d.size() > 0) {
            bVar = (b) this.d.get(str);
        }
        return bVar == null ? TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL : bVar.b;
    }

    public int a(int i) {
        if (this.e == null || this.e.size() <= 0) {
            return -2;
        }
        Integer num = (Integer) this.e.get(Integer.valueOf(i));
        return num == null ? -2 : num.intValue();
    }

    public int c(String str) {
        if (this.f != null && this.f.size() > 0) {
            Integer num = (Integer) this.f.get(str);
            if (num != null) {
                return num.intValue();
            }
        }
        return -1;
    }

    public int a() {
        return this.g.a;
    }

    public String b() {
        return this.a.a;
    }

    public int c() {
        return this.a.b;
    }

    public boolean a(Context context) {
        if (this.i == null || BuildConfig.VERSION_NAME.equals(this.i) || this.d == null || this.f == null || this.g == null || this.a == null || this.b == null) {
            return false;
        }
        XLStatLog.d(c, "analysisXML", "parse begin ... ");
        boolean a = a.a(this.i, this.d, this.e, this.f, this.g, this.a, this.b, context);
        XLStatLog.d(c, "analysisXML", "parse end");
        XLStatLog.d(c, "analysisXML", new StringBuilder("eventmap: ").append(this.d).append(", priorityMap: ").append(this.e).append(", contextMap: ").append(this.f).append(", contextPriority: ").append(this.g).toString());
        return a;
    }
}
