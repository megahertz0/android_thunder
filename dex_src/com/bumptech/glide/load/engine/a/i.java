package com.bumptech.glide.load.engine.a;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.h.h;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import org.android.spdy.SpdyAgent;

@TargetApi(19)
// compiled from: SizeConfigStrategy.java
public final class i implements g {
    private static final Config[] a;
    private static final Config[] b;
    private static final Config[] c;
    private static final Config[] d;
    private final b e;
    private final e<a, Bitmap> f;
    private final Map<Config, NavigableMap<Integer, Integer>> g;

    // compiled from: SizeConfigStrategy.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Config.values().length];
            try {
                a[Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    // compiled from: SizeConfigStrategy.java
    static final class a implements h {
        int a;
        Config b;
        private final b c;

        public a(b bVar) {
            this.c = bVar;
        }

        public final void a() {
            this.c.a(this);
        }

        public final String toString() {
            return i.b(this.a, this.b);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.a != aVar.a) {
                return false;
            }
            if (this.b == null) {
                if (aVar.b != null) {
                    return false;
                }
            } else if (!this.b.equals(aVar.b)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return (this.b != null ? this.b.hashCode() : 0) + (this.a * 31);
        }
    }

    // compiled from: SizeConfigStrategy.java
    static class b extends b<a> {
        b() {
        }

        public final a a(int i, Config config) {
            a aVar = (a) b();
            aVar.a = i;
            aVar.b = config;
            return aVar;
        }

        protected final /* synthetic */ h a() {
            return new a(this);
        }
    }

    public i() {
        this.e = new b();
        this.f = new e();
        this.g = new HashMap();
    }

    static {
        a = new Config[]{Config.ARGB_8888, null};
        b = new Config[]{Config.RGB_565};
        c = new Config[]{Config.ARGB_4444};
        d = new Config[]{Config.ALPHA_8};
    }

    public final void a(Bitmap bitmap) {
        a a = this.e.a(h.a(bitmap), bitmap.getConfig());
        this.f.a(a, bitmap);
        NavigableMap a2 = a(bitmap.getConfig());
        Integer num = (Integer) a2.get(Integer.valueOf(a.a));
        a2.put(Integer.valueOf(a.a), Integer.valueOf(num == null ? 1 : num.intValue() + 1));
    }

    public final Bitmap a(int i, int i2, Config config) {
        Config[] configArr;
        h a;
        Bitmap bitmap;
        int i3 = 0;
        int a2 = h.a(i, i2, config);
        h a3 = this.e.a(a2, config);
        switch (AnonymousClass_1.a[config.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                configArr = a;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                configArr = b;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                configArr = c;
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                configArr = d;
                break;
            default:
                configArr = new Config[]{config};
                break;
        }
        int length = configArr.length;
        while (i3 < length) {
            Config config2 = configArr[i3];
            Integer num = (Integer) a(config2).ceilingKey(Integer.valueOf(a2));
            if (num != null && num.intValue() <= a2 * 8) {
                if (num.intValue() == a2) {
                    if (config2 != null) {
                    }
                }
                this.e.a(a3);
                a = this.e.a(num.intValue(), config2);
                bitmap = (Bitmap) this.f.a(a);
                if (bitmap != null) {
                    a(Integer.valueOf(h.a(bitmap)), bitmap.getConfig());
                    bitmap.reconfigure(i, i2, bitmap.getConfig() == null ? bitmap.getConfig() : Config.ARGB_8888);
                }
                return bitmap;
            }
            i3++;
        }
        a = a3;
        bitmap = (Bitmap) this.f.a(a);
        if (bitmap != null) {
            a(Integer.valueOf(h.a(bitmap)), bitmap.getConfig());
            if (bitmap.getConfig() == null) {
            }
            bitmap.reconfigure(i, i2, bitmap.getConfig() == null ? bitmap.getConfig() : Config.ARGB_8888);
        }
        return bitmap;
    }

    public final Bitmap a() {
        Bitmap bitmap = (Bitmap) this.f.a();
        if (bitmap != null) {
            a(Integer.valueOf(h.a(bitmap)), bitmap.getConfig());
        }
        return bitmap;
    }

    private void a(Integer num, Config config) {
        NavigableMap a = a(config);
        Integer num2 = (Integer) a.get(num);
        if (num2.intValue() == 1) {
            a.remove(num);
        } else {
            a.put(num, Integer.valueOf(num2.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> a(Config config) {
        NavigableMap<Integer, Integer> navigableMap = (NavigableMap) this.g.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        NavigableMap treeMap = new TreeMap();
        this.g.put(config, treeMap);
        return treeMap;
    }

    public final String b(Bitmap bitmap) {
        return b(h.a(bitmap), bitmap.getConfig());
    }

    public final String b(int i, int i2, Config config) {
        return b(h.a(i, i2, config), config);
    }

    public final int c(Bitmap bitmap) {
        return h.a(bitmap);
    }

    public final String toString() {
        StringBuilder append = new StringBuilder("SizeConfigStrategy{groupedMap=").append(this.f).append(", sortedSizes=(");
        for (Entry entry : this.g.entrySet()) {
            append.append(entry.getKey()).append('[').append(entry.getValue()).append("], ");
        }
        if (!this.g.isEmpty()) {
            append.replace(append.length() - 2, append.length(), com.umeng.a.d);
        }
        return append.append(")}").toString();
    }

    private static String b(int i, Config config) {
        return new StringBuilder("[").append(i).append("](").append(config).append(SocializeConstants.OP_CLOSE_PAREN).toString();
    }
}
