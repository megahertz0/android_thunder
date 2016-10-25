package com.taobao.accs;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import com.taobao.accs.base.BaseService;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;

// compiled from: Taobao
public class ChannelService extends BaseService {
    static final int NOTIFY_ID = 9371;
    public static int SDK_VERSION_CODE = 0;
    public static final String SUPPORT_FOREGROUND_VERSION_KEY = "support_foreground_v";
    static final String TAG = "ChannelService";
    private static ChannelService mInstance;
    private boolean mFristStarted;

    // compiled from: Taobao
    public static class KernelService extends Service {
        private static com.taobao.accs.ChannelService.KernelService a;

        public void onCreate() {
            super.onCreate();
            a = this;
        }

        public int onStartCommand(Intent intent, int i, int i2) {
            try {
                ChannelService instance = ChannelService.getInstance();
                int i3 = getPackageManager().getPackageInfo(getPackageName(), 0).applicationInfo.icon;
                if (i3 != 0) {
                    Builder builder = new Builder(this);
                    builder.setSmallIcon(i3);
                    instance.startForeground(NOTIFY_ID, builder.build());
                    Builder builder2 = new Builder(this);
                    builder2.setSmallIcon(i3);
                    startForeground(NOTIFY_ID, builder2.build());
                    stopForeground(true);
                }
                stopSelf();
            } catch (Throwable th) {
                ALog.e(TAG, " onStartCommand", th, new Object[0]);
            }
            return super.onStartCommand(intent, i, i2);
        }

        public void onDestroy() {
            try {
                stopForeground(true);
            } catch (Throwable th) {
                ALog.e(TAG, "onDestroy", th, new Object[0]);
            }
            a = null;
            super.onDestroy();
        }

        public IBinder onBind(Intent intent) {
            return null;
        }
    }

    public ChannelService() {
        this.mFristStarted = true;
    }

    static {
        SDK_VERSION_CODE = 212;
    }

    public static ChannelService getInstance() {
        return mInstance;
    }

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        if (VERSION.SDK_INT < 18) {
            try {
                startForeground(NOTIFY_ID, new Notification());
            } catch (Throwable th) {
                ALog.e(TAG, "ChannelService onCreate", th, new Object[0]);
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (this.mFristStarted) {
            this.mFristStarted = false;
            startKernel(getApplicationContext());
        }
        return super.onStartCommand(intent, i, i2);
    }

    public void onDestroy() {
        if (VERSION.SDK_INT < 18) {
            try {
                stopForeground(true);
            } catch (Throwable th) {
                ALog.e(TAG, "ChannelService onDestroy", th, new Object[0]);
            }
        }
        stopKernel(getApplicationContext());
        super.onDestroy();
    }

    static void startKernel(Context context) {
        try {
            int supportForegroundVer = getSupportForegroundVer(context);
            if (supportForegroundVer <= 0 || VERSION.SDK_INT < supportForegroundVer) {
                Intent intent = new Intent(context, KernelService.class);
                intent.setPackage(context.getPackageName());
                context.startService(intent);
            }
        } catch (Throwable th) {
            ALog.e(TAG, "startKernel", th, new Object[0]);
        }
    }

    static void stopKernel(Context context) {
        try {
            int supportForegroundVer = getSupportForegroundVer(context);
            if (supportForegroundVer <= 0 || VERSION.SDK_INT < supportForegroundVer) {
                Intent intent = new Intent(context, KernelService.class);
                intent.setPackage(context.getPackageName());
                context.stopService(intent);
            }
        } catch (Throwable th) {
            ALog.e(TAG, "stopKernel", th, new Object[0]);
        }
    }

    static int getSupportForegroundVer(Context context) {
        try {
            return context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getInt(SUPPORT_FOREGROUND_VERSION_KEY, -1);
        } catch (Throwable th) {
            ALog.e(TAG, "getSupportForegroundVer fail:", th, "key", SUPPORT_FOREGROUND_VERSION_KEY);
            return -1;
        }
    }
}
