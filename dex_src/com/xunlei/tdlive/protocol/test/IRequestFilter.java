package com.xunlei.tdlive.protocol.test;

import com.xunlei.tdlive.protocol.XLLiveRequest;

public interface IRequestFilter {
    boolean filter(Class<? extends XLLiveRequest> cls);
}
