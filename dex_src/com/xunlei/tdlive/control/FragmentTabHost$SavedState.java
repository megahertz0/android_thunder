package com.xunlei.tdlive.control;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

class FragmentTabHost$SavedState extends BaseSavedState {
    public static final Creator<FragmentTabHost$SavedState> CREATOR;
    String a;

    FragmentTabHost$SavedState(Parcelable parcelable) {
        super(parcelable);
    }

    private FragmentTabHost$SavedState(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
    }

    public String toString() {
        return new StringBuilder("FragmentTabHost.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" curTab=").append(this.a).append("}").toString();
    }

    static {
        CREATOR = new i();
    }
}
