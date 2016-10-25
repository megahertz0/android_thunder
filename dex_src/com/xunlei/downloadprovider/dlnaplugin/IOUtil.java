package com.xunlei.downloadprovider.dlnaplugin;

import android.text.TextUtils;
import anet.channel.security.ISecurity;
import com.umeng.a;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.zip.CRC32;
import java.util.zip.ZipFile;

public class IOUtil {
    public static final long CRC32_VALUE_INVALID = -1;
    private static final char[] HEX_DIGITS;
    public static String md5;

    static {
        HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        md5 = "c141cd93c03faea6efd293f8e0f80ea9";
    }

    public static boolean contentEquals(InputStream inputStream, InputStream inputStream2) throws IOException {
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        if (!(inputStream2 instanceof BufferedInputStream)) {
            inputStream2 = new BufferedInputStream(inputStream2);
        }
        for (int read = inputStream.read(); -1 != read; read = inputStream.read()) {
            if (read != inputStream2.read()) {
                return false;
            }
        }
        return inputStream2.read() == -1;
    }

    public static boolean contentEquals(File file, String str) {
        Exception e;
        Closeable closeable;
        Throwable th;
        Closeable closeable2 = null;
        boolean z = false;
        if (file == null || !file.exists() || str == null) {
            return false;
        }
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                InputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
            } catch (Exception e2) {
                e = e2;
                closeable = null;
                InputStream inputStream = fileInputStream;
                e.printStackTrace();
                closeStream(closeable2);
                closeStream(closeable);
                return z;
            } catch (Throwable th2) {
                th = th2;
                closeStream(r4);
                closeStream(closeable2);
                throw th;
            }
            try {
                z = contentEquals(fileInputStream, byteArrayInputStream);
                closeStream(fileInputStream);
                closeStream(byteArrayInputStream);
                return z;
            } catch (Exception e3) {
                e = e3;
                inputStream = fileInputStream;
            } catch (Throwable th3) {
                th = th3;
                inputStream = byteArrayInputStream;
                closeStream(r4);
                closeStream(closeable2);
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            closeable = null;
            try {
                e.printStackTrace();
                closeStream(closeable2);
                closeStream(closeable);
                return z;
            } catch (Throwable th4) {
                th = th4;
                Closeable closeable3 = closeable2;
                closeable2 = closeable;
            }
        } catch (Throwable th5) {
            th = th5;
            closeable3 = null;
            closeStream(closeable3);
            closeStream(closeable2);
            throw th;
        }
    }

    public static byte[] readByte(String str) {
        byte[] bArr = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        try {
            Closeable fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr2 = new byte[fileInputStream.available()];
                fileInputStream.read(bArr2);
                closeStream(fileInputStream);
                bArr = bArr2;
                return bArr;
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            try {
                Exception e3;
                e3.printStackTrace();
                closeStream(fileInputStream);
                return bArr;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        } catch (Throwable th3) {
            fileInputStream = null;
            th2 = th3;
            closeStream(fileInputStream);
            throw th2;
        }
    }

    public static void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeZipFile(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static long getCRC32Value(File file) {
        long j = CRC32_VALUE_INVALID;
        if (file == null || !file.exists()) {
            return CRC32_VALUE_INVALID;
        }
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                j = getCRC32Value(fileInputStream);
                closeStream(fileInputStream);
                return j;
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            r3 = null;
            try {
                Exception e3;
                Closeable closeable;
                e3.printStackTrace();
                closeStream(closeable);
                return j;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        } catch (Throwable th3) {
            th2 = th3;
            closeable = null;
            closeStream(closeable);
            throw th2;
        }
    }

    public static long getCRC32Value(InputStream inputStream) throws Exception {
        byte[] bArr = new byte[8192];
        CRC32 crc32 = new CRC32();
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return crc32.getValue();
            }
            crc32.update(bArr, 0, read);
        }
    }

    public static String getFileMd5(String str) {
        byte[] bArr = new byte[1024];
        String str2 = a.d;
        try {
            InputStream fileInputStream = new FileInputStream(str);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    bufferedInputStream.close();
                    fileInputStream.close();
                    return toHexString(instance.digest());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static String toHexString(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append(HEX_DIGITS[(bArr[i] & 240) >>> 4]);
            stringBuilder.append(HEX_DIGITS[bArr[i] & 15]);
        }
        return stringBuilder.toString();
    }
}
