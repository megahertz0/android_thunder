package com.xunlei.thundersniffer.sniff.sniffer;

import com.xunlei.thundersniffer.sniff.SniffingResource$Category;
import java.util.HashMap;

final class ac extends HashMap<String, SniffingResource$Category> {
    ac() {
        int i;
        int i2 = 0;
        String[] strArr = ab.a;
        int length = strArr.length;
        for (i = 0; i < length; i++) {
            put(strArr[i], SniffingResource$Category.BT);
        }
        strArr = ab.b;
        length = strArr.length;
        for (i = 0; i < length; i++) {
            put(strArr[i], SniffingResource$Category.VIDEO);
        }
        strArr = ab.c;
        length = strArr.length;
        for (i = 0; i < length; i++) {
            put(strArr[i], SniffingResource$Category.AUDIO);
        }
        strArr = ab.d;
        length = strArr.length;
        for (i = 0; i < length; i++) {
            put(strArr[i], SniffingResource$Category.DOCUMENT);
        }
        strArr = ab.e;
        length = strArr.length;
        for (i = 0; i < length; i++) {
            put(strArr[i], SniffingResource$Category.ARCHIVE);
        }
        String[] strArr2 = ab.f;
        int length2 = strArr2.length;
        while (i2 < length2) {
            put(strArr2[i2], SniffingResource$Category.SOFTWARE);
            i2++;
        }
    }
}
