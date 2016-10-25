package com.sina.weibo.sdk.exception;

public class WeiboShareException extends WeiboException {
    private static final long serialVersionUID = 1;

    public WeiboShareException(String str) {
        super(str);
    }

    public WeiboShareException(String str, Throwable th) {
        super(str, th);
    }

    public WeiboShareException(Throwable th) {
        super(th);
    }
}
