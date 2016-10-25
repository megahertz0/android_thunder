package com.xunlei.downloadprovider.dlnaplugin;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;

public class PluginProxyService extends Service {
    private String mApkFilePath;
    private String mLaunchService;
    private String mPluginID;
    private IPluginService mPluginService;

    public static void openService(Context context, String str, String str2, String str3, String str4, Intent intent) {
        intent.putExtra(PluginStatic.PARAM_PLUGIN_LOCATION, str2);
        intent.putExtra(PluginStatic.PARAM_LAUNCH_SERVICE, str4);
        intent.putExtra(PluginStatic.PARAM_PATH, str3);
        try {
            context.getApplicationContext().startService(intent);
        } catch (Throwable th) {
        }
    }

    public static void bindService(Context context, String str, String str2, String str3, String str4, Intent intent, ServiceConnection serviceConnection) {
        intent.putExtra(PluginStatic.PARAM_PLUGIN_LOCATION, str2);
        intent.putExtra(PluginStatic.PARAM_LAUNCH_SERVICE, str4);
        intent.putExtra(PluginStatic.PARAM_PATH, str3);
        try {
            context.getApplicationContext().bindService(intent, serviceConnection, 1);
        } catch (Throwable th) {
        }
    }

    public static void unbindSerivce(Context context, ServiceConnection serviceConnection) {
        try {
            context.getApplicationContext().unbindService(serviceConnection);
        } catch (Throwable th) {
        }
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        if (startPluginIfNeccessary(intent) && this.mPluginService != null) {
            this.mPluginService.IOnStart(intent, i);
        }
    }

    @SuppressLint({"NewApi"})
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (startPluginIfNeccessary(intent) && this.mPluginService != null) {
            this.mPluginService.IOnStartCommand(intent, i, i2);
        }
        return XZBDevice.DOWNLOAD_LIST_FAILED;
    }

    public boolean onUnbind(Intent intent) {
        return this.mPluginService != null ? this.mPluginService.IOnUnbind(intent) : super.onUnbind(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mPluginService != null) {
            this.mPluginService.IOnDestroy();
            this.mPluginService = null;
        }
    }

    public IBinder onBind(Intent intent) {
        if (this.mPluginService == null) {
            startPluginIfNeccessary(intent);
        }
        return this.mPluginService != null ? this.mPluginService.IOnBind(intent) : null;
    }

    protected boolean startPluginIfNeccessary(Intent intent) {
        String stringExtra = intent.getStringExtra(PluginStatic.PARAM_PLUGIN_LOCATION);
        String stringExtra2 = intent.getStringExtra(PluginStatic.PARAM_LAUNCH_SERVICE);
        String stringExtra3 = intent.getStringExtra(PluginStatic.PARAM_PATH);
        if (this.mPluginService != null) {
            return this.mPluginID.equals(stringExtra) && this.mLaunchService.equals(stringExtra2);
        } else {
            this.mPluginID = stringExtra;
            this.mApkFilePath = stringExtra3;
            this.mLaunchService = stringExtra2;
            ClassLoader classLoader = PluginStatic.getClassLoader(this.mPluginID);
            if (classLoader != null) {
                intent.setExtrasClassLoader(classLoader);
            }
            File file = new File(this.mApkFilePath);
            if (!file.exists() && !file.isFile()) {
                return true;
            }
            try {
                if (initPlugin() != null) {
                    return false;
                }
                this.mPluginService.IOnCreate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                stopSelf();
                return true;
            }
        }
    }

    private String initPlugin() throws Exception {
        PackageInfo packageInfo = (PackageInfo) PluginStatic.sPackageInfoMap.get(this.mApkFilePath);
        if (packageInfo == null) {
            Object packageInfo2;
            try {
                packageInfo2 = ApkFileParser.getPackageInfo(this, this.mApkFilePath, 1);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            PluginStatic.sPackageInfoMap.put(this.mApkFilePath, packageInfo2);
        }
        PackageInfo packageInfo3 = packageInfo;
        ClassLoader orCreateClassLoaderByPath = PluginStatic.getOrCreateClassLoaderByPath(this, this.mPluginID, this.mApkFilePath);
        try {
            this.mPluginService = (IPluginService) orCreateClassLoaderByPath.loadClass(this.mLaunchService).newInstance();
            this.mPluginService.IInit(this.mPluginID, this.mApkFilePath, this, orCreateClassLoaderByPath, packageInfo3, -1);
            return null;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return "new PluginService failed!";
        }
    }
}
