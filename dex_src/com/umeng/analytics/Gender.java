package com.umeng.analytics;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Locale;
import org.android.spdy.SpdyAgent;
import u.aly.ay;

public enum Gender {
    Male(1) {
        public final String toString() {
            return String.format(Locale.US, "Male:%d", new Object[]{Integer.valueOf(this.value)});
        }
    },
    Female(2) {
        public final String toString() {
            return String.format(Locale.US, "Female:%d", new Object[]{Integer.valueOf(this.value)});
        }
    },
    Unknown(0) {
        public final String toString() {
            return String.format(Locale.US, "Unknown:%d", new Object[]{Integer.valueOf(this.value)});
        }
    };
    public int value;

    /* synthetic */ class AnonymousClass_4 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Gender.values().length];
            try {
                a[Male.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Female.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Unknown.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static {
        Male = new AnonymousClass_1("Male", 0, 1);
        Female = new AnonymousClass_2("Female", 1, 2);
        Unknown = new AnonymousClass_3("Unknown", 2, 0);
        a = new Gender[]{Male, Female, Unknown};
    }

    private Gender(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static Gender getGender(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return Male;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return Female;
            default:
                return Unknown;
        }
    }

    public static ay transGender(Gender gender) {
        switch (AnonymousClass_4.a[gender.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return ay.a;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return ay.b;
            default:
                return ay.c;
        }
    }
}
