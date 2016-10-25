package com.xiaomi.push.service.module;

public enum d {
    MODULE_CDATA("mpcd");
    public String b;

    static {
        String str = "mpcd";
        a = new d("MODULE_CDATA", 0, "mpcd");
        c = new d[]{a};
    }

    private d(String str) {
        this.b = str;
    }
}
