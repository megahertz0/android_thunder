package com.xunlei.downloadprovider.web.record;

import com.xunlei.downloadprovider.model.b;

// compiled from: RecordInfo.java
public final class t {
    public boolean a;
    public Object b;

    public t(Object obj) {
        this.a = false;
        this.b = obj;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return (this.b instanceof b) && ((b) this.b).c.equals(((b) ((t) obj).b).c);
    }

    public final int hashCode() {
        if (this.b instanceof b) {
            return ((b) this.b).c == null ? 0 : ((b) this.b).c.hashCode();
        } else {
            return 1;
        }
    }
}
