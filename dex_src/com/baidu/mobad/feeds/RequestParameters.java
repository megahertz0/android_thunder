package com.baidu.mobad.feeds;

import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class RequestParameters implements IXAdFeedsRequestParameters {
    public static final int ADS_TYPE_DOWNLOAD = 2;
    public static final int ADS_TYPE_OPENPAGE = 1;
    public static final int DOWNLOAD_APP_CONFIRM_ALWAYS = 2;
    public static final int DOWNLOAD_APP_CONFIRM_CUSTOM_BY_APP = 4;
    public static final int DOWNLOAD_APP_CONFIRM_NEVER = 3;
    public static final int DOWNLOAD_APP_CONFIRM_ONLY_MOBILE = 1;
    public static final int MAX_ASSETS_RESERVED = 15;
    public static final String TAG = "RequestParameters";
    private final String a;
    private int b;
    private boolean c;
    private Map<String, String> d;
    private int e;
    private int f;
    private int g;
    protected String mPlacementId;

    public static class Builder {
        private String a;
        private Map<String, String> b;
        private int c;
        private boolean d;
        private int e;
        private int f;
        private int g;

        public Builder() {
            this.b = new HashMap();
            this.c = 3;
            this.d = false;
            this.e = 640;
            this.f = 480;
            this.g = 1;
        }

        public final com.baidu.mobad.feeds.RequestParameters.Builder setWidth(int i) {
            this.e = i;
            return this;
        }

        public final com.baidu.mobad.feeds.RequestParameters.Builder setHeight(int i) {
            this.f = i;
            return this;
        }

        @Deprecated
        public final com.baidu.mobad.feeds.RequestParameters.Builder confirmDownloading(boolean z) {
            if (z) {
                downloadAppConfirmPolicy(DOWNLOAD_APP_CONFIRM_ALWAYS);
            } else {
                downloadAppConfirmPolicy(DOWNLOAD_APP_CONFIRM_NEVER);
            }
            return this;
        }

        public final com.baidu.mobad.feeds.RequestParameters.Builder downloadAppConfirmPolicy(int i) {
            this.g = i;
            return this;
        }

        public final com.baidu.mobad.feeds.RequestParameters.Builder addExtra(String str, String str2) {
            this.b.put(str, str2);
            return this;
        }

        public final RequestParameters build() {
            return new RequestParameters();
        }
    }

    private RequestParameters(Builder builder) {
        this.e = 0;
        this.f = 0;
        this.a = builder.a;
        this.b = builder.c;
        this.e = builder.e;
        this.f = builder.f;
        this.c = builder.d;
        this.g = builder.g;
        setExtras(builder.b);
    }

    public final String getKeywords() {
        return this.a;
    }

    public final int getWidth() {
        return this.e;
    }

    public final int getHeight() {
        return this.f;
    }

    public final int getAdsType() {
        return this.b;
    }

    public final void setAdsType(int i) {
        this.b = i;
    }

    public final boolean isConfirmDownloading() {
        return this.c;
    }

    public final Map<String, String> getExtras() {
        return this.d;
    }

    public final void setExtras(Map<String, String> map) {
        this.d = map;
    }

    public final HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("mKeywords", this.a);
        hashMap.put("adsType", Integer.valueOf(this.b));
        hashMap.put("confirmDownloading", Boolean.valueOf(this.c));
        HashMap hashMap2 = new HashMap();
        if (this.d != null) {
            for (Entry entry : this.d.entrySet()) {
                hashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        hashMap.put("extras", hashMap2);
        return hashMap;
    }

    public final String getAdPlacementId() {
        return this.mPlacementId;
    }

    public final int getAPPConfirmPolicy() {
        return this.g;
    }
}
