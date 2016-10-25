package com.sina.weibo.sdk.statistic;

import android.os.Environment;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.umeng.a;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class LogFileUtil {
    public static final String ANALYTICS_FILE_NAME = "app_logs";
    private static final String ANALYTICS_FILE_SUFFIX = ".txt";
    private static final String SDCARD_WEIBO_ANALYTICS_DIR = "/sina/weibo/.applogs/";

    LogFileUtil() {
    }

    public static String getAppLogs(String str) {
        return TextUtils.isEmpty(str) ? a.d : readStringFromFile(str);
    }

    public static String getAppLogPath(String str) {
        String str2 = a.d;
        if (LogReport.getPackageName() != null) {
            str2 = new StringBuilder(String.valueOf(MD5.hexdigest(LogReport.getPackageName()))).append("/").toString();
        }
        return new StringBuilder(String.valueOf(getSDPath())).append(SDCARD_WEIBO_ANALYTICS_DIR).append(str2).append(str).append(ANALYTICS_FILE_SUFFIX).toString();
    }

    private static String getSDPath() {
        File externalStorageDirectory;
        if (Environment.getExternalStorageState().equals("mounted")) {
            externalStorageDirectory = Environment.getExternalStorageDirectory();
        } else {
            externalStorageDirectory = null;
        }
        return externalStorageDirectory != null ? externalStorageDirectory.toString() : null;
    }

    private static String readStringFromFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return a.d;
        }
        File file = new File(str);
        if (!file.isFile() || !file.exists()) {
            return a.d;
        }
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder((int) file.length());
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        try {
                            break;
                        } catch (IOException e) {
                        }
                    } else {
                        stringBuilder.append(readLine);
                    }
                } catch (IOException e2) {
                    e = e2;
                } catch (OutOfMemoryError e3) {
                    e = e3;
                    bufferedReader = bufferedReader2;
                }
            }
            bufferedReader2.close();
        } catch (IOException e4) {
            e = e4;
            bufferedReader2 = null;
            try {
                IOException e5;
                e5.printStackTrace();
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e6) {
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e7) {
                    }
                }
                throw th2;
            }
            return stringBuilder.toString();
        } catch (OutOfMemoryError e8) {
            e = e8;
            try {
                OutOfMemoryError e9;
                e9.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e10) {
                    }
                }
            } catch (Throwable th3) {
                th2 = th3;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th2;
            }
            return stringBuilder.toString();
        }
        return stringBuilder.toString();
    }

    public static synchronized void writeToFile(String str, String str2, boolean z) {
        FileWriter fileWriter;
        Throwable th;
        synchronized (LogFileUtil.class) {
            if (!TextUtils.isEmpty(str)) {
                LogUtil.i(WBAgent.TAG, new StringBuilder("filePath:").append(str).toString());
                if (!(str2 == null || str2.length() == 0)) {
                    StringBuilder stringBuilder = new StringBuilder(str2);
                    if (stringBuilder.charAt(0) == '[') {
                        stringBuilder.replace(0, 1, a.d);
                    }
                    if (stringBuilder.charAt(stringBuilder.length() - 1) != ',') {
                        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), MiPushClient.ACCEPT_TIME_SEPARATOR);
                    }
                    File file = new File(str);
                    FileWriter fileWriter2 = null;
                    try {
                        File parentFile = file.getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        if (!file.exists()) {
                            file.createNewFile();
                        } else if (file.lastModified() > 0 && System.currentTimeMillis() - file.lastModified() > 86400000) {
                            z = false;
                        }
                        fileWriter = new FileWriter(file, z);
                        try {
                            fileWriter.write(stringBuilder.toString());
                            fileWriter.flush();
                            try {
                                fileWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e2) {
                            fileWriter2 = fileWriter;
                            if (fileWriter2 != null) {
                                fileWriter2.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileWriter != null) {
                                fileWriter.close();
                            }
                            throw th;
                        }
                    } catch (IOException e3) {
                        if (fileWriter2 != null) {
                            try {
                                fileWriter2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        fileWriter = null;
                        th = th4;
                        if (fileWriter != null) {
                            try {
                                fileWriter.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            }
        }
    }

    public static boolean delete(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        file.delete();
        return true;
    }
}
