package com.xunlei.downloadprovider.util.sniff;

import com.xunlei.thundersniffer.sniff.SniffingFilter;
import com.xunlei.thundersniffer.sniff.SniffingResource.Category;
import java.util.HashSet;
import java.util.Set;

// compiled from: SniffResourceFilter.java
public final class d implements SniffingFilter {
    private static SniffingFilter b;
    protected HashSet<String> a;

    public d(Set<String> set) {
        this.a = new HashSet(set);
    }

    public final boolean accept(String str, String str2, String str3, Category category, String str4) {
        return this.a.contains(str3);
    }

    public static SniffingFilter a() {
        if (b == null) {
            b = new e();
        }
        return b;
    }
}
