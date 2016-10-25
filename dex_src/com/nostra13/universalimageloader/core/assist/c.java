package com.nostra13.universalimageloader.core.assist;

// compiled from: ImageSize.java
public final class c {
    public final int a;
    public final int b;

    public c(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public c(int i, int i2, int i3) {
        if (i3 % 180 == 0) {
            this.a = i;
            this.b = i2;
            return;
        }
        this.a = i2;
        this.b = i;
    }

    public final String toString() {
        return new StringBuilder(9).append(this.a).append("x").append(this.b).toString();
    }
}
