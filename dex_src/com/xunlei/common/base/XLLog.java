package com.xunlei.common.base;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Debug;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import com.umeng.socialize.common.SocializeConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class XLLog {
    public static final boolean DEBUG_MODE = false;
    public static final int LOG_BOTH = 3;
    public static final int LOG_FILE = 2;
    public static final int LOG_LOGCAT = 1;
    private static final String LOG_NAME = "/acc-log";
    public static final int LOG_NO = 0;
    public static final String SD_FILE_PATH = "/xunlei/xl-acc-log";
    private static LogHandler mLogHandler;
    private static int mLogLevel;
    private static SimpleDateFormat mSdf;

    static class LogHandler extends Handler {
        private static int mLogIndex;
        private File mLogFile;
        private FileOutputStream mLogOutput;

        static {
            mLogIndex = 0;
        }

        public LogHandler() {
            this.mLogOutput = null;
            this.mLogFile = null;
            createLogFile();
        }

        public void handleMessage(Message message) {
            if (this.mLogFile != null) {
                try {
                    if (this.mLogOutput == null) {
                        this.mLogOutput = new FileOutputStream(this.mLogFile, true);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    if (!createLogFile()) {
                    }
                }
                if (this.mLogOutput != null) {
                    String str = ((String) message.obj) + "\n\n";
                    if (str != null) {
                        byte[] bytes = str.getBytes();
                        try {
                            this.mLogOutput.write(bytes, LOG_NO, bytes.length);
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            this.mLogOutput = null;
                        }
                    }
                }
            }
        }

        public void flush() {
            if (this.mLogOutput != null) {
                try {
                    this.mLogOutput.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean createLogFile() {
            if (XLLog.isExternalStorageAvailable()) {
                File access$100 = XLLog.getLogFileDirectory();
                if (!access$100.exists()) {
                    access$100.mkdirs();
                }
                this.mLogFile = getValidLogFile(access$100.getAbsolutePath());
                return true;
            }
            this.mLogFile = null;
            return DEBUG_MODE;
        }

        private File getValidLogFile(String str) {
            File file;
            while (true) {
                String str2 = ".txt";
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str).append(LOG_NAME);
                if (mLogIndex == 0) {
                    stringBuffer.append(str2);
                } else {
                    stringBuffer.append(SocializeConstants.OP_DIVIDER_MINUS).append(mLogIndex).append(str2);
                }
                file = new File(stringBuffer.toString());
                if (file.exists()) {
                    mLogIndex++;
                } else {
                    try {
                        file.createNewFile();
                        break;
                    } catch (IOException e) {
                    }
                }
            }
            return file;
        }
    }

    static {
        mLogLevel = 3;
        mLogHandler = null;
        mSdf = null;
    }

    private XLLog() {
    }

    public static void i(String str, String str2) {
    }

    public static void d(String str, String str2) {
    }

    public static void v(String str, String str2) {
    }

    public static void w(String str, String str2) {
    }

    public static void e(String str, String str2) {
    }

    public static void e(String str, Throwable th) {
    }

    public static void logHeapStats(String str, Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        Debug.MemoryInfo memoryInfo2 = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo2);
        long nativeHeapSize = Debug.getNativeHeapSize();
        long nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize();
        long nativeHeapFreeSize = Debug.getNativeHeapFreeSize();
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        new StringBuilder("heap_stats heap_size=").append(decimalFormat.format((double) (((float) nativeHeapSize) / 1048576.0f))).append("M allocated=").append(decimalFormat.format((double) (((float) nativeHeapAllocatedSize) / 1048576.0f))).append("M free=").append(decimalFormat.format((double) (((float) nativeHeapFreeSize) / 1048576.0f))).append("M memory_stats memory_usage=").append(decimalFormat.format((double) (((float) memoryInfo2.getTotalPss()) / 1024.0f))).append("M dalvik_usage=").append(decimalFormat.format((double) (((float) memoryInfo2.dalvikPss) / 1024.0f))).append("M native_usage=").append(decimalFormat.format((double) (((float) memoryInfo2.nativePss) / 1024.0f))).append("M other_usage=").append(decimalFormat.format((double) (((float) memoryInfo2.otherPss) / 1024.0f))).append("M system_stats system_available=").append(decimalFormat.format((double) (((float) memoryInfo.availMem) / 1048576.0f))).append("M");
    }

    public static void logStackTrace(String str) {
        StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) Thread.getAllStackTraces().get(Thread.currentThread());
        int length = stackTraceElementArr.length;
        for (int i = LOG_NO; i < length; i++) {
            stackTraceElementArr[i].toString();
        }
    }

    public static void flush() {
        mLogHandler.flush();
    }

    private static void initFileHandler() {
        if (mLogHandler == null) {
            mLogHandler = new LogHandler();
        }
    }

    private static void sendHandlerMsg(String str, String str2, String str3) {
        if (mSdf == null) {
            mSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mSdf.format(Calendar.getInstance().getTime())).append(" [");
        stringBuilder.append("Thread-").append(Thread.currentThread().getId()).append("] ");
        stringBuilder.append(str.toUpperCase()).append(" ");
        stringBuilder.append(str2).append(" : ").append(str3);
        Message obtainMessage = mLogHandler.obtainMessage();
        obtainMessage.obj = stringBuilder.toString();
        mLogHandler.sendMessage(obtainMessage);
    }

    private static boolean isExternalStorageAvailable() {
        return "mounted".equals(Environment.getExternalStorageState()) ? true : DEBUG_MODE;
    }

    private static File getLogFileDirectory() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + SD_FILE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
