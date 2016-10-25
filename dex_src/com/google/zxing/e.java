package com.google.zxing;

// compiled from: FormatException.java
public final class e extends m {
    private static final e c;

    static {
        e eVar = new e();
        c = eVar;
        eVar.setStackTrace(b);
    }

    private e() {
    }

    private e(Throwable th) {
        super(th);
    }

    public static e a() {
        return a ? new e() : c;
    }

    public static e a(Throwable th) {
        return a ? new e(th) : c;
    }
}
