package com.inmobi.commons.core.utilities.uid;

import android.util.Base64;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.a.c;
import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.json.JSONObject;

// compiled from: UidMap.java
public class d {
    private Map<String, Boolean> a;

    public d(Map<String, Boolean> map) {
        this.a = map;
    }

    public HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("u-id-map", c());
        return hashMap;
    }

    public Map<String, String> b() {
        String toString = Integer.toString(new Random().nextInt());
        String a = c.a(new JSONObject(a(toString, true)).toString());
        Map<String, String> hashMap = new HashMap();
        hashMap.put("u-id-map", a);
        hashMap.put("u-id-key", toString);
        hashMap.put("u-key-ver", c.a().f());
        return hashMap;
    }

    private String c() {
        return new JSONObject(a(null, false)).toString();
    }

    public Map<String, String> a(String str, boolean z) {
        String a;
        Object a2;
        Map<String, String> hashMap = new HashMap();
        if (((Boolean) this.a.get("O1")).booleanValue() && !c.a().l()) {
            a = c.a().a(c.a().i());
            if (z) {
                a2 = a(a, str);
            }
            hashMap.put("O1", a2);
        }
        if (((Boolean) this.a.get("UM5")).booleanValue() && !c.a().l()) {
            a = c.a().b(c.a().i());
            if (z) {
                a2 = a(a, str);
            }
            hashMap.put("UM5", a2);
        }
        if (((Boolean) this.a.get("LID")).booleanValue()) {
            a = c.a().g();
            if (a != null && a.trim().length() > 0) {
                if (z) {
                    a2 = a(a, str);
                }
                hashMap.put("LID", a2);
            }
        }
        if (((Boolean) this.a.get("SID")).booleanValue()) {
            a = c.a().h();
            if (a != null && a.trim().length() > 0) {
                if (z) {
                    a2 = a(a, str);
                }
                hashMap.put("SID", a2);
            }
        }
        if (((Boolean) this.a.get("GPID")).booleanValue()) {
            a j = c.a().j();
            if (j != null) {
                a = j.b();
                if (a != null) {
                    if (z) {
                        a2 = a(a, str);
                    }
                    hashMap.put("GPID", a2);
                }
            }
        }
        if (((Boolean) this.a.get("IMID")).booleanValue()) {
            a = c.a().a(a.b());
            if (a != null) {
                if (z) {
                    a2 = a(a, str);
                }
                hashMap.put("IMID", a2);
            }
        }
        if (((Boolean) this.a.get("AIDL")).booleanValue()) {
            a = c.a().b(a.b());
            if (a != null) {
                if (z) {
                    a2 = a(a, str);
                }
                hashMap.put("AIDL", a2);
            }
        }
        return hashMap;
    }

    private String a(String str, String str2) {
        String str3 = com.umeng.a.d;
        try {
            byte[] bytes = str.getBytes(GameManager.DEFAULT_CHARSET);
            byte[] bArr = new byte[bytes.length];
            byte[] bytes2 = str2.getBytes(GameManager.DEFAULT_CHARSET);
            for (int i = 0; i < bytes.length; i++) {
                bArr[i] = (byte) (bytes[i] ^ bytes2[i % bytes2.length]);
            }
            return new String(Base64.encode(bArr, XZBDevice.DOWNLOAD_LIST_RECYCLE), GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str3;
        }
    }
}
