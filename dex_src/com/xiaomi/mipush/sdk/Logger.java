package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.taobao.accs.data.Message;
import com.umeng.message.MsgConstant;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.log.e;
import com.xiaomi.push.log.f;

public class Logger {
    private static boolean sDisablePushLog;
    private static LoggerInterface sUserLogger;

    static {
        sDisablePushLog = false;
        sUserLogger = null;
    }

    public static void disablePushFileLog(Context context) {
        sDisablePushLog = true;
        setPushLog(context);
    }

    public static void enablePushFileLog(Context context) {
        sDisablePushLog = false;
        setPushLog(context);
    }

    private static boolean hasWritePermission(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), Message.FLAG_ERR).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE.equals(strArr[i])) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void setLogger(Context context, LoggerInterface loggerInterface) {
        sUserLogger = loggerInterface;
        setPushLog(context);
    }

    private static void setPushLog(Context context) {
        Object obj = sUserLogger != null ? 1 : null;
        LoggerInterface fVar = new f(context);
        if (!sDisablePushLog && hasWritePermission(context) && obj != null) {
            b.a(new e(sUserLogger, fVar));
        } else if (!sDisablePushLog && hasWritePermission(context)) {
            b.a(fVar);
        } else if (obj != null) {
            b.a(sUserLogger);
        } else {
            b.a(new e(null, null));
        }
    }
}
