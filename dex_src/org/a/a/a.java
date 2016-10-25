package org.a.a;

public final class a extends Exception {
    private Throwable a;

    public a(String str) {
        super(str);
    }

    public a(Throwable th) {
        super(th.getMessage());
        this.a = th;
    }

    public final Throwable getCause() {
        return this.a;
    }
}
