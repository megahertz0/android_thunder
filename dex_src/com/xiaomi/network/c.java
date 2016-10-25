package com.xiaomi.network;

import java.util.ArrayList;
import java.util.Iterator;

class c extends Fallback {
    Fallback i;
    final /* synthetic */ Fallback j;
    final /* synthetic */ HostManager k;

    c(HostManager hostManager, String str, Fallback fallback) {
        this.k = hostManager;
        this.j = fallback;
        super(str);
        this.i = this.j;
        if (this.j != null) {
            this.f = this.j.f;
        }
    }

    public synchronized ArrayList<String> a(boolean z) {
        ArrayList<String> arrayList;
        try {
            arrayList = new ArrayList();
            if (this.i != null) {
                arrayList.addAll(this.i.a(true));
            }
            synchronized (HostManager.mReservedHosts) {
                ArrayList arrayList2 = (ArrayList) HostManager.mReservedHosts.get(this.b);
                if (arrayList2 != null) {
                    ArrayList a;
                    if (this.i != null) {
                        a = this.i.a(false);
                    } else {
                        ArrayList<String> arrayList3 = arrayList;
                    }
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        String str = (String) it.next();
                        if (a.indexOf(str) == -1) {
                            arrayList.add(str);
                        }
                    }
                    arrayList.remove(this.b);
                    arrayList.add(this.b);
                }
            }
        } catch (Throwable th) {
        }
        return arrayList;
    }

    public synchronized void a(String str, AccessHistory accessHistory) {
        if (this.i != null) {
            this.i.a(str, accessHistory);
        }
    }

    public boolean b() {
        return false;
    }
}
