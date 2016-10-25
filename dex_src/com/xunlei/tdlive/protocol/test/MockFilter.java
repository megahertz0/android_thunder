package com.xunlei.tdlive.protocol.test;

import com.xunlei.tdlive.protocol.XLLiveRequest;

public class MockFilter implements IRequestFilter {
    public boolean filter(Class<? extends XLLiveRequest> cls) {
        return false;
    }
}
