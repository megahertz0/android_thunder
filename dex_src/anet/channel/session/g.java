package anet.channel.session;

import anet.channel.RequestCb;
import anet.channel.request.Request;

// compiled from: Taobao
class g implements Runnable {
    final /* synthetic */ Request a;
    final /* synthetic */ RequestCb b;
    final /* synthetic */ e c;

    g(e eVar, Request request, RequestCb requestCb) {
        this.c = eVar;
        this.a = request;
        this.b = requestCb;
    }

    public void run() {
        c.a(this.a, new h(this));
    }
}
