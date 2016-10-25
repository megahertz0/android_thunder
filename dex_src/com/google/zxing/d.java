package com.google.zxing;

// compiled from: Dimension.java
public final class d {
    public final int a;
    public final int b;

    public final boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.a == dVar.a && this.b == dVar.b;
    }

    public final int hashCode() {
        return (this.a * 32713) + this.b;
    }

    public final String toString() {
        return this.a + "x" + this.b;
    }
}
