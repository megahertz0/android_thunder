package com.uc.addon.sdk.remote.protocol;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class HistorySearchParam implements Parcelable {
    public static final Creator CREATOR;
    public int type;

    static {
        CREATOR = new Creator() {
            public final HistorySearchParam createFromParcel(Parcel parcel) {
                return new HistorySearchParam(parcel);
            }

            public final HistorySearchParam[] newArray(int i) {
                return new HistorySearchParam[i];
            }
        };
    }

    public HistorySearchParam() {
        this.type = 0;
    }

    public HistorySearchParam(Parcel parcel) {
        this.type = 0;
        this.type = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
    }
}
