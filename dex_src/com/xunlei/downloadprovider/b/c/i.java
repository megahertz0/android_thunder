package com.xunlei.downloadprovider.b.c;

// compiled from: IBpDataLoaderParser.java
public abstract class i {
    public int mErrCode;

    public abstract Object parse(byte[] bArr);

    public int getError() {
        return this.mErrCode;
    }
}
