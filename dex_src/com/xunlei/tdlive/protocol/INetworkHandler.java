package com.xunlei.tdlive.protocol;

public interface INetworkHandler {
    void onInvalidVersion(String str, String str2);

    void onNoConnection();

    void onSessionInavlid();
}
