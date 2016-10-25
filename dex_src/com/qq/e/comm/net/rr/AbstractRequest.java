package com.qq.e.comm.net.rr;

import android.net.Uri;
import android.net.Uri.Builder;
import com.qq.e.comm.net.rr.Request.Method;
import com.qq.e.comm.util.StringUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractRequest implements Request {
    private boolean a;
    private int b;
    private int c;
    private int d;
    private String e;
    private Map<String, String> f;
    private Map<String, String> g;
    private Map<String, String> h;
    private Map<String, String> i;
    private Method j;
    private byte[] k;

    public AbstractRequest(String str, Method method, byte[] bArr) {
        this.a = true;
        this.f = new HashMap();
        this.g = new HashMap();
        this.h = Collections.unmodifiableMap(this.f);
        this.i = Collections.unmodifiableMap(this.g);
        this.e = str;
        this.j = method;
        if (bArr == null) {
            this.k = null;
        } else {
            this.k = (byte[]) bArr.clone();
        }
    }

    public void addHeader(String str, String str2) {
        if (!StringUtil.isEmpty(str) && !StringUtil.isEmpty(str2)) {
            this.f.put(str, str2);
        }
    }

    public void addQuery(String str, String str2) {
        this.g.put(str, str2);
    }

    public int getConnectionTimeOut() {
        return this.c;
    }

    public Map<String, String> getHeaders() {
        return this.h;
    }

    public Method getMethod() {
        return this.j;
    }

    public byte[] getPostData() throws Exception {
        return this.k;
    }

    public int getPriority() {
        return this.b;
    }

    public Map<String, String> getQuerys() {
        return this.i;
    }

    public int getSocketTimeOut() {
        return this.d;
    }

    public String getUrl() {
        return this.e;
    }

    public String getUrlWithParas() {
        if (getQuerys().isEmpty()) {
            return getUrl();
        }
        Builder buildUpon = Uri.parse(getUrl()).buildUpon();
        for (Entry entry : getQuerys().entrySet()) {
            buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        return buildUpon.build().toString();
    }

    public boolean isAutoClose() {
        return this.a;
    }

    public void setAutoClose(boolean z) {
        this.a = z;
    }

    public void setConnectionTimeOut(int i) {
        this.c = i;
    }

    public void setPriority(int i) {
        this.b = i;
    }

    public void setSocketTimeOut(int i) {
        this.d = i;
    }
}
