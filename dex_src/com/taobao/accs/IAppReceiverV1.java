package com.taobao.accs;

import java.util.Map;

// compiled from: Taobao
public abstract class IAppReceiverV1 implements IAppReceiver {
    public abstract Map<String, String> getAllServices();

    public abstract String getService(String str);

    public abstract void onBindApp(int i, String str);

    public abstract void onBindUser(String str, int i);

    public abstract void onUnbindApp(int i);

    public abstract void onUnbindUser(int i);

    @Deprecated
    public void onBindApp(int i) {
    }

    public void onData(String str, String str2, byte[] bArr) {
    }

    public void onSendData(String str, int i) {
    }
}
