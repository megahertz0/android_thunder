package com.xunlei.downloadprovider.member.payment.external;

import java.util.Observable;

// compiled from: PaySuccessChange.java
public class h extends Observable {
    private static h a;

    private h() {
    }

    public static h a() {
        if (a == null) {
            synchronized (h.class) {
                if (a == null) {
                    a = new h();
                }
            }
        }
        return a;
    }

    public final void a(boolean z) {
        setChanged();
        notifyObservers(Boolean.valueOf(z));
    }
}
