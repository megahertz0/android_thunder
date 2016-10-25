package com.baidu.mobads.interfaces;

import android.location.Location;

public interface IXAdManager {
    String getVersion();

    IXAdContext newAdContext();

    void setAppSid(String str);

    void setLocation(Location location);
}
