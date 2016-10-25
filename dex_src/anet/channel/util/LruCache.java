package anet.channel.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

// compiled from: Taobao
public class LruCache<K, V> extends LinkedHashMap<K, V> {
    private int a;

    public LruCache(int i) {
        super(i + 1, 1.0f, true);
        this.a = i;
    }

    protected boolean removeEldestEntry(Entry<K, V> entry) {
        return size() > this.a ? a(entry) : false;
    }

    public boolean a(Entry<K, V> entry) {
        return true;
    }
}
