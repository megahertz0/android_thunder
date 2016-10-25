package com.uc.addon.sdk.remote;

import com.uc.addon.sdk.remote.protocol.NavigationItem;

public interface Navigation {
    void addItem(NavigationItem navigationItem);

    boolean existItem(String str, String str2);
}
