package com.alipay.b.a.a.e;

import com.umeng.a;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class d {
    private static String a;
    private static String b;
    private static String c;

    static {
        a = a.d;
        b = a.d;
        c = a.d;
    }

    public static synchronized void a(String str) {
        synchronized (d.class) {
            List arrayList = new ArrayList();
            arrayList.add(str);
            a(arrayList);
        }
    }

    public static synchronized void a(String str, String str2, String str3) {
        synchronized (d.class) {
            a = str;
            b = str2;
            c = str3;
        }
    }

    public static synchronized void a(Throwable th) {
        synchronized (d.class) {
            Object toString;
            List arrayList = new ArrayList();
            if (th != null) {
                Writer stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                toString = stringWriter.toString();
            } else {
                toString = a.d;
            }
            arrayList.add(toString);
            a(arrayList);
        }
    }

    private static synchronized void a(List<String> list) {
        synchronized (d.class) {
            if (!(com.alipay.b.a.a.a.a.a(b) || com.alipay.b.a.a.a.a.a(c))) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(c);
                for (String str : list) {
                    stringBuffer.append(new StringBuilder(", ").append(str).toString());
                }
                stringBuffer.append("\n");
                try {
                    File file = new File(a);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(a, b);
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    FileWriter fileWriter = file2.length() + ((long) stringBuffer.length()) <= 51200 ? new FileWriter(file2, true) : new FileWriter(file2);
                    fileWriter.write(stringBuffer.toString());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
