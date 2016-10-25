package org.android.spdy;

public class NetTimeGaurd {
    public static final int CREATE = 0;
    public static final int ERROR = 2;
    public static final int PING = 1;
    public static final int STREAM = 3;
    private static final long calltime = 10;
    private static final long total = 50;
    private static long[] totaltime;

    static {
        totaltime = new long[4];
    }

    static void start(int i) {
        if (SpdyAgent.enableTimeGaurd) {
            totaltime[i] = 0;
        }
    }

    static long begin() {
        return SpdyAgent.enableTimeGaurd ? System.currentTimeMillis() : 0;
    }

    static void end(String str, int i, long j) {
        if (SpdyAgent.enableTimeGaurd) {
            long currentTimeMillis = System.currentTimeMillis() - j;
            totaltime[i] = totaltime[i] + currentTimeMillis;
            new StringBuilder("NetTimeGaurd[end]").append(str).append(" time=").append(currentTimeMillis).append(" total=").append(totaltime[i]);
            if (currentTimeMillis > 10) {
                throw new SpdyErrorException(new StringBuilder("CallBack:").append(str).append(" timeconsuming:").append(currentTimeMillis).append("  mustlessthan:10").toString(), -1);
            }
        }
    }

    static void finish(int i) {
        if (SpdyAgent.enableTimeGaurd) {
            new StringBuilder("NetTimeGaurd[finish]:time=").append(totaltime[i]);
            if (totaltime[i] > 50) {
                throw new SpdyErrorException(new StringBuilder("CallBack totaltimeconsuming:").append(totaltime[i]).append("  mustlessthan:50").toString(), -1);
            }
        }
    }
}
