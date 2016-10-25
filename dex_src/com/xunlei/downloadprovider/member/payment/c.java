package com.xunlei.downloadprovider.member.payment;

import com.xunlei.downloadprovider.member.payment.external.PayFrom;

// compiled from: ReportReferUtil.java
public final class c {

    // compiled from: ReportReferUtil.java
    static /* synthetic */ class AnonymousClass_1 {
        public static final /* synthetic */ int[] a;

        static {
            a = new int[PayFrom.values().length];
            try {
                a[PayFrom.PERSONAL_CENTER_ICON.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[PayFrom.PERSONAL_CENTER_TOP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[PayFrom.PERSONAL_CENTER_RENEWTIP.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[PayFrom.ACCOUNT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[PayFrom.VIP_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[PayFrom.LIXIAN_SPACE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[PayFrom.LIXIAN_SPACE_RENEWTIP.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[PayFrom.PLAY_LIST.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[PayFrom.PLAY_LIST_RENEWTIP.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[PayFrom.NEARBY_RESOURCE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[PayFrom.NEARBY_STATION.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[PayFrom.DOWNLOAD_TOTAL.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[PayFrom.DOWNLOAD_TASK_DETAIL.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[PayFrom.DOWNLOAD_TASK.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[PayFrom.DOWNLOAD_TASK_RENEWTIP.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[PayFrom.DOWNLOAD_NOTIFICATION.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[PayFrom.DOWNLOAD_TASK_LIXIAN.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[PayFrom.DOWNLOAD_TASK_HIGH_SPEED.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[PayFrom.BIRD_PAGE.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[PayFrom.BIRD_NOTICE.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                a[PayFrom.BIRD_TIP.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                a[PayFrom.PROMOTION_CHOU_JIANG.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
        }
    }

    public static boolean a(int i) {
        return i >= -3 && i < 0;
    }

    public static boolean b(int i) {
        return i > 0 && i <= 4;
    }

    public static boolean c(int i) {
        return i >= 14 && i <= 15;
    }
}
