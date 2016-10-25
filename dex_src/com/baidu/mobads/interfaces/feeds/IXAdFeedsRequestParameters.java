package com.baidu.mobads.interfaces.feeds;

import java.util.HashMap;
import java.util.Map;

public interface IXAdFeedsRequestParameters {
    int getAPPConfirmPolicy();

    String getAdPlacementId();

    int getAdsType();

    Map<String, String> getExtras();

    String getKeywords();

    @Deprecated
    boolean isConfirmDownloading();

    HashMap<String, Object> toHashMap();
}
