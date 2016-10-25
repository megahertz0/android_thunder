package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class a {
    private static int a(List<String> list, String str) {
        int i = 0;
        while (list != null && i < list.size()) {
            if (!TextUtils.isEmpty(str) && str.equalsIgnoreCase((String) list.get(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static String a(String str) {
        if (str == null) {
            return str;
        }
        String[] split = str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        return (split == null || split.length <= 0) ? str : split[split.length - 1];
    }

    public static List<String> a(Context context) {
        List<String> arrayList = new ArrayList();
        CharSequence b = b(context);
        if (!TextUtils.isEmpty(b)) {
            arrayList.add(b);
        }
        b = g.a("ro.product.cpu.abi", BuildConfig.VERSION_NAME);
        if (!TextUtils.isEmpty(b)) {
            arrayList.add(b);
        }
        b = g.a("ro.product.cpu.abi2", BuildConfig.VERSION_NAME);
        if (!TextUtils.isEmpty(b)) {
            arrayList.add(b);
        }
        Object a = g.a("ro.product.cpu.abilist", BuildConfig.VERSION_NAME);
        if (!TextUtils.isEmpty(a)) {
            String[] split = a.split(",");
            int i = 0;
            while (split != null && i < split.length) {
                if (!TextUtils.isEmpty(split[i])) {
                    arrayList.add(split[i]);
                }
                i++;
            }
        }
        arrayList.add("armeabi");
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r11, java.lang.String r12, java.lang.String r13) {
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.channel.commonutils.android.a.a(android.content.Context, java.lang.String, java.lang.String):void");
        /*
        r2 = 0;
        if (r12 == 0) goto L_0x001e;
    L_0x0003:
        r1 = java.io.File.separator;	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r1 = r12.endsWith(r1);	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        if (r1 != 0) goto L_0x001e;
    L_0x000b:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r1.<init>();	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r1 = r1.append(r12);	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r3 = java.io.File.separator;	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r12 = r1.toString();	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
    L_0x001e:
        if (r13 == 0) goto L_0x003b;
    L_0x0020:
        r1 = java.io.File.separator;	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r1 = r13.endsWith(r1);	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        if (r1 != 0) goto L_0x003b;
    L_0x0028:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r1.<init>();	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r1 = r1.append(r13);	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r3 = java.io.File.separator;	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r13 = r1.toString();	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
    L_0x003b:
        r4 = new java.util.HashMap;	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r4.<init>();	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r5 = a(r11);	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r3 = new java.util.zip.ZipFile;	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r3.<init>(r12);	 Catch:{ Exception -> 0x0136, all -> 0x0130 }
        r6 = r3.entries();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r7 = new byte[r1];	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
    L_0x0051:
        r1 = r6.hasMoreElements();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        if (r1 == 0) goto L_0x011d;
    L_0x0057:
        r1 = r6.nextElement();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r0 = r1;
        r0 = (java.util.zip.ZipEntry) r0;	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r2 = r0;
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r8 = "ze.getName() = ";
        r1.<init>(r8);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r8 = r2.getName();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        com.xiaomi.channel.commonutils.logger.b.b(r1);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1 = r2.getName();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r8 = "lib/";
        r1 = r1.startsWith(r8);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        if (r1 == 0) goto L_0x0051;
    L_0x0083:
        r1 = r2.isDirectory();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        if (r1 != 0) goto L_0x0051;
    L_0x0089:
        r1 = r2.getName();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r8 = a(r1);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1 = r2.getName();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r9 = b(r1);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1 = r4.get(r8);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r10 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        if (r10 != 0) goto L_0x00b1;
    L_0x00a5:
        r10 = a(r5, r9);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        if (r10 < 0) goto L_0x0051;
    L_0x00ab:
        r1 = a(r5, r1);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        if (r10 >= r1) goto L_0x0051;
    L_0x00b1:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r10 = "use abi ";
        r1.<init>(r10);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1 = r1.append(r9);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        com.xiaomi.channel.commonutils.logger.b.b(r1);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r4.put(r8, r9);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r9.<init>();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r9 = r9.append(r13);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r8 = r9.append(r8);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r8 = r8.toString();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1.<init>(r8);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r8 = r1.exists();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        if (r8 == 0) goto L_0x00e6;
    L_0x00e3:
        r1.delete();	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
    L_0x00e6:
        r8 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r8.<init>(r1);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r2 = r3.getInputStream(r2);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
    L_0x00f4:
        r2 = 0;
        r9 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = r1.read(r7, r2, r9);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        r9 = -1;
        if (r2 == r9) goto L_0x010e;
    L_0x00fe:
        r9 = 0;
        r8.write(r7, r9, r2);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        goto L_0x00f4;
    L_0x0103:
        r1 = move-exception;
        r2 = r3;
    L_0x0105:
        r1.printStackTrace();	 Catch:{ all -> 0x0133 }
        if (r2 == 0) goto L_0x010d;
    L_0x010a:
        r2.close();	 Catch:{ Exception -> 0x0126 }
    L_0x010d:
        return;
    L_0x010e:
        com.xiaomi.channel.commonutils.file.a.a(r1);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        com.xiaomi.channel.commonutils.file.a.a(r8);	 Catch:{ Exception -> 0x0103, all -> 0x0116 }
        goto L_0x0051;
    L_0x0116:
        r1 = move-exception;
    L_0x0117:
        if (r3 == 0) goto L_0x011c;
    L_0x0119:
        r3.close();	 Catch:{ Exception -> 0x012b }
    L_0x011c:
        throw r1;
    L_0x011d:
        r3.close();	 Catch:{ Exception -> 0x0121 }
        goto L_0x010d;
    L_0x0121:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x010d;
    L_0x0126:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x010d;
    L_0x012b:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x011c;
    L_0x0130:
        r1 = move-exception;
        r3 = r2;
        goto L_0x0117;
    L_0x0133:
        r1 = move-exception;
        r3 = r2;
        goto L_0x0117;
    L_0x0136:
        r1 = move-exception;
        goto L_0x0105;
        */
    }

    public static String b(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            Field declaredField = Class.forName("android.content.pm.ApplicationInfo").getDeclaredField("primaryCpuAbi");
            declaredField.setAccessible(true);
            return (String) declaredField.get(applicationInfo);
        } catch (Throwable th) {
            return null;
        }
    }

    private static String b(String str) {
        if (str != null) {
            String[] split = str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            if (split != null && split.length > 1) {
                return split[split.length - 2];
            }
        }
        return "armeabi";
    }
}
