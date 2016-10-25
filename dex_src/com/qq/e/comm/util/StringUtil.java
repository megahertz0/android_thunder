package com.qq.e.comm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String join(String str, String[] strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (strArr != null) {
            int i = 0;
            while (i < strArr.length) {
                if (str != null && i != 0) {
                    stringBuffer.append(str);
                }
                stringBuffer.append(strArr[i]);
                i++;
            }
        }
        return stringBuffer.toString();
    }

    public static int parseInteger(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Throwable th) {
            return i;
        }
    }

    public static String readAll(File file) throws IOException {
        IOException e;
        Throwable th;
        String str = null;
        if (file != null && file.exists()) {
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                    }
                    str = stringBuilder.toString();
                    try {
                        bufferedReader.close();
                    } catch (Throwable e2) {
                        GDTLogger.e("Exception while close bufferreader", e2);
                    }
                } catch (IOException e3) {
                    e = e3;
                    try {
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (IOException e4) {
                IOException iOException = e4;
                bufferedReader = null;
                e = iOException;
                throw e;
            } catch (Throwable e22) {
                Throwable th3 = e22;
                bufferedReader = null;
                th = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e222) {
                        GDTLogger.e("Exception while close bufferreader", e222);
                    }
                }
                throw th;
            }
        }
        return str;
    }

    public static void writeTo(String str, File file) throws IOException {
        if (file == null) {
            throw new IOException("Target File Can not be null in StringUtil.writeTo");
        }
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(str);
        fileWriter.close();
    }
}
