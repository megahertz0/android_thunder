package com.umeng.socialize;

import com.umeng.a;

public class SocializeException extends RuntimeException {
    private static final long b = 1;
    protected int a;
    private String c;

    public int getErrorCode() {
        return this.a;
    }

    public SocializeException(int i, String str) {
        super(str);
        this.a = 5000;
        this.c = a.d;
        this.a = i;
        this.c = str;
    }

    public SocializeException(String str, Throwable th) {
        super(str, th);
        this.a = 5000;
        this.c = a.d;
        this.c = str;
    }

    public SocializeException(String str) {
        super(str);
        this.a = 5000;
        this.c = a.d;
        this.c = str;
    }

    public String getMessage() {
        return this.c;
    }
}
