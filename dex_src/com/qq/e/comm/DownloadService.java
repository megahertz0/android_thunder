package com.qq.e.comm;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.SVSD;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class DownloadService extends Service {
    private SVSD a;

    private boolean a(String str) {
        if (this.a == null) {
            try {
                if (GDTADManager.getInstance().initWith(getApplicationContext(), str)) {
                    this.a = GDTADManager.getInstance().getPM().getPOFactory().getAPKDownloadServiceDelegate(this);
                    this.a.onCreate();
                } else {
                    GDTLogger.report("Init GDTADManager fail in DownloadService.oncreate");
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return this.a != null;
    }

    public IBinder onBind(Intent intent) {
        GDTLogger.d("DownloadService.onBind");
        if (this.a != null) {
            return this.a.onBind(intent);
        }
        String stringExtra = intent.getStringExtra("GDT_APPID");
        GDTLogger.d(new StringBuilder("DownloadService.onBind,appID=").append(stringExtra).toString());
        return (StringUtil.isEmpty(stringExtra) || !a(stringExtra)) ? null : this.a.onBind(intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.a != null) {
            this.a.onConfigurationChanged(configuration);
        }
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        if (this.a != null) {
            this.a.onDestroy();
        }
    }

    public void onLowMemory() {
        if (this.a != null) {
            this.a.onLowMemory();
        }
    }

    public void onRebind(Intent intent) {
        if (this.a != null) {
            this.a.onRebind(intent);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            stopSelf(i2);
            return XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }
        String stringExtra = intent.getStringExtra("GDT_APPID");
        if (!StringUtil.isEmpty(stringExtra) && a(stringExtra)) {
            return this.a.onStartCommand(intent, i, i2);
        }
        GDTLogger.w("Failto Start new download Service");
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    public void onTaskRemoved(Intent intent) {
        if (this.a != null) {
            this.a.onTaskRemoved(intent);
        }
    }

    public void onTrimMemory(int i) {
        if (this.a != null) {
            this.a.onTrimMemory(i);
        }
    }

    public boolean onUnbind(Intent intent) {
        return this.a != null ? this.a.onUnbind(intent) : super.onUnbind(intent);
    }
}
