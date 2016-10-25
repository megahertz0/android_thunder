package com.xiaomi.push.service;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.xiaomi.channel.commonutils.string.a;

public class ad {
    private static int a;
    private byte[] b;
    private int c;
    private int d;
    private int e;

    static {
        a = 8;
    }

    public ad() {
        this.e = -666;
        this.b = new byte[256];
        this.d = 0;
        this.c = 0;
    }

    public static int a(byte b) {
        return b >= null ? b : b + 256;
    }

    public static String a(byte[] bArr, String str) {
        return String.valueOf(a.a(a(bArr, str.getBytes())));
    }

    private void a(int i, byte[] bArr, boolean z) {
        int i2 = 0;
        int length = bArr.length;
        for (int i3 = 0; i3 < 256; i3++) {
            this.b[i3] = (byte) i3;
        }
        this.d = 0;
        this.c = 0;
        while (this.c < i) {
            this.d = ((this.d + a(this.b[this.c])) + a(bArr[this.c % length])) % 256;
            a(this.b, this.c, this.d);
            this.c++;
        }
        if (i != 256) {
            this.e = ((this.d + a(this.b[i])) + a(bArr[i % length])) % 256;
        }
        if (z) {
            System.out.print(new StringBuilder("S_").append(i - 1).append(":").toString());
            while (i2 <= i) {
                System.out.print(new StringBuilder(" ").append(a(this.b[i2])).toString());
                i2++;
            }
            System.out.print(new StringBuilder("   j_").append(i - 1).append("=").append(this.d).toString());
            System.out.print(new StringBuilder("   j_").append(i).append("=").append(this.e).toString());
            System.out.print(new StringBuilder("   S_").append(i - 1).append("[j_").append(i - 1).append("]=").append(a(this.b[this.d])).toString());
            System.out.print(new StringBuilder("   S_").append(i - 1).append("[j_").append(i).append("]=").append(a(this.b[this.e])).toString());
            if (this.b[1] != null) {
                System.out.print("   S[1]!=0");
            }
            System.out.println();
        }
    }

    private void a(byte[] bArr) {
        a((int) AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY, bArr, false);
    }

    private static void a(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }

    public static byte[] a(String str, String str2) {
        int i = 0;
        byte[] a = a.a(str);
        byte[] bytes = str2.getBytes();
        byte[] bArr = new byte[((a.length + 1) + bytes.length)];
        for (int i2 = 0; i2 < a.length; i2++) {
            bArr[i2] = a[i2];
        }
        bArr[a.length] = (byte) 95;
        while (i < bytes.length) {
            bArr[(a.length + 1) + i] = bytes[i];
            i++;
        }
        return bArr;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length];
        ad adVar = new ad();
        adVar.a(bArr);
        adVar.b();
        for (int i = 0; i < bArr2.length; i++) {
            bArr3[i] = (byte) (bArr2[i] ^ adVar.a());
        }
        return bArr3;
    }

    private void b() {
        this.d = 0;
        this.c = 0;
    }

    public static byte[] b(byte[] bArr, String str) {
        return a(bArr, a.a(str));
    }

    byte a() {
        this.c = (this.c + 1) % 256;
        this.d = (this.d + a(this.b[this.c])) % 256;
        a(this.b, this.c, this.d);
        return this.b[(a(this.b[this.c]) + a(this.b[this.d])) % 256];
    }
}
