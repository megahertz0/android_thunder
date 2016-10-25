package anet.channel.util;

import android.text.TextUtils;
import com.taobao.tlog.adapter.AdapterForTLog;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: Taobao
public class ALog {
    private static Object LOG_BREAK;
    private static boolean isPrintLog;
    private static boolean isUseTlog;

    // compiled from: Taobao
    public static class a {
        public static final int D = 1;
        public static final int E = 4;
        public static final int I = 2;
        public static final int L = 5;
        public static final int V = 0;
        public static final int W = 3;

        public static int a(String str) {
            switch (str.charAt(V)) {
                case R.styleable.AppCompatTheme_searchViewStyle:
                    return D;
                case R.styleable.AppCompatTheme_listPreferredItemHeight:
                    return E;
                case R.styleable.AppCompatTheme_listPreferredItemPaddingRight:
                    return I;
                case R.styleable.AppCompatTheme_colorControlActivated:
                    return V;
                case R.styleable.AppCompatTheme_colorControlHighlight:
                    return W;
                default:
                    return L;
            }
        }
    }

    static {
        LOG_BREAK = "|";
        isPrintLog = true;
        isUseTlog = true;
    }

    public static void setPrintLog(boolean z) {
        isPrintLog = z;
    }

    public static void setUseTlog(boolean z) {
        isUseTlog = z;
    }

    @Deprecated
    public static void setEnableTLog(boolean z) {
        isUseTlog = z;
    }

    @Deprecated
    public static boolean isPrintLog() {
        return false;
    }

    public static boolean isPrintLog(int i) {
        if (!isPrintLog) {
            return false;
        }
        if (isUseTlog) {
            return i >= a.a(AdapterForTLog.getLogLevel());
        } else {
            return true;
        }
    }

    public static void d(String str, String str2, String str3, Object... objArr) {
        if (!isPrintLog(1)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.logd(buildLogTag(str), buildLogMsg(str2, str3, objArr));
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    public static void i(String str, String str2, String str3, Object... objArr) {
        if (!isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.logi(buildLogTag(str), buildLogMsg(str2, str3, objArr));
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    public static void w(String str, String str2, String str3, Object... objArr) {
        if (!isPrintLog(XZBDevice.DOWNLOAD_LIST_FAILED)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.logw(buildLogTag(str), buildLogMsg(str2, str3, objArr));
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    public static void w(String str, String str2, String str3, Throwable th, Object... objArr) {
        if (!isPrintLog(XZBDevice.DOWNLOAD_LIST_FAILED)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.logw(buildLogTag(str), buildLogMsg(str2, str3, objArr), th);
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    public static void e(String str, String str2, String str3, Object... objArr) {
        if (!isPrintLog(XZBDevice.DOWNLOAD_LIST_ALL)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.loge(buildLogTag(str), buildLogMsg(str2, str3, objArr));
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    public static void e(String str, String str2, String str3, Throwable th, Object... objArr) {
        if (!isPrintLog(XZBDevice.DOWNLOAD_LIST_ALL)) {
            return;
        }
        if (isUseTlog) {
            AdapterForTLog.loge(buildLogTag(str), buildLogMsg(str2, str3, objArr), th);
            return;
        }
        buildLogTag(str);
        buildLogMsg(str2, str3, objArr);
    }

    private static String buildLogTag(String str) {
        return str;
    }

    static String buildLogMsg(String str, String str2, Object... objArr) {
        if (str == null && str2 == null && objArr == null) {
            return com.umeng.a.d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append(LOG_BREAK).append("[seq:").append(str2).append("]");
        }
        if (str != null) {
            stringBuilder.append(" ").append(str);
        }
        if (objArr != null) {
            int i = 0;
            while (i + 1 < objArr.length) {
                stringBuilder.append(" ").append(objArr[i] != null ? objArr[i] : com.umeng.a.d).append(":").append(objArr[i + 1] != null ? objArr[i + 1] : com.umeng.a.d);
                i += 2;
            }
            if (i < objArr.length) {
                stringBuilder.append(" ");
                stringBuilder.append(objArr[i]);
            }
        }
        return stringBuilder.toString();
    }
}
