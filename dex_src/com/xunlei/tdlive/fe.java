package com.xunlei.tdlive;

import com.xunlei.tdlive.protocol.XLLiveGetSearchHotKeywordsRequest.GetSearchHotKeywordsResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.sdk.IHost;

// compiled from: WebBrowserActivity.java
class fe implements ObjectCallBack {
    final /* synthetic */ WebBrowserActivity a;

    fe(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
    }

    public void onResponse(int i, String str, Object obj) {
        GetSearchHotKeywordsResp getSearchHotKeywordsResp = (GetSearchHotKeywordsResp) obj;
        if (i == 0 && getSearchHotKeywordsResp != null && getSearchHotKeywordsResp.data != null && getSearchHotKeywordsResp.data.keys != null) {
            this.a.mHotKeys = getSearchHotKeywordsResp.data.keys;
            if (this.a.mHotKeys.size() > 0) {
                this.a.setTimer(IHost.HOST_NOFITY_REFRESH_LIST, 0, 5000);
            }
        }
    }
}
