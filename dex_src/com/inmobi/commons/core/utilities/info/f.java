package com.inmobi.commons.core.utilities.info;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.taobao.accs.common.Constants;
import java.util.HashMap;
import org.json.JSONObject;

// compiled from: SessionInfo.java
public class f {
    private static final String a;
    private static f b;
    private static Object c;
    private String d;
    private long e;
    private long f;
    private boolean g;

    static {
        a = f.class.getSimpleName();
        c = new Object();
    }

    public static f a() {
        f fVar = b;
        if (fVar == null) {
            synchronized (c) {
                fVar = b;
                if (fVar == null) {
                    fVar = new f();
                    b = fVar;
                }
            }
        }
        return fVar;
    }

    private f() {
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(long j) {
        this.e = j;
    }

    public void b(long j) {
        this.f = j;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.KEY_SID, this.d);
            jSONObject.put("s-ts", this.e);
            jSONObject.put("e-ts", this.f);
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Problem converting session object to Json.", e);
        }
        return jSONObject;
    }

    public void a(boolean z) {
        this.g = z;
        if (!this.g) {
            d();
        }
    }

    private void d() {
        this.d = null;
        this.e = 0;
        this.f = 0;
    }

    public HashMap<String, String> c() {
        HashMap<String, String> hashMap = new HashMap();
        if (this.g && this.d != null) {
            hashMap.put("u-s-id", this.d);
        }
        return hashMap;
    }
}
