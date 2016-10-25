package com.bumptech.glide.load.engine;

import com.taobao.accs.flowcontrol.FlowControl;

public enum DiskCacheStrategy {
    ALL(true, true),
    NONE(false, false),
    SOURCE(true, false),
    RESULT(false, true);
    private final boolean a;
    private final boolean b;

    static {
        ALL = new DiskCacheStrategy(FlowControl.SERVICE_ALL, 0, true, true);
        NONE = new DiskCacheStrategy("NONE", 1, false, false);
        SOURCE = new DiskCacheStrategy("SOURCE", 2, true, false);
        RESULT = new DiskCacheStrategy("RESULT", 3, false, true);
        c = new DiskCacheStrategy[]{ALL, NONE, SOURCE, RESULT};
    }

    private DiskCacheStrategy(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
    }

    public final boolean cacheSource() {
        return this.a;
    }

    public final boolean cacheResult() {
        return this.b;
    }
}
