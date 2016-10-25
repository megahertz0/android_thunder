package com.mediav.ads.sdk.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import com.mediav.ads.sdk.adcore.UpdateBridge;
import com.mediav.ads.sdk.interfaces.IBridge;
import com.mediav.ads.sdk.interfaces.ServiceBridge;
import com.mediav.ads.sdk.log.MVLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public final class MvService extends Service {
    private ServiceBridge serviceBridge;

    public final IBinder onBind(Intent intent) {
        return this.serviceBridge.onBind(intent);
    }

    public final void onCreate() {
        IBridge bridge = UpdateBridge.getBridge(this);
        if (bridge == null) {
            MVLog.e(1, "unable get updateBridge.");
            return;
        }
        this.serviceBridge = bridge.getServiceBridge(this);
        this.serviceBridge.onCreate();
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        return this.serviceBridge.onStartCommand(intent, i, i2);
    }

    public final void onDestroy() {
        this.serviceBridge.onDestroy();
        this.serviceBridge = null;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        this.serviceBridge.onConfigurationChanged(configuration);
    }

    public final void onLowMemory() {
        this.serviceBridge.onLowMemory();
    }

    public final void onTrimMemory(int i) {
        this.serviceBridge.onTrimMemory(i);
    }

    public final boolean onUnbind(Intent intent) {
        return this.serviceBridge.onUnbind(intent);
    }

    public final void onRebind(Intent intent) {
        this.serviceBridge.onRebind(intent);
    }

    public final void onTaskRemoved(Intent intent) {
        this.serviceBridge.onTaskRemoved(intent);
    }

    protected final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.serviceBridge.dump(fileDescriptor, printWriter, strArr);
    }
}
