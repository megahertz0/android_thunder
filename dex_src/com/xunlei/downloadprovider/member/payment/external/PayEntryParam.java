package com.xunlei.downloadprovider.member.payment.external;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PayEntryParam implements Parcelable {
    public static final Creator<PayEntryParam> CREATOR;
    public PayFrom a;
    public OperType b;
    public int c;
    public String d;
    public b e;
    private String f;

    private PayEntryParam(PayFrom payFrom, OperType operType) {
        this.b = OperType.NORMAL;
        this.c = Integer.MAX_VALUE;
        this.f = new StringBuilder("PayEntryParam{payFrom=").append(this.a).append(", operType=").append(this.b).append(", expiredDays=").append(this.c).append(", reportRefer='").append(this.d).append('\'').append(", successDest=").append(this.e).append('}').toString();
        this.a = payFrom;
        this.b = operType;
        this.c = Integer.MAX_VALUE;
        this.d = null;
    }

    public PayEntryParam(PayFrom payFrom) {
        this(payFrom, OperType.NORMAL);
    }

    public PayEntryParam(Parcel parcel) {
        this.b = OperType.NORMAL;
        this.c = Integer.MAX_VALUE;
        this.f = new StringBuilder("PayEntryParam{payFrom=").append(this.a).append(", operType=").append(this.b).append(", expiredDays=").append(this.c).append(", reportRefer='").append(this.d).append('\'').append(", successDest=").append(this.e).append('}').toString();
        this.a = (PayFrom) parcel.readSerializable();
        this.b = (OperType) parcel.readSerializable();
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = (b) parcel.readSerializable();
    }

    public String toString() {
        return this.f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.a);
        parcel.writeSerializable(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeSerializable(this.e);
    }

    static {
        CREATOR = new e();
    }
}
