package com.xunlei.common.httpclient;

import org.apache.http.Header;

public interface BaseHttpClientListener {
    void onFailure(Throwable th, byte[] bArr);

    void onSuccess(int i, Header[] headerArr, byte[] bArr);
}
