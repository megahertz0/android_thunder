package com.xunlei.downloadprovider.aidl;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: ExternTaskInfo.java
final class a implements Creator<ExternTaskInfo> {
    a() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ExternTaskInfo[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        ExternTaskInfo externTaskInfo = new ExternTaskInfo();
        externTaskInfo.a = parcel.readInt();
        externTaskInfo.b = parcel.readString();
        externTaskInfo.c = parcel.readString();
        externTaskInfo.d = parcel.readLong();
        externTaskInfo.e = parcel.readString();
        return externTaskInfo;
    }
}
