package com.xunlei.common.httpclient;

import android.content.Context;
import org.apache.http.Header;

public interface BaseHttpClient {
    void get(Context context, String str, Header[] headerArr, BaseHttpClientListener baseHttpClientListener);

    void post(Context context, String str, Header[] headerArr, byte[] bArr, BaseHttpClientListener baseHttpClientListener);

    void setHttpClientTimeout(int i);
}
