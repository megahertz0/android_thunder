package com.xunlei.tdlive.control;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: FragmentTabHost.java
final class i implements Creator<FragmentTabHost$SavedState> {
    i() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public final FragmentTabHost$SavedState a(Parcel parcel) {
        return new FragmentTabHost$SavedState(null);
    }

    public final FragmentTabHost$SavedState[] a(int i) {
        return new FragmentTabHost$SavedState[i];
    }
}
