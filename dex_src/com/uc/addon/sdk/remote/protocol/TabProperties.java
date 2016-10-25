package com.uc.addon.sdk.remote.protocol;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TabProperties implements Parcelable {
    public static final Creator CREATOR;
    public int id;
    public Boolean isActive;
    public String title;
    public String url;

    static {
        CREATOR = new Creator() {
            public final TabProperties createFromParcel(Parcel parcel) {
                return new TabProperties((byte) 0);
            }

            public final TabProperties[] newArray(int i) {
                return null;
            }
        };
    }

    private TabProperties(Parcel parcel) {
        this.id = parcel.readInt();
        this.url = parcel.readString();
        this.title = parcel.readString();
        this.isActive = (Boolean) parcel.readSerializable();
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return new StringBuilder("TabProperties [id=").append(this.id).append(", url=").append(this.url).append(", isActive=").append(this.isActive).append(", title=").append(this.title).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.url);
        parcel.writeString(this.title);
        parcel.writeSerializable(this.isActive);
    }
}
