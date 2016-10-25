package com.uc.addon.sdk.remote.protocol;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.umeng.a;

public class JSParam implements Parcelable {
    public static final Creator CREATOR;
    public String javascript;

    static {
        CREATOR = new Creator() {
            public final JSParam createFromParcel(Parcel parcel) {
                return new JSParam(parcel);
            }

            public final JSParam[] newArray(int i) {
                return new JSParam[i];
            }
        };
    }

    public JSParam() {
        this.javascript = a.d;
    }

    public JSParam(Parcel parcel) {
        this.javascript = a.d;
        this.javascript = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.javascript);
    }
}
