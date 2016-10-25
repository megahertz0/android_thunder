package com.uc.addon.sdk.remote.protocol;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class NavigationItem implements Parcelable {
    public static final Creator CREATOR;
    public Bitmap icon;
    public String title;
    public String url;

    static {
        CREATOR = new Creator() {
            public final NavigationItem createFromParcel(Parcel parcel) {
                return new NavigationItem((byte) 0);
            }

            public final NavigationItem[] newArray(int i) {
                return new NavigationItem[i];
            }
        };
    }

    private NavigationItem(Parcel parcel) {
        this.title = parcel.readString();
        this.url = parcel.readString();
        this.icon = (Bitmap) parcel.readParcelable(NavigationItem.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.url);
        parcel.writeParcelable(this.icon, i);
    }
}
