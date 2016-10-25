package com.nostra13.universalimageloader.b;

import java.util.Comparator;

// compiled from: MemoryCacheUtils.java
public final class e implements Comparator<String> {
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        String str = (String) obj;
        String str2 = (String) obj2;
        return str.substring(0, str.lastIndexOf("_")).compareTo(str2.substring(0, str2.lastIndexOf("_")));
    }
}
