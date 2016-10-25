package com.tencent.map.b;

import android.net.wifi.ScanResult;
import com.umeng.socialize.editorpage.ShareActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public final class c {
    private static c a;
    private long b;
    private List<a> c;
    private List<b> d;
    private String e;

    static class a {
        public int a;
        public int b;
        public int c;
        public int d;

        private a() {
            this.a = -1;
            this.b = -1;
            this.c = -1;
            this.d = -1;
        }
    }

    static class b {
        public String a;

        private b() {
            this.a = null;
        }
    }

    public c() {
        this.b = 0;
        this.c = new ArrayList();
        this.d = new ArrayList();
    }

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    private static boolean a(StringBuffer stringBuffer) {
        try {
            return new JSONObject(stringBuffer.toString()).getJSONObject(ShareActivity.KEY_LOCATION).getDouble("accuracy") < 5000.0d;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean a(List<ScanResult> list) {
        if (list == null) {
            return false;
        }
        int i;
        if (this.d != null) {
            i = 0;
            for (int i2 = 0; i2 < this.d.size(); i2++) {
                String str = ((b) this.d.get(i2)).a;
                int i3 = 0;
                while (str != null && i3 < list.size()) {
                    if (str.equals(((ScanResult) list.get(i3)).BSSID)) {
                        i++;
                        break;
                    }
                    i3++;
                }
            }
        } else {
            i = 0;
        }
        int size = list.size();
        return (size < 6 || i < (size / 2) + 1) ? (size >= 6 || i < 2) ? this.d.size() <= 2 && list.size() <= 2 && Math.abs(System.currentTimeMillis() - this.b) <= 30000 : true : true;
    }

    public final void a(int i, int i2, int i3, int i4, List<ScanResult> list) {
        this.b = System.currentTimeMillis();
        this.e = null;
        this.c.clear();
        a aVar = new a();
        aVar.a = i;
        aVar.b = i2;
        aVar.c = i3;
        aVar.d = i4;
        this.c.add(aVar);
        if (list != null) {
            this.d.clear();
            for (int i5 = 0; i5 < list.size(); i5++) {
                b bVar = new b();
                bVar.a = ((ScanResult) list.get(i5)).BSSID;
                int i6 = ((ScanResult) list.get(i5)).level;
                this.d.add(bVar);
            }
        }
    }

    public final void a(String str) {
        this.e = str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String b(int r8, int r9, int r10, int r11, java.util.List<android.net.wifi.ScanResult> r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.map.b.c.b(int, int, int, int, java.util.List):java.lang.String");
        /*
        this = this;
        r6 = 2;
        r1 = 0;
        r0 = r7.e;
        if (r0 == 0) goto L_0x0010;
    L_0x0006:
        r0 = r7.e;
        r0 = r0.length();
        r2 = 10;
        if (r0 >= r2) goto L_0x0011;
    L_0x0010:
        return r1;
    L_0x0011:
        r0 = r7.e;
        if (r0 == 0) goto L_0x0017;
    L_0x0015:
        if (r12 != 0) goto L_0x005a;
    L_0x0017:
        r0 = r1;
    L_0x0018:
        r7.e = r0;
        r0 = r7.e;
        if (r0 == 0) goto L_0x0010;
    L_0x001e:
        r0 = r7.c;
        if (r0 == 0) goto L_0x0095;
    L_0x0022:
        r0 = r7.c;
        r0 = r0.size();
        if (r0 <= 0) goto L_0x0095;
    L_0x002a:
        r0 = r7.c;
        r2 = 0;
        r0 = r0.get(r2);
        r0 = (com.tencent.map.b.c.a) r0;
        r2 = r0.a;
        if (r2 != r8) goto L_0x0010;
    L_0x0037:
        r2 = r0.b;
        if (r2 != r9) goto L_0x0010;
    L_0x003b:
        r2 = r0.c;
        if (r2 != r10) goto L_0x0010;
    L_0x003f:
        r0 = r0.d;
        if (r0 != r11) goto L_0x0010;
    L_0x0043:
        r0 = r7.d;
        if (r0 == 0) goto L_0x004f;
    L_0x0047:
        r0 = r7.d;
        r0 = r0.size();
        if (r0 != 0) goto L_0x008b;
    L_0x004f:
        if (r12 == 0) goto L_0x0057;
    L_0x0051:
        r0 = r12.size();
        if (r0 != 0) goto L_0x008b;
    L_0x0057:
        r1 = r7.e;
        goto L_0x0010;
    L_0x005a:
        r2 = java.lang.System.currentTimeMillis();
        r4 = r7.b;
        r2 = r2 - r4;
        r2 = java.lang.Math.abs(r2);
        r4 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x0071;
    L_0x006b:
        r4 = r12.size();
        if (r4 > r6) goto L_0x0089;
    L_0x0071:
        r4 = 45000; // 0xafc8 float:6.3058E-41 double:2.2233E-319;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x007e;
    L_0x0078:
        r2 = r12.size();
        if (r2 <= r6) goto L_0x0089;
    L_0x007e:
        r2 = new java.lang.StringBuffer;
        r2.<init>(r0);
        r2 = a(r2);
        if (r2 != 0) goto L_0x0018;
    L_0x0089:
        r0 = r1;
        goto L_0x0018;
    L_0x008b:
        r0 = r7.a(r12);
        if (r0 == 0) goto L_0x0095;
    L_0x0091:
        r1 = r7.e;
        goto L_0x0010;
    L_0x0095:
        r0 = r7.a(r12);
        if (r0 == 0) goto L_0x0010;
    L_0x009b:
        r1 = r7.e;
        goto L_0x0010;
        */
    }

    public final void b() {
        this.e = null;
    }
}
