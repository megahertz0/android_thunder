package com.uc.addon.sdk.remote.protocol;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ToastBuilder implements Parcelable {
    public static final Creator CREATOR;
    public static final int LONG_DURATION = 1;
    public static final int SHORT_DURATION = 0;
    private String a;
    private int b;

    static {
        CREATOR = new Creator() {
            public final ToastBuilder createFromParcel(Parcel parcel) {
                return new ToastBuilder((byte) 0);
            }

            public final ToastBuilder[] newArray(int i) {
                return new ToastBuilder[i];
            }
        };
    }

    private ToastBuilder(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readInt();
    }

    private ToastBuilder(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public static ToastBuilder makeText(String str, int i) {
        return new ToastBuilder(str, i);
    }

    public int describeContents() {
        return 0;
    }

    public int getDuration() {
        return this.b;
    }

    public String getText() {
        return this.a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
    }
}
