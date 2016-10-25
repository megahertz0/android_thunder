package u.aly;

import org.apache.commons.logging.impl.SimpleLog;

// compiled from: ErrorSource.java
public enum aw {
    LEGIT(1),
    ALIEN(2);
    private final int c;

    static {
        a = new aw("LEGIT", 0, 1);
        b = new aw("ALIEN", 1, 2);
        d = new aw[]{a, b};
    }

    private aw(int i) {
        this.c = i;
    }

    public final int a() {
        return this.c;
    }

    public static aw a(int i) {
        switch (i) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return a;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return b;
            default:
                return null;
        }
    }
}
