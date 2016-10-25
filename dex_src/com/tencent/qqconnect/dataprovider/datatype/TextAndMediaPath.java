package com.tencent.qqconnect.dataprovider.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

// compiled from: ProGuard
public class TextAndMediaPath implements Parcelable {
    public static final Creator<TextAndMediaPath> CREATOR;
    private String a;
    private String b;

    public TextAndMediaPath(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String getText() {
        return this.a;
    }

    public String getMediaPath() {
        return this.b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }

    static {
        CREATOR = new Creator<TextAndMediaPath>() {
            public final TextAndMediaPath createFromParcel(Parcel parcel) {
                return new TextAndMediaPath(null);
            }

            public final TextAndMediaPath[] newArray(int i) {
                return new TextAndMediaPath[i];
            }
        };
    }

    private TextAndMediaPath(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }
}
