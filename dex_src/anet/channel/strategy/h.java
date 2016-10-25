package anet.channel.strategy;

import android.os.AsyncTask;
import java.io.Serializable;
import java.util.Map.Entry;

// compiled from: Taobao
class h extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ Entry a;
    final /* synthetic */ LURStrategyMap b;

    h(LURStrategyMap lURStrategyMap, Entry entry) {
        this.b = lURStrategyMap;
        this.a = entry;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    protected Void a() {
        l.a((Serializable) this.a.getValue(), StrategyInfoHolder.b(((StrategyTable) this.a.getValue()).a));
        return null;
    }
}
