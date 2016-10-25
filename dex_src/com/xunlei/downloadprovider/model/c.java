package com.xunlei.downloadprovider.model;

import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.o;
import com.xunlei.common.encrypt.CharsetConvert;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

// compiled from: SignInUtil.java
public final class c extends o {
    private Map<String, String> a;

    public c(String str, b<JSONObject> bVar, a aVar) {
        super(0, str, null, bVar, aVar);
        this.a = new HashMap(1);
        this.a.put("Charset", CharsetConvert.UTF_8);
    }
}
