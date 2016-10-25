package com.xunlei.downloadprovider.dlnaplugin;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.IBinder;

public interface IPluginService {
    void IInit(String str, String str2, Service service, ClassLoader classLoader, PackageInfo packageInfo, int i);

    IBinder IOnBind(Intent intent);

    void IOnCreate();

    void IOnDestroy();

    void IOnStart(Intent intent, int i);

    int IOnStartCommand(Intent intent, int i, int i2);

    boolean IOnUnbind(Intent intent);
}
