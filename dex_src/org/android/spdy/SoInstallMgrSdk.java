package org.android.spdy;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class SoInstallMgrSdk {
    private static final String ARMEABI = "armeabi";
    private static final int EventID_SO_INIT = 21033;
    static final String LOGTAG = "INIT_SO";
    private static final String MIPS = "mips";
    private static final String X86 = "x86";
    static Context mContext;

    static {
        mContext = null;
    }

    public static void init(Context context) {
        mContext = context;
    }

    public static boolean initSo(String str, int i) {
        return initSo(str, i, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean initSo(java.lang.String r6, int r7, java.lang.ClassLoader r8) {
        throw new UnsupportedOperationException("Method not decompiled: org.android.spdy.SoInstallMgrSdk.initSo(java.lang.String, int, java.lang.ClassLoader):boolean");
        /*
        r0 = 1;
        r1 = 0;
        if (r8 != 0) goto L_0x0017;
    L_0x0004:
        java.lang.System.loadLibrary(r6);	 Catch:{ Exception -> 0x0042, UnsatisfiedLinkError -> 0x0048, Error -> 0x004e }
    L_0x0007:
        if (r0 != 0) goto L_0x0016;
    L_0x0009:
        r2 = isExist(r6, r7);	 Catch:{ Exception -> 0x0077, UnsatisfiedLinkError -> 0x0082, Error -> 0x0088 }
        if (r2 == 0) goto L_0x0057;
    L_0x000f:
        r2 = _loadUnzipSo(r6, r7, r8);	 Catch:{ Exception -> 0x0077, UnsatisfiedLinkError -> 0x0082, Error -> 0x0088 }
        if (r2 == 0) goto L_0x0054;
    L_0x0015:
        r0 = r2;
    L_0x0016:
        return r0;
    L_0x0017:
        r2 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x0042, UnsatisfiedLinkError -> 0x0048, Error -> 0x004e }
        r3 = 2;
        r3 = new java.lang.Class[r3];	 Catch:{ Exception -> 0x0042, UnsatisfiedLinkError -> 0x0048, Error -> 0x004e }
        r4 = 0;
        r5 = java.lang.String.class;
        r3[r4] = r5;	 Catch:{ Exception -> 0x0042, UnsatisfiedLinkError -> 0x0048, Error -> 0x004e }
        r4 = 1;
        r5 = java.lang.ClassLoader.class;
        r3[r4] = r5;	 Catch:{ Exception -> 0x0042, UnsatisfiedLinkError -> 0x0048, Error -> 0x004e }
        r4 = java.lang.Runtime.class;
        r5 = "loadLibrary";
        r3 = r4.getDeclaredMethod(r5, r3);	 Catch:{ Exception -> 0x0042, UnsatisfiedLinkError -> 0x0048, Error -> 0x004e }
        r4 = 1;
        r3.setAccessible(r4);	 Catch:{ Exception -> 0x0042, UnsatisfiedLinkError -> 0x0048, Error -> 0x004e }
        r4 = 2;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0042, UnsatisfiedLinkError -> 0x0048, Error -> 0x004e }
        r5 = 0;
        r4[r5] = r6;	 Catch:{ Exception -> 0x0042, UnsatisfiedLinkError -> 0x0048, Error -> 0x004e }
        r5 = 1;
        r4[r5] = r8;	 Catch:{ Exception -> 0x0042, UnsatisfiedLinkError -> 0x0048, Error -> 0x004e }
        r3.invoke(r2, r4);	 Catch:{ Exception -> 0x0042, UnsatisfiedLinkError -> 0x0048, Error -> 0x004e }
        goto L_0x0007;
    L_0x0042:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x0007;
    L_0x0048:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x0007;
    L_0x004e:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x0007;
    L_0x0054:
        removeSoIfExit(r6, r7);	 Catch:{ Exception -> 0x0077, UnsatisfiedLinkError -> 0x0082, Error -> 0x0088 }
    L_0x0057:
        r2 = _cpuType();	 Catch:{ Exception -> 0x0077, UnsatisfiedLinkError -> 0x0082, Error -> 0x0088 }
        r3 = "mips";
        r3 = r2.equalsIgnoreCase(r3);	 Catch:{ Exception -> 0x0077, UnsatisfiedLinkError -> 0x0082, Error -> 0x0088 }
        if (r3 != 0) goto L_0x0016;
    L_0x0064:
        r3 = "x86";
        r2 = r2.equalsIgnoreCase(r3);	 Catch:{ Exception -> 0x0077, UnsatisfiedLinkError -> 0x0082, Error -> 0x0088 }
        if (r2 != 0) goto L_0x0016;
    L_0x006d:
        r0 = unZipSelectedFiles(r6, r7, r8);	 Catch:{ ZipException -> 0x0072, IOException -> 0x007d }
        goto L_0x0016;
    L_0x0072:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ Exception -> 0x0077, UnsatisfiedLinkError -> 0x0082, Error -> 0x0088 }
        goto L_0x0016;
    L_0x0077:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x0016;
    L_0x007d:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ Exception -> 0x0077, UnsatisfiedLinkError -> 0x0082, Error -> 0x0088 }
        goto L_0x0016;
    L_0x0082:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x0016;
    L_0x0088:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x0016;
        */
    }

    private static String _getFieldReflectively(Build build, String str) {
        try {
            return Build.class.getField(str).get(build).toString();
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private static String _cpuType() {
        String _getFieldReflectively = _getFieldReflectively(new Build(), "CPU_ABI");
        if (_getFieldReflectively == null || _getFieldReflectively.length() == 0 || _getFieldReflectively.equals("Unknown")) {
            _getFieldReflectively = ARMEABI;
        }
        return _getFieldReflectively.toLowerCase();
    }

    static String _targetSoFile(String str, int i) {
        Context context = mContext;
        if (context == null) {
            return BuildConfig.VERSION_NAME;
        }
        String toString = new StringBuilder("/data/data/").append(context.getPackageName()).append("/files").toString();
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            toString = filesDir.getPath();
        }
        return toString + "/lib" + str + "bk" + i + ".so";
    }

    static void removeSoIfExit(String str, int i) {
        File file = new File(_targetSoFile(str, i));
        if (file.exists()) {
            file.delete();
        }
    }

    static boolean isExist(String str, int i) {
        return new File(_targetSoFile(str, i)).exists();
    }

    static boolean _loadUnzipSo(String str, int i, ClassLoader classLoader) {
        try {
            if (!isExist(str, i)) {
                return true;
            }
            if (classLoader == null) {
                System.load(_targetSoFile(str, i));
                return true;
            }
            Runtime runtime = Runtime.getRuntime();
            Method declaredMethod = Runtime.class.getDeclaredMethod("load", new Class[]{String.class, ClassLoader.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(runtime, new Object[]{_targetSoFile(str, i), classLoader});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return false;
        } catch (Error e3) {
            e3.printStackTrace();
            return false;
        }
    }

    static boolean unZipSelectedFiles(String str, int i, ClassLoader classLoader) throws ZipException, IOException {
        InputStream inputStream;
        Throwable th;
        FileChannel fileChannel;
        FileChannel fileChannel2 = null;
        String toString = new StringBuilder("lib/armeabi/lib").append(str).append(".so").toString();
        try {
            String str2 = BuildConfig.VERSION_NAME;
            Context context = mContext;
            if (context == null) {
                return false;
            }
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo != null) {
                str2 = applicationInfo.sourceDir;
            }
            ZipFile zipFile = new ZipFile(str2);
            Enumeration entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                if (zipEntry.getName().startsWith(toString)) {
                    FileOutputStream openFileOutput;
                    try {
                        removeSoIfExit(str, i);
                        inputStream = zipFile.getInputStream(zipEntry);
                        try {
                            openFileOutput = context.openFileOutput(new StringBuilder("lib").append(str).append("bk").append(i).append(".so").toString(), 0);
                        } catch (Throwable th2) {
                            th = th2;
                            fileChannel = fileChannel2;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (fileChannel2 != null) {
                                fileChannel2.close();
                            }
                            if (openFileOutput != null) {
                                openFileOutput.close();
                            }
                            zipFile.close();
                            throw th;
                        }
                        try {
                            fileChannel2 = openFileOutput.getChannel();
                            byte[] bArr = new byte[1024];
                            int i2 = 0;
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                fileChannel2.write(ByteBuffer.wrap(bArr, 0, read));
                                i2 += read;
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (fileChannel2 != null) {
                                try {
                                    fileChannel2.close();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                            if (openFileOutput != null) {
                                try {
                                    openFileOutput.close();
                                } catch (Exception e22) {
                                    e22.printStackTrace();
                                }
                            }
                            zipFile.close();
                            return i2 > 0 ? _loadUnzipSo(str, i, classLoader) : false;
                        } catch (Throwable th3) {
                            th = th3;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (fileChannel2 != null) {
                                fileChannel2.close();
                            }
                            if (openFileOutput != null) {
                                openFileOutput.close();
                            }
                            zipFile.close();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        fileChannel = fileChannel2;
                        FileChannel fileChannel3 = fileChannel2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (fileChannel2 != null) {
                            try {
                                fileChannel2.close();
                            } catch (Exception e222) {
                                e222.printStackTrace();
                            }
                        }
                        if (openFileOutput != null) {
                            try {
                                openFileOutput.close();
                            } catch (Exception e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        zipFile.close();
                        throw th;
                    }
                }
            }
            return false;
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }
}
