package com.taobao.accs.net;

import anet.channel.strategy.StrategyCenter;

// compiled from: Taobao
class h implements Runnable {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void run() {
        StrategyCenter.getInstance().saveData();
    }
}
