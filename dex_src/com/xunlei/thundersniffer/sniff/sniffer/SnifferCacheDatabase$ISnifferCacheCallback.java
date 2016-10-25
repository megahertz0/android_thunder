package com.xunlei.thundersniffer.sniff.sniffer;

public interface SnifferCacheDatabase$ISnifferCacheCallback {
    public static final int CACHE_NOT_FOUND = -1;
    public static final int CACHE_OUT_OF_DATE = 1;
    public static final int CACHE_VALID = 0;

    void onSnifferCacheCallback(String str, int i, String str2, long j, String str3);
}
