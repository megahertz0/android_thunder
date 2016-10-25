package com.xunlei.analytics.c;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.xunlei.analytics.b.c;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

public class e {
    public static final String a = "guid";
    private static String b = null;
    private static final String[] c;
    private static final String d = "mac";
    private static final String e = "imei";
    private static final String f = ".mainiconfig";
    private static e k;
    private String g;
    private String h;
    private Map<String, String> i;
    private Context j;

    static {
        b = e.class.getSimpleName();
        c = new String[]{"0", c.f, c.e, c.c, c.d, "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    }

    private e(Context context) {
        CharSequence c;
        this.g = "999999999999";
        this.h = "999999999999999";
        this.j = context;
        this.i = c(context);
        int size = this.i.size();
        if (!this.i.containsKey(d)) {
            c = b.c(context);
            if (!TextUtils.isEmpty(c)) {
                this.i.put(d, c);
            }
        }
        if (!this.i.containsKey(e)) {
            c = b.b(context);
            if (!TextUtils.isEmpty(c)) {
                this.i.put(e, c);
            }
        }
        if (size != this.i.size()) {
            a(this.i, context);
        }
    }

    public static e a(Context context) {
        if (k == null) {
            k = new e(context);
        }
        return k;
    }

    private static String a(byte b) {
        int i;
        if (b < null) {
            i = b + 256;
        }
        return c[i / 16] + c[i % 16];
    }

    public static String a(String str) {
        if (str == null) {
            return BuildConfig.VERSION_NAME;
        }
        try {
            String str2 = new String(str);
            try {
                return a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
            } catch (NoSuchAlgorithmException e) {
                NoSuchAlgorithmException noSuchAlgorithmException = e;
                String str3 = str2;
                NoSuchAlgorithmException noSuchAlgorithmException2 = noSuchAlgorithmException;
            }
        } catch (NoSuchAlgorithmException e2) {
            noSuchAlgorithmException2 = e2;
            str3 = null;
            noSuchAlgorithmException2.printStackTrace();
            return str3;
        }
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(a(b));
        }
        return stringBuffer.toString();
    }

    public static boolean a(Map<String, String> map, Context context) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        File file = new File(b(context) + f);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                JSONObject jSONObject = new JSONObject();
                for (Entry entry : map.entrySet()) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
                bufferedWriter.write(jSONObject.toString());
                bufferedWriter.flush();
                bufferedWriter.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String b(Context context) {
        Environment.getExternalStorageDirectory().getAbsolutePath();
        String absolutePath = context.getFilesDir().getAbsolutePath();
        return absolutePath.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR) ? absolutePath : absolutePath + MqttTopic.TOPIC_LEVEL_SEPARATOR;
    }

    public static Map<String, String> c(Context context) {
        Exception e;
        Map<String, String> hashMap = new HashMap();
        if (!d(context)) {
            return hashMap;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(b(context) + f)));
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    JSONObject jSONObject = new JSONObject(readLine);
                    if (jSONObject.has(a)) {
                        hashMap.put(a, (String) jSONObject.get(a));
                    }
                    if (jSONObject.has(d)) {
                        hashMap.put(d, (String) jSONObject.get(d));
                    }
                    if (jSONObject.has(e)) {
                        hashMap.put(e, (String) jSONObject.get(e));
                    }
                }
                bufferedReader.close();
                try {
                    bufferedReader.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (Exception e3) {
                e2 = e3;
                e2.printStackTrace();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return hashMap;
            }
        } catch (Exception e4) {
            e2 = e4;
            bufferedReader = null;
            try {
                e2.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th2;
            }
            return hashMap;
        } catch (Throwable th3) {
            th2 = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            throw th2;
        }
        return hashMap;
    }

    public static boolean d(Context context) {
        return new File(new StringBuilder().append(b(context)).append(f).toString()).exists();
    }

    public String a() {
        Object obj = 1;
        String str = this.i != null ? (String) this.i.get(a) : null;
        if (TextUtils.isEmpty(str)) {
            String[] strArr;
            int i;
            Object obj2;
            String b = b.b(this.j);
            str = b.c(this.j);
            if (str == null || str.length() <= 2) {
                Object[] objArr = null;
            } else {
                String[] split = str.split(":");
                str = BuildConfig.VERSION_NAME;
                strArr = split;
            }
            if (strArr != null && strArr.length > 1) {
                i = 0;
                while (i < strArr.length) {
                    i++;
                    str = r0 + strArr[i];
                }
            }
            if (TextUtils.isEmpty(b)) {
                b = this.h;
                i = 1;
            } else {
                obj2 = null;
            }
            if (TextUtils.isEmpty(str)) {
                str = this.g;
            } else {
                obj = null;
            }
            str = a(str + "_" + b);
            this.i.put(a, str);
            if (obj2 == null || r2 == null) {
                a(this.i, this.j);
            }
        }
        return str;
    }
}
