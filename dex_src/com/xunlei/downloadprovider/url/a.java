package com.xunlei.downloadprovider.url;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.ArrayList;

// compiled from: DownData.java
final class a implements Creator<DownData> {
    a() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new DownData[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        int i = 0;
        DownData downData = new DownData();
        downData.a = parcel.readString();
        downData.b = parcel.readString();
        downData.c = parcel.readString();
        downData.d = parcel.readString();
        downData.e = parcel.readString();
        downData.f = parcel.readInt();
        downData.g = parcel.readInt();
        downData.h = parcel.readString();
        downData.i = parcel.readString();
        downData.j = parcel.readInt();
        downData.k = parcel.readInt() != 0;
        downData.l = parcel.readString();
        if (parcel.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        downData.m = z;
        if (parcel.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        downData.n = z;
        if (parcel.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        downData.o = z;
        downData.p = parcel.readInt();
        downData.r = parcel.readLong();
        downData.s = parcel.readString();
        downData.q = parcel.readString();
        if (parcel.readInt() == 0) {
            z2 = false;
        }
        downData.t = z2;
        int readInt = parcel.readInt();
        if (downData.t && readInt > 0) {
            downData.u = new ArrayList();
            while (i < readInt) {
                CharSequence readString = parcel.readString();
                if (!TextUtils.isEmpty(readString)) {
                    downData.u.add(readString);
                }
                i++;
            }
        }
        return downData;
    }
}
