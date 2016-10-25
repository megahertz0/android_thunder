package com.xunlei.common.accelerator.controller;

import android.content.Context;
import com.xunlei.common.accelerator.http.XLAccelHttpReqInfo;
import com.xunlei.common.accelerator.interactor.BaseInteractor;
import com.xunlei.common.accelerator.model.BaseModel;
import com.xunlei.common.httpclient.AsyncHttpClient;
import com.xunlei.common.httpclient.BaseHttpClientListener;

public abstract class BaseController<I extends BaseInteractor, M extends BaseModel> {
    private Context context;
    protected I interator;
    private AsyncHttpClient mAsyncHttpClient;
    protected M model;

    public BaseController(Context context, I i) {
        this.mAsyncHttpClient = null;
        this.context = context;
        this.interator = i;
        this.mAsyncHttpClient = new AsyncHttpClient();
        this.mAsyncHttpClient.setTimeout(XLAccelHttpReqInfo.HTTP_CLIENT_SOCKET_TIMEOUT);
    }

    protected void excuteRequest(String str, BaseHttpClientListener baseHttpClientListener) {
        this.mAsyncHttpClient.get(this.context, str, null, baseHttpClientListener);
    }
}
