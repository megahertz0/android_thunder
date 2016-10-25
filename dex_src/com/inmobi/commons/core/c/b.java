package com.inmobi.commons.core.c;

import java.util.HashMap;
import java.util.Map;

// compiled from: TelemetryComponentConfig.java
public class b {
    private int a;
    private String b;
    private boolean c;
    private boolean d;
    private Map<String, a> e;

    // compiled from: TelemetryComponentConfig.java
    static final class a {
        private String a;
        private int b;
        private boolean c;

        public a(String str, int i, boolean z) {
            a(str);
            a(i);
            a(z);
        }

        public final String a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final void a(String str) {
            this.a = str;
        }

        public final void a(int i) {
            this.b = i;
        }

        public final void a(boolean z) {
            this.c = z;
        }

        public final boolean c() {
            return this.c;
        }
    }

    public b() {
        this.a = 0;
        this.b = "telemetry";
        this.c = false;
        this.d = false;
        this.e = new HashMap();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public b(java.lang.String r6, org.json.JSONObject r7, com.inmobi.commons.core.c.b r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.c.b.<init>(java.lang.String, org.json.JSONObject, com.inmobi.commons.core.c.b):void");
        /*
        this = this;
        r0 = 0;
        r5.<init>();
        r5.a = r0;
        r1 = "telemetry";
        r5.b = r1;
        r5.c = r0;
        r5.d = r0;
        r1 = new java.util.HashMap;
        r1.<init>();
        r5.e = r1;
        if (r7 != 0) goto L_0x001c;
    L_0x0018:
        r5.a(r6, r8);
    L_0x001b:
        return;
    L_0x001c:
        if (r6 == 0) goto L_0x0028;
    L_0x001e:
        r1 = r6.trim();	 Catch:{ JSONException -> 0x00e5 }
        r1 = r1.length();	 Catch:{ JSONException -> 0x00e5 }
        if (r1 != 0) goto L_0x00ca;
    L_0x0028:
        r1 = r8.a();	 Catch:{ JSONException -> 0x00e5 }
    L_0x002c:
        r5.b = r1;	 Catch:{ JSONException -> 0x00e5 }
        r1 = "enabled";
        r1 = r7.has(r1);	 Catch:{ JSONException -> 0x00e5 }
        if (r1 == 0) goto L_0x00cd;
    L_0x0037:
        r1 = "enabled";
        r1 = r7.getBoolean(r1);	 Catch:{ JSONException -> 0x00e5 }
    L_0x003e:
        r5.c = r1;	 Catch:{ JSONException -> 0x00e5 }
        r1 = "samplingFactor";
        r1 = r7.has(r1);	 Catch:{ JSONException -> 0x00e5 }
        if (r1 == 0) goto L_0x00d0;
    L_0x0049:
        r1 = "samplingFactor";
        r1 = r7.getInt(r1);	 Catch:{ JSONException -> 0x00e5 }
    L_0x0050:
        r5.a(r1);	 Catch:{ JSONException -> 0x00e5 }
        r1 = "metricEnabled";
        r1 = r7.has(r1);	 Catch:{ JSONException -> 0x00e5 }
        if (r1 == 0) goto L_0x00d6;
    L_0x005c:
        r1 = "metricEnabled";
        r1 = r7.getBoolean(r1);	 Catch:{ JSONException -> 0x00e5 }
    L_0x0063:
        r5.a(r1);	 Catch:{ JSONException -> 0x00e5 }
        r1 = new java.util.HashMap;	 Catch:{ JSONException -> 0x00e5 }
        r1.<init>();	 Catch:{ JSONException -> 0x00e5 }
        r5.e = r1;	 Catch:{ JSONException -> 0x00e5 }
        r1 = "events";
        r1 = r7.has(r1);	 Catch:{ JSONException -> 0x00e5 }
        if (r1 == 0) goto L_0x001b;
    L_0x0076:
        r1 = "events";
        r2 = r7.getJSONArray(r1);	 Catch:{ JSONException -> 0x00e5 }
        r1 = r0;
    L_0x007e:
        r0 = r2.length();	 Catch:{ JSONException -> 0x00e5 }
        if (r1 >= r0) goto L_0x001b;
    L_0x0084:
        r3 = new com.inmobi.commons.core.c.b$a;	 Catch:{ JSONException -> 0x00e5 }
        r3.<init>();	 Catch:{ JSONException -> 0x00e5 }
        r4 = r2.getJSONObject(r1);	 Catch:{ JSONException -> 0x00e5 }
        r0 = "type";
        r0 = r4.getString(r0);	 Catch:{ JSONException -> 0x00e5 }
        r3.a(r0);	 Catch:{ JSONException -> 0x00e5 }
        r0 = "samplingFactor";
        r0 = r4.has(r0);	 Catch:{ JSONException -> 0x00e5 }
        if (r0 == 0) goto L_0x00db;
    L_0x00a0:
        r0 = "samplingFactor";
        r0 = r4.getInt(r0);	 Catch:{ JSONException -> 0x00e5 }
    L_0x00a7:
        r3.a(r0);	 Catch:{ JSONException -> 0x00e5 }
        r0 = "metricEnabled";
        r0 = r4.has(r0);	 Catch:{ JSONException -> 0x00e5 }
        if (r0 == 0) goto L_0x00e0;
    L_0x00b3:
        r0 = "metricEnabled";
        r0 = r4.getBoolean(r0);	 Catch:{ JSONException -> 0x00e5 }
    L_0x00ba:
        r3.a(r0);	 Catch:{ JSONException -> 0x00e5 }
        r0 = r5.e;	 Catch:{ JSONException -> 0x00e5 }
        r4 = r3.a();	 Catch:{ JSONException -> 0x00e5 }
        r0.put(r4, r3);	 Catch:{ JSONException -> 0x00e5 }
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x007e;
    L_0x00ca:
        r1 = r6;
        goto L_0x002c;
    L_0x00cd:
        r1 = 1;
        goto L_0x003e;
    L_0x00d0:
        r1 = r8.c();	 Catch:{ JSONException -> 0x00e5 }
        goto L_0x0050;
    L_0x00d6:
        r1 = r8.d();	 Catch:{ JSONException -> 0x00e5 }
        goto L_0x0063;
    L_0x00db:
        r0 = r5.c();	 Catch:{ JSONException -> 0x00e5 }
        goto L_0x00a7;
    L_0x00e0:
        r0 = r5.d();	 Catch:{ JSONException -> 0x00e5 }
        goto L_0x00ba;
    L_0x00e5:
        r0 = move-exception;
        r5.a(r6, r8);
        goto L_0x001b;
        */
    }

    public String a() {
        return this.b;
    }

    public boolean b() {
        return this.c;
    }

    public a a(String str) {
        a aVar = (a) this.e.get(str);
        return aVar != null ? aVar : new a(str, c(), d());
    }

    private void a(String str, b bVar) {
        b(true);
        b(str);
    }

    public int c() {
        return this.a;
    }

    public boolean d() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public void a(int i) {
        this.a = i;
    }

    public void b(String str) {
        this.b = str;
    }
}
