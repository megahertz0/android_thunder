package com.xunlei.common.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;
import com.xunlei.download.proguard.c;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class XLCrashHandler implements UncaughtExceptionHandler {
    private static XLCrashHandler INSTANCE = null;
    public static final String SD_FILE_PATH = "/xunlei/xl-acc-crash/";
    public static final String TAG = "CrashHandler";
    private Context mContext;
    private UncaughtExceptionHandler mDefaultHandler;
    private Map<String, String> mDevInfos;
    private DateFormat mFormatter;

    static {
        INSTANCE = null;
    }

    private XLCrashHandler() {
        this.mDefaultHandler = null;
        this.mContext = null;
        this.mDevInfos = new HashMap();
        this.mFormatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    }

    public static XLCrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XLCrashHandler();
        }
        return INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (handleException(th) || this.mDefaultHandler == null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                XLLog.e(TAG, new StringBuilder("error : ").append(e.getMessage()).toString());
            }
            Process.killProcess(Process.myPid());
            System.exit(1);
            return;
        }
        this.mDefaultHandler.uncaughtException(thread, th);
    }

    private boolean handleException(Throwable th) {
        if (th == null) {
            return false;
        }
        new Thread() {
            public void run() {
                Looper.prepare();
                Toast.makeText(XLCrashHandler.this.mContext, "\u5f88\u62b1\u6b49,\u7a0b\u5e8f\u51fa\u73b0\u5f02\u5e38,\u5373\u5c06\u9000\u51fa.", 1).show();
                Looper.loop();
            }
        }.start();
        collectDeviceInfo(this.mContext);
        saveCrashInfo2File(th);
        return true;
    }

    public void collectDeviceInfo(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                Object obj = packageInfo.versionName == null ? "null" : packageInfo.versionName;
                String str = packageInfo.versionCode;
                this.mDevInfos.put("versionName", obj);
                this.mDevInfos.put("versionCode", str);
            }
        } catch (NameNotFoundException e) {
            XLLog.e(TAG, new StringBuilder("an error occured when collect package info = ").append(e.getMessage()).toString());
        }
        Field[] declaredFields = Build.class.getDeclaredFields();
        int length = declaredFields.length;
        for (int i = 0; i < length; i++) {
            Field field = declaredFields[i];
            try {
                field.setAccessible(true);
                this.mDevInfos.put(field.getName(), field.get(null).toString());
                XLLog.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e2) {
                XLLog.e(TAG, new StringBuilder("an error occured when collect crash info = ").append(e2.getMessage()).toString());
            }
        }
    }

    private static String getCrashFileDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + SD_FILE_PATH;
    }

    private String saveCrashInfo2File(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry entry : this.mDevInfos.entrySet()) {
            String str = (String) entry.getKey();
            stringBuffer.append(str + "=" + ((String) entry.getValue()) + "\n");
        }
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        stringBuffer.append(stringWriter.toString());
        try {
            String toString = new StringBuilder("xl-acc-crash-").append(this.mFormatter.format(new Date())).append(c.q).append(System.currentTimeMillis()).append(c.n).toString();
            if (!Environment.getExternalStorageState().equals("mounted")) {
                return toString;
            }
            str = getCrashFileDirectory();
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str + toString);
            fileOutputStream.write(stringBuffer.toString().getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            return toString;
        } catch (Exception e) {
            XLLog.e(TAG, new StringBuilder("an error occured while writing file ").append(e.getMessage()).toString());
            return null;
        }
    }
}
