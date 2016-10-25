package com.xunlei.tdlive;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: StatService.java
final class ep implements Creator<ValueData> {
    ep() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public final ValueData a(Parcel parcel) {
        BaseData baseData = (BaseData) BaseData.CREATOR.createFromParcel(parcel);
        ValueData valueData = new ValueData();
        valueData.a = baseData.a;
        valueData.b = baseData.b;
        valueData.c = parcel.readInt();
        return valueData;
    }

    public final ValueData[] a(int i) {
        return new ValueData[i];
    }
}
