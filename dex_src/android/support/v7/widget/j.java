package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.j.c;
import android.text.TextUtils;
import android.util.Xml;
import com.sina.weibo.sdk.component.GameManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlSerializer;

// compiled from: ActivityChooserModel.java
class j extends DataSetObservable {
    private static final String c;
    private static final Object d;
    private static final Map<String, j> e;
    final Object a;
    final List<a> b;
    private final List<c> f;
    private final Context g;
    private final String h;
    private Intent i;
    private b j;
    private int k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private d p;

    // compiled from: ActivityChooserModel.java
    public final class a implements Comparable<android.support.v7.widget.j.a> {
        public final ResolveInfo a;
        public float b;

        public final /* synthetic */ int compareTo(Object obj) {
            return Float.floatToIntBits(((android.support.v7.widget.j.a) obj).b) - Float.floatToIntBits(this.b);
        }

        public a(ResolveInfo resolveInfo) {
            this.a = resolveInfo;
        }

        public final int hashCode() {
            return Float.floatToIntBits(this.b) + 31;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            return Float.floatToIntBits(this.b) == Float.floatToIntBits(((android.support.v7.widget.j.a) obj).b);
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append("resolveInfo:").append(this.a.toString());
            stringBuilder.append("; weight:").append(new BigDecimal((double) this.b));
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    // compiled from: ActivityChooserModel.java
    public static interface b {
    }

    // compiled from: ActivityChooserModel.java
    public static final class c {
        public final ComponentName a;
        public final long b;
        public final float c;

        public c(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public c(ComponentName componentName, long j, float f) {
            this.a = componentName;
            this.b = j;
            this.c = f;
        }

        public final int hashCode() {
            return (((((this.a == null ? 0 : this.a.hashCode()) + 31) * 31) + ((int) (this.b ^ (this.b >>> 32)))) * 31) + Float.floatToIntBits(this.c);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            android.support.v7.widget.j.c cVar = (android.support.v7.widget.j.c) obj;
            if (this.a == null) {
                if (cVar.a != null) {
                    return false;
                }
            } else if (!this.a.equals(cVar.a)) {
                return false;
            }
            if (this.b != cVar.b) {
                return false;
            }
            return Float.floatToIntBits(this.c) == Float.floatToIntBits(cVar.c);
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append("; activity:").append(this.a);
            stringBuilder.append("; time:").append(this.b);
            stringBuilder.append("; weight:").append(new BigDecimal((double) this.c));
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    // compiled from: ActivityChooserModel.java
    public static interface d {
        boolean a();
    }

    // compiled from: ActivityChooserModel.java
    private final class e extends AsyncTask<Object, Void, Void> {
        private e() {
        }

        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a(objArr);
        }

        private Void a(Object... objArr) {
            int i = 0;
            List list = (List) objArr[0];
            try {
                OutputStream openFileOutput = j.this.g.openFileOutput((String) objArr[1], 0);
                XmlSerializer newSerializer = Xml.newSerializer();
                try {
                    newSerializer.setOutput(openFileOutput, null);
                    newSerializer.startDocument(GameManager.DEFAULT_CHARSET, Boolean.valueOf(true));
                    newSerializer.startTag(null, "historical-records");
                    int size = list.size();
                    while (i < size) {
                        c cVar = (c) list.remove(0);
                        newSerializer.startTag(null, "historical-record");
                        newSerializer.attribute(null, "activity", j.this.flattenToString());
                        newSerializer.attribute(null, "time", String.valueOf(cVar.b));
                        newSerializer.attribute(null, "weight", String.valueOf(cVar.c));
                        newSerializer.endTag(null, "historical-record");
                        i++;
                    }
                    newSerializer.endTag(null, "historical-records");
                    newSerializer.endDocument();
                    j.this.l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (IllegalArgumentException e2) {
                    c;
                    new StringBuilder("Error writing historical recrod file: ").append(j.this.h);
                    j.this.l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (IllegalStateException e4) {
                    c;
                    new StringBuilder("Error writing historical recrod file: ").append(j.this.h);
                    j.this.l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e5) {
                        }
                    }
                } catch (IOException e6) {
                    c;
                    new StringBuilder("Error writing historical recrod file: ").append(j.this.h);
                    j.this.l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e7) {
                        }
                    }
                }
            } catch (FileNotFoundException e8) {
                c;
            }
            return null;
        }
    }

    static {
        c = j.class.getSimpleName();
        d = new Object();
        e = new HashMap();
    }

    public final int a() {
        int size;
        synchronized (this.a) {
            d();
            size = this.b.size();
        }
        return size;
    }

    public final ResolveInfo a(int i) {
        ResolveInfo resolveInfo;
        synchronized (this.a) {
            d();
            resolveInfo = ((a) this.b.get(i)).a;
        }
        return resolveInfo;
    }

    public final int a(ResolveInfo resolveInfo) {
        synchronized (this.a) {
            d();
            List list = this.b;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (((a) list.get(i)).a == resolveInfo) {
                    return i;
                }
            }
            return -1;
        }
    }

    public final Intent b(int i) {
        synchronized (this.a) {
            if (this.i == null) {
                return null;
            }
            d();
            a aVar = (a) this.b.get(i);
            ComponentName componentName = new ComponentName(aVar.a.activityInfo.packageName, aVar.a.activityInfo.name);
            Intent intent = new Intent(this.i);
            intent.setComponent(componentName);
            if (this.p != null) {
                Intent intent2 = new Intent(intent);
                if (this.p.a()) {
                    return null;
                }
            }
            a(new c(componentName, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }

    public final ResolveInfo b() {
        synchronized (this.a) {
            d();
            if (this.b.isEmpty()) {
                return null;
            }
            ResolveInfo resolveInfo = ((a) this.b.get(0)).a;
            return resolveInfo;
        }
    }

    public final int c() {
        int size;
        synchronized (this.a) {
            d();
            size = this.f.size();
        }
        return size;
    }

    final void d() {
        int i;
        int i2 = 1;
        if (!this.o || this.i == null) {
            i = 0;
        } else {
            this.o = false;
            this.b.clear();
            List queryIntentActivities = this.g.getPackageManager().queryIntentActivities(this.i, 0);
            int size = queryIntentActivities.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.b.add(new a((ResolveInfo) queryIntentActivities.get(i3)));
            }
            i = 1;
        }
        if (this.l && this.n && !TextUtils.isEmpty(this.h)) {
            this.l = false;
            this.m = true;
            h();
        } else {
            i2 = 0;
        }
        i |= i2;
        g();
        if (i != 0) {
            f();
            notifyChanged();
        }
    }

    private boolean f() {
        if (this.j == null || this.i == null || this.b.isEmpty() || this.f.isEmpty()) {
            return false;
        }
        Collections.unmodifiableList(this.f);
        return true;
    }

    final boolean a(c cVar) {
        boolean add = this.f.add(cVar);
        if (add) {
            this.n = true;
            g();
            if (this.m) {
                if (this.n) {
                    this.n = false;
                    if (!TextUtils.isEmpty(this.h)) {
                        AsyncTaskCompat.executeParallel(new e(), new ArrayList(this.f), this.h);
                    }
                }
                f();
                notifyChanged();
            } else {
                throw new IllegalStateException("No preceding call to #readHistoricalData");
            }
        }
        return add;
    }

    private void g() {
        int size = this.f.size() - this.k;
        if (size > 0) {
            this.n = true;
            for (int i = 0; i < size; i++) {
                this.f.remove(0);
            }
        }
    }

    private void h() {
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.j.h():void");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in android.support.v7.widget.j.h():void
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
        r8 = 1;
        r0 = r9.g;	 Catch:{ FileNotFoundException -> 0x00c1 }
        r1 = r9.h;	 Catch:{ FileNotFoundException -> 0x00c1 }
        r1 = r0.openFileInput(r1);	 Catch:{ FileNotFoundException -> 0x00c1 }
        r2 = android.util.Xml.newPullParser();	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r0 = "UTF-8";
        r2.setInput(r1, r0);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r0 = 0;
    L_0x0014:
        if (r0 == r8) goto L_0x001e;
    L_0x0016:
        r3 = 2;
        if (r0 == r3) goto L_0x001e;
    L_0x0019:
        r0 = r2.next();	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        goto L_0x0014;
    L_0x001e:
        r0 = "historical-records";
        r3 = r2.getName();	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r0 = r0.equals(r3);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        if (r0 != 0) goto L_0x0048;
    L_0x002b:
        r0 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r2 = "Share records file does not start with historical-records tag.";
        r0.<init>(r2);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        throw r0;	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
    L_0x0034:
        r0 = move-exception;
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ae }
        r2 = "Error reading historical recrod file: ";
        r0.<init>(r2);	 Catch:{ all -> 0x00ae }
        r2 = r9.h;	 Catch:{ all -> 0x00ae }
        r0.append(r2);	 Catch:{ all -> 0x00ae }
        if (r1 == 0) goto L_0x0047;
    L_0x0044:
        r1.close();	 Catch:{ IOException -> 0x00bd }
    L_0x0047:
        return;
    L_0x0048:
        r0 = r9.f;	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r0.clear();	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
    L_0x004d:
        r3 = r2.next();	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        if (r3 == r8) goto L_0x00b5;
    L_0x0053:
        r4 = 3;
        if (r3 == r4) goto L_0x004d;
    L_0x0056:
        r4 = 4;
        if (r3 == r4) goto L_0x004d;
    L_0x0059:
        r3 = r2.getName();	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r4 = "historical-record";
        r3 = r4.equals(r3);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        if (r3 != 0) goto L_0x0085;
    L_0x0066:
        r0 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r2 = "Share records file not well-formed.";
        r0.<init>(r2);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        throw r0;	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
    L_0x006f:
        r0 = move-exception;
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ae }
        r2 = "Error reading historical recrod file: ";
        r0.<init>(r2);	 Catch:{ all -> 0x00ae }
        r2 = r9.h;	 Catch:{ all -> 0x00ae }
        r0.append(r2);	 Catch:{ all -> 0x00ae }
        if (r1 == 0) goto L_0x0047;
    L_0x007f:
        r1.close();	 Catch:{ IOException -> 0x0083 }
        goto L_0x0047;
    L_0x0083:
        r0 = move-exception;
        goto L_0x0047;
    L_0x0085:
        r3 = 0;
        r4 = "activity";
        r3 = r2.getAttributeValue(r3, r4);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r4 = 0;
        r5 = "time";
        r4 = r2.getAttributeValue(r4, r5);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r4 = java.lang.Long.parseLong(r4);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r6 = 0;
        r7 = "weight";
        r6 = r2.getAttributeValue(r6, r7);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r6 = java.lang.Float.parseFloat(r6);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r7 = new android.support.v7.widget.j$c;	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r7.<init>(r3, r4, r6);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        r0.add(r7);	 Catch:{ XmlPullParserException -> 0x0034, IOException -> 0x006f }
        goto L_0x004d;
    L_0x00ae:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00b4;
    L_0x00b1:
        r1.close();	 Catch:{ IOException -> 0x00bf }
    L_0x00b4:
        throw r0;
    L_0x00b5:
        if (r1 == 0) goto L_0x0047;
    L_0x00b7:
        r1.close();	 Catch:{ IOException -> 0x00bb }
        goto L_0x0047;
    L_0x00bb:
        r0 = move-exception;
        goto L_0x0047;
    L_0x00bd:
        r0 = move-exception;
        goto L_0x0047;
    L_0x00bf:
        r1 = move-exception;
        goto L_0x00b4;
    L_0x00c1:
        r0 = move-exception;
        goto L_0x0047;
        */
    }
}
