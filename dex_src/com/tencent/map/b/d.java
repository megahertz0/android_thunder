package com.tencent.map.b;

import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.tencent.map.b.d.b;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public final class d {
    private Context a;
    private TelephonyManager b;
    private a c;
    private c d;
    private b e;
    private boolean f;
    private List<NeighboringCellInfo> g;
    private byte[] h;
    private byte[] i;
    private boolean j;

    public class a extends PhoneStateListener {
        private int a;
        private int b;
        private int c;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;
        private Method i;
        private Method j;
        private Method k;
        private Method l;
        private Method m;

        public a(int i, int i2) {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = -1;
            this.g = Integer.MAX_VALUE;
            this.h = Integer.MAX_VALUE;
            this.i = null;
            this.j = null;
            this.k = null;
            this.l = null;
            this.m = null;
            this.b = i;
            this.a = i2;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onCellLocationChanged(android.telephony.CellLocation r12) {
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.map.b.d.a.onCellLocationChanged(android.telephony.CellLocation):void");
            /*
            this = this;
            r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
            r4 = 3;
            r3 = -1;
            r1 = 0;
            r11.f = r3;
            r11.e = r3;
            r11.d = r3;
            r11.c = r3;
            if (r12 == 0) goto L_0x0015;
        L_0x0010:
            r0 = r11.a;
            switch(r0) {
                case 1: goto L_0x0049;
                case 2: goto L_0x00aa;
                default: goto L_0x0015;
            };
        L_0x0015:
            r10 = com.tencent.map.b.d.this;
            r0 = new com.tencent.map.b.d$b;
            r1 = com.tencent.map.b.d.this;
            r2 = r11.a;
            r3 = r11.b;
            r4 = r11.c;
            r5 = r11.d;
            r6 = r11.e;
            r7 = r11.f;
            r8 = r11.g;
            r9 = r11.h;
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9);
            r10.e = r0;
            r0 = com.tencent.map.b.d.this;
            r0 = r0.d;
            if (r0 == 0) goto L_0x0048;
        L_0x0039:
            r0 = com.tencent.map.b.d.this;
            r0 = r0.d;
            r1 = com.tencent.map.b.d.this;
            r1 = r1.e;
            r0.a(r1);
        L_0x0048:
            return;
        L_0x0049:
            r0 = 0;
            r12 = (android.telephony.gsm.GsmCellLocation) r12;	 Catch:{ Exception -> 0x009e }
            r0 = r12.getLac();	 Catch:{ Exception -> 0x0173 }
            if (r0 > 0) goto L_0x0065;
        L_0x0052:
            r0 = r12.getCid();	 Catch:{ Exception -> 0x0173 }
            if (r0 > 0) goto L_0x0065;
        L_0x0058:
            r0 = com.tencent.map.b.d.this;	 Catch:{ Exception -> 0x0173 }
            r0 = r0.b;	 Catch:{ Exception -> 0x0173 }
            r0 = r0.getCellLocation();	 Catch:{ Exception -> 0x0173 }
            r0 = (android.telephony.gsm.GsmCellLocation) r0;	 Catch:{ Exception -> 0x0173 }
            r12 = r0;
        L_0x0065:
            r0 = 1;
        L_0x0066:
            if (r0 == 0) goto L_0x0015;
        L_0x0068:
            if (r12 == 0) goto L_0x0015;
        L_0x006a:
            r0 = com.tencent.map.b.d.this;
            r0 = r0.b;
            r0 = r0.getNetworkOperator();
            if (r0 == 0) goto L_0x008b;
        L_0x0076:
            r1 = r0.length();	 Catch:{ Exception -> 0x00a2 }
            if (r1 <= r4) goto L_0x008b;
        L_0x007c:
            r1 = 3;
            r0 = r0.substring(r1);	 Catch:{ Exception -> 0x00a2 }
            r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x00a2 }
            r0 = r0.intValue();	 Catch:{ Exception -> 0x00a2 }
            r11.c = r0;	 Catch:{ Exception -> 0x00a2 }
        L_0x008b:
            r0 = r12.getLac();	 Catch:{ Exception -> 0x00a2 }
            r11.d = r0;	 Catch:{ Exception -> 0x00a2 }
            r0 = r12.getCid();	 Catch:{ Exception -> 0x00a2 }
            r11.e = r0;	 Catch:{ Exception -> 0x00a2 }
        L_0x0097:
            r0 = com.tencent.map.b.d.this;
            com.tencent.map.b.d.c(r0);
            goto L_0x0015;
        L_0x009e:
            r2 = move-exception;
            r12 = r0;
        L_0x00a0:
            r0 = r1;
            goto L_0x0066;
        L_0x00a2:
            r0 = move-exception;
            r11.e = r3;
            r11.d = r3;
            r11.c = r3;
            goto L_0x0097;
        L_0x00aa:
            if (r12 == 0) goto L_0x0015;
        L_0x00ac:
            r0 = r11.i;	 Catch:{ Exception -> 0x0166 }
            if (r0 != 0) goto L_0x010f;
        L_0x00b0:
            r0 = "android.telephony.cdma.CdmaCellLocation";
            r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0166 }
            r1 = "getBaseStationId";
            r2 = 0;
            r2 = new java.lang.Class[r2];	 Catch:{ Exception -> 0x0166 }
            r0 = r0.getMethod(r1, r2);	 Catch:{ Exception -> 0x0166 }
            r11.i = r0;	 Catch:{ Exception -> 0x0166 }
            r0 = "android.telephony.cdma.CdmaCellLocation";
            r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0166 }
            r1 = "getSystemId";
            r2 = 0;
            r2 = new java.lang.Class[r2];	 Catch:{ Exception -> 0x0166 }
            r0 = r0.getMethod(r1, r2);	 Catch:{ Exception -> 0x0166 }
            r11.j = r0;	 Catch:{ Exception -> 0x0166 }
            r0 = "android.telephony.cdma.CdmaCellLocation";
            r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0166 }
            r1 = "getNetworkId";
            r2 = 0;
            r2 = new java.lang.Class[r2];	 Catch:{ Exception -> 0x0166 }
            r0 = r0.getMethod(r1, r2);	 Catch:{ Exception -> 0x0166 }
            r11.k = r0;	 Catch:{ Exception -> 0x0166 }
            r0 = "android.telephony.cdma.CdmaCellLocation";
            r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0166 }
            r1 = "getBaseStationLatitude";
            r2 = 0;
            r2 = new java.lang.Class[r2];	 Catch:{ Exception -> 0x0166 }
            r0 = r0.getMethod(r1, r2);	 Catch:{ Exception -> 0x0166 }
            r11.l = r0;	 Catch:{ Exception -> 0x0166 }
            r0 = "android.telephony.cdma.CdmaCellLocation";
            r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0166 }
            r1 = "getBaseStationLongitude";
            r2 = 0;
            r2 = new java.lang.Class[r2];	 Catch:{ Exception -> 0x0166 }
            r0 = r0.getMethod(r1, r2);	 Catch:{ Exception -> 0x0166 }
            r11.m = r0;	 Catch:{ Exception -> 0x0166 }
        L_0x010f:
            r0 = r11.j;	 Catch:{ Exception -> 0x0166 }
            r1 = 0;
            r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x0166 }
            r0 = r0.invoke(r12, r1);	 Catch:{ Exception -> 0x0166 }
            r0 = (java.lang.Integer) r0;	 Catch:{ Exception -> 0x0166 }
            r0 = r0.intValue();	 Catch:{ Exception -> 0x0166 }
            r11.c = r0;	 Catch:{ Exception -> 0x0166 }
            r0 = r11.k;	 Catch:{ Exception -> 0x0166 }
            r1 = 0;
            r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x0166 }
            r0 = r0.invoke(r12, r1);	 Catch:{ Exception -> 0x0166 }
            r0 = (java.lang.Integer) r0;	 Catch:{ Exception -> 0x0166 }
            r0 = r0.intValue();	 Catch:{ Exception -> 0x0166 }
            r11.d = r0;	 Catch:{ Exception -> 0x0166 }
            r0 = r11.i;	 Catch:{ Exception -> 0x0166 }
            r1 = 0;
            r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x0166 }
            r0 = r0.invoke(r12, r1);	 Catch:{ Exception -> 0x0166 }
            r0 = (java.lang.Integer) r0;	 Catch:{ Exception -> 0x0166 }
            r0 = r0.intValue();	 Catch:{ Exception -> 0x0166 }
            r11.e = r0;	 Catch:{ Exception -> 0x0166 }
            r0 = r11.l;	 Catch:{ Exception -> 0x0166 }
            r1 = 0;
            r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x0166 }
            r0 = r0.invoke(r12, r1);	 Catch:{ Exception -> 0x0166 }
            r0 = (java.lang.Integer) r0;	 Catch:{ Exception -> 0x0166 }
            r0 = r0.intValue();	 Catch:{ Exception -> 0x0166 }
            r11.g = r0;	 Catch:{ Exception -> 0x0166 }
            r0 = r11.m;	 Catch:{ Exception -> 0x0166 }
            r1 = 0;
            r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x0166 }
            r0 = r0.invoke(r12, r1);	 Catch:{ Exception -> 0x0166 }
            r0 = (java.lang.Integer) r0;	 Catch:{ Exception -> 0x0166 }
            r0 = r0.intValue();	 Catch:{ Exception -> 0x0166 }
            r11.h = r0;	 Catch:{ Exception -> 0x0166 }
            goto L_0x0015;
        L_0x0166:
            r0 = move-exception;
            r11.e = r3;
            r11.d = r3;
            r11.c = r3;
            r11.g = r5;
            r11.h = r5;
            goto L_0x0015;
        L_0x0173:
            r0 = move-exception;
            goto L_0x00a0;
            */
        }

        public final void onSignalStrengthChanged(int i) {
            if (this.a == 1) {
                d.c(d.this);
            }
            if (Math.abs(i - ((this.f + 113) / 2)) <= 3) {
                return;
            }
            if (this.f == -1) {
                this.f = (i << 1) - 113;
                return;
            }
            this.f = (i << 1) - 113;
            d.this.e = new b(d.this, this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            if (d.this.d != null) {
                d.this.d.a(d.this.e);
            }
        }
    }

    public class b implements Cloneable {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;

        public b(d dVar, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = Integer.MAX_VALUE;
            this.h = Integer.MAX_VALUE;
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
            this.g = i7;
            this.h = i8;
        }

        public final Object clone() {
            try {
                return (com.tencent.map.b.d.b) super.clone();
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static interface c {
        void a(b bVar);
    }

    public d() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = false;
        this.g = new LinkedList();
        this.h = new byte[0];
        this.i = new byte[0];
        this.j = false;
    }

    private int a(int i) {
        int intValue;
        String networkOperator = this.b.getNetworkOperator();
        if (networkOperator != null && networkOperator.length() >= 3) {
            try {
                intValue = Integer.valueOf(networkOperator.substring(0, XZBDevice.DOWNLOAD_LIST_FAILED)).intValue();
            } catch (Exception e) {
            }
            return (i == 2 || intValue != -1) ? intValue : 0;
        }
        intValue = -1;
        if (i == 2) {
        }
    }

    static /* synthetic */ void c(d dVar) {
        if (!dVar.j) {
            dVar.j = true;
            new Thread() {
                public final void run() {
                    if (d.this.b != null) {
                        Collection neighboringCellInfo = d.this.b.getNeighboringCellInfo();
                        synchronized (d.this.i) {
                            if (neighboringCellInfo != null) {
                                d.this.g.clear();
                                d.this.g.addAll(neighboringCellInfo);
                            }
                        }
                    }
                    d.this.j = false;
                }
            }.start();
        }
    }

    public final void a() {
        synchronized (this.h) {
            if (this.f) {
                if (!(this.b == null || this.c == null)) {
                    try {
                        this.b.listen(this.c, 0);
                    } catch (Exception e) {
                        this.f = false;
                    }
                }
                this.f = false;
                return;
            }
        }
    }

    public final boolean a(Context context, c cVar) {
        synchronized (this.h) {
            if (this.f) {
                return true;
            } else if (context == null || cVar == null) {
                return false;
            } else {
                this.a = context;
                this.d = cVar;
                try {
                    this.b = (TelephonyManager) this.a.getSystemService("phone");
                    if (this.b == null) {
                        return false;
                    }
                    int phoneType = this.b.getPhoneType();
                    this.c = new a(a(phoneType), phoneType);
                    if (this.c == null) {
                        return false;
                    }
                    this.b.listen(this.c, R.styleable.Toolbar_collapseIcon);
                    this.f = true;
                    return this.f;
                } catch (Exception e) {
                    return false;
                }
            }
        }
    }

    public final List<NeighboringCellInfo> b() {
        List<NeighboringCellInfo> list = null;
        synchronized (this.i) {
            if (this.g != null) {
                list = new LinkedList();
                list.addAll(this.g);
            }
        }
        return list;
    }
}
