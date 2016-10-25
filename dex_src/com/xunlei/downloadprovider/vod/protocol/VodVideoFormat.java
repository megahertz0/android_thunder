package com.xunlei.downloadprovider.vod.protocol;

public enum VodVideoFormat {
    flv,
    mp4;

    static {
        flv = new VodVideoFormat("flv", 0);
        mp4 = new VodVideoFormat("mp4", 1);
        a = new VodVideoFormat[]{flv, mp4};
    }
}
