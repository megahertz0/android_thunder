package com.xunlei.downloadprovider.frame.user;

// compiled from: UserCenterStateManager.java
public final class bn {
    private static bn c;
    public boolean a;
    private boolean b;

    private bn() {
        this.b = true;
    }

    public static bn a() {
        if (c == null) {
            c = new bn();
        }
        return c;
    }
}
