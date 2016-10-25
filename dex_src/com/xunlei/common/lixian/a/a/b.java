package com.xunlei.common.lixian.a.a;

import com.xunlei.common.encrypt.CharsetConvert;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class b {
    private final Object a;

    public b(int i) {
        this.a = new Integer(524288);
    }

    public b(long j) {
        this.a = new Long(j);
    }

    public b(Number number) {
        this.a = number;
    }

    public b(String str) {
        this.a = str.getBytes(CharsetConvert.UTF_8);
    }

    public b(String str, String str2) {
        this.a = str.getBytes(str2);
    }

    public b(List list) {
        this.a = list;
    }

    public b(Map map) {
        this.a = map;
    }

    public b(byte[] bArr) {
        this.a = bArr;
    }

    private byte[] g() {
        try {
            return (byte[]) this.a;
        } catch (ClassCastException e) {
            throw new d(e.toString());
        }
    }

    private short h() {
        return c().shortValue();
    }

    private int i() {
        return c().intValue();
    }

    public final Object a() {
        return this.a;
    }

    public final String a(String str) {
        try {
            return new String(g(), str);
        } catch (ClassCastException e) {
            throw new d(e.toString());
        } catch (UnsupportedEncodingException e2) {
            throw new InternalError(e2.toString());
        }
    }

    public final String b() {
        return a(CharsetConvert.UTF_8);
    }

    public final Number c() {
        try {
            return (Number) this.a;
        } catch (ClassCastException e) {
            throw new d(e.toString());
        }
    }

    public final long d() {
        return c().longValue();
    }

    public final List e() {
        if (this.a instanceof ArrayList) {
            return (ArrayList) this.a;
        }
        throw new d("Excepted List<BEvalue> !");
    }

    public final Map f() {
        if (this.a instanceof HashMap) {
            return (Map) this.a;
        }
        throw new d("Expected Map<String, BEValue> !");
    }
}
