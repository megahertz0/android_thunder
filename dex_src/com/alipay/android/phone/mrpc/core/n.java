package com.alipay.android.phone.mrpc.core;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class n implements ThreadFactory {
    private final AtomicInteger a;

    n() {
        this.a = new AtomicInteger(1);
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, new StringBuilder("com.alipay.mobile.common.transport.http.HttpManager.HttpWorker #").append(this.a.getAndIncrement()).toString());
        thread.setPriority(XZBDevice.DOWNLOAD_LIST_ALL);
        return thread;
    }
}
