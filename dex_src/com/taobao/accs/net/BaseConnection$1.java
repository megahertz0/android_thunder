package com.taobao.accs.net;

import com.taobao.accs.data.Message;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

// compiled from: Taobao
class BaseConnection$1 extends LinkedHashMap<Integer, Message> {
    final /* synthetic */ a a;

    BaseConnection$1(a aVar) {
        this.a = aVar;
    }

    protected boolean removeEldestEntry(Entry<Integer, Message> entry) {
        return size() > 10;
    }
}
