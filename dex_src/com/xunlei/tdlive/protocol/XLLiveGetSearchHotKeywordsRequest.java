package com.xunlei.tdlive.protocol;

public class XLLiveGetSearchHotKeywordsRequest extends XLLiveRequest {
    public XLLiveGetSearchHotKeywordsRequest(String str, String str2) {
        super(str, str2);
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=user&a=hotkeys";
    }

    protected Class<?> onGetObjectClass() {
        return GetSearchHotKeywordsResp.class;
    }
}
