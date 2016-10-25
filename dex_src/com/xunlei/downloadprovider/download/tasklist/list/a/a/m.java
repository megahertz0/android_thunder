package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.a;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import com.xunlei.downloadprovider.download.tasklist.list.a.n;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.android.spdy.SpdyAgent;

// compiled from: ListItemADClient.java
public final class m {
    public static m a;
    public boolean b;
    public Map<Integer, Boolean> c;

    private m() {
        this.c = new HashMap();
    }

    public static m a() {
        if (a == null) {
            a = new m();
        }
        return a;
    }

    public static void a(a aVar) {
        if (a.a().b.contains(h.a(aVar)) || !h.d(aVar.b())) {
            aVar.e();
            return;
        }
        b bVar;
        Map b = a.a().b(aVar.b());
        if (b != null) {
            bVar = (b) b.get(h.a(aVar));
        } else {
            bVar = null;
        }
        if (bVar != null) {
            aVar.a(bVar);
        } else if (h.a[aVar.b()]) {
            aVar.e();
        } else {
            aVar.a();
            Set set = a.a().a;
            if (!set.contains(h.a(aVar))) {
                set.add(h.a(aVar));
                n.a().a(new com.xunlei.downloadprovider.download.tasklist.list.a.a(aVar));
                p pVar = new p(aVar);
                o oVar = new o(aVar);
                f a = f.a();
                switch (aVar.b()) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        b = a.b;
                        break;
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        b = a.c;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        b = a.d;
                        break;
                    default:
                        b = a.h;
                        break;
                }
                AD_TYPE a2 = com.xunlei.downloadprovider.ad.common.b.a(b);
                l lVar;
                n nVar;
                if (a2 == AD_TYPE.SOURCE_GDT_FLAG) {
                    lVar = new l(aVar);
                    lVar.a(pVar);
                    pVar.a(oVar);
                    nVar = new n();
                    n.a(BrothersApplication.a(), 0, aVar.b()).a(lVar);
                } else if (a2 == AD_TYPE.SOURCE_BAIDU_FLAG) {
                    e eVar = new e(aVar);
                    eVar.a(pVar);
                    pVar.a(oVar);
                    nVar = new n();
                    n.a(BrothersApplication.a(), XZBDevice.DOWNLOAD_LIST_RECYCLE, aVar.b()).a(eVar);
                } else if (a2 == AD_TYPE.SOURCE_SSP_FLAG) {
                    t tVar = new t(aVar);
                    i iVar = new i(aVar);
                    iVar.a(pVar);
                    pVar.a(oVar);
                    tVar.a(iVar);
                    tVar.a();
                } else {
                    lVar = new l(aVar);
                    lVar.a(pVar);
                    pVar.a(oVar);
                    nVar = new n();
                    n.a(BrothersApplication.a(), 0, aVar.b()).a(lVar);
                }
            }
        }
    }
}
