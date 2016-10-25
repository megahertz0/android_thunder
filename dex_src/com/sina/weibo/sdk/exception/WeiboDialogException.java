package com.sina.weibo.sdk.exception;

public class WeiboDialogException extends WeiboException {
    private static final long serialVersionUID = 1;
    private int mErrorCode;
    private String mFailingUrl;

    public WeiboDialogException(String str, int i, String str2) {
        super(str);
        this.mErrorCode = i;
        this.mFailingUrl = str2;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String getFailingUrl() {
        return this.mFailingUrl;
    }
}
