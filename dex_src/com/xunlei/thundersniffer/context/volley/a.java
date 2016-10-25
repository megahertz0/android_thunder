package com.xunlei.thundersniffer.context.volley;

import java.util.concurrent.Executor;

final class a implements Executor {
    final /* synthetic */ RequestManager a;

    a(RequestManager requestManager) {
        this.a = requestManager;
    }

    public final void execute(Runnable runnable) {
        if (runnable != null) {
            RequestManager.executorService().execute(runnable);
        }
    }
}
