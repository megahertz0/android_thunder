package com.xunlei.downloadprovider.ad.recommend.a;

import android.text.TextUtils;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: RecommendAdModel.java
public class i {
    private static String c;
    private static i d;
    final Map<Integer, List<com.xunlei.downloadprovider.ad.common.a>> a;
    final Map<Integer, List<String>> b;

    // compiled from: RecommendAdModel.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[AD_TYPE.values().length];
            try {
                a[AD_TYPE.SOURCE_GDT_FLAG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[AD_TYPE.SOURCE_BAIDU_FLAG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[AD_TYPE.SOURCE_SSP_FLAG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    // compiled from: RecommendAdModel.java
    public static interface a {
        void a(String str, int i, String str2);

        void a(List<com.xunlei.downloadprovider.ad.common.a> list, int i, String str);
    }

    static {
        c = i.class.getSimpleName();
        d = null;
    }

    private i() {
        this.a = new HashMap();
        this.b = new HashMap();
        this.b.put(Integer.valueOf(0), new ArrayList(3));
        this.b.put(Integer.valueOf(1), new ArrayList(3));
        this.b.put(Integer.valueOf(SimpleLog.LOG_LEVEL_DEBUG), new ArrayList(3));
        this.a.put(Integer.valueOf(0), new ArrayList(3));
        this.a.put(Integer.valueOf(1), new ArrayList(3));
        this.a.put(Integer.valueOf(SimpleLog.LOG_LEVEL_DEBUG), new ArrayList(3));
    }

    public static i a() {
        if (d == null) {
            d = new i();
        }
        return d;
    }

    public static void b() {
        d = null;
    }

    static /* synthetic */ com.xunlei.downloadprovider.ad.common.a a(i iVar, int i, List list) {
        List<com.xunlei.downloadprovider.ad.common.a> list2 = (List) iVar.a.get(Integer.valueOf(i));
        for (com.xunlei.downloadprovider.ad.common.a aVar : list) {
            for (com.xunlei.downloadprovider.ad.common.a aVar2 : list2) {
                Object obj;
                int i2;
                if (aVar == aVar2) {
                    i2 = 1;
                } else if (aVar == null || aVar2 == null) {
                    obj = null;
                } else if (aVar.o() == aVar2.o() && aVar.a().equals(aVar2.a())) {
                    i2 = 1;
                } else {
                    obj = null;
                }
                if (obj == null) {
                }
            }
            return aVar;
        }
        return null;
    }

    static /* synthetic */ boolean a(i iVar, int i, com.xunlei.downloadprovider.ad.common.a aVar) {
        String r = aVar.r();
        if (TextUtils.isEmpty(r)) {
            new StringBuilder("positionId is invalid: ").append(r).append(", plz set valid positionId first");
            return false;
        }
        RecommendSSPAdMapping fromPositionId = RecommendSSPAdMapping.fromPositionId(r);
        if (fromPositionId == null) {
            new StringBuilder("positionId is invalid: ").append(r).append(", can not find correct local position");
            return false;
        }
        ((List) iVar.a.get(Integer.valueOf(i))).set(fromPositionId.position, aVar);
        new StringBuilder("isSaveSuccess pageIndex: ").append(i).append(" position: ").append(fromPositionId.position);
        return true;
    }

    static /* synthetic */ void a(i iVar, int i, com.xunlei.downloadprovider.ad.common.c.a aVar) {
        if (((List) iVar.b.get(Integer.valueOf(i))).isEmpty()) {
            List list = (List) iVar.a.get(Integer.valueOf(i));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((com.xunlei.downloadprovider.ad.common.a) it.next()) == null) {
                    it.remove();
                }
            }
            List arrayList = new ArrayList(list.size());
            arrayList.addAll(list);
            if (arrayList.isEmpty()) {
                if (aVar != null) {
                    aVar.a(com.xunlei.downloadprovider.ad.recommend.a.b.a.a.e, com.xunlei.downloadprovider.ad.recommend.a.b.a.a.f);
                }
            } else if (aVar != null) {
                aVar.a(arrayList);
            }
        }
    }
}
