package com.baidu.mobads.interfaces;

import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import org.json.JSONObject;

public interface IXAdProdInfo {
    String getAdPlacementId();

    String getAdRequestURL();

    int getApt();

    JSONObject getAttribute();

    int getInstanceCount();

    @Deprecated
    String getProdType();

    int getRequestAdHeight();

    int getRequestAdWidth();

    SlotType getType();

    boolean isAutoPlay();

    boolean isMsspTagAvailable();
}
