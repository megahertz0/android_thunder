package com.xunlei.downloadprovider.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ExternTaskInfo implements Parcelable {
    public static final Creator<ExternTaskInfo> CREATOR;
    public int a;
    public String b;
    public String c;
    public long d;
    public String e;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeLong(this.d);
        parcel.writeString(this.e);
    }

    static {
        CREATOR = new a();
    }
}
