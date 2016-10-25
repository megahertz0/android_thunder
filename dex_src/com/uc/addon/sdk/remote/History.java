package com.uc.addon.sdk.remote;

import android.webkit.ValueCallback;
import com.uc.addon.sdk.remote.protocol.HistorySearchParam;

public interface History {
    void delete(String str, String str2, int i);

    void search(HistorySearchParam historySearchParam, ValueCallback valueCallback);
}
