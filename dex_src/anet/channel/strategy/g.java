package anet.channel.strategy;

// compiled from: Taobao
class g implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ StrategyInfoHolder b;

    g(StrategyInfoHolder strategyInfoHolder, String str) {
        this.b = strategyInfoHolder;
        this.a = str;
    }

    public void run() {
        this.b.a(StrategyInfoHolder.b(this.a), this.a);
    }
}
