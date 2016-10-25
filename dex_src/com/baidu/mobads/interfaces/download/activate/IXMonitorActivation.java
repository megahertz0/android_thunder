package com.baidu.mobads.interfaces.download.activate;

public interface IXMonitorActivation {
    void addAppInfoForMonitor(IXAppInfo iXAppInfo);

    void setIXActivateListener(IXActivateListener iXActivateListener);

    void startMonitor();
}
