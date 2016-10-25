package com.google.zxing;

// compiled from: ReaderException.java
public abstract class m extends Exception {
    protected static final boolean a;
    protected static final StackTraceElement[] b;

    static {
        boolean z;
        if (System.getProperty("surefire.test.class.path") != null) {
            z = true;
        } else {
            z = false;
        }
        a = z;
        b = new StackTraceElement[0];
    }

    m() {
    }

    m(Throwable th) {
        super(th);
    }

    public final Throwable fillInStackTrace() {
        return null;
    }
}
