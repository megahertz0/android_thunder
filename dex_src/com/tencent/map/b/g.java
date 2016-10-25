package com.tencent.map.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.map.b.g.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

public final class g {
    private Context a;
    private WifiManager b;
    private a c;
    private Handler d;
    private Runnable e;
    private int f;
    private c g;
    private b h;
    private boolean i;
    private byte[] j;

    public static interface c {
        void a(b bVar);

        void b(int i);
    }

    public class a extends BroadcastReceiver {
        private int a;
        private List<ScanResult> b;
        private boolean c;

        public a() {
            this.a = 4;
            this.b = null;
            this.c = false;
        }

        private void a(List<ScanResult> list) {
            if (list != null) {
                if (this.c) {
                    if (this.b == null) {
                        this.b = new ArrayList();
                    }
                    int size = this.b.size();
                    for (ScanResult scanResult : list) {
                        for (int i = 0; i < size; i++) {
                            if (((ScanResult) this.b.get(i)).BSSID.equals(scanResult.BSSID)) {
                                this.b.remove(i);
                                break;
                            }
                        }
                        this.b.add(scanResult);
                    }
                    return;
                }
                if (this.b == null) {
                    this.b = new ArrayList();
                } else {
                    this.b.clear();
                }
                for (ScanResult scanResult2 : list) {
                    this.b.add(scanResult2);
                }
            }
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                this.a = intent.getIntExtra("wifi_state", XZBDevice.DOWNLOAD_LIST_ALL);
                if (g.this.g != null) {
                    g.this.g.b(this.a);
                }
            }
            if (intent.getAction().equals("android.net.wifi.SCAN_RESULTS") || intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                List list = null;
                if (g.this.b != null) {
                    list = g.this.b.getScanResults();
                }
                if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                    if (list == null) {
                        return;
                    }
                    if (list != null && list.size() == 0) {
                        return;
                    }
                }
                if (this.c || this.b == null || this.b.size() < 4 || list == null || list.size() > 2) {
                    a(list);
                    this.c = false;
                    g.this.h = new b(g.this, this.b, System.currentTimeMillis(), this.a);
                    if (g.this.g != null) {
                        g.this.g.a(g.this.h);
                    }
                    g.this.a(((long) g.this.f) * 20000);
                    return;
                }
                a(list);
                this.c = true;
                g.this.a(0);
            }
        }
    }

    public class b implements Cloneable {
        private List<ScanResult> a;

        public b(g gVar, List<ScanResult> list, long j, int i) {
            this.a = null;
            if (list != null) {
                this.a = new ArrayList();
                for (ScanResult scanResult : list) {
                    this.a.add(scanResult);
                }
            }
        }

        public final List<ScanResult> a() {
            return this.a;
        }

        public final Object clone() {
            com.tencent.map.b.g.b bVar;
            try {
                bVar = (com.tencent.map.b.g.b) super.clone();
            } catch (Exception e) {
                bVar = null;
            }
            if (this.a != null) {
                bVar.a = new ArrayList();
                bVar.a.addAll(this.a);
            }
            return bVar;
        }
    }

    public g() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = new Runnable() {
            public final void run() {
                g.a(g.this);
            }
        };
        this.f = 1;
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = new byte[0];
    }

    static /* synthetic */ void a(g gVar) {
        if (gVar.b != null && gVar.b.isWifiEnabled()) {
            gVar.b.startScan();
        }
    }

    public final void a() {
        synchronized (this.j) {
            if (!this.i) {
            } else if (this.a == null || this.c == null) {
            } else {
                try {
                    this.a.unregisterReceiver(this.c);
                } catch (Exception e) {
                }
                this.d.removeCallbacks(this.e);
                this.i = false;
            }
        }
    }

    public final void a(long j) {
        if (this.d != null && this.i) {
            this.d.removeCallbacks(this.e);
            this.d.postDelayed(this.e, j);
        }
    }

    public final boolean a(Context context, c cVar, int i) {
        synchronized (this.j) {
            if (this.i) {
                return true;
            } else if (context == null || cVar == null) {
                return false;
            } else {
                this.d = new Handler(Looper.getMainLooper());
                this.a = context;
                this.g = cVar;
                this.f = 1;
                try {
                    this.b = (WifiManager) this.a.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                    IntentFilter intentFilter = new IntentFilter();
                    this.c = new a();
                    if (this.b == null || this.c == null) {
                        return false;
                    }
                    intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                    intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
                    this.a.registerReceiver(this.c, intentFilter);
                    a(0);
                    this.i = true;
                    return this.i;
                } catch (Exception e) {
                    return false;
                }
            }
        }
    }

    public final boolean b() {
        return this.i;
    }

    public final boolean c() {
        return (this.a == null || this.b == null) ? false : this.b.isWifiEnabled();
    }
}
