package android.support.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.h;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

// compiled from: MultiDexExtractor.java
final class b {
    private static Method a;

    static List<File> a(Context context, ApplicationInfo applicationInfo, File file, boolean z) throws IOException {
        List<File> a;
        Object obj = null;
        new StringBuilder("MultiDexExtractor.load(").append(applicationInfo.sourceDir).append(", ").append(z).append(SocializeConstants.OP_CLOSE_PAREN);
        File file2 = new File(applicationInfo.sourceDir);
        long a2 = d.a(file2);
        if (a2 == -1) {
            a2--;
        }
        if (!z) {
            SharedPreferences a3 = a(context);
            if (!(a3.getLong("timestamp", -1) == b(file2) && a3.getLong("crc", -1) == a2)) {
                obj = 1;
            }
            if (obj == null) {
                try {
                    a = a(context, file2, file);
                } catch (IOException e) {
                }
                new StringBuilder("load found ").append(a.size()).append(" secondary dex files");
                return a;
            }
        }
        a = a(file2, file);
        long b = b(file2);
        int size = a.size() + 1;
        Editor edit = a(context).edit();
        edit.putLong("timestamp", b);
        edit.putLong("crc", a2);
        edit.putInt("dex.number", size);
        if (a != null) {
            try {
                a.invoke(edit, new Object[0]);
            } catch (InvocationTargetException e2) {
            } catch (IllegalAccessException e3) {
            }
            new StringBuilder("load found ").append(a.size()).append(" secondary dex files");
            return a;
        }
        edit.commit();
        new StringBuilder("load found ").append(a.size()).append(" secondary dex files");
        return a;
    }

    private static List<File> a(Context context, File file, File file2) throws IOException {
        String str = file.getName() + ".classes";
        int i = a(context).getInt("dex.number", 1);
        List<File> arrayList = new ArrayList(i);
        int i2 = XZBDevice.DOWNLOAD_LIST_RECYCLE;
        while (i2 <= i) {
            File file3 = new File(file2, str + i2 + ".zip");
            if (file3.isFile()) {
                arrayList.add(file3);
                if (a(file3)) {
                    i2++;
                } else {
                    new StringBuilder("Invalid zip file: ").append(file3);
                    throw new IOException("Invalid ZIP file.");
                }
            }
            throw new IOException(new StringBuilder("Missing extracted secondary dex file '").append(file3.getPath()).append("'").toString());
        }
        return arrayList;
    }

    private static long b(File file) {
        long lastModified = file.lastModified();
        return lastModified == -1 ? lastModified - 1 : lastModified;
    }

    private static List<File> a(File file, File file2) throws IOException {
        String str = file.getName() + ".classes";
        a(file2, str);
        List<File> arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        try {
            ZipEntry entry = zipFile.getEntry("classes2.dex");
            int i = 2;
            while (entry != null) {
                boolean z;
                File file3 = new File(file2, str + i + ".zip");
                arrayList.add(file3);
                new StringBuilder("Extraction is needed for file ").append(file3);
                Object obj = null;
                int i2 = 0;
                while (i2 < 3 && !z) {
                    int i3 = i2 + 1;
                    a(zipFile, entry, file3, str);
                    boolean a = a(file3);
                    new StringBuilder("Extraction ").append(a ? MsgConstant.KEY_SUCCESS : e.b).append(" - length ").append(file3.getAbsolutePath()).append(": ").append(file3.length());
                    if (!a) {
                        file3.delete();
                        if (file3.exists()) {
                            new StringBuilder("Failed to delete corrupted secondary dex '").append(file3.getPath()).append("'");
                            z = a;
                            i2 = i3;
                        }
                    }
                    z = a;
                    i2 = i3;
                }
                if (z) {
                    i2 = i + 1;
                    entry = zipFile.getEntry(new StringBuilder("classes").append(i2).append(".dex").toString());
                    i = i2;
                } else {
                    throw new IOException(new StringBuilder("Could not create zip file ").append(file3.getAbsolutePath()).append(" for secondary dex (").append(i).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                }
            }
            try {
                zipFile.close();
            } catch (IOException e) {
            }
            return arrayList;
        } catch (Throwable th) {
            try {
                zipFile.close();
            } catch (IOException e2) {
            }
        }
    }

    private static SharedPreferences a(Context context) {
        return context.getSharedPreferences("multidex.version", VERSION.SDK_INT < 11 ? 0 : XZBDevice.DOWNLOAD_LIST_ALL);
    }

    private static void a(File file, String str) throws IOException {
        c(file.getParentFile());
        c(file);
        File[] listFiles = file.listFiles(new c(str));
        if (listFiles == null) {
            new StringBuilder("Failed to list secondary dex dir content (").append(file.getPath()).append(").");
            return;
        }
        for (File file2 : listFiles) {
            new StringBuilder("Trying to delete old file ").append(file2.getPath()).append(" of size ").append(file2.length());
            if (file2.delete()) {
                new StringBuilder("Deleted old file ").append(file2.getPath());
            } else {
                new StringBuilder("Failed to delete old file ").append(file2.getPath());
            }
        }
    }

    private static void c(File file) throws IOException {
        file.mkdir();
        if (!file.isDirectory()) {
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                new StringBuilder("Failed to create dir ").append(file.getPath()).append(". Parent file is null.");
            } else {
                new StringBuilder("Failed to create dir ").append(file.getPath()).append(". parent file is a dir ").append(parentFile.isDirectory()).append(", a file ").append(parentFile.isFile()).append(", exists ").append(parentFile.exists()).append(", readable ").append(parentFile.canRead()).append(", writable ").append(parentFile.canWrite());
            }
            throw new IOException(new StringBuilder("Failed to create cache directory ").append(file.getPath()).toString());
        }
    }

    private static void a(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException, FileNotFoundException {
        Closeable inputStream = zipFile.getInputStream(zipEntry);
        File createTempFile = File.createTempFile(str, ".zip", file.getParentFile());
        new StringBuilder("Extracting ").append(createTempFile.getPath());
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(createTempFile)));
            ZipEntry zipEntry2 = new ZipEntry("classes.dex");
            zipEntry2.setTime(zipEntry.getTime());
            zipOutputStream.putNextEntry(zipEntry2);
            byte[] bArr = new byte[16384];
            for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            new StringBuilder("Renaming to ").append(file.getPath());
            if (createTempFile.renameTo(file)) {
                a(inputStream);
                createTempFile.delete();
                return;
            }
            throw new IOException(new StringBuilder("Failed to rename \"").append(createTempFile.getAbsolutePath()).append("\" to \"").append(file.getAbsolutePath()).append(h.f).toString());
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean a(java.io.File r2) {
        throw new UnsupportedOperationException("Method not decompiled: android.support.multidex.b.a(java.io.File):boolean");
        /*
        r0 = new java.util.zip.ZipFile;	 Catch:{ ZipException -> 0x001c, IOException -> 0x0034 }
        r0.<init>(r2);	 Catch:{ ZipException -> 0x001c, IOException -> 0x0034 }
        r0.close();	 Catch:{ IOException -> 0x000a, ZipException -> 0x001c }
        r0 = 1;
    L_0x0009:
        return r0;
    L_0x000a:
        r0 = move-exception;
        r0 = new java.lang.StringBuilder;	 Catch:{ ZipException -> 0x001c, IOException -> 0x0034 }
        r1 = "Failed to close zip file: ";
        r0.<init>(r1);	 Catch:{ ZipException -> 0x001c, IOException -> 0x0034 }
        r1 = r2.getAbsolutePath();	 Catch:{ ZipException -> 0x001c, IOException -> 0x0034 }
        r0.append(r1);	 Catch:{ ZipException -> 0x001c, IOException -> 0x0034 }
    L_0x001a:
        r0 = 0;
        goto L_0x0009;
    L_0x001c:
        r0 = move-exception;
        r0 = new java.lang.StringBuilder;
        r1 = "File ";
        r0.<init>(r1);
        r1 = r2.getAbsolutePath();
        r0 = r0.append(r1);
        r1 = " is not a valid zip file.";
        r0.append(r1);
        goto L_0x001a;
    L_0x0034:
        r0 = move-exception;
        r0 = new java.lang.StringBuilder;
        r1 = "Got an IOException trying to open zip file: ";
        r0.<init>(r1);
        r1 = r2.getAbsolutePath();
        r0.append(r1);
        goto L_0x001a;
        */
    }

    private static void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }

    static {
        try {
            a = Editor.class.getMethod("apply", new Class[0]);
        } catch (NoSuchMethodException e) {
            a = null;
        }
    }
}
