package com.xunlei.tdlive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.HashMap;

// compiled from: StatService.java
final class en implements Creator<BaseData> {
    en() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public final BaseData a(Parcel parcel) {
        BaseData baseData = new BaseData();
        baseData.a = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt > 0) {
            baseData.b = new HashMap();
            for (int i = 0; i < readInt; i++) {
                baseData.b.put(parcel.readString(), parcel.readString());
            }
        }
        return baseData;
    }

    public final BaseData[] a(int i) {
        return new BaseData[i];
    }
}
