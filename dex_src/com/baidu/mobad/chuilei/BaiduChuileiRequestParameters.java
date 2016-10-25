package com.baidu.mobad.chuilei;

import com.baidu.mobad.feeds.RequestParameters;
import com.baidu.mobad.feeds.RequestParameters.Builder;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import java.util.HashMap;
import java.util.Map;

public class BaiduChuileiRequestParameters implements IXAdFeedsRequestParameters {
    private RequestParameters a;

    public RequestParameters getRequestParameters() {
        return this.a;
    }

    public BaiduChuileiRequestParameters() {
        this.a = new Builder().build();
    }

    public String getKeywords() {
        return this.a.getKeywords();
    }

    public int getAdsType() {
        return this.a.getAdsType();
    }

    @Deprecated
    public boolean isConfirmDownloading() {
        return this.a.isConfirmDownloading();
    }

    public int getAPPConfirmPolicy() {
        return this.a.getAPPConfirmPolicy();
    }

    public Map<String, String> getExtras() {
        return this.a.getExtras();
    }

    public HashMap<String, Object> toHashMap() {
        return this.a.toHashMap();
    }

    public String getAdPlacementId() {
        return this.a.getAdPlacementId();
    }
}
