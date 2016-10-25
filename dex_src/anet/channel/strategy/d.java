package anet.channel.strategy;

import android.os.AsyncTask;
import anet.channel.entity.ConnType;
import anet.channel.util.ALog;
import com.taobao.accs.internal.b;
import com.xunlei.tdlive.R;
import java.net.InetAddress;

// compiled from: Taobao
class d extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ String a;
    final /* synthetic */ Object b;
    final /* synthetic */ c c;

    d(c cVar, String str, Object obj) {
        this.c = cVar;
        this.a = str;
        this.b = obj;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    protected Void a() {
        try {
            String hostAddress = InetAddress.getByName(this.a).getHostAddress();
            if (anet.channel.util.d.a(hostAddress)) {
                if (n.c(this.a)) {
                    this.c.a.put(this.a, a.a(hostAddress, a.a()));
                } else {
                    this.c.a.put(this.a, a.a(hostAddress, a.a(R.styleable.AppCompatTheme_panelMenuListTheme, ConnType.HTTP)));
                }
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.LocalDnsStrategyTable", "resolve ip by local dns", null, b.ELECTION_KEY_HOST, this.a, "ip", hostAddress);
                }
            } else {
                this.c.a.put(this.a, c.c);
            }
            synchronized (this.c.b) {
                this.c.b.remove(this.a);
            }
            synchronized (this.b) {
                this.b.notifyAll();
            }
        } catch (Exception e) {
            try {
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.LocalDnsStrategyTable", "resolve ip by local dns failed", null, b.ELECTION_KEY_HOST, this.a);
                }
                synchronized (this.c.b) {
                }
                this.c.b.remove(this.a);
                synchronized (this.b) {
                }
                this.b.notifyAll();
            } catch (Throwable th) {
                synchronized (this.c.b) {
                }
                this.c.b.remove(this.a);
                synchronized (this.b) {
                }
                this.b.notifyAll();
            }
        }
        return null;
    }
}
