package com.xunlei.downloadprovider.dlnaplugin;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

@SuppressLint({"NewApi"})
public abstract class PluginService extends Service implements IPluginService {
    protected String mApkFilePath;
    private Context mContext;
    protected ClassLoader mDexClassLoader;
    protected boolean mIsRunInPlugin;
    protected Service mOutService;
    protected PackageInfo mPackageInfo;

    public void onCreate() {
        if (!this.mIsRunInPlugin) {
        }
    }

    public void onStart(Intent intent, int i) {
        if (!this.mIsRunInPlugin) {
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return this.mIsRunInPlugin ? -1 : MqttConnectOptions.MQTT_VERSION_3_1;
    }

    public boolean onUnbind(Intent intent) {
        return false;
    }

    public void onDestroy() {
        if (!this.mIsRunInPlugin) {
        }
    }

    public void IOnCreate() {
        try {
            onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void IOnStart(Intent intent, int i) {
        onStart(intent, i);
    }

    public int IOnStartCommand(Intent intent, int i, int i2) {
        return onStartCommand(intent, i, i2);
    }

    public IBinder IOnBind(Intent intent) {
        return onBind(intent);
    }

    public boolean IOnUnbind(Intent intent) {
        return onUnbind(intent);
    }

    public void IOnDestroy() {
        onDestroy();
    }

    public Object getSystemService(String str) {
        return ("window".equals(str) || "search".equals(str)) ? this.mIsRunInPlugin ? this.mOutService.getSystemService(str) : super.getSystemService(str) : this.mContext != null ? this.mContext.getSystemService(str) : super.getSystemService(str);
    }

    public String getPackageName() {
        return this.mIsRunInPlugin ? this.mPackageInfo.packageName : super.getPackageName();
    }

    public PackageInfo getPackageInfo() {
        return this.mIsRunInPlugin ? this.mPackageInfo : null;
    }

    public ApplicationInfo getApplicationInfo() {
        return this.mIsRunInPlugin ? this.mPackageInfo.applicationInfo : super.getApplicationInfo();
    }

    public void IInit(String str, String str2, Service service, ClassLoader classLoader, PackageInfo packageInfo, int i) {
        this.mIsRunInPlugin = true;
        this.mApkFilePath = str2;
        this.mOutService = service;
        this.mDexClassLoader = classLoader;
        this.mPackageInfo = packageInfo;
        if (this.mContext == null) {
            try {
                this.mContext = new PluginContext(service, 0, this.mApkFilePath, this.mDexClassLoader, service.getResources());
            } catch (Error e) {
            }
        }
        attachBaseContext(this.mContext);
    }
}
