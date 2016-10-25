package com.tencent.stat;

public class MtaSDkException extends Exception {
    public MtaSDkException(String str) {
        super(str);
    }

    public MtaSDkException(String str, Throwable th) {
        super(str, th);
    }

    public MtaSDkException(Throwable th) {
        super(th);
    }
}
