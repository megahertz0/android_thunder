package com.tencent.mm.sdk.diffdev.a;

public enum g {
    UUID_EXPIRED(402),
    UUID_CANCELED(403),
    UUID_SCANED(404),
    UUID_CONFIRM(405),
    UUID_KEEP_CONNECT(408),
    UUID_ERROR(500);
    private int code;

    static {
        az = new g("UUID_EXPIRED", 0, 402);
        aA = new g("UUID_CANCELED", 1, 403);
        aB = new g("UUID_SCANED", 2, 404);
        aC = new g("UUID_CONFIRM", 3, 405);
        aD = new g("UUID_KEEP_CONNECT", 4, 408);
        aE = new g("UUID_ERROR", 5, 500);
        aF = new g[]{az, aA, aB, aC, aD, aE};
    }

    private g(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    public final String toString() {
        return new StringBuilder("UUIDStatusCode:").append(this.code).toString();
    }
}
