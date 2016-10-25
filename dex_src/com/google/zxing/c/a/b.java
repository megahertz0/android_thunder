package com.google.zxing.c.a;

import com.umeng.socialize.common.SocializeConstants;

// compiled from: DataCharacter.java
public class b {
    public final int a;
    public final int b;

    public b(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final String toString() {
        return this.a + SocializeConstants.OP_OPEN_PAREN + this.b + ')';
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.a == bVar.a && this.b == bVar.b;
    }

    public final int hashCode() {
        return this.a ^ this.b;
    }
}
