package com.inmobi.commons.core.utilities;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.PowerManager;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.sina.weibo.sdk.component.GameManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

// compiled from: NetworkUtils.java
public class c {
    private static final String a;

    static {
        a = c.class.getSimpleName();
    }

    public static boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) a.b().getSystemService("connectivity")).getActiveNetworkInfo();
        return (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || b()) ? false : true;
    }

    private static boolean b() {
        return VERSION.SDK_INT > 22 ? ((PowerManager) a.b().getSystemService("power")).isDeviceIdleMode() : false;
    }

    public static String a(Map<String, ? extends Object> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : map.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(str);
            }
            stringBuilder.append(String.format(Locale.US, "%s=%s", new Object[]{a(str2), a(map.get(str2).toString())}));
        }
        return stringBuilder.toString();
    }

    public static String a(String str) {
        String str2 = com.umeng.a.d;
        try {
            return URLEncoder.encode(str, GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static void a(Map<String, String> map) {
        if (map != null) {
            Iterator it = map.entrySet().iterator();
            Map hashMap = new HashMap();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.getValue() == null || ((String) entry.getValue()).trim().length() == 0 || entry.getKey() == null || ((String) entry.getKey()).trim().length() == 0) {
                    it.remove();
                } else if (((String) entry.getKey()).equals(((String) entry.getKey()).trim())) {
                    hashMap.put(entry.getKey(), ((String) entry.getValue()).trim());
                } else {
                    it.remove();
                    hashMap.put(((String) entry.getKey()).trim(), ((String) entry.getValue()).trim());
                }
            }
            map.putAll(hashMap);
        }
    }

    public static String a(String str, Map<String, String> map) {
        if (map != null && map.size() > 0) {
            for (Entry entry : map.entrySet()) {
                str = str.replace((CharSequence) entry.getKey(), (CharSequence) entry.getValue());
            }
        }
        return str;
    }

    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        Closeable byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            Closeable gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                bArr2 = a((InputStream) gZIPInputStream);
                a(byteArrayInputStream);
                a(gZIPInputStream);
                return bArr2;
            } catch (IOException e) {
                e = e;
            }
        } catch (IOException e2) {
            e = e2;
            gZIPInputStream = null;
            try {
                Throwable e3;
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to decompress response", e3);
                a(byteArrayInputStream);
                a(gZIPInputStream);
                return bArr2;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        } catch (Throwable e32) {
            gZIPInputStream = null;
            th2 = e32;
            a(byteArrayInputStream);
            a(gZIPInputStream);
            throw th2;
        }
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                bArr = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return bArr;
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to close closable", e);
            }
        }
    }
}
