package com.uc.addon.sdk.remote;

public interface AddonStateListener {
    void onAllUnbind();

    void onBind(Browser browser);

    void onUnbind(Browser browser);
}
