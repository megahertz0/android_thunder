package com.baidu.mobads.command;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class c implements Creator<XAdLandingPageExtraInfo> {
    c() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public final XAdLandingPageExtraInfo a(Parcel parcel) {
        return new XAdLandingPageExtraInfo(null);
    }

    public final XAdLandingPageExtraInfo[] a(int i) {
        return new XAdLandingPageExtraInfo[i];
    }
}
