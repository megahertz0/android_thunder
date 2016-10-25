package com.baidu.mobads.interfaces;

import java.util.ArrayList;

public interface IXAdResponseInfo {
    ArrayList<IXAdInstanceInfo> getAdInstanceList();

    int getAdsNum();

    String getBaiduidOfCookie();

    String getDefaultFillInThemeForStaticAds();

    String getErrorCode();

    String getErrorMessage();

    String getExp2();

    String getExtentionActionExp();

    int getIntIcon();

    int getLatitude();

    int getLongitude();

    int getLunpan();

    Boolean getOpenPointModeForWall();

    String getOriginResponseStr();

    String getPointUnitForWall();

    IXAdInstanceInfo getPrimaryAdInstanceInfo();

    String getRequestId();

    long getTimeStamp();

    void setAdInstanceList(ArrayList<IXAdInstanceInfo> arrayList);

    void setAdsNum(int i);

    void setBaiduidOfCookie(String str);

    void setDefaultFillInThemeForStaticAds(String str);

    void setErrorCode(String str);

    void setErrorMessage(String str);

    void setExp2(String str);

    void setExtentionActionExp(String str);

    void setIntIcon(int i);

    void setLatitude(int i);

    void setLongitude(int i);

    void setLunpan(int i);

    void setOpenPointModeForWall(Boolean bool);

    void setOriginResponseStr(String str);

    void setPointUnitForWall(String str);

    void setRequestId(String str);
}
