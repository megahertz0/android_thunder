package com.xunlei.downloadprovider.ad.common;

import anet.channel.util.ErrorConstant;
import com.android.volley.l;
import com.android.volley.w;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.tdlive.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

// compiled from: CommonUtil.java
public final class b {
    public static int a(w wVar) {
        if (wVar == null) {
            return ErrorConstant.ERROR_TNET_EXCEPTION;
        }
        l lVar = wVar.a;
        return lVar != null ? lVar.a : ErrorConstant.ERROR_TNET_EXCEPTION;
    }

    public static AD_TYPE a(Map<AD_TYPE, Integer> map) {
        int i = 0;
        int i2 = 0;
        for (AD_TYPE ad_type : map.keySet()) {
            i2 = ((Integer) map.get(ad_type)).intValue() + i2;
        }
        if (i2 != 100) {
            return AD_TYPE.SOURCE_GDT_FLAG;
        }
        int size = map.keySet().size();
        AD_TYPE[] ad_typeArr = new AD_TYPE[size];
        int[] iArr = new int[size];
        List arrayList = new ArrayList();
        arrayList.addAll(map.keySet());
        for (i2 = 0; i2 < size; i2++) {
            ad_typeArr[i2] = (AD_TYPE) arrayList.get(i2);
            if (i2 > 0) {
                iArr[i2] = ((Integer) map.get(ad_typeArr[i2])).intValue() + iArr[i2 - 1];
            } else {
                iArr[i2] = ((Integer) map.get(ad_typeArr[i2])).intValue();
            }
        }
        int nextInt = new Random().nextInt(R.styleable.AppCompatTheme_buttonStyle) + 1;
        while (i < size) {
            if (i == 0 && nextInt <= iArr[i]) {
                return ad_typeArr[i];
            }
            if (i > 0 && iArr[i - 1] < nextInt && nextInt <= iArr[i]) {
                return ad_typeArr[i];
            }
            i++;
        }
        return AD_TYPE.SOURCE_GDT_FLAG;
    }

    public static <T> T a(Map<T, Integer> map, T t) {
        int i = 0;
        int i2 = 0;
        for (Object obj : map.keySet()) {
            i2 = ((Integer) map.get(obj)).intValue() + i2;
        }
        if (i2 != 100) {
            return t;
        }
        int size = map.keySet().size();
        Object[] objArr = new Object[map.keySet().size()];
        int[] iArr = new int[size];
        List arrayList = new ArrayList();
        arrayList.addAll(map.keySet());
        for (int i3 = 0; i3 < size; i3++) {
            objArr[i3] = arrayList.get(i3);
            if (i3 > 0) {
                iArr[i3] = ((Integer) map.get(objArr[i3])).intValue() + iArr[i3 - 1];
            } else {
                iArr[i3] = ((Integer) map.get(objArr[i3])).intValue();
            }
        }
        i2 = new Random().nextInt(R.styleable.AppCompatTheme_buttonStyle) + 1;
        while (i < size) {
            if (i == 0 && i2 <= iArr[i]) {
                return objArr[i];
            }
            if (i > 0 && iArr[i - 1] < i2 && i2 <= iArr[i]) {
                return objArr[i];
            }
            i++;
        }
        return t;
    }

    public static String a(a aVar) {
        return (aVar.o() == AD_TYPE.SOURCE_SSP_FLAG || aVar.o() == AD_TYPE.SOURCE_SSP_DEFAULT_FLAG) ? aVar.s() : aVar.o().getSourceName();
    }
}
