package com.baidu.mobads.openad.b;

import java.util.HashMap;

public class d implements a {
    public static String a;
    private HashMap<String, String> b;

    static {
        a = "_uid=b000_5511089179943706094;expires=Dec, 21 Aug 2014 06:40:41 GMT;domain=.youku.com;path=/;";
    }

    public d() {
        this.b = new HashMap();
    }

    public void a(String str, String str2) {
        this.b.put(str, str2);
    }

    public void b() {
        for (String str : this.b.keySet()) {
            this.b.remove(str);
        }
    }

    public void a() {
        b();
    }

    public String a(String str) {
        return (String) this.b.get(str);
    }
}
