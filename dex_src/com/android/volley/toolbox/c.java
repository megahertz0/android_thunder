package com.android.volley.toolbox;

import java.util.Comparator;

// compiled from: ByteArrayPool.java
final class c implements Comparator<byte[]> {
    c() {
    }

    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        return ((byte[]) obj).length - ((byte[]) obj2).length;
    }
}
