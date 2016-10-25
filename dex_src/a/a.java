package a;

import anet.channel.security.ISecurity;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

// compiled from: ByteString.java
public class a implements Serializable, Comparable<a> {
    static final char[] a;
    public static final a b;
    final byte[] c;
    transient int d;

    public /* synthetic */ int compareTo(Object obj) {
        a aVar = (a) obj;
        int length = this.c.length;
        int length2 = aVar.c.length;
        int min = Math.min(length, length2);
        int i = 0;
        while (i < min) {
            int i2 = this.c[i] & 255;
            int i3 = aVar.c[i] & 255;
            if (i2 != i3) {
                return i2 < i3 ? -1 : 1;
            } else {
                i++;
            }
        }
        if (length == length2) {
            return 0;
        }
        return length >= length2 ? 1 : -1;
    }

    static {
        a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        b = a(new byte[0]);
    }

    private a(byte[] bArr) {
        this.c = bArr;
    }

    private static a a(byte... bArr) {
        if (bArr != null) {
            return new a((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    private a a(String str) {
        try {
            return a(MessageDigest.getInstance(str).digest(this.c));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private String a() {
        int i = 0;
        char[] cArr = new char[(this.c.length * 2)];
        byte[] bArr = this.c;
        int length = bArr.length;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            cArr[i2] = a[(b >> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = a[b & 15];
            i++;
        }
        return new String(cArr);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof a) && ((a) obj).c.length == this.c.length) {
            Object obj2;
            a aVar = (a) obj;
            byte[] bArr = this.c;
            int length = this.c.length;
            if (aVar.c.length - length < 0 || bArr.length - length < 0 || !b.a(aVar.c, bArr, length)) {
                obj2 = null;
            } else {
                int i = 1;
            }
            if (obj2 != null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        i = Arrays.hashCode(this.c);
        this.d = i;
        return i;
    }

    public String toString() {
        if (this.c.length == 0) {
            return "ByteString[size=0]";
        }
        if (this.c.length <= 16) {
            return String.format("ByteString[size=%s data=%s]", new Object[]{Integer.valueOf(this.c.length), a()});
        }
        return String.format("ByteString[size=%s md5=%s]", new Object[]{Integer.valueOf(this.c.length), a(ISecurity.SIGN_ALGORITHM_MD5).a()});
    }
}
