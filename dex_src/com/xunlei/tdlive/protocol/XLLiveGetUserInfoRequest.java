package com.xunlei.tdlive.protocol;

public class XLLiveGetUserInfoRequest extends XLLiveRequest {
    private LType mLType;
    private String mQueryUid;

    public enum LType {
        XL,
        WX
    }

    public XLLiveGetUserInfoRequest(String str, String str2, String str3, LType lType) {
        super(str, str2);
        this.mLType = lType;
        this.mQueryUid = str3;
    }

    protected String onGetURL() {
        return String.format("http://biz.live.xunlei.com/caller?c=%s&a=getinfo&type=%s", new Object[]{"user", Integer.valueOf(this.mLType.ordinal())});
    }

    protected Class<?> onGetObjectClass() {
        return GetUserInfoResp.class;
    }
}
