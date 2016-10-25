package com.tencent.qqconnect.dataprovider.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

// compiled from: ProGuard
public class TextOnly implements Parcelable {
    public static final Creator<TextOnly> CREATOR;
    private String a;

    public TextOnly(String str) {
        this.a = str;
    }

    public String getText() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }

    static {
        CREATOR = new Creator<TextOnly>() {
            public final TextOnly createFromParcel(Parcel parcel) {
                return new TextOnly(null);
            }

            public final TextOnly[] newArray(int i) {
                return new TextOnly[i];
            }
        };
    }

    private TextOnly(Parcel parcel) {
        this.a = parcel.readString();
    }
}
