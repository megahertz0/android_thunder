package com.xunlei.downloadprovider.member.login.ui;

// compiled from: UserInfoUtil.java
public final class ad {
    public static int a(int i) {
        int i2 = 0;
        while (i >= (i2 * 50) * (i2 + 3)) {
            i2++;
        }
        return i2 > 1 ? i2 - 1 : 0;
    }

    public static int b(int i) {
        return ((i + 1) * 50) * (i + 4);
    }
}
