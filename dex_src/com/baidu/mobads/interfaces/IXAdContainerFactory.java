package com.baidu.mobads.interfaces;

import android.content.Context;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;
import com.baidu.mobads.interfaces.download.activate.IXMonitorActivation;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import java.util.HashMap;

public interface IXAdContainerFactory {
    IXAppInfo createAppInfo();

    IXAdContainer createXAdContainer(IXAdContainerContext iXAdContainerContext, HashMap<String, String> hashMap);

    Boolean getDebugMode();

    String getFullProxyVersion();

    double getProxyVersion();

    double getRemoteVersion();

    IXMonitorActivation getXMonitorActivation(Context context, IXAdLogger iXAdLogger);

    double handleShakeVersion(double d, String str);

    void setDebugMode(Boolean bool);
}
