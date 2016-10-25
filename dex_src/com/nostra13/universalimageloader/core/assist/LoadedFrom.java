package com.nostra13.universalimageloader.core.assist;

public enum LoadedFrom {
    NETWORK,
    DISC_CACHE,
    MEMORY_CACHE;

    static {
        NETWORK = new LoadedFrom("NETWORK", 0);
        DISC_CACHE = new LoadedFrom("DISC_CACHE", 1);
        MEMORY_CACHE = new LoadedFrom("MEMORY_CACHE", 2);
        a = new LoadedFrom[]{NETWORK, DISC_CACHE, MEMORY_CACHE};
    }
}
