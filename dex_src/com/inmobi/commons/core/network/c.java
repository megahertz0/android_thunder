package com.inmobi.commons.core.network;

import java.util.List;
import java.util.Map;

// compiled from: NetworkResponse.java
public class c {
    private NetworkRequest a;
    private String b;
    private NetworkError c;
    private Map<String, List<String>> d;

    public c(NetworkRequest networkRequest) {
        this.a = networkRequest;
    }

    public boolean a() {
        return this.c != null;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(Map<String, List<String>> map) {
        this.d = map;
    }

    public NetworkError c() {
        return this.c;
    }

    public void a(NetworkError networkError) {
        this.c = networkError;
    }
}
