package com.xunlei.common.lixian.a;

import com.xunlei.xllib.R;
import java.util.LinkedList;
import java.util.List;

public final class f {
    private static String a = "header_version";
    private static String b = "header_seqno";
    private static String c = "header_datalen";
    private static String d = "header_thunderflag";
    private static String e = "header_compress";
    private static String f = "header_cmdid";
    private static int h;
    private List g;

    static {
        h = 1000;
    }

    public f() {
        this.g = new LinkedList();
        List list = this.g;
        list.add(new g(this, "header_version", Integer.class, 4, Integer.valueOf(R.styleable.Toolbar_titleMarginEnd)));
        int i = h;
        h = i + 1;
        list.add(new g(this, "header_seqno", Integer.class, 4, Integer.valueOf(i)));
        list.add(new g(this, "header_datalen", Integer.class, 4, Integer.valueOf(0)));
        list.add(new g(this, "header_thunderflag", Integer.class, 4, Integer.valueOf(0)));
        list.add(new g(this, "header_compress", Short.class, 2, Short.valueOf((short) 0)));
        list.add(new g(this, "header_cmdid", Short.class, 2, Short.valueOf((short) 0)));
    }

    private void a(List list) {
        list.add(new g(this, "header_version", Integer.class, 4, Integer.valueOf(R.styleable.Toolbar_titleMarginEnd)));
        int i = h;
        h = i + 1;
        list.add(new g(this, "header_seqno", Integer.class, 4, Integer.valueOf(i)));
        list.add(new g(this, "header_datalen", Integer.class, 4, Integer.valueOf(0)));
        list.add(new g(this, "header_thunderflag", Integer.class, 4, Integer.valueOf(0)));
        list.add(new g(this, "header_compress", Short.class, 2, Short.valueOf((short) 0)));
        list.add(new g(this, "header_cmdid", Short.class, 2, Short.valueOf((short) 0)));
    }

    private boolean a(String str, int i) {
        return a(str, Integer.valueOf(i));
    }

    private boolean a(String str, long j) {
        return a(str, Long.valueOf(j));
    }

    private boolean a(String str, String str2) {
        return a(str, (Object) str2);
    }

    private boolean a(String str, short s) {
        return a(str, Short.valueOf(s));
    }

    private g b(String str) {
        for (int i = 0; i < this.g.size(); i++) {
            g gVar = (g) this.g.get(i);
            if (gVar.d().equals(str)) {
                return gVar;
            }
        }
        return null;
    }

    private boolean b(String str, int i) {
        return a(str, Integer.valueOf(i));
    }

    private List c() {
        return this.g;
    }

    public final Object a(String str) {
        g b = b(str);
        return b == null ? null : b.a();
    }

    public final boolean a(String str, Object obj) {
        g b = b(str);
        return b == null ? false : b.a(obj);
    }

    public final boolean a(byte[] bArr) {
        if (bArr.length < b()) {
            return false;
        }
        b bVar = new b(bArr);
        for (int i = 0; i < this.g.size(); i++) {
            g gVar = (g) this.g.get(i);
            String simpleName = gVar.c().getSimpleName();
            if (simpleName.equalsIgnoreCase("Integer")) {
                gVar.a(Integer.valueOf(bVar.a()));
            } else if (simpleName.equalsIgnoreCase("Short")) {
                gVar.a(Short.valueOf(bVar.c()));
            } else if (simpleName.equalsIgnoreCase("Long")) {
                gVar.a(Long.valueOf(bVar.b()));
            }
        }
        return true;
    }

    public final byte[] a() {
        e eVar = new e();
        for (int i = 0; i < this.g.size(); i++) {
            g gVar = (g) this.g.get(i);
            String simpleName = gVar.c().getSimpleName();
            if (simpleName.equalsIgnoreCase("Integer")) {
                eVar.a(Integer.valueOf(gVar.a().toString()).intValue());
            } else if (simpleName.equalsIgnoreCase("Short")) {
                eVar.b(Short.valueOf(gVar.a().toString()).shortValue());
            } else if (simpleName.equalsIgnoreCase("Long")) {
                eVar.a(Long.valueOf(gVar.a().toString()).longValue());
            }
        }
        eVar.flush();
        return eVar.a();
    }

    public final int b() {
        int i = 0;
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            i += ((g) this.g.get(i2)).b();
        }
        return i;
    }
}
