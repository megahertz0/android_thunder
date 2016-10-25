package com.taobao.accs.net;

import anet.channel.strategy.dispatch.DispatchEvent;
import anet.channel.strategy.dispatch.HttpDispatcher.IDispatchEventListener;
import com.taobao.accs.common.a;
import java.util.concurrent.TimeUnit;

// compiled from: Taobao
class g implements IDispatchEventListener {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void onEvent(DispatchEvent dispatchEvent) {
        a.a(new h(this), 2000, TimeUnit.MILLISECONDS);
    }
}
