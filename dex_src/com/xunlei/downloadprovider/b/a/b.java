package com.xunlei.downloadprovider.b.a;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import com.xunlei.xllib.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint({"HandlerLeak"})
// compiled from: BpRunnerManager.java
public class b implements d$a {
    public List<a> a;
    public Map<Integer, a> b;
    int c;
    private int d;
    private Handler e;

    @SuppressLint({"UseSparseArrays"})
    public b() {
        this.e = new c(this, Looper.getMainLooper());
        this.a = new ArrayList();
        this.b = new HashMap();
        this.c = 0;
        this.d = 10000;
    }

    final void b() {
        synchronized (this) {
            if (this.c < 30) {
                for (a aVar : this.a) {
                    if (aVar.getStatus() == 0) {
                        this.c++;
                        aVar.start();
                        break;
                    }
                }
            }
        }
    }

    public final void a(int i) {
        synchronized (this) {
            d dVar = (a) this.b.get(Integer.valueOf(i));
            if (dVar != null) {
                if (dVar.getStatus() == 1 || dVar.getStatus() == 0) {
                    if (dVar.getStatus() == 1) {
                        this.c--;
                    }
                    a(dVar);
                    dVar.stop();
                }
            }
        }
    }

    protected final void a(d dVar) {
        if (dVar != null) {
            synchronized (this) {
                this.a.remove(dVar);
                this.b.remove(dVar);
            }
        }
    }

    public final void a(int i, d dVar) {
        if (i == 4 || i == 2 || i == 3) {
            this.e.obtainMessage(R.styleable.AppCompatTheme_buttonStyle, dVar).sendToTarget();
        }
    }

    public final int a(a aVar) {
        if (this.d > 100000) {
            this.d = 10000;
        } else {
            this.d++;
        }
        int i = this.d;
        aVar.a.b = i;
        aVar.setOnStatusChangedListener(this);
        synchronized (this) {
            this.a.add(aVar);
            this.b.put(Integer.valueOf(i), aVar);
        }
        new StringBuilder("startRunner-------------").append(aVar.getRunnerId());
        b();
        return i;
    }
}
