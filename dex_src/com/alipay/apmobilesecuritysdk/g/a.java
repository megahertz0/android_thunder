package com.alipay.apmobilesecuritysdk.g;

import android.content.Context;
import android.os.Environment;
import com.alipay.b.a.a.d.b;
import com.alipay.b.a.a.d.c;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class a {
    public static String a(Context context, String str, String str2) {
        if (context == null || com.alipay.b.a.a.a.a.a(str) || com.alipay.b.a.a.a.a.a(str2)) {
            return null;
        }
        try {
            String a = c.a(context, str, str2, com.umeng.a.d);
            return !com.alipay.b.a.a.a.a.a(a) ? com.alipay.b.a.a.a.a.c.b(com.alipay.b.a.a.a.a.c.a(), a) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String a(String str, String str2) {
        if (com.alipay.b.a.a.a.a.a(str) || com.alipay.b.a.a.a.a.a(str2)) {
            return null;
        }
        try {
            String a = com.alipay.b.a.a.d.a.a(str);
            if (com.alipay.b.a.a.a.a.a(a)) {
                return null;
            }
            a = new JSONObject(a).getString(str2);
            return !com.alipay.b.a.a.a.a.a(a) ? com.alipay.b.a.a.a.a.c.b(com.alipay.b.a.a.a.a.c.a(), a) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2) && context != null) {
            try {
                String a = com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), str3);
                Map hashMap = new HashMap();
                hashMap.put(str2, a);
                c.a(context, str, hashMap);
            } catch (Exception e) {
            }
        }
    }

    public static void a(String str, String str2, String str3) {
        Throwable th;
        if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2)) {
            try {
                String a = com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), str3);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(str2, a);
                String toString = jSONObject.toString();
                try {
                    if (!com.alipay.b.a.a.a.a.a(toString)) {
                        System.setProperty(str, toString);
                    }
                } catch (Throwable th2) {
                }
                if (b.a()) {
                    a = new StringBuilder(".SystemConfig").append(File.separator).append(str).toString();
                    try {
                        if (b.a()) {
                            File file = new File(Environment.getExternalStorageDirectory(), a);
                            if (!file.exists()) {
                                file.getParentFile().mkdirs();
                            }
                            File file2 = new File(file.getAbsolutePath());
                            FileWriter fileWriter = null;
                            FileWriter fileWriter2;
                            try {
                                fileWriter2 = new FileWriter(file2, false);
                                try {
                                    fileWriter2.write(toString);
                                    try {
                                        fileWriter2.close();
                                    } catch (IOException e) {
                                    }
                                } catch (Exception e2) {
                                    if (fileWriter2 != null) {
                                        fileWriter2.close();
                                    }
                                } catch (Throwable th3) {
                                    Throwable th4 = th3;
                                    fileWriter = fileWriter2;
                                    th = th4;
                                    if (fileWriter != null) {
                                        fileWriter.close();
                                    }
                                    throw th;
                                }
                            } catch (Exception e3) {
                                fileWriter2 = null;
                                if (fileWriter2 != null) {
                                    try {
                                        fileWriter2.close();
                                    } catch (IOException e4) {
                                    }
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                if (fileWriter != null) {
                                    try {
                                        fileWriter.close();
                                    } catch (IOException e5) {
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e6) {
                    }
                }
            } catch (Exception e7) {
            }
        }
    }
}
