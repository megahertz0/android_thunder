package com.xunlei.XLStat.f;

import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.g.c;

public class a {
    private static String a;
    private c b;
    private int c;
    private int d;

    static {
        a = "XLStatContextManager";
    }

    public a(c cVar) {
        this.c = 0;
        this.d = 0;
        this.b = cVar;
        this.c = 0;
        this.d = 0;
    }

    public boolean a(com.xunlei.XLStat.c cVar, String str, String str2, String str3, int i) {
        XLStatLog.d(a, "startContext", new StringBuilder("storeHelper: ").append(cVar).append("  context name: ").append(str).append("  srcContextName: ").append(str2).append("  extData: ").append(str3).append("  process id: ").append(i).toString());
        int c = this.b.c(str);
        if (c < 0) {
            XLStatLog.d(a, "startContext", new StringBuilder("illege id: ").append(c).toString());
            return false;
        }
        int a = a(str2);
        XLStatLog.d(a, "startContext", new StringBuilder("context id: ").append(c).append("  src context id: ").append(a).toString());
        cVar.a(new b(i, a, c, 0, str3, this.b.a()));
        a(c, a);
        return true;
    }

    private void a(int i, int i2) {
        if (i >= 0) {
            this.d = i;
        } else if (i2 >= 0) {
            this.d = i2;
        } else {
            this.d = this.c;
        }
    }

    private int a(String str) {
        int c = this.b.c(str);
        if (c < 0) {
            return this.d >= 0 ? this.d : this.c;
        } else {
            return c;
        }
    }
}
