package com.xunlei.downloadprovider.member.payment;

import com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType;

// compiled from: PayReporter.java
/* synthetic */ class b$1 {
    static final /* synthetic */ int[] a;

    static {
        a = new int[OrderType.values().length];
        try {
            a[OrderType.OPEN.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[OrderType.RENEW.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[OrderType.UPGRADE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
