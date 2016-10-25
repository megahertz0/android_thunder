package com.qq.e.comm.util;

import com.taobao.accs.data.Message;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
    public static boolean copyTo(InputStream inputStream, File file) {
        Throwable th;
        Throwable th2;
        OutputStream outputStream = null;
        if (inputStream == null || file == null) {
            return false;
        }
        try {
            if (file.getParentFile().exists() || file.getParentFile().mkdirs()) {
                OutputStream fileOutputStream = new FileOutputStream(file);
                int i = Message.FLAG_ERR;
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            tryClose(inputStream);
                            tryClose(fileOutputStream);
                            return true;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    outputStream = fileOutputStream;
                    tryClose(inputStream);
                    tryClose(outputStream);
                    throw th2;
                }
            }
            tryClose(inputStream);
            tryClose(null);
            return false;
        } catch (Throwable th4) {
            th = th4;
            GDTLogger.e(String.format("Exception while copy from InputStream to File %s", new Object[]{file.getAbsolutePath()}), th);
            tryClose(inputStream);
            tryClose(outputStream);
            return false;
        }
    }

    public static String getFileName(String str) {
        return Md5Util.encode(str);
    }

    public static String getTempFileName(String str) {
        return Md5Util.encode(str) + ".temp";
    }

    public static boolean renameTo(File file, File file2) {
        return (file == null || file2 == null || !file.exists()) ? false : !file.renameTo(file2) ? copyTo(null, file2) : true;
    }

    public static void tryClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    public static void tryClose(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e) {
            }
        }
    }
}
