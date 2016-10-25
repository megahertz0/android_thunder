package com.tencent.bugly.crashreport.crash.anr;

import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.a;
import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.b;
import com.tencent.bugly.proguard.w;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

// compiled from: BUGLY
public class TraceFileHelper {

    // compiled from: BUGLY
    public static interface b {
        boolean a(long j);

        boolean a(long j, long j2, String str);

        boolean a(String str, int i, String str2, String str3);
    }

    // compiled from: BUGLY
    final class AnonymousClass_1 implements b {
        private /* synthetic */ a a;
        private /* synthetic */ boolean b;

        AnonymousClass_1(a aVar, boolean z) {
            this.a = aVar;
            this.b = z;
        }

        public final boolean a(String str, int i, String str2, String str3) {
            w.c("new thread %s", str);
            if (this.a.a > 0 && this.a.c > 0 && this.a.b != null) {
                if (this.a.d == null) {
                    this.a.d = new HashMap();
                }
                this.a.d.put(str, new Object{str2, str3, String.valueOf(i)});
            }
            return true;
        }

        public final boolean a(long j, long j2, String str) {
            w.c("new process %s", str);
            if (!str.equals(str)) {
                return true;
            }
            this.a.a = j;
            this.a.b = str;
            this.a.c = j2;
            return this.b;
        }

        public final boolean a(long j) {
            w.c("process end %d", Long.valueOf(j));
            return this.a.a <= 0 || this.a.c <= 0 || this.a.b == null;
        }
    }

    // compiled from: BUGLY
    final class AnonymousClass_2 implements b {
        private /* synthetic */ a a;
        private /* synthetic */ boolean b;

        AnonymousClass_2(a aVar, boolean z) {
            this.a = aVar;
            this.b = z;
        }

        public final boolean a(String str, int i, String str2, String str3) {
            w.c("new thread %s", str);
            if (this.a.d == null) {
                this.a.d = new HashMap();
            }
            this.a.d.put(str, new Object{str2, str3, String.valueOf(i)});
            return true;
        }

        public final boolean a(long j, long j2, String str) {
            w.c("new process %s", str);
            this.a.a = j;
            this.a.b = str;
            this.a.c = j2;
            return this.b;
        }

        public final boolean a(long j) {
            w.c("process end %d", Long.valueOf(j));
            return false;
        }
    }

    // compiled from: BUGLY
    public static class a {
        public long a;
        public String b;
        public long c;
        public Map<String, String[]> d;
    }

    public static a readTargetDumpInfo(String str, String str2, boolean z) {
        if (str == null || str2 == null) {
            return null;
        }
        a aVar = new a();
        readTraceFile(str2, new AnonymousClass_1(aVar, z));
        return (aVar.a <= 0 || aVar.c <= 0 || aVar.b == null) ? null : aVar;
    }

    public static a readFirstDumpInfo(String str, boolean z) {
        if (str == null) {
            w.e("path:%s", str);
            return null;
        }
        a aVar = new a();
        readTraceFile(str, new AnonymousClass_2(aVar, z));
        if (aVar.a > 0 && aVar.c > 0 && aVar.b != null) {
            return aVar;
        }
        w.e("first dump error %s", aVar.a + " " + aVar.c + " " + aVar.b);
        return null;
    }

    public static void readTraceFile(java.lang.String r12, com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.b r13) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readTraceFile(java.lang.String, com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$b):void");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readTraceFile(java.lang.String, com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$b):void
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:54)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:40)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.ProcessClass.process(ProcessClass.java:22)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
*/
        /*
        if (r12 == 0) goto L_0x0004;
    L_0x0002:
        if (r13 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = new java.io.File;
        r0.<init>(r12);
        r1 = r0.exists();
        if (r1 == 0) goto L_0x0004;
    L_0x0010:
        r0.lastModified();
        r0.length();
        r1 = 0;
        r7 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x01d9, all -> 0x01c1 }
        r2 = new java.io.FileReader;	 Catch:{ Exception -> 0x01d9, all -> 0x01c1 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x01d9, all -> 0x01c1 }
        r7.<init>(r2);	 Catch:{ Exception -> 0x01d9, all -> 0x01c1 }
        r0 = "-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}";
        r0 = java.util.regex.Pattern.compile(r0);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = "-{5}\\send\\s\\d+\\s-{5}";
        r8 = java.util.regex.Pattern.compile(r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = "Cmd\\sline:\\s(\\S+)";
        r9 = java.util.regex.Pattern.compile(r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = "\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*";
        r10 = java.util.regex.Pattern.compile(r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r11 = new java.text.SimpleDateFormat;	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = "yyyy-MM-dd HH:mm:ss";
        r2 = java.util.Locale.US;	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r11.<init>(r1, r2);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
    L_0x0047:
        r1 = 1;
        r1 = new java.util.regex.Pattern[r1];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = 0;
        r1[r2] = r0;	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = a(r7, r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        if (r1 == 0) goto L_0x01b0;
    L_0x0053:
        r2 = 1;
        r1 = r1[r2];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = "\\s";
        r1 = r1.split(r2);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = 2;
        r2 = r1[r2];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = java.lang.Long.parseLong(r2);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r4.<init>();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r5 = 4;
        r5 = r1[r5];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r5 = " ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r5 = 5;
        r1 = r1[r5];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r4.append(r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r11.parse(r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r4 = r1.getTime();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = 1;
        r1 = new java.util.regex.Pattern[r1];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r6 = 0;
        r1[r6] = r9;	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = a(r7, r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        if (r1 != 0) goto L_0x00ab;
    L_0x009a:
        r7.close();	 Catch:{ IOException -> 0x009f }
        goto L_0x0004;
    L_0x009f:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.w.a(r0);
        if (r1 != 0) goto L_0x0004;
    L_0x00a6:
        r0.printStackTrace();
        goto L_0x0004;
    L_0x00ab:
        r6 = 1;
        r1 = r1[r6];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r9.matcher(r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1.find();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r6 = 1;
        r1.group(r6);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r6 = 1;
        r6 = r1.group(r6);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r13;
        r1 = r1.a(r2, r4, r6);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        if (r1 != 0) goto L_0x00da;
    L_0x00c9:
        r7.close();	 Catch:{ IOException -> 0x00ce }
        goto L_0x0004;
    L_0x00ce:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.w.a(r0);
        if (r1 != 0) goto L_0x0004;
    L_0x00d5:
        r0.printStackTrace();
        goto L_0x0004;
    L_0x00da:
        r1 = 2;
        r1 = new java.util.regex.Pattern[r1];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = 0;
        r1[r2] = r10;	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = 1;
        r1[r2] = r8;	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = a(r7, r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        if (r1 == 0) goto L_0x0047;
    L_0x00e9:
        r2 = 0;
        r2 = r1[r2];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        if (r2 != r10) goto L_0x0184;
    L_0x00ee:
        r2 = 1;
        r1 = r1[r2];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = "\".+\"";
        r2 = java.util.regex.Pattern.compile(r2);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = r2.matcher(r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2.find();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = r2.group();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r3 = 1;
        r4 = r2.length();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r4 = r4 + -1;
        r2 = r2.substring(r3, r4);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r3 = "NATIVE";
        r1.contains(r3);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r3 = "tid=\\d+";
        r3 = java.util.regex.Pattern.compile(r3);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r3.matcher(r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1.find();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r1.group();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r3 = "=";
        r3 = r1.indexOf(r3);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r3 = r3 + 1;
        r1 = r1.substring(r3);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r3 = a(r7);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r4 = b(r7);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r13.a(r2, r1, r3, r4);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        goto L_0x00da;
    L_0x0147:
        r0 = move-exception;
        r1 = r7;
    L_0x0149:
        r2 = "trace open fail:%s : %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x01d6 }
        r4 = 0;
        r5 = r0.getClass();	 Catch:{ all -> 0x01d6 }
        r5 = r5.getName();	 Catch:{ all -> 0x01d6 }
        r3[r4] = r5;	 Catch:{ all -> 0x01d6 }
        r4 = 1;
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01d6 }
        r5.<init>();	 Catch:{ all -> 0x01d6 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x01d6 }
        r0 = r5.append(r0);	 Catch:{ all -> 0x01d6 }
        r0 = r0.toString();	 Catch:{ all -> 0x01d6 }
        r3[r4] = r0;	 Catch:{ all -> 0x01d6 }
        com.tencent.bugly.proguard.w.d(r2, r3);	 Catch:{ all -> 0x01d6 }
        if (r1 == 0) goto L_0x0004;
    L_0x0173:
        r1.close();	 Catch:{ IOException -> 0x0178 }
        goto L_0x0004;
    L_0x0178:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.w.a(r0);
        if (r1 != 0) goto L_0x0004;
    L_0x017f:
        r0.printStackTrace();
        goto L_0x0004;
    L_0x0184:
        r2 = 1;
        r1 = r1[r2];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = "\\s";
        r1 = r1.split(r2);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = 2;
        r1 = r1[r2];	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r2 = java.lang.Long.parseLong(r1);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        r1 = r13.a(r2);	 Catch:{ Exception -> 0x0147, all -> 0x01d4 }
        if (r1 != 0) goto L_0x0047;
    L_0x019f:
        r7.close();	 Catch:{ IOException -> 0x01a4 }
        goto L_0x0004;
    L_0x01a4:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.w.a(r0);
        if (r1 != 0) goto L_0x0004;
    L_0x01ab:
        r0.printStackTrace();
        goto L_0x0004;
    L_0x01b0:
        r7.close();	 Catch:{ IOException -> 0x01b5 }
        goto L_0x0004;
    L_0x01b5:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.w.a(r0);
        if (r1 != 0) goto L_0x0004;
    L_0x01bc:
        r0.printStackTrace();
        goto L_0x0004;
    L_0x01c1:
        r0 = move-exception;
        r7 = r1;
    L_0x01c3:
        if (r7 == 0) goto L_0x01c8;
    L_0x01c5:
        r7.close();	 Catch:{ IOException -> 0x01c9 }
    L_0x01c8:
        throw r0;
    L_0x01c9:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.w.a(r1);
        if (r2 != 0) goto L_0x01c8;
    L_0x01d0:
        r1.printStackTrace();
        goto L_0x01c8;
    L_0x01d4:
        r0 = move-exception;
        goto L_0x01c3;
    L_0x01d6:
        r0 = move-exception;
        r7 = r1;
        goto L_0x01c3;
    L_0x01d9:
        r0 = move-exception;
        goto L_0x0149;
        */
    }

    private static Object[] a(BufferedReader bufferedReader, Pattern... patternArr) throws IOException {
        if (bufferedReader == null || patternArr == null) {
            return null;
        }
        while (true) {
            CharSequence readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            int length = patternArr.length;
            for (int i = 0; i < length; i++) {
                if (patternArr[i].matcher(readLine).matches()) {
                    return new Object[]{patternArr[i], readLine};
                }
            }
        }
    }

    private static String a(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            stringBuffer.append(readLine + "\n");
        }
        return stringBuffer.toString();
    }

    private static String b(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null || readLine.trim().length() <= 0) {
                break;
            }
            stringBuffer.append(readLine + "\n");
        }
        return stringBuffer.toString();
    }
}
