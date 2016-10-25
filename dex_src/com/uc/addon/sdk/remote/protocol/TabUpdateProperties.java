package com.uc.addon.sdk.remote.protocol;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TabUpdateProperties implements Parcelable {
    public static final Creator CREATOR;
    public Boolean isActive;
    public String url;

    static {
        CREATOR = new Creator() {
            public final TabUpdateProperties createFromParcel(Parcel parcel) {
                return new TabUpdateProperties((byte) 0);
            }

            public final TabUpdateProperties[] newArray(int i) {
                return null;
            }
        };
    }

    private TabUpdateProperties(Parcel parcel) {
        this.url = parcel.readString();
        this.isActive = (Boolean) parcel.readSerializable();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeSerializable(this.isActive);
    }
}
