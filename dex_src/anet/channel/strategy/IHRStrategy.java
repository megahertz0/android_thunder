package anet.channel.strategy;

// compiled from: Taobao
public interface IHRStrategy {
    long getHRInterval();

    String getHRStrategy();

    int getHrNum();

    long getLastHRTime();

    int getParallelConnNum();

    String getUrlPath();
}
