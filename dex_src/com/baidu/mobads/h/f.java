package com.baidu.mobads.h;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class f implements Creator<e> {
    f() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public final e a(Parcel parcel) {
        return new e(parcel, null);
    }

    public final e[] a(int i) {
        return new e[i];
    }
}
