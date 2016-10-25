package com.xunlei.downloadprovider.c.a;

import com.umeng.socialize.weixin.BuildConfig;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xllib.R;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

// compiled from: AbsRequest.java
abstract class a {
    private final int a;
    private final String b;
    private String c;
    private long d;
    private String e;

    protected abstract HashMap<String, String> e();

    a() {
        this.a = 17;
        this.b = BuildConfig.VERSION_NAME;
    }

    public int a() {
        return R.styleable.Toolbar_maxButtonHeight;
    }

    public String b() {
        return BuildConfig.VERSION_NAME;
    }

    public String c() {
        String str = System.currentTimeMillis();
        this.c = str;
        return str;
    }

    protected long d() {
        return this.d;
    }

    protected void a(long j) {
        this.d = j;
    }

    public String a(HashMap<String, String> hashMap) {
        try {
            this.e = b(hashMap);
        } catch (IOException e) {
            this.e = null;
            e.printStackTrace();
        }
        return this.e;
    }

    private static String b(HashMap<String, String> hashMap) throws IOException {
        String str = "ed35b80ab6de3944a96466be405de2fc";
        Set<Entry> entrySet = new TreeMap(hashMap).entrySet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : entrySet) {
            stringBuilder.append((String) entry.getKey()).append("=").append((String) entry.getValue());
        }
        if (str.trim().length() > 0) {
            stringBuilder.append(str);
        }
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(stringBuilder.toString().getBytes(CharsetConvert.UTF_8));
            StringBuilder stringBuilder2 = new StringBuilder();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(b & 255);
                if (toHexString.length() == 1) {
                    stringBuilder2.append("0");
                }
                stringBuilder2.append(toHexString);
            }
            return stringBuilder2.toString();
        } catch (Throwable e) {
            throw new IOException(e);
        } catch (Throwable e2) {
            throw new IOException(e2);
        }
    }
}
