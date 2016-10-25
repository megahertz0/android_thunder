package com.xunlei.downloadlib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReLinker {
    private static final int COPY_BUFFER_SIZE = 4096;
    private static final String LIB_DIR = "lib";
    private static final int MAX_TRIES = 5;
    private static final String TAG = "Relinker";

    private static class MissingLibraryException extends RuntimeException {
        public MissingLibraryException(String str) {
            super(str);
        }
    }

    private ReLinker() {
    }

    public static void loadLibrary(Context context, String str) {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Given library is either null or empty");
        } else {
            try {
                String str2;
                try {
                    str2 = (String) ApplicationInfo.class.getField("nativeLibraryDir").get(context.getApplicationInfo());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    str2 = null;
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                    str2 = null;
                } catch (NoSuchFieldException e3) {
                    e3.printStackTrace();
                    str2 = null;
                }
                if (str2 != null) {
                    System.load(new File(str2, System.mapLibraryName(str)).getAbsolutePath());
                    return;
                }
                throw new UnsatisfiedLinkError(new StringBuilder("path is null,when load libray").append(str).toString());
            } catch (UnsatisfiedLinkError e4) {
                XLLog.i(TAG, new StringBuilder("loadLibrary, UnsatisfiedLinkError1=").append(e4.getMessage()).toString());
                try {
                    File workaroundLibFile = getWorkaroundLibFile(context, str);
                    XLLog.i(TAG, new StringBuilder("loadLibrary, workaroundFile=").append(workaroundLibFile.getPath()).toString());
                    if (!workaroundLibFile.exists()) {
                        unpackLibrary(context, str);
                    }
                    System.load(workaroundLibFile.getAbsolutePath());
                } catch (Exception e5) {
                    XLLog.i(TAG, new StringBuilder("Exception=").append(e5.getMessage()).toString());
                    System.loadLibrary(str);
                }
            }
        }
    }

    private static File getWorkaroundLibDir(Context context) {
        return context.getDir(LIB_DIR, 0);
    }

    private static File getWorkaroundLibFile(Context context, String str) {
        return new File(getWorkaroundLibDir(context), System.mapLibraryName(str));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"NewApi"})
    private static void unpackLibrary(android.content.Context r13, java.lang.String r14) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadlib.ReLinker.unpackLibrary(android.content.Context, java.lang.String):void");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.xunlei.downloadlib.ReLinker.unpackLibrary(android.content.Context, java.lang.String):void
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
        r12 = 5;
        r2 = 0;
        r5 = 0;
        r0 = "Relinker";
        r1 = "unpackLibrary";
        com.xunlei.downloadlib.XLLog.i(r0, r1);
        r3 = r13.getApplicationInfo();	 Catch:{ Exception -> 0x01b9, all -> 0x01b1 }
        r0 = r5;
    L_0x0011:
        r1 = r0 + 1;
        if (r0 >= r12) goto L_0x01ce;
    L_0x0015:
        r4 = new java.util.zip.ZipFile;	 Catch:{ IOException -> 0x0042 }
        r0 = new java.io.File;	 Catch:{ IOException -> 0x0042 }
        r6 = r3.sourceDir;	 Catch:{ IOException -> 0x0042 }
        r0.<init>(r6);	 Catch:{ IOException -> 0x0042 }
        r6 = 1;
        r4.<init>(r0, r6);	 Catch:{ IOException -> 0x0042 }
    L_0x0022:
        if (r4 != 0) goto L_0x0048;
    L_0x0024:
        r0 = "Relinker";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r2 = "zipFile == null, path=";
        r1.<init>(r2);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r2 = r3.sourceDir;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        com.xunlei.downloadlib.XLLog.i(r0, r1);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        if (r4 == 0) goto L_0x0041;
    L_0x003e:
        r4.close();	 Catch:{ IOException -> 0x01ab }
    L_0x0041:
        return;
    L_0x0042:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x01b9, all -> 0x01b1 }
        r0 = r1;
        goto L_0x0011;
    L_0x0048:
        r0 = r5;
    L_0x0049:
        r6 = r0 + 1;
        if (r0 >= r12) goto L_0x014b;
    L_0x004d:
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = 21;
        if (r0 < r1) goto L_0x005a;
    L_0x0053:
        r0 = getSupportedABIs();	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = r0.length;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        if (r1 > 0) goto L_0x01cb;
    L_0x005a:
        r0 = 2;
        r0 = new java.lang.String[r0];	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = 0;
        r3 = android.os.Build.CPU_ABI;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0[r1] = r3;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = 1;
        r3 = android.os.Build.CPU_ABI2;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0[r1] = r3;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r7 = r0;
    L_0x0068:
        r8 = r7.length;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r3 = r5;
        r0 = r2;
        r1 = r2;
    L_0x006c:
        if (r3 >= r8) goto L_0x00ae;
    L_0x006e:
        r0 = r7[r3];	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r9 = "lib/";
        r1.<init>(r9);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0 = r1.append(r0);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = "/";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = java.lang.System.mapLibraryName(r14);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = r4.getEntry(r0);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r9 = "Relinker";
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r11 = "zipFile.getEntry, jniNameInApk=";
        r10.<init>(r11);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r10 = r10.append(r0);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r10 = r10.toString();	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        com.xunlei.downloadlib.XLLog.i(r9, r10);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        if (r1 != 0) goto L_0x00ae;
    L_0x00ab:
        r3 = r3 + 1;
        goto L_0x006c;
    L_0x00ae:
        if (r1 != 0) goto L_0x00f4;
    L_0x00b0:
        r1 = "Relinker";
        r2 = "Does not exist in the APK";
        com.xunlei.downloadlib.XLLog.i(r1, r2);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        if (r0 == 0) goto L_0x00e7;
    L_0x00bb:
        r1 = new com.xunlei.downloadlib.ReLinker$MissingLibraryException;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1.<init>(r0);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        throw r1;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
    L_0x00c1:
        r0 = move-exception;
        r2 = r4;
    L_0x00c3:
        r1 = "Relinker";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01b5 }
        r4 = "unpackLibrary, Exception=";
        r3.<init>(r4);	 Catch:{ all -> 0x01b5 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x01b5 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x01b5 }
        r0 = r0.toString();	 Catch:{ all -> 0x01b5 }
        com.xunlei.downloadlib.XLLog.i(r1, r0);	 Catch:{ all -> 0x01b5 }
        if (r2 == 0) goto L_0x0041;
    L_0x00df:
        r2.close();	 Catch:{ IOException -> 0x00e4 }
        goto L_0x0041;
    L_0x00e4:
        r0 = move-exception;
        goto L_0x0041;
    L_0x00e7:
        r0 = new com.xunlei.downloadlib.ReLinker$MissingLibraryException;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0.<init>(r14);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        throw r0;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
    L_0x00ed:
        r0 = move-exception;
    L_0x00ee:
        if (r4 == 0) goto L_0x00f3;
    L_0x00f0:
        r4.close();	 Catch:{ IOException -> 0x01ae }
    L_0x00f3:
        throw r0;
    L_0x00f4:
        r0 = getWorkaroundLibFile(r13, r14);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0.delete();	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r3 = r0.createNewFile();	 Catch:{ IOException -> 0x010d }
        if (r3 != 0) goto L_0x012b;
    L_0x0101:
        r0 = "Relinker";
        r1 = "outputFile.createNewFile() failed";
        com.xunlei.downloadlib.XLLog.i(r0, r1);	 Catch:{ IOException -> 0x010d }
        r0 = r6;
        goto L_0x0049;
    L_0x010d:
        r0 = move-exception;
        r1 = "Relinker";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r7 = "IOException ignored, ";
        r3.<init>(r7);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        com.xunlei.downloadlib.XLLog.i(r1, r0);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0 = r6;
        goto L_0x0049;
    L_0x012b:
        r3 = r4.getInputStream(r1);	 Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x017b, all -> 0x01a1 }
        r1 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x01c6, IOException -> 0x01c1, all -> 0x01bc }
        r1.<init>(r0);	 Catch:{ FileNotFoundException -> 0x01c6, IOException -> 0x01c1, all -> 0x01bc }
        copy(r3, r1);	 Catch:{ FileNotFoundException -> 0x01c9, IOException -> 0x01c4 }
        closeSilently(r3);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        closeSilently(r1);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = 1;
        r2 = 0;
        r0.setReadable(r1, r2);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = 1;
        r2 = 0;
        r0.setExecutable(r1, r2);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r1 = 1;
        r0.setWritable(r1);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
    L_0x014b:
        if (r4 == 0) goto L_0x0041;
    L_0x014d:
        r4.close();	 Catch:{ IOException -> 0x0152 }
        goto L_0x0041;
    L_0x0152:
        r0 = move-exception;
        goto L_0x0041;
    L_0x0155:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
    L_0x0158:
        r7 = "Relinker";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01bf }
        r9 = " copy(inputStream, fileOut), FileNotFoundException, ";
        r8.<init>(r9);	 Catch:{ all -> 0x01bf }
        r0 = r0.getMessage();	 Catch:{ all -> 0x01bf }
        r0 = r8.append(r0);	 Catch:{ all -> 0x01bf }
        r0 = r0.toString();	 Catch:{ all -> 0x01bf }
        com.xunlei.downloadlib.XLLog.i(r7, r0);	 Catch:{ all -> 0x01bf }
        closeSilently(r3);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        closeSilently(r1);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0 = r6;
        goto L_0x0049;
    L_0x017b:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
    L_0x017e:
        r7 = "Relinker";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01bf }
        r9 = " copy(inputStream, fileOut), IOException, ";
        r8.<init>(r9);	 Catch:{ all -> 0x01bf }
        r0 = r0.getMessage();	 Catch:{ all -> 0x01bf }
        r0 = r8.append(r0);	 Catch:{ all -> 0x01bf }
        r0 = r0.toString();	 Catch:{ all -> 0x01bf }
        com.xunlei.downloadlib.XLLog.i(r7, r0);	 Catch:{ all -> 0x01bf }
        closeSilently(r3);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        closeSilently(r1);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        r0 = r6;
        goto L_0x0049;
    L_0x01a1:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
    L_0x01a4:
        closeSilently(r3);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        closeSilently(r1);	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
        throw r0;	 Catch:{ Exception -> 0x00c1, all -> 0x00ed }
    L_0x01ab:
        r0 = move-exception;
        goto L_0x0041;
    L_0x01ae:
        r1 = move-exception;
        goto L_0x00f3;
    L_0x01b1:
        r0 = move-exception;
        r4 = r2;
        goto L_0x00ee;
    L_0x01b5:
        r0 = move-exception;
        r4 = r2;
        goto L_0x00ee;
    L_0x01b9:
        r0 = move-exception;
        goto L_0x00c3;
    L_0x01bc:
        r0 = move-exception;
        r1 = r2;
        goto L_0x01a4;
    L_0x01bf:
        r0 = move-exception;
        goto L_0x01a4;
    L_0x01c1:
        r0 = move-exception;
        r1 = r2;
        goto L_0x017e;
    L_0x01c4:
        r0 = move-exception;
        goto L_0x017e;
    L_0x01c6:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0158;
    L_0x01c9:
        r0 = move-exception;
        goto L_0x0158;
    L_0x01cb:
        r7 = r0;
        goto L_0x0068;
    L_0x01ce:
        r4 = r2;
        goto L_0x0022;
        */
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String[] getSupportedABIs() {
        String[] strArr = new String[0];
        try {
            return (String[]) Build.class.getField("SUPPORTED_ABIS").get(null);
        } catch (IllegalAccessException e) {
            XLLog.i(TAG, new StringBuilder(" getSupportedABIs IllegalAccessException, ").append(e.getMessage()).toString());
            e.printStackTrace();
            return strArr;
        } catch (IllegalArgumentException e2) {
            XLLog.i(TAG, new StringBuilder(" getSupportedABIs IllegalArgumentException, ").append(e2.getMessage()).toString());
            e2.printStackTrace();
            return strArr;
        } catch (NoSuchFieldException e3) {
            XLLog.i(TAG, new StringBuilder(" getSupportedABIs NoSuchFieldException, ").append(e3.getMessage()).toString());
            e3.printStackTrace();
            return strArr;
        }
    }
}
