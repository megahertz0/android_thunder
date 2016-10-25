package com.xunlei.downloadprovidercommon.b.b;

// compiled from: Parameter.java
public final class a implements Comparable<a> {
    public final String a;
    public final String b;

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        a aVar = (a) obj;
        int compareTo = this.a.compareTo(aVar.a);
        return compareTo == 0 ? this.b.compareTo(aVar.b) : compareTo;
    }

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return aVar.a.equals(this.a) && aVar.b.equals(this.b);
    }

    public final int hashCode() {
        return this.a.hashCode() + this.b.hashCode();
    }
}
