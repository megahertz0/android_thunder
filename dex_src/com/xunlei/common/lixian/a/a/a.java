package com.xunlei.common.lixian.a.a;

import com.xunlei.common.encrypt.CharsetConvert;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class a {
    private final InputStream a;
    private int b;

    private a(InputStream inputStream) {
        this.b = 0;
        this.a = inputStream;
    }

    private int a() {
        if (this.b == 0) {
            this.b = this.a.read();
        }
        return this.b;
    }

    public static b a(InputStream inputStream) {
        return new a(inputStream).b();
    }

    private static b a(ByteBuffer byteBuffer) {
        return a(new ByteArrayInputStream(byteBuffer.array()));
    }

    private byte[] a(int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = this.a.read(bArr, i2, i - i2);
            if (read == -1) {
                throw new EOFException();
            }
            i2 += read;
        }
        return bArr;
    }

    private b b() {
        if (a() == -1) {
            return null;
        }
        if (this.b >= 48 && this.b <= 57) {
            int a = a();
            int i = a - 48;
            if (i < 0 || i > 9) {
                throw new d(new StringBuilder("Number expected, not '").append((char) a).append("'").toString());
            }
            this.b = 0;
            int g = g();
            a = g - 48;
            while (a >= 0 && a <= 9) {
                i = (i * 10) + a;
                g = g();
                a = g - 48;
            }
            if (g == 58) {
                return new b(a(i));
            }
            throw new d(new StringBuilder("Colon expected, not '").append((char) g).append("'").toString());
        } else if (this.b == 105) {
            return d();
        } else {
            if (this.b == 108) {
                return e();
            }
            if (this.b == 100) {
                return f();
            }
            throw new d(new StringBuilder("Unknown indicator '").append(this.b).append("'").toString());
        }
    }

    private b c() {
        int a = a();
        int i = a - 48;
        if (i < 0 || i > 9) {
            throw new d(new StringBuilder("Number expected, not '").append((char) a).append("'").toString());
        }
        this.b = 0;
        int g = g();
        a = g - 48;
        while (a >= 0 && a <= 9) {
            i = (i * 10) + a;
            g = g();
            a = g - 48;
        }
        if (g == 58) {
            return new b(a(i));
        }
        throw new d(new StringBuilder("Colon expected, not '").append((char) g).append("'").toString());
    }

    private b d() {
        int a = a();
        if (a != 105) {
            throw new d(new StringBuilder("Expected 'i', not '").append((char) a).append("'").toString());
        }
        this.b = 0;
        int g = g();
        if (g == 48) {
            a = g();
            if (a == 101) {
                return new b(BigInteger.ZERO);
            }
            throw new d(new StringBuilder("'e' expected after zero, not '").append((char) a).append("'").toString());
        }
        char[] cArr = new char[256];
        if (g == 45) {
            g = g();
            if (g == 48) {
                throw new d("Negative zero not allowed");
            }
            cArr[0] = '-';
            a = 1;
        } else {
            a = 0;
        }
        if (g < 49 || g > 57) {
            throw new d(new StringBuilder("Invalid Integer start '").append((char) g).append("'").toString());
        }
        int i;
        do {
            cArr[a] = (char) g;
            a++;
            g = g();
            i = g - 48;
            if (i < 0) {
                break;
            }
        } while (i <= 9);
        if (g == 101) {
            return new b(new BigInteger(new String(cArr, 0, a)));
        }
        throw new d("Integer should end with 'e'");
    }

    private b e() {
        int a = a();
        if (a != 108) {
            throw new d(new StringBuilder("Expected 'l', not '").append((char) a).append("'").toString());
        }
        this.b = 0;
        List arrayList = new ArrayList();
        a = a();
        while (a != 101) {
            arrayList.add(b());
            a = a();
        }
        this.b = 0;
        return new b(arrayList);
    }

    private b f() {
        int a = a();
        if (a != 100) {
            throw new d(new StringBuilder("Expected 'd', not '").append((char) a).append("'").toString());
        }
        this.b = 0;
        Map hashMap = new HashMap();
        a = a();
        while (a != 101) {
            hashMap.put(b().a(CharsetConvert.UTF_8), b());
            a = a();
        }
        this.b = 0;
        return new b(hashMap);
    }

    private int g() {
        int read = this.a.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }
}
