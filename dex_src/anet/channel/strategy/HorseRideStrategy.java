package anet.channel.strategy;

import java.io.Serializable;

// compiled from: Taobao
class HorseRideStrategy implements IHRStrategy, Serializable {
    public final long horseRideInterval;
    public final String horseRideMethod;
    public final int hrNum;
    public long lastHorseRideTime;
    public final int maxParallelConnNum;
    public final String urlPath;

    // compiled from: Taobao
    static class a {
        a() {
        }

        static HorseRideStrategy a(String str, String str2, long j, int i, int i2) {
            return new HorseRideStrategy(str2, j, i, i2, null);
        }
    }

    private HorseRideStrategy(String str, String str2, long j, int i, int i2) {
        this.horseRideMethod = str;
        this.urlPath = str2;
        this.horseRideInterval = j;
        this.maxParallelConnNum = i;
        this.hrNum = i2;
    }

    public String getHRStrategy() {
        return this.horseRideMethod;
    }

    public long getHRInterval() {
        return this.horseRideInterval;
    }

    public String getUrlPath() {
        return this.urlPath;
    }

    public int getParallelConnNum() {
        return this.maxParallelConnNum;
    }

    public long getLastHRTime() {
        return this.lastHorseRideTime;
    }

    public int getHrNum() {
        return this.hrNum;
    }
}
