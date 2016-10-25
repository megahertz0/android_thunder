package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.smack.util.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class d {
    protected static final String a;
    public static final DateFormat b;
    private static String c;
    private static String d;
    private static long e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private List<a> l;
    private final Map<String, Object> m;
    private h n;

    static {
        a = Locale.getDefault().getLanguage().toLowerCase();
        c = null;
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        b = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        d = g.a((int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED) + SocializeConstants.OP_DIVIDER_MINUS;
        e = 0;
    }

    public d() {
        this.f = c;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = new CopyOnWriteArrayList();
        this.m = new HashMap();
        this.n = null;
    }

    public d(Bundle bundle) {
        this.f = c;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = new CopyOnWriteArrayList();
        this.m = new HashMap();
        this.n = null;
        this.h = bundle.getString("ext_to");
        this.i = bundle.getString("ext_from");
        this.j = bundle.getString("ext_chid");
        this.g = bundle.getString("ext_pkt_id");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.l = new ArrayList(parcelableArray.length);
            int length = parcelableArray.length;
            for (int i = 0; i < length; i++) {
                a a = a.a((Bundle) parcelableArray[i]);
                if (a != null) {
                    this.l.add(a);
                }
            }
        }
        Bundle bundle2 = bundle.getBundle("ext_ERROR");
        if (bundle2 != null) {
            this.n = new h(bundle2);
        }
    }

    public static synchronized String j() {
        String toString;
        synchronized (d.class) {
            StringBuilder append = new StringBuilder().append(d);
            long j = e;
            e = 1 + j;
            toString = append.append(Long.toString(j)).toString();
        }
        return toString;
    }

    public static String u() {
        return a;
    }

    public abstract String a();

    public void a(a aVar) {
        this.l.add(aVar);
    }

    public void a(h hVar) {
        this.n = hVar;
    }

    public a b(String str, String str2) {
        for (a aVar : this.l) {
            if ((str2 == null || str2.equals(aVar.b())) && str.equals(aVar.a())) {
                return aVar;
            }
        }
        return null;
    }

    public Bundle c() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.f)) {
            bundle.putString("ext_ns", this.f);
        }
        if (!TextUtils.isEmpty(this.i)) {
            bundle.putString("ext_from", this.i);
        }
        if (!TextUtils.isEmpty(this.h)) {
            bundle.putString("ext_to", this.h);
        }
        if (!TextUtils.isEmpty(this.g)) {
            bundle.putString("ext_pkt_id", this.g);
        }
        if (!TextUtils.isEmpty(this.j)) {
            bundle.putString("ext_chid", this.j);
        }
        if (this.n != null) {
            bundle.putBundle("ext_ERROR", this.n.c());
        }
        if (this.l != null) {
            Parcelable[] parcelableArr = new Parcelable[this.l.size()];
            int i = 0;
            for (a aVar : this.l) {
                int i2;
                Bundle e = aVar.e();
                if (e != null) {
                    i2 = i + 1;
                    parcelableArr[i] = e;
                } else {
                    i2 = i;
                }
                i = i2;
            }
            bundle.putParcelableArray("ext_exts", parcelableArr);
        }
        return bundle;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.n == null ? dVar.n != null : !this.n.equals(dVar.n)) {
            return false;
        }
        if (this.i == null ? dVar.i != null : !this.i.equals(dVar.i)) {
            return false;
        }
        if (!this.l.equals(dVar.l)) {
            return false;
        }
        if (this.g == null ? dVar.g != null : !this.g.equals(dVar.g)) {
            return false;
        }
        if (this.j == null ? dVar.j != null : !this.j.equals(dVar.j)) {
            return false;
        }
        if (this.m == null ? dVar.m != null : !this.m.equals(dVar.m)) {
            return false;
        }
        if (this.h == null ? dVar.h != null : !this.h.equals(dVar.h)) {
            return false;
        }
        if (this.f != null) {
            if (this.f.equals(dVar.f)) {
                return true;
            }
        } else if (dVar.f == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((this.j != null ? this.j.hashCode() : 0) + (((this.i != null ? this.i.hashCode() : 0) + (((this.h != null ? this.h.hashCode() : 0) + (((this.g != null ? this.g.hashCode() : 0) + ((this.f != null ? this.f.hashCode() : 0) * 31)) * 31)) * 31)) * 31)) * 31) + this.l.hashCode()) * 31) + this.m.hashCode()) * 31;
        if (this.n != null) {
            i = this.n.hashCode();
        }
        return hashCode + i;
    }

    public String k() {
        if ("ID_NOT_AVAILABLE".equals(this.g)) {
            return null;
        }
        if (this.g == null) {
            this.g = j();
        }
        return this.g;
    }

    public void k(String str) {
        this.g = str;
    }

    public String l() {
        return this.j;
    }

    public void l(String str) {
        this.j = str;
    }

    public String m() {
        return this.h;
    }

    public void m(String str) {
        this.h = str;
    }

    public String n() {
        return this.i;
    }

    public void n(String str) {
        this.i = str;
    }

    public String o() {
        return this.k;
    }

    public void o(String str) {
        this.k = str;
    }

    public a p(String str) {
        return b(str, null);
    }

    public h p() {
        return this.n;
    }

    public synchronized Object q(String str) {
        return this.m == null ? null : this.m.get(str);
    }

    public synchronized Collection<a> q() {
        return this.l == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(this.l));
    }

    public synchronized Collection<String> r() {
        return this.m == null ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet(this.m.keySet()));
    }

    protected synchronized java.lang.String s() {
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smack.packet.d.s():java.lang.String");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.xiaomi.smack.packet.d.s():java.lang.String
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:54)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:40)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.ProcessClass.process(ProcessClass.java:22)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
*/
        /*
        this = this;
        r4 = 0;
        monitor-enter(r8);
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0023 }
        r6.<init>();	 Catch:{ all -> 0x0023 }
        r1 = r8.q();	 Catch:{ all -> 0x0023 }
        r2 = r1.iterator();	 Catch:{ all -> 0x0023 }
    L_0x000f:
        r1 = r2.hasNext();	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0026;
    L_0x0015:
        r1 = r2.next();	 Catch:{ all -> 0x0023 }
        r1 = (com.xiaomi.smack.packet.a) r1;	 Catch:{ all -> 0x0023 }
        r1 = r1.d();	 Catch:{ all -> 0x0023 }
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        goto L_0x000f;
    L_0x0023:
        r1 = move-exception;
        monitor-exit(r8);
        throw r1;
    L_0x0026:
        r1 = r8.m;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x015a;
    L_0x002a:
        r1 = r8.m;	 Catch:{ all -> 0x0023 }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x0023 }
        if (r1 != 0) goto L_0x015a;
    L_0x0032:
        r1 = "<properties xmlns=\"http://www.jivesoftware.com/xmlns/xmpp/properties\">";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r8.r();	 Catch:{ all -> 0x0023 }
        r7 = r1.iterator();	 Catch:{ all -> 0x0023 }
    L_0x0040:
        r1 = r7.hasNext();	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0154;
    L_0x0046:
        r1 = r7.next();	 Catch:{ all -> 0x0023 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0023 }
        r2 = r8.q(r1);	 Catch:{ all -> 0x0023 }
        r3 = "<property>";
        r6.append(r3);	 Catch:{ all -> 0x0023 }
        r3 = "<name>";
        r3 = r6.append(r3);	 Catch:{ all -> 0x0023 }
        r1 = com.xiaomi.smack.util.g.a(r1);	 Catch:{ all -> 0x0023 }
        r1 = r3.append(r1);	 Catch:{ all -> 0x0023 }
        r3 = "</name>";
        r1.append(r3);	 Catch:{ all -> 0x0023 }
        r1 = "<value type=\"";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r2 instanceof java.lang.Integer;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x008d;
    L_0x0075:
        r1 = "integer\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
    L_0x0086:
        r1 = "</property>";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        goto L_0x0040;
    L_0x008d:
        r1 = r2 instanceof java.lang.Long;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00a3;
    L_0x0091:
        r1 = "long\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x0086;
    L_0x00a3:
        r1 = r2 instanceof java.lang.Float;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00b9;
    L_0x00a7:
        r1 = "float\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x0086;
    L_0x00b9:
        r1 = r2 instanceof java.lang.Double;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00cf;
    L_0x00bd:
        r1 = "double\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x0086;
    L_0x00cf:
        r1 = r2 instanceof java.lang.Boolean;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00e5;
    L_0x00d3:
        r1 = "boolean\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x0086;
    L_0x00e5:
        r1 = r2 instanceof java.lang.String;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0101;
    L_0x00e9:
        r1 = "string\">";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r0 = r2;
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0023 }
        r1 = r0;
        r1 = com.xiaomi.smack.util.g.a(r1);	 Catch:{ all -> 0x0023 }
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = "</value>";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        goto L_0x0086;
    L_0x0101:
        r5 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0131, all -> 0x0146 }
        r5.<init>();	 Catch:{ Exception -> 0x0131, all -> 0x0146 }
        r3 = new java.io.ObjectOutputStream;	 Catch:{ Exception -> 0x0171, all -> 0x0168 }
        r3.<init>(r5);	 Catch:{ Exception -> 0x0171, all -> 0x0168 }
        r3.writeObject(r2);	 Catch:{ Exception -> 0x0175, all -> 0x016b }
        r1 = "java-object\">";
        r6.append(r1);	 Catch:{ Exception -> 0x0175, all -> 0x016b }
        r1 = r5.toByteArray();	 Catch:{ Exception -> 0x0175, all -> 0x016b }
        r1 = com.xiaomi.smack.util.g.a(r1);	 Catch:{ Exception -> 0x0175, all -> 0x016b }
        r1 = r6.append(r1);	 Catch:{ Exception -> 0x0175, all -> 0x016b }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ Exception -> 0x0175, all -> 0x016b }
        r3.close();	 Catch:{ Exception -> 0x0160 }
    L_0x0129:
        r5.close();	 Catch:{ Exception -> 0x012e }
        goto L_0x0086;
    L_0x012e:
        r1 = move-exception;
        goto L_0x0086;
    L_0x0131:
        r1 = move-exception;
        r2 = r4;
        r3 = r4;
    L_0x0134:
        r1.printStackTrace();	 Catch:{ all -> 0x016d }
        if (r2 == 0) goto L_0x013c;
    L_0x0139:
        r2.close();	 Catch:{ Exception -> 0x0162 }
    L_0x013c:
        if (r3 == 0) goto L_0x0086;
    L_0x013e:
        r3.close();	 Catch:{ Exception -> 0x0143 }
        goto L_0x0086;
    L_0x0143:
        r1 = move-exception;
        goto L_0x0086;
    L_0x0146:
        r1 = move-exception;
        r3 = r4;
        r5 = r4;
    L_0x0149:
        if (r3 == 0) goto L_0x014e;
    L_0x014b:
        r3.close();	 Catch:{ Exception -> 0x0164 }
    L_0x014e:
        if (r5 == 0) goto L_0x0153;
    L_0x0150:
        r5.close();	 Catch:{ Exception -> 0x0166 }
    L_0x0153:
        throw r1;	 Catch:{ all -> 0x0023 }
    L_0x0154:
        r1 = "</properties>";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
    L_0x015a:
        r1 = r6.toString();	 Catch:{ all -> 0x0023 }
        monitor-exit(r8);
        return r1;
    L_0x0160:
        r1 = move-exception;
        goto L_0x0129;
    L_0x0162:
        r1 = move-exception;
        goto L_0x013c;
    L_0x0164:
        r2 = move-exception;
        goto L_0x014e;
    L_0x0166:
        r2 = move-exception;
        goto L_0x0153;
    L_0x0168:
        r1 = move-exception;
        r3 = r4;
        goto L_0x0149;
    L_0x016b:
        r1 = move-exception;
        goto L_0x0149;
    L_0x016d:
        r1 = move-exception;
        r5 = r3;
        r3 = r2;
        goto L_0x0149;
    L_0x0171:
        r1 = move-exception;
        r2 = r4;
        r3 = r5;
        goto L_0x0134;
    L_0x0175:
        r1 = move-exception;
        r2 = r3;
        r3 = r5;
        goto L_0x0134;
        */
    }

    public String t() {
        return this.f;
    }
}
