package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.j;
import com.tencent.bugly.proguard.w;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: BUGLY
public class b {
    private StringBuilder a;
    private int b;

    private void d(String str) {
        for (int i = 0; i < this.b; i++) {
            this.a.append('\t');
        }
        if (str != null) {
            this.a.append(str).append(": ");
        }
    }

    public b(StringBuilder stringBuilder, int i) {
        this.b = 0;
        this.a = stringBuilder;
        this.b = i;
    }

    private static Map<String, Integer> c(String str) {
        if (str == null) {
            return null;
        }
        try {
            Map<String, Integer> hashMap = new HashMap();
            String[] split = str.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            int length = split.length;
            for (int i = 0; i < length; i++) {
                String[] split2 = split[i].split(":");
                if (split2.length != 2) {
                    w.e("error format at %s", r6);
                    return null;
                }
                hashMap.put(split2[0], Integer.valueOf(Integer.parseInt(split2[1])));
            }
            return hashMap;
        } catch (Exception e) {
            w.e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    public b a(boolean z, String str) {
        d(str);
        this.a.append(z ? 'T' : 'F').append('\n');
        return this;
    }

    public b a(byte b, String str) {
        d(str);
        this.a.append(b).append('\n');
        return this;
    }

    public b a(char c, String str) {
        d(str);
        this.a.append(c).append('\n');
        return this;
    }

    public b a(short s, String str) {
        d(str);
        this.a.append(s).append('\n');
        return this;
    }

    protected static String a(String str) {
        if (str == null) {
            return a.d;
        }
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                stringBuilder.append(str2).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public b a(int i, String str) {
        d(str);
        this.a.append(i).append('\n');
        return this;
    }

    public b a(long j, String str) {
        d(str);
        this.a.append(j).append('\n');
        return this;
    }

    public b a(float f, String str) {
        d(str);
        this.a.append(f).append('\n');
        return this;
    }

    private static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        if (map == null) {
            return null;
        }
        if (com.tencent.bugly.crashreport.common.info.a.a(context) == null) {
            w.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str = (String) map.get("intStateStr");
        if (str == null || str.trim().length() <= 0) {
            w.e("no intStateStr", new Object[0]);
            return null;
        }
        Map c = c(str);
        if (c == null) {
            w.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            int intValue = ((Integer) c.get("ep")).intValue();
            int intValue2 = ((Integer) c.get("et")).intValue();
            ((Integer) c.get("sino")).intValue();
            int intValue3 = ((Integer) c.get("sico")).intValue();
            int intValue4 = ((Integer) c.get("spd")).intValue();
            ((Integer) c.get("sud")).intValue();
            long intValue5 = (long) ((Integer) c.get("ets")).intValue();
            long intValue6 = (long) ((Integer) c.get("etms")).intValue();
            String str2 = (String) map.get("soVersion");
            if (str2 == null) {
                w.e("error format at version", new Object[0]);
                return null;
            }
            String str3;
            String str4;
            String str5;
            String str6;
            String toString;
            str = (String) map.get("errorAddr");
            String str7 = str == null ? "unknown2" : str;
            str = (String) map.get("codeMsg");
            if (str == null) {
                str3 = "unknown2";
            } else {
                str3 = str;
            }
            str = (String) map.get("tombPath");
            if (str == null) {
                str4 = "unknown2";
            } else {
                str4 = str;
            }
            str = (String) map.get("signalName");
            if (str == null) {
                str5 = "unknown2";
            } else {
                str5 = str;
            }
            map.get("errnoMsg");
            str = (String) map.get("stack");
            if (str == null) {
                str6 = "unknown2";
            } else {
                str6 = str;
            }
            str = (String) map.get("jstack");
            if (str != null) {
                str = str6 + "java:\n" + str;
            } else {
                str = str6;
            }
            intValue5 = (intValue5 * 1000) + (intValue6 / 1000);
            String a = a(str);
            str = (String) map.get("sendingProcess");
            if (str == null) {
                str = "UNKNOWN";
            }
            String str8 = str + SocializeConstants.OP_OPEN_PAREN + intValue4 + SocializeConstants.OP_CLOSE_PAREN;
            if (intValue3 > 0) {
                str5 = str5 + SocializeConstants.OP_OPEN_PAREN + str3 + SocializeConstants.OP_CLOSE_PAREN;
                str3 = "KERNEL";
            }
            str = (String) map.get("nativeLog");
            byte[] bArr = null;
            if (!(str == null || str.isEmpty())) {
                bArr = com.tencent.bugly.proguard.a.a(null, str);
            }
            str = (String) map.get("processName");
            if (str == null) {
                toString = new StringBuilder("unknown(").append(intValue).append(SocializeConstants.OP_CLOSE_PAREN).toString();
            } else {
                toString = str;
            }
            str = (String) map.get("threadName");
            if (str == null) {
                str = UtilityImpl.NET_TYPE_UNKNOWN;
            }
            String str9 = str + SocializeConstants.OP_OPEN_PAREN + intValue2 + SocializeConstants.OP_CLOSE_PAREN;
            Map map2 = null;
            str = (String) map.get("key-value");
            if (str != null) {
                map2 = new HashMap();
                for (String str10 : str.split("\n")) {
                    String[] split = str10.split("=");
                    if (split.length == 2) {
                        map2.put(split[0], split[1]);
                    }
                }
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(toString, str9, intValue5, str5, str7, a, str3, str8, str4, str2, bArr, map2, false);
            if (packageCrashDatas != null) {
                str = (String) map.get("userId");
                if (str != null) {
                    packageCrashDatas.m = str;
                }
                str = (String) map.get("sysLog");
                if (str != null) {
                    packageCrashDatas.w = str;
                }
                str = (String) map.get(Constants.KEY_APP_VERSION);
                if (str != null) {
                    packageCrashDatas.f = str;
                }
                packageCrashDatas.y = null;
                packageCrashDatas.k = true;
            }
            return packageCrashDatas;
        } catch (Throwable th) {
            w.e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    public b a(double d, String str) {
        d(str);
        this.a.append(d).append('\n');
        return this;
    }

    public b b(String str, String str2) {
        d(str2);
        if (str == null) {
            this.a.append("null\n");
        } else {
            this.a.append(str).append('\n');
        }
        return this;
    }

    public b a(byte[] bArr, String str) {
        d(str);
        if (bArr == null) {
            this.a.append("null\n");
        } else if (bArr.length == 0) {
            this.a.append(bArr.length).append(", []\n");
        } else {
            this.a.append(bArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (byte b : bArr) {
                bVar.a(b, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(short[] sArr, String str) {
        d(str);
        if (sArr == null) {
            this.a.append("null\n");
        } else if (sArr.length == 0) {
            this.a.append(sArr.length).append(", []\n");
        } else {
            this.a.append(sArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (short s : sArr) {
                bVar.a(s, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(int[] iArr, String str) {
        d(str);
        if (iArr == null) {
            this.a.append("null\n");
        } else if (iArr.length == 0) {
            this.a.append(iArr.length).append(", []\n");
        } else {
            this.a.append(iArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (int i : iArr) {
                bVar.a(i, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(long[] jArr, String str) {
        d(str);
        if (jArr == null) {
            this.a.append("null\n");
        } else if (jArr.length == 0) {
            this.a.append(jArr.length).append(", []\n");
        } else {
            this.a.append(jArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (long j : jArr) {
                bVar.a(j, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(float[] fArr, String str) {
        d(str);
        if (fArr == null) {
            this.a.append("null\n");
        } else if (fArr.length == 0) {
            this.a.append(fArr.length).append(", []\n");
        } else {
            this.a.append(fArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (float f : fArr) {
                bVar.a(f, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(double[] dArr, String str) {
        d(str);
        if (dArr == null) {
            this.a.append("null\n");
        } else if (dArr.length == 0) {
            this.a.append(dArr.length).append(", []\n");
        } else {
            this.a.append(dArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (double d : dArr) {
                bVar.a(d, null);
            }
            a(']', null);
        }
        return this;
    }

    private static String a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            if (read == 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append((char) read);
        }
    }

    public <K, V> b a(Map<K, V> map, String str) {
        d(str);
        if (map == null) {
            this.a.append("null\n");
        } else if (map.isEmpty()) {
            this.a.append(map.size()).append(", {}\n");
        } else {
            this.a.append(map.size()).append(", {\n");
            b bVar = new b(this.a, this.b + 1);
            b bVar2 = new b(this.a, this.b + 2);
            for (Entry entry : map.entrySet()) {
                bVar.a('(', null);
                bVar2.a(entry.getKey(), null);
                bVar2.a(entry.getValue(), null);
                bVar.a(')', null);
            }
            a('}', null);
        }
        return this;
    }

    public static CrashDetailBean a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) {
        IOException e;
        FileInputStream fileInputStream;
        Throwable th;
        CrashDetailBean crashDetailBean = null;
        if (context == null || str == null || nativeExceptionHandler == null) {
            w.e("get eup record file args error", new Object[0]);
        } else {
            File file = new File(str, "rqd_record.eup");
            if (file.exists() && file.canRead()) {
                try {
                    InputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        String a = a(fileInputStream2);
                        if (a == null || !a.equals("NATIVE_RQD_REPORT")) {
                            w.e("record read fail! %s", a);
                            try {
                                fileInputStream2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            Map hashMap = new HashMap();
                            Object obj = null;
                            while (true) {
                                String a2 = a(fileInputStream2);
                                if (a2 == null) {
                                    break;
                                } else if (obj == null) {
                                    a = a2;
                                } else {
                                    hashMap.put(obj, a2);
                                    obj = null;
                                }
                            }
                            if (obj != null) {
                                w.e("record not pair! drop! %s", obj);
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            } else {
                                crashDetailBean = a(context, hashMap, nativeExceptionHandler);
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e222) {
                                    e222.printStackTrace();
                                }
                            }
                        }
                    } catch (IOException e3) {
                        e222 = e3;
                        e222.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return crashDetailBean;
                    }
                } catch (IOException e4) {
                    e222 = e4;
                    fileInputStream = null;
                    try {
                        e222.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                    return crashDetailBean;
                } catch (Throwable th3) {
                    fileInputStream = null;
                    th = th3;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22222) {
                            e22222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        }
        return crashDetailBean;
    }

    public <T> b a(T[] tArr, String str) {
        d(str);
        if (tArr == null) {
            this.a.append("null\n");
        } else if (tArr.length == 0) {
            this.a.append(tArr.length).append(", []\n");
        } else {
            this.a.append(tArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (Object obj : tArr) {
                bVar.a(obj, null);
            }
            a(']', null);
        }
        return this;
    }

    public <T> b a(Collection<T> collection, String str) {
        if (collection != null) {
            return a(collection.toArray(), str);
        }
        d(str);
        this.a.append("null\t");
        return this;
    }

    private static String c(String str, String str2) {
        String str3 = null;
        BufferedReader a = com.tencent.bugly.proguard.a.a(str, "reg_record.txt");
        if (a != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                if (a.readLine().startsWith(str2)) {
                    String str4 = "                ";
                    int i = 0;
                    int i2 = 18;
                    int i3 = 0;
                    while (true) {
                        String readLine = a.readLine();
                        if (readLine == null) {
                            break;
                        }
                        if (i3 % 4 == 0) {
                            if (i3 > 0) {
                                stringBuilder.append("\n");
                            }
                            stringBuilder.append("  ");
                        } else {
                            if (readLine.length() > 16) {
                                i2 = R.styleable.AppCompatTheme_actionModeCloseButtonStyle;
                            }
                            stringBuilder.append(str4.substring(0, i2 - i));
                        }
                        i = readLine.length();
                        stringBuilder.append(readLine);
                        i3++;
                    }
                    stringBuilder.append("\n");
                    str3 = stringBuilder.toString();
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e) {
                            w.a(e);
                        }
                    }
                } else if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e2) {
                        w.a(e2);
                    }
                }
            } catch (Throwable e22) {
                try {
                    w.a(e22);
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e222) {
                            w.a(e222);
                        }
                    }
                } catch (Throwable th) {
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e2222) {
                            w.a(e2222);
                        }
                    }
                }
            }
        }
        return str3;
    }

    public <T> b a(T t, String str) {
        if (t == null) {
            this.a.append("null\n");
        } else if (t instanceof Byte) {
            a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            b((String) t, str);
        } else if (t instanceof Map) {
            a((Map) t, str);
        } else if (t instanceof List) {
            a((List) t, str);
        } else if (t instanceof j) {
            a((j) t, str);
        } else if (t instanceof byte[]) {
            a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            a((boolean[]) t, str);
        } else if (t instanceof short[]) {
            a((short[]) t, str);
        } else if (t instanceof int[]) {
            a((int[]) t, str);
        } else if (t instanceof long[]) {
            a((long[]) t, str);
        } else if (t instanceof float[]) {
            a((float[]) t, str);
        } else if (t instanceof double[]) {
            a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            a((Object[]) t, str);
        } else {
            throw new com.tencent.bugly.proguard.b("write object error: unsupport type.");
        }
        return this;
    }

    private static String d(String str, String str2) {
        String str3 = null;
        BufferedReader a = com.tencent.bugly.proguard.a.a(str, "map_record.txt");
        if (a != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                if (a.readLine().startsWith(str2)) {
                    while (true) {
                        String readLine = a.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append("  ");
                        stringBuilder.append(readLine);
                        stringBuilder.append("\n");
                    }
                    str3 = stringBuilder.toString();
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e) {
                            w.a(e);
                        }
                    }
                } else if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e2) {
                        w.a(e2);
                    }
                }
            } catch (Throwable e22) {
                try {
                    w.a(e22);
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e222) {
                            w.a(e222);
                        }
                    }
                } catch (Throwable th) {
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e2222) {
                            w.a(e2222);
                        }
                    }
                }
            }
        }
        return str3;
    }

    public b a(j jVar, String str) {
        a('{', str);
        if (jVar == null) {
            this.a.append('\t').append("null");
        } else {
            jVar.a(this.a, this.b + 1);
        }
        a('}', null);
        return this;
    }

    public static String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String c = c(str, str2);
        if (!(c == null || c.isEmpty())) {
            stringBuilder.append("Register infos:\n");
            stringBuilder.append(c);
        }
        c = d(str, str2);
        if (!(c == null || c.isEmpty())) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append("System SO infos:\n");
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static void b(String str) {
        File file = new File(str, "rqd_record.eup");
        if (file.exists() && file.canWrite()) {
            file.delete();
            w.c("delete record file %s", file.getAbsoluteFile());
        }
        file = new File(str, "reg_record.txt");
        if (file.exists() && file.canWrite()) {
            file.delete();
            w.c("delete record file %s", file.getAbsoluteFile());
        }
        file = new File(str, "map_record.txt");
        if (file.exists() && file.canWrite()) {
            file.delete();
            w.c("delete record file %s", file.getAbsoluteFile());
        }
        file = new File(str, "backup_record.txt");
        if (file.exists() && file.canWrite()) {
            file.delete();
            w.c("delete record file %s", file.getAbsoluteFile());
        }
    }
}
