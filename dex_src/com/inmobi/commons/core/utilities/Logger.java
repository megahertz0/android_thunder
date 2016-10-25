package com.inmobi.commons.core.utilities;

import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

public final class Logger {
    private static InternalLogLevel a;

    /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[InternalLogLevel.values().length];
            try {
                a[InternalLogLevel.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[InternalLogLevel.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[InternalLogLevel.INTERNAL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum InternalLogLevel {
        NONE,
        ERROR,
        DEBUG,
        INTERNAL
    }

    public static void a(InternalLogLevel internalLogLevel, String str, String str2) {
        if (internalLogLevel.ordinal() <= a.ordinal()) {
            int[] iArr = AnonymousClass_1.a;
            internalLogLevel.ordinal();
        }
    }

    public static void a(InternalLogLevel internalLogLevel, String str, String str2, Throwable th) {
        if (internalLogLevel.ordinal() <= a.ordinal()) {
            int[] iArr = AnonymousClass_1.a;
            internalLogLevel.ordinal();
        }
    }

    public static void a(InternalLogLevel internalLogLevel) {
        a = internalLogLevel;
    }

    static {
        a = "production".equalsIgnoreCase("staging") ? InternalLogLevel.INTERNAL : InternalLogLevel.NONE;
    }
}
