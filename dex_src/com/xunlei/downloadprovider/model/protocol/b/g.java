package com.xunlei.downloadprovider.model.protocol.b;

import com.xunlei.xiazaibao.BuildConfig;
import java.util.List;

// compiled from: RelaxLogUtil.java
public final class g {
    public static final String a(List<d> list) {
        StringBuilder stringBuilder = new StringBuilder(BuildConfig.VERSION_NAME);
        if (!(list == null || list.isEmpty())) {
            for (d dVar : list) {
                stringBuilder.append(new StringBuilder(" ").append(dVar.a).toString());
            }
        }
        return stringBuilder.toString();
    }
}
