package com.xunlei.tdlive.protocol.test;

import com.umeng.a;
import com.xunlei.tdlive.protocol.XLLiveRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

public class MockRequest extends XLLiveRequest {
    private JsonCallBack mJsonCallback;
    private ObjectCallBack mObjectCallback;
    private Object mResult;

    private MockRequest(String str, String str2) {
        super(str, str2);
    }

    protected String onGetURL() {
        return null;
    }

    public MockRequest(Object obj) {
        super(a.d, a.d);
        this.mResult = obj;
    }

    public void send(JsonCallBack jsonCallBack) {
        this.mJsonCallback = jsonCallBack;
        new MyAsynTask(this, null).execute(new String[0]);
    }

    public void send(ObjectCallBack objectCallBack) {
        this.mObjectCallback = objectCallBack;
        new MyAsynTask(this, null).execute(new String[0]);
    }
}
