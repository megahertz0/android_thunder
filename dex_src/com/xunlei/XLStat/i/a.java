package com.xunlei.XLStat.i;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.b.b;
import com.xunlei.XLStat.j.c;
import com.xunlei.analytics.c.e;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

public class a {
    private static String a;
    private static a f;
    private String b;
    private String c;
    private Map<String, String> d;
    private Context e;

    static {
        a = "Guid";
    }

    public static a a(Context context) {
        if (f == null) {
            f = new a(context);
        }
        return f;
    }

    public a(Context context) {
        CharSequence c;
        this.b = "999999999999";
        this.c = "999999999999999";
        if (context == null) {
            this.e = null;
        } else {
            this.e = context;
        }
        this.d = c(context);
        int size = this.d.size();
        if (!this.d.containsKey(SocializeProtocolConstants.PROTOCOL_KEY_MAC)) {
            c = b.c(context);
            if (!TextUtils.isEmpty(c)) {
                this.d.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, c);
            }
        }
        if (!this.d.containsKey(SocializeProtocolConstants.PROTOCOL_KEY_IMEI)) {
            c = b.b(context);
            if (!TextUtils.isEmpty(c)) {
                this.d.put(SocializeProtocolConstants.PROTOCOL_KEY_IMEI, c);
            }
        }
        if (size != this.d.size()) {
            a(this.d, context);
        }
    }

    public String a() {
        if (this.d == null) {
            return BuildConfig.VERSION_NAME;
        }
        String str = (String) this.d.get(SocializeProtocolConstants.PROTOCOL_KEY_MAC);
        return str == null ? BuildConfig.VERSION_NAME : str;
    }

    public String b() {
        if (this.d == null) {
            return BuildConfig.VERSION_NAME;
        }
        String str = (String) this.d.get(SocializeProtocolConstants.PROTOCOL_KEY_IMEI);
        return str == null ? BuildConfig.VERSION_NAME : str;
    }

    public String c() {
        String str;
        if (this.d != null) {
            str = (String) this.d.get(e.a);
        } else {
            str = null;
        }
        if (str != null) {
            return str;
        }
        String str2;
        String str3;
        String b = b.b(this.e);
        str = b.c(this.e);
        if (str == null || str.length() <= 2) {
            Object[] objArr = null;
        } else {
            String[] split = str.split(":");
            str = BuildConfig.VERSION_NAME;
        }
        if (split != null && split.length > 1) {
            int length = split.length;
            int i = 0;
            while (i < length) {
                i++;
                str = str + split[i];
            }
        }
        if (b == null || b == BuildConfig.VERSION_NAME) {
            str2 = this.c;
            int i2 = 1;
        } else {
            str2 = b;
            Object obj = null;
        }
        if (str == null || str == BuildConfig.VERSION_NAME) {
            str3 = this.b;
            int i3 = 1;
        } else {
            str3 = str;
            Object obj2 = null;
        }
        XLStatLog.d(a, "generateField", new StringBuilder("imei: ").append(str2).append(", mac: ").append(str3).toString());
        XLStatLog.d(a, "generateField", new StringBuilder("guid: ").append(c.a(str3 + "_" + str2)).toString());
        str3 = c.a(str3 + "_" + str2);
        this.d.put(e.a, str3);
        XLStatLog.i("wang.log", e.a, str3);
        if (obj != null && r0 != null) {
            return str3;
        }
        a(this.d, this.e);
        return str3;
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
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(b(context) + ".mainiconfig")));
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    JSONObject jSONObject = new JSONObject(readLine);
                    if (jSONObject.has(e.a)) {
                        hashMap.put(e.a, (String) jSONObject.get(e.a));
                    }
                    if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_MAC)) {
                        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, (String) jSONObject.get(SocializeProtocolConstants.PROTOCOL_KEY_MAC));
                    }
                    if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_IMEI)) {
                        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_IMEI, (String) jSONObject.get(SocializeProtocolConstants.PROTOCOL_KEY_IMEI));
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
                XLStatLog.i(a, "   ", Environment.getExternalStorageDirectory().getAbsolutePath());
                XLStatLog.i(a, "   ", hashMap.toString());
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
            XLStatLog.i(a, "   ", Environment.getExternalStorageDirectory().getAbsolutePath());
            XLStatLog.i(a, "   ", hashMap.toString());
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
        XLStatLog.i(a, "   ", Environment.getExternalStorageDirectory().getAbsolutePath());
        XLStatLog.i(a, "   ", hashMap.toString());
        return hashMap;
    }

    public static boolean d(Context context) {
        return new File(new StringBuilder().append(b(context)).append(".mainiconfig").toString()).exists();
    }

    public static boolean a(Map<String, String> map, Context context) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        XLStatLog.i(a, "write:  ", map.toString());
        File file = new File(b(context) + ".mainiconfig");
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
}
