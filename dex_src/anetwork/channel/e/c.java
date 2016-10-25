package anetwork.channel.e;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

// compiled from: Taobao
final class c extends LinkedHashMap<String, String> {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    protected final boolean removeEldestEntry(Entry<String, String> entry) {
        return size() > 100;
    }
}
