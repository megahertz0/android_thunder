package com.xunlei.xiazaibao.sdk.tools;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class XZBLog {
    private static int LOG_CAT;
    private static int LOG_FILE;
    private static String LOG_FILE_NAME;
    private static int LOG_NO;
    private static int MSG_LOG;
    private static String SD_FILE_PATH;
    private static Context context;
    private static File logFile;
    private static Handler logFileHandler;
    private static OutputStream logFileOutput;
    private static int logType;
    private static String processName;
    private static SimpleDateFormat simpleDateFormat;

    static {
        SD_FILE_PATH = "/xzblog/";
        LOG_FILE_NAME = "log.txt";
        LOG_NO = 0;
        LOG_CAT = 1;
        LOG_FILE = 2;
        logType = LOG_NO;
        MSG_LOG = 4369;
    }

    private static void initHandler() {
        logFileHandler = new Handler() {
            public final void handleMessage(Message message) {
                if (message.what == MSG_LOG && logFile != null) {
                    try {
                        if (logFileOutput == null) {
                            logFileOutput = new FileOutputStream(logFile, true);
                        }
                        byte[] bytes = (((String) message.obj) + "\n\n").getBytes();
                        logFileOutput.write(bytes, 0, bytes.length);
                        logFileOutput.close();
                        logFileOutput = null;
                    } catch (FileNotFoundException e) {
                    } catch (IOException e2) {
                    }
                    super.handleMessage(message);
                }
            }
        };
    }

    public static void init(Context context) {
        context = context;
        processName = getProcessName(context);
        try {
            int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
        }
        logType = LOG_CAT;
    }

    private static boolean resetLogFile(String str) {
        File file = new File(getLogFileDir(str) + LOG_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            logFile = file;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean createLogFileDir(String str) {
        File file = new File(getLogFileDir(str));
        return !file.exists() ? file.mkdirs() : false;
    }

    private static String getLogFileDir(String str) {
        return Environment.getExternalStorageDirectory() + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + SD_FILE_PATH;
    }

    private static String getProcessName(Context context) {
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return BuildConfig.VERSION_NAME;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return BuildConfig.VERSION_NAME;
    }

    private static void sendToLogFile(String str, String str2, String str3) {
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(simpleDateFormat.format(Calendar.getInstance().getTime())).append(" [");
        stringBuilder.append("Process-").append(Process.myPid()).append("] [");
        stringBuilder.append("Thread-").append(Thread.currentThread().getId()).append("] ");
        stringBuilder.append(str.toUpperCase()).append(" ");
        stringBuilder.append(str2).append(" : ").append(str3);
        Message obtainMessage = logFileHandler.obtainMessage();
        obtainMessage.obj = stringBuilder.toString();
        obtainMessage.what = MSG_LOG;
        logFileHandler.sendMessage(obtainMessage);
    }

    public static void v(String str, String str2) {
        if ((logType & LOG_FILE) != 0) {
            sendToLogFile("Verbose", str, str2);
        }
    }

    public static void d(String str, String str2) {
        if ((logType & LOG_FILE) != 0) {
            sendToLogFile("Debug", str, str2);
        }
    }

    public static void i(String str, String str2) {
        if ((logType & LOG_FILE) != 0) {
            sendToLogFile("Info", str, str2);
        }
    }

    public static void w(String str, String str2) {
        if ((logType & LOG_FILE) != 0) {
            sendToLogFile("Warn", str, str2);
        }
    }

    public static void e(String str, String str2) {
        if ((logType & LOG_FILE) != 0) {
            sendToLogFile("Error", str, str2);
        }
    }
}
