package com.taobao.accs.data;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

// compiled from: Taobao
class MessageHandler$1 extends LinkedHashMap<String, String> {
    final /* synthetic */ b a;

    MessageHandler$1(b bVar) {
        this.a = bVar;
    }

    protected boolean removeEldestEntry(Entry<String, String> entry) {
        return size() > 50;
    }
}
