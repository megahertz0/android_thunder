package com.xunlei.downloadprovider.homepage.relax.e;

import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.RelaxDataType;
import com.xunlei.downloadprovider.model.protocol.b.d;
import java.util.ArrayList;
import java.util.List;

// compiled from: RelaxUtil.java
public final class a {
    public static List<d> a(List<d> list, RelaxDataType relaxDataType, GuestureType guestureType, int i) {
        int i2 = 0;
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<d> arrayList = new ArrayList();
        if (relaxDataType.equals(RelaxDataType.FAVOR) || guestureType.equals(GuestureType.BOTTOM)) {
            while (i2 < i && i2 < list.size()) {
                arrayList.add((d) list.get(i2));
                i2++;
            }
            return arrayList;
        } else if (!guestureType.equals(GuestureType.TOP)) {
            return arrayList;
        } else {
            int i3 = 0;
            i2 = list.size() - 1;
            while (i3 < i && i2 >= 0) {
                arrayList.add((d) list.get(i2));
                i3++;
                i2--;
            }
            return a((List) arrayList);
        }
    }

    public static List<d> a(List<d> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<d> arrayList = new ArrayList();
        for (d dVar : list) {
            Object obj;
            int i = 0;
            while (i < arrayList.size()) {
                if (((d) arrayList.get(i)).a < dVar.a) {
                    obj = 1;
                    break;
                }
                i++;
            }
            obj = null;
            if (obj == null) {
                i = arrayList.size();
            }
            arrayList.add(i, dVar);
        }
        return arrayList;
    }

    public static int a(int i) {
        return i == 0 ? 1 : -1;
    }
}
