package com.xunlei.downloadprovider.member.b.a;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

// compiled from: VipRenewalUpdateSchedule.java
public final class e {
    public boolean a;
    final ScheduledExecutorService b;

    // compiled from: VipRenewalUpdateSchedule.java
    private static class a {
        public static final e a;

        static {
            a = new e();
        }
    }

    private e() {
        this.b = Executors.newScheduledThreadPool(1);
    }
}
