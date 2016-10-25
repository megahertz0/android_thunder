package com.sina.weibo.sdk.api.share;

public interface IWeiboHandler {

    public static interface Request {
        void onRequest(BaseRequest baseRequest);
    }

    public static interface Response {
        void onResponse(BaseResponse baseResponse);
    }
}
