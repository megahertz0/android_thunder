package com.baidu.mobads.vo;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a implements Creator<XAdInstanceInfo> {
    a() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public final XAdInstanceInfo a(Parcel parcel) {
        return new XAdInstanceInfo(parcel, null);
    }

    public final XAdInstanceInfo[] a(int i) {
        return new XAdInstanceInfo[i];
    }
}
