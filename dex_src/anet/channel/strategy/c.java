package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.entity.ConnType;
import anet.channel.util.ALog;
import com.taobao.accs.internal.b;
import com.umeng.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

// compiled from: Taobao
class c {
    static final IPConnStrategy c;
    final ConcurrentHashMap<String, IPConnStrategy> a;
    final HashMap<String, Object> b;

    c() {
        this.a = new ConcurrentHashMap();
        this.b = new HashMap();
    }

    static {
        c = a.a(a.d, a.a(0, ConnType.HTTP));
    }

    public List a(String str) {
        if (TextUtils.isEmpty(str) || !n.f(str)) {
            return Collections.EMPTY_LIST;
        }
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.LocalDnsStrategyTable", "try resolve ip with local dns", null, b.ELECTION_KEY_HOST, str);
        }
        List list = Collections.EMPTY_LIST;
        if (!this.a.containsKey(str)) {
            Object obj;
            synchronized (this.b) {
                if (this.b.containsKey(str)) {
                    obj = this.b.get(str);
                } else {
                    Object obj2 = new Object();
                    this.b.put(str, obj2);
                    a(str, obj2);
                    obj = obj2;
                }
            }
            if (obj != null) {
                try {
                    synchronized (obj) {
                        obj.wait(500);
                    }
                } catch (InterruptedException e) {
                }
            }
        }
        IPConnStrategy iPConnStrategy = (IPConnStrategy) this.a.get(str);
        if (iPConnStrategy == null || iPConnStrategy == c) {
            return list;
        }
        list = new ArrayList();
        list.add(iPConnStrategy);
        return list;
    }

    private void a(String str, Object obj) {
        new d(this, str, obj).execute(new Void[0]);
    }
}
