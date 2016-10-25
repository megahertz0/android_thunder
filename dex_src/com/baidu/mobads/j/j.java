package com.baidu.mobads.j;

import android.util.Log;
import com.baidu.mobads.a.a;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class j implements IXAdLogger {
    private static volatile j a;

    static {
        a = null;
    }

    public static j a() {
        if (a == null) {
            synchronized (j.class) {
                if (a == null) {
                    a = new j();
                }
            }
        }
        return a;
    }

    public boolean isLoggable(String str, int i) {
        return i >= a.b;
    }

    public boolean isLoggable(int i) {
        return isLoggable(IXAdLogger.TAG, i);
    }

    private String a(Object[] objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object obj : objArr) {
            stringBuilder.append(obj).append(' ');
        }
        return stringBuilder.toString();
    }

    public int d(Object... objArr) {
        return !isLoggable(XZBDevice.DOWNLOAD_LIST_FAILED) ? -1 : d(a(objArr));
    }

    public int d(String str) {
        return d(IXAdLogger.TAG, str);
    }

    public int d(String str, String str2) {
        if (!isLoggable(XZBDevice.DOWNLOAD_LIST_FAILED)) {
            return -1;
        }
        try {
            return Log.d(str, str2);
        } catch (Exception e) {
            return -1;
        }
    }

    public int d(Throwable th) {
        return d(com.umeng.a.d, th);
    }

    public int d(String str, Throwable th) {
        if (!isLoggable(XZBDevice.DOWNLOAD_LIST_FAILED)) {
            return -1;
        }
        try {
            return Log.d(IXAdLogger.TAG, str, th);
        } catch (Exception e) {
            return -1;
        }
    }

    public int w(String str) {
        if (!isLoggable(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)) {
            return -1;
        }
        try {
            return Log.w(IXAdLogger.TAG, str);
        } catch (Exception e) {
            return -1;
        }
    }

    public int w(Object... objArr) {
        return !isLoggable(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED) ? -1 : w(a(objArr));
    }

    public int w(String str, Throwable th) {
        if (!isLoggable(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)) {
            return -1;
        }
        try {
            return Log.w(IXAdLogger.TAG, str, th);
        } catch (Exception e) {
            return -1;
        }
    }

    public int w(Throwable th) {
        return w(com.umeng.a.d, th);
    }

    public int e(Object... objArr) {
        return !isLoggable(R.styleable.Toolbar_contentInsetEnd) ? -1 : e(a(objArr));
    }

    public int e(String str) {
        if (!isLoggable(R.styleable.Toolbar_contentInsetEnd)) {
            return -1;
        }
        try {
            return Log.e(IXAdLogger.TAG, str);
        } catch (Exception e) {
            return -1;
        }
    }

    public int e(Throwable th) {
        return e(com.umeng.a.d, th);
    }

    public int e(String str, Throwable th) {
        if (!isLoggable(R.styleable.Toolbar_contentInsetEnd)) {
            return -1;
        }
        try {
            return Log.e(IXAdLogger.TAG, str, th);
        } catch (Exception e) {
            return -1;
        }
    }

    public int i(String str) {
        return i(IXAdLogger.TAG, str);
    }

    public int i(String str, String str2) {
        if (!isLoggable(XZBDevice.DOWNLOAD_LIST_ALL)) {
            return -1;
        }
        try {
            return Log.i(str, str2);
        } catch (Exception e) {
            return -1;
        }
    }

    public int i(Object... objArr) {
        return !isLoggable(XZBDevice.DOWNLOAD_LIST_ALL) ? -1 : i(a(objArr));
    }

    public int i(String str, Throwable th) {
        if (!isLoggable(XZBDevice.DOWNLOAD_LIST_ALL)) {
            return -1;
        }
        try {
            return Log.i(IXAdLogger.TAG, str, th);
        } catch (Exception e) {
            return -1;
        }
    }
}
