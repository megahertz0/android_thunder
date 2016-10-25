package com.xunlei.downloadprovider.homepage.relax.b;

import android.os.Handler;
import android.os.Looper;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.RelaxDataType;
import com.xunlei.downloadprovider.model.protocol.b.d;
import com.xunlei.downloadprovider.model.protocol.b.g;
import java.util.List;

// compiled from: GetDataTask.java
public class a {
    private static final String k;
    public RelaxDataType a;
    public GuestureType b;
    public int c;
    public a d;
    public long e;
    public long f;
    public int g;
    public long h;
    public Looper i;
    public Handler j;

    // compiled from: GetDataTask.java
    public static interface a {
        void a(int i, RelaxDataType relaxDataType, GuestureType guestureType, List<d> list);
    }

    static {
        k = a.class.getSimpleName();
    }

    public a(RelaxDataType relaxDataType, GuestureType guestureType, a aVar, long j, long j2, Looper looper) {
        this.a = relaxDataType;
        this.b = guestureType;
        this.c = 0;
        this.d = aVar;
        this.e = j;
        this.g = 10;
        this.f = j2;
        this.i = looper;
    }

    public final void a(int i, String str, RelaxDataType relaxDataType, GuestureType guestureType, List<d> list) {
        if (this.d != null) {
            this.d.a(i, relaxDataType, guestureType, list);
            if (relaxDataType == RelaxDataType.RES && guestureType == GuestureType.TOP && list != null && !list.isEmpty()) {
                RelaxDataManager.a();
                RelaxDataManager.a(str, ((d) list.get(list.size() - 1)).a - 1);
            }
            new StringBuilder("relaxlog ").append(g.a(list));
        }
    }
}
