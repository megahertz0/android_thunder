package com.google.zxing.pdf417.a;

import com.google.zxing.pdf417.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: BarcodeValue.java
final class b {
    private final Map<Integer, Integer> a;

    b() {
        this.a = new HashMap();
    }

    final void a(int i) {
        Integer num = (Integer) this.a.get(Integer.valueOf(i));
        if (num == null) {
            num = Integer.valueOf(0);
        }
        this.a.put(Integer.valueOf(i), Integer.valueOf(num.intValue() + 1));
    }

    final int[] a() {
        Collection arrayList = new ArrayList();
        int i = -1;
        for (Entry entry : this.a.entrySet()) {
            if (((Integer) entry.getValue()).intValue() > i) {
                int intValue = ((Integer) entry.getValue()).intValue();
                arrayList.clear();
                arrayList.add(entry.getKey());
                i = intValue;
            } else if (((Integer) entry.getValue()).intValue() == i) {
                arrayList.add(entry.getKey());
            }
        }
        return a.a(arrayList);
    }
}
