package anetwork.channel.monitor.speed;

// compiled from: Taobao
public enum NetworkSpeed {
    Slow("\u5f31\u7f51\u7edc", 1),
    Fast("\u5f3a\u7f51\u7edc", 5);
    private final String a;
    private final int b;

    static {
        String str = "\u5f31\u7f51\u7edc";
        Slow = new NetworkSpeed("Slow", 0, "\u5f31\u7f51\u7edc", 1);
        str = "\u5f3a\u7f51\u7edc";
        Fast = new NetworkSpeed("Fast", 1, "\u5f3a\u7f51\u7edc", 5);
        c = new NetworkSpeed[]{Slow, Fast};
    }

    private NetworkSpeed(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public final String getDesc() {
        return this.a;
    }

    public final int getCode() {
        return this.b;
    }

    public static NetworkSpeed valueOfCode(int i) {
        return i == 1 ? Slow : Fast;
    }
}
